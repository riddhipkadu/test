package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.QueryDao;
import lcmt.dao.RequestByPOCDao;
import lcmt.domain.ActivityLogs;
import lcmt.domain.LitigationDocuments;
import lcmt.domain.QueryDocuments;
import lcmt.domain.QueryHistory;
import lcmt.domain.QueryHistoryReference;
import lcmt.domain.QueryMaster;
import lcmt.domain.QueryRequest;
import lcmt.domain.Query_Reference;
import lcmt.domain.RequestDocument;
import lcmt.service.QueryService;
import lcmt.service.RequestByPOCService;
import lcmt.service.SendMailService;
import lcmt.service.UtilitiesService;

@Service("queryService")
public class QueryServiceImpl implements QueryService {

	@Autowired
	QueryDao queryDao;
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	SendMailService sendMailService;
	@Autowired
	RequestByPOCService requestByPOCService;
	@Autowired
	RequestByPOCDao requestByPOCDao;

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	
	// Method Created : Harshad Padole
	// Method Purpose : Save Query
	@Override
	public int persist(Query_Reference query_Reference, ArrayList<MultipartFile> query_doc,int id, HttpSession session,String status) {
		try {
			SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MM-yyyy");
			Date query_date = null;
			if(!query_Reference.getQuer_query_date().equals("")){
				 query_date = dateFormatOut.parse(query_Reference.getQuer_query_date());
			}
			
			Date reply_date = null;
			if(!query_Reference.getQuer_reply_date().equals("")){
				reply_date = dateFormatOut.parse(query_Reference.getQuer_reply_date());
			}
			
			Date reminder_date = null;
			if (!query_Reference.getQuer_reminder_date().equals("")) {
				reminder_date = dateFormatOut.parse(query_Reference.getQuer_reminder_date());
			}
			
			Date turnaround_time = null;
			if (!query_Reference.getQuer_turnaround_time_name().equals("")) {
				turnaround_time = dateFormatOut.parse(query_Reference.getQuer_turnaround_time_name());
			}
			QueryMaster query = new QueryMaster();
			query.setQuer_entity_id(query_Reference.getQuer_entity_id());
			query.setQuer_unit_id(query_Reference.getQuer_unit_id());
			query.setQuer_function_id(query_Reference.getQuer_function_id());
			query.setQuer_from_id(query_Reference.getQuer_from_id());
			query.setQuer_query(query_Reference.getQuer_query());
			if(status.equals("Draft")){
				query.setQuer_assigned_to(0);	
			}else{
			    query.setQuer_assigned_to(query_Reference.getQuer_assigned_to());
			}
			query.setQuer_status(status);
			query.setQuer_query_date(query_date);
			query.setQuer_reply_date(reply_date);
			query.setQuer_reminder_date(reminder_date);
			query.setQuer_criticality(query_Reference.getQuer_criticality());
			query.setQuer_turnaround_time(turnaround_time);
			query.setQuer_created_at(utilitiesService.getCurrentDateWithTime());
			query.setQuer_updated_at(utilitiesService.getCurrentDateWithTime());
			query.setQuer_added_by(utilitiesService.getCurrentSessionUserId(httpSession));

			int query_id = queryDao.persist(query);
			
			if(id > 0){
				QueryRequest queryReq = requestByPOCService.getAllQueryRequest(id, session);
				queryReq.setReq_query_approval_status(2);
				queryDao.update(queryReq);
				
				List<RequestDocument> query_req_doc = requestByPOCDao.getAllRequestDocument(id, "QueryRequest");
				if(query_req_doc !=null){
				Iterator<RequestDocument> itr = query_req_doc.iterator();
				//MultipartFile teDoc;
				while (itr.hasNext()) {
					RequestDocument requestDocument = (RequestDocument) itr.next();
					QueryDocuments documents = new QueryDocuments();

					documents.setQuer_query_id(query_id);
					documents.setQuer_doc_original_file_name(requestDocument.getReq_doc_original_file_name());
					documents.setQuer_doc_generated_file_name(requestDocument.getReq_doc_generated_file_name());
					documents.setQuer_doc_last_generated_value_for_query_id(requestDocument.getReq_doc_last_generated_value_for_req_id());
					documents.setQuer_doc_created_date(utilitiesService.getCurrentDateWithTime());
					documents.setQuer_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					documents.setQuer_reply_doc_related_value_for_query_id(1);
					queryDao.saveRecord(documents);
				}
				}
				
			}
			
			if(status.equals("Query Raised")){
				sendMailService.sendQueryAssignMailToResponsiblePerson(query_id, session);
				// persist query logs for add new query
				ActivityLogs logs = new ActivityLogs();
				
				logs.setLog_related_to("Query");
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(query.getQuer_assigned_to());
				logs.setLog_sub_activity("Add Query");
				logs.setLog_activity_id(query_id);
				logs.setLog_related_name(query.getQuer_from_id());
				logs.setLog_added_by(query.getQuer_added_by());
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				
				queryDao.saveQueryLogs(logs);
			}
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = queryDao.getLastGeneratedValueForQueryDoc(query_id);
			for (int i = 0; i < query_doc.size(); i++) {
				MultipartFile file = query_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "QueryDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedQueryDoc_" + query_id + "_"+ 0 +"_"+ lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					QueryDocuments documents = new QueryDocuments();

					documents.setQuer_query_id(query_id);

					documents.setQuer_doc_original_file_name(originalFileName);
					documents.setQuer_doc_generated_file_name("C:/"+project_name+"/Documents/QueryDocuments/" + generatedFileName);
					documents.setQuer_doc_last_generated_value_for_query_id(lastGeneratedValueDoc);
					documents.setQuer_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					documents.setQuer_doc_created_date(utilitiesService.getCurrentDate());
					documents.setQuer_reply_doc_related_value_for_query_id(1);
					queryDao.saveQueryDocuments(documents);
				}
				
			}
			
			return query_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Harshad Padole
	// Method Purpose : get List of query
	@Override
	public List<Query_Reference> getAllQueryList(HttpSession session) {
		try {
			List<Query_Reference> sendList = new ArrayList<>();
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			List<Object> result = queryDao.getListOfQuery(session);
			Iterator<Object> iterator = result.iterator();

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				Query_Reference master = new Query_Reference();
				Date replyDate = null;
				String date_reply = null;
				if(objects[6] != null){
				replyDate = sdfIn.parse(objects[6].toString());
				date_reply = sdfOut.format(replyDate);
				}else{
				date_reply = "NA";	
				} 
				master.setQuer_id(Integer.parseInt(objects[8].toString()));
				master.setQuer_entity(objects[0].toString());
				master.setQuer_unit(objects[1].toString());
				master.setQuer_function(objects[2].toString());
				if(Integer.parseInt(objects[10].toString()) != 0){
				master.setQuer_assigned_to_name(objects[3].toString() + " " + objects[4].toString());
				}else{
					master.setQuer_assigned_to_name("NA");
				}
				// master.setQuer_from_name(objects[11].toString());
				if(!objects[5].toString().equals("")){
					master.setQuer_query(objects[5].toString());
				}else{
					master.setQuer_query("NA");
				}
				master.setQuer_reply_date(date_reply);
				master.setQuer_status(objects[7].toString());
				master.setQuer_added_by(Integer.parseInt(objects[9].toString()));

				sendList.add(master);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Get Query by id
	@Override
	public Query_Reference getQueryById(int query_id) {
		try {

			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			QueryMaster master = queryDao.getQueryById(query_id);
			Query_Reference query_Reference = new Query_Reference();
			query_Reference.setQuer_id(query_id);
			query_Reference.setQuer_entity_id(master.getQuer_entity_id());
			query_Reference.setQuer_unit_id(master.getQuer_unit_id());
			query_Reference.setQuer_function_id(master.getQuer_function_id());
			query_Reference.setQuer_from_id(master.getQuer_from_id());
			query_Reference.setQuer_query(master.getQuer_query());
			query_Reference.setQuer_assigned_to(master.getQuer_assigned_to());
			query_Reference.setQuer_criticality(master.getQuer_criticality());
			Date queryDate = sdfIn.parse(master.getQuer_query_date().toString());
			String quer_query_date = sdfOut.format(queryDate);
			query_Reference.setQuer_query_date(quer_query_date);
			
			if(master.getQuer_reply_date() != null){
				Date replyDate = sdfIn.parse(master.getQuer_reply_date().toString());
				String date_reply = sdfOut.format(replyDate);
				query_Reference.setQuer_reply_date(date_reply);
			}else{
				
			}
			query_Reference.setQuer_status(master.getQuer_status());
			if(master.getQuer_reminder_date() != null){
			Date Reminder_Date = sdfIn.parse(master.getQuer_reminder_date().toString());
			String date_reminder = sdfOut.format(Reminder_Date);
			query_Reference.setQuer_reminder_date(date_reminder);
			}else{
				
			}
			if(master.getQuer_turnaround_time() != null){
				Date turnaroud_time = sdfIn.parse(master.getQuer_turnaround_time().toString());
				String date_turnaround = sdfOut.format(turnaroud_time);
				query_Reference.setQuer_turnaround_time_name(date_turnaround);
				}else{
					
				}
			
			query_Reference.setQuer_added_by(master.getQuer_added_by());
			query_Reference.setQuer_created_at(sdfOut.format(sdfIn.parse(master.getQuer_created_at().toString())));
			query_Reference.setQuer_updated_at(sdfOut.format(sdfIn.parse(master.getQuer_updated_at().toString())));
			return query_Reference;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Harshad Padole
	// Method Purpose : Update Query
	@Override
	public void updateQuery(Query_Reference query_Reference, ArrayList<MultipartFile> query_doc, String status, HttpSession session) {
		try {
			QueryMaster oldData = queryDao.getQueryById(query_Reference.getQuer_id());
			QueryMaster master = new QueryMaster();
			SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MM-yyyy");
			Date quer_query_date = null;
			if(!query_Reference.getQuer_query_date().toString().equals("")){
				quer_query_date = dateFormatOut.parse(query_Reference.getQuer_query_date());
			}
			if(!query_Reference.getQuer_reply_date().toString().equals("")){
				Date reply_date = dateFormatOut.parse(query_Reference.getQuer_reply_date());
				master.setQuer_reply_date(reply_date);
			}
			if (!query_Reference.getQuer_reminder_date().toString().equals("")) {
				Date reminder_date = dateFormatOut.parse(query_Reference.getQuer_reminder_date());
				master.setQuer_reminder_date(reminder_date);
			}
			if (!query_Reference.getQuer_turnaround_time_name().toString().equals("")) {
				Date turnaround_date = dateFormatOut.parse(query_Reference.getQuer_turnaround_time_name());
				master.setQuer_turnaround_time(turnaround_date);
			}
			master.setQuer_criticality(query_Reference.getQuer_criticality());
			master.setQuer_id(query_Reference.getQuer_id());
			master.setQuer_entity_id(query_Reference.getQuer_entity_id());
			master.setQuer_unit_id(query_Reference.getQuer_unit_id());
			master.setQuer_function_id(query_Reference.getQuer_function_id());
			master.setQuer_from_id(query_Reference.getQuer_from_id());
			master.setQuer_query(query_Reference.getQuer_query());
			if(!status.equals("Draft")){
			master.setQuer_assigned_to(query_Reference.getQuer_assigned_to());
			}else{
			master.setQuer_assigned_to(0);	
			}
			master.setQuer_query_date(quer_query_date);
			
			master.setQuer_updated_at(utilitiesService.getCurrentDate());
			// Set old data
			master.setQuer_created_at(oldData.getQuer_created_at());
			master.setQuer_added_by(oldData.getQuer_added_by());
			if(!oldData.getQuer_status().equals("Draft"))
			    master.setQuer_status(oldData.getQuer_status());
			else
				master.setQuer_status(status);
			
			queryDao.updateQuery(master);

			if(status.equals("Query Raised") && oldData.getQuer_status().toString().equals("Draft")){
				sendMailService.sendQueryAssignMailToResponsiblePerson(query_Reference.getQuer_id(), session);
				// persist query logs for add new query
				ActivityLogs logs = new ActivityLogs();

				logs.setLog_related_to("Query");
				logs.setLog_activity("Add");
				logs.setLog_assinged_to_id(master.getQuer_assigned_to());
				logs.setLog_sub_activity("Add Query");
				logs.setLog_related_name(master.getQuer_from_id());
				logs.setLog_activity_id(query_Reference.getQuer_id());
				logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());

				queryDao.saveQueryLogs(logs);
			}
			
			if(!oldData.getQuer_status().toString().equals("Draft")){
			// persist query logs for add new query
			ActivityLogs logs = new ActivityLogs();

			logs.setLog_related_to("Query");
			logs.setLog_activity("Update");
			logs.setLog_assinged_to_id(master.getQuer_assigned_to());
			logs.setLog_sub_activity("Update Query");
			logs.setLog_related_name(master.getQuer_from_id());
			logs.setLog_activity_id(query_Reference.getQuer_id());
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());

			queryDao.saveQueryLogs(logs);
			}
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = queryDao.getLastGeneratedValueForQueryDoc(query_Reference.getQuer_id());
			for (int i = 0; i < query_doc.size(); i++) {
				MultipartFile file = query_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "QueryDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedQueryDoc_" + query_Reference.getQuer_id() + "_" + 0 + "_" + lastGeneratedValueDoc
							+ "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					QueryDocuments documents = new QueryDocuments();

					documents.setQuer_query_id(query_Reference.getQuer_id());

					documents.setQuer_doc_original_file_name(originalFileName);
					documents.setQuer_doc_generated_file_name("C:/"+project_name+"/Documents/QueryDocuments/" + generatedFileName);
					documents.setQuer_doc_last_generated_value_for_query_id(lastGeneratedValueDoc);
					documents.setQuer_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					documents.setQuer_doc_created_date(utilitiesService.getCurrentDate());
					documents.setQuer_reply_doc_related_value_for_query_id(1);
					queryDao.saveQueryDocuments(documents);
				}
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : search Query
	@SuppressWarnings("unchecked")
	@Override
	public String searchQuery(int quer_entity_id, int quer_unit_id, int quer_function_id, int quer_assigned_to,
			String from_date, String to_date, HttpSession session) {
		try {
			JSONArray sendList = new JSONArray();

			SimpleDateFormat sdfIn1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut1 = new SimpleDateFormat("yyyy-MM-dd");
			
			String from_query_date = "";
			String to_query_date = "";
			if (!from_date.equals("") && !to_date.equals("")) {
				Date f_date = sdfIn1.parse(from_date);
				from_query_date = sdfOut1.format(f_date);

				Date t_date = sdfIn1.parse(to_date);
				to_query_date = sdfOut1.format(t_date);
			}

			List<Object> result = queryDao.searchQuery(quer_entity_id, quer_unit_id, quer_function_id, quer_assigned_to,
					from_query_date, to_query_date, session);

			Iterator<Object> iterator = result.iterator();
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject search = new JSONObject();
				if(objects[5] != null){
				Date replyDate = sdfIn.parse(objects[5].toString());
				
				search.put("quer_reply_date", sdfOut.format(replyDate));
				}else{
					search.put("quer_reply_date","NA");	
				}
				search.put("quer_id", objects[7].toString());
				search.put("entity_name", objects[0]);
				search.put("unit_name", objects[1]);
				search.put("dept_name", objects[2]);
				search.put("quer_query", objects[8]);
				
				if(Integer.parseInt(objects[10].toString()) != 0){
				search.put("quer_assigned_to_name", objects[3] + " " + objects[4]);
				}else{
					search.put("quer_assigned_to_name","NA");	
				}
				search.put("query_status", objects[6]);
				search.put("added_by", objects[9].toString());
				search.put("user_role", session.getAttribute("sess_user_role").toString());
				search.put("user_id", session.getAttribute("sess_user_id").toString());

				sendList.add(search);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created by : Harshad Padole
	// Method purpose : get list of all documents by Id
	@Override
	public List<QueryDocuments> getDocumentByQueryId(int query_id) {
		try {
			return queryDao.getDocumentByQueryId(query_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : delete Query by id
	@Override
	public int deleteQueryDocumentById(int quer_doc_id) {
		try {
			QueryDocuments queryDocuments = queryDao.getQueryDocumentById(quer_doc_id);
			File file = new File(queryDocuments.getQuer_doc_generated_file_name());

			if (file.delete()) {
				//System.out.println(file.getName() + " is deleted!");
			} else {
				//System.out.println("Delete operation is failed.");
			}
			return queryDao.deleteQueryDocument(queryDocuments);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : save sending of query reply
	@Override
	public void persistReplyQuery(QueryHistory queryHistory, ArrayList<MultipartFile> hst_doc, HttpSession session) {
		try {
			queryHistory.setQuery_hst_added_by(Integer.parseInt(session.getAttribute("sess_user_id").toString()));
			queryHistory.setQuery_hst_created_at(utilitiesService.getCurrentDateWithTime());
			int quer_hst_id = queryDao.persistQueryReply(queryHistory);

			QueryMaster queryMaster = queryDao.getQueryById(queryHistory.getQuery_quer_id());
			if(!queryHistory.getQuery_hst_status().equals("Save As Draft")){
				queryMaster.setQuer_status(queryHistory.getQuery_hst_status());
				queryDao.updateQuery(queryMaster);
				//Save Query history logs
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_related_to("Query");
				logs.setLog_activity("Add");
				logs.setLog_sub_activity("Query History");
				logs.setLog_activity_id(queryHistory.getQuery_quer_id());
				logs.setLog_assinged_to_id(queryHistory.getQuery_hst_action_assigned_to());
				logs.setLog_sub_activity_id(quer_hst_id);
				
				String status_hst = queryHistory.getQuery_hst_status();
				
				if(status_hst.equals("Others")){
					logs.setLog_related_name(queryHistory.getQuery_hst_others());
				}else{
					logs.setLog_related_name(queryHistory.getQuery_hst_status());
				}
					
				logs.setLog_added_by(queryHistory.getQuery_hst_added_by());
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				queryDao.saveQueryLogs(logs);
			}

			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = queryDao.getLastGeneratedValueForQueryDoc(queryHistory.getQuery_quer_id());
			for (int i = 0; i < hst_doc.size(); i++) {
				MultipartFile file = hst_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "QueryDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedReplyQueryDoc_" + queryHistory.getQuery_quer_id() + "_"
							+quer_hst_id +"_"+ lastGeneratedValueDoc + "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					QueryDocuments documents = new QueryDocuments();

					documents.setQuer_query_id(queryHistory.getQuery_quer_id());

					documents.setQuer_doc_original_file_name(originalFileName);
					documents.setQuer_doc_generated_file_name("C:/"+project_name+"/Documents/QueryDocuments/" + generatedFileName);
					documents.setQuer_doc_last_generated_value_for_query_id(lastGeneratedValueDoc);
					documents.setQuer_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					documents.setQuer_doc_created_date(utilitiesService.getCurrentDate());
					documents.setQuer_reply_doc_related_value_for_query_id(2);
					documents.setQuer_hst_doc_id(quer_hst_id);
					queryDao.saveQueryDocuments(documents);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get history of query by Id
	@Override
	public List<QueryHistoryReference> getReplyQueryById(int query_id) {
		try {
			SimpleDateFormat dateIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateOut = new SimpleDateFormat("dd-MM-yyyy");
			List<QueryHistoryReference> sendList = new ArrayList<>();
			List<Object> query = queryDao.getReplyQueryById(query_id);
			Iterator<Object> iterator = query.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();
				QueryHistoryReference queryHistoryReference = new QueryHistoryReference();
				queryHistoryReference.setQuery_hst_id(Integer.parseInt(object[0].toString()));
				queryHistoryReference.setQuery_hst_comments(object[1].toString());
				if(object[2] != null){
				queryHistoryReference.setQuery_hst_replied_date(dateOut.format(dateIn.parse(object[2].toString())));
				}else{
				queryHistoryReference.setQuery_hst_replied_date("NA");
				}
				queryHistoryReference.setQuery_hst_status(object[3].toString());
				queryHistoryReference.setQuery_hst_action_tobe_performed(object[5].toString());
				queryHistoryReference.setQuery_hst_action_tobe_performed_by(object[6].toString());
				if(Integer.parseInt(object[12].toString()) != 0){
				queryHistoryReference
						.setQuery_hst_action_assigned_to(object[7].toString() + " " + object[8].toString());
				}else{
					queryHistoryReference.setQuery_hst_action_assigned_to("NA");
				}
				if(object[14] != null){
					queryHistoryReference.setQuery_hst_type(object[14].toString());
				}else{
					queryHistoryReference.setQuery_hst_type("NA");
				}
				queryHistoryReference.setQuery_hst_action_performed_others(object[9].toString());
				queryHistoryReference.setQuery_hst_action_performed_by_others(object[10].toString());
				queryHistoryReference.setQuery_hst_others(object[11].toString());
				queryHistoryReference
						.setHst_doc(queryDao.getDocumentByQueryHistoryId(Integer.parseInt(object[0].toString())));
				queryHistoryReference.setQuery_hst_added_by(Integer.parseInt(object[13].toString()));
				sendList.add(queryHistoryReference);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get details of query by Id
	@Override
	public Query_Reference getJoinedQueryDetailsById(int query_id, HttpSession session) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<Object> queryDetailsList = queryDao.getJoinedQueryDetailsById(query_id, session);
			Query_Reference query_Reference = new Query_Reference();
			Iterator<Object> iterator = queryDetailsList.iterator();
			while (iterator.hasNext()) {
				Object[] object = (Object[]) iterator.next();

				query_Reference.setQuer_id(Integer.parseInt(object[9].toString()));
				query_Reference.setQuer_entity(object[0].toString());
				query_Reference.setQuer_unit(object[1].toString());
				query_Reference.setQuer_function(object[2].toString());
				query_Reference.setQuer_from_name(object[5].toString());
				query_Reference.setQuer_query(object[10].toString());
				if(Integer.parseInt(object[12].toString()) != 0){
					query_Reference.setQuer_assigned_to_name(object[3].toString() + " " + object[4].toString());	
				}else{
					query_Reference.setQuer_assigned_to_name("NA");
				}
				if(object[6] != null){
				query_Reference.setQuer_query_date(sdfOut.format(sdfIn.parse(object[6].toString())));
				}else{
					query_Reference.setQuer_query_date("NA");
				}
				if(object[7] != null){
				query_Reference.setQuer_reply_date(sdfOut.format(sdfIn.parse(object[7].toString())));
				}else{
					query_Reference.setQuer_reply_date("NA");	
				}
				if(object[8] != null){
				query_Reference.setQuer_reminder_date(sdfOut.format(sdfIn.parse(object[8].toString())));
				}else{
				query_Reference.setQuer_reminder_date("NA");	
				}
				query_Reference.setQuer_status(object[11].toString());
				if(object[13] != null){
				query_Reference.setQuer_criticality(object[13].toString());
				}else{
					query_Reference.setQuer_criticality("NA");
				}
				if(object[14] != null){
				query_Reference.setQuer_turnaround_time_name(sdfOut.format(sdfIn.parse(object[14].toString())));
				}else{
				query_Reference.setQuer_turnaround_time_name("NA");	
				}
				}
			// litigationDetails.setLiti_case_filing_date(sdfOut.format(sdfIn.parse(litigationDetails.getLiti_case_filing_date().toString())));
			return query_Reference;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Get created documents of query by Id
	@Override
	public <T> List<T> getAllQueryListWithDocuments(int query_id) {
		try {
			return queryDao.getRaisedDocumentByQueryId(query_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAllUserByFunction(int orga_id, int loca_id, int dept_id) {
		try {
			List<Object> userList = queryDao.getAllUserByFunction(orga_id, loca_id, dept_id);
			JSONArray user_list = new JSONArray();

			Iterator<Object> iterator = userList.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("user_id", objects[0]);
				jsonObject.put("user_name", objects[1] + " " + objects[2]);
				user_list.add(jsonObject);
			}
			return user_list.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void downloadQueryDocument(int quer_doc_id, HttpServletResponse response) {
		try {
			if (queryDao.getQueryDocumentFilePath(quer_doc_id) != null) {
				File file = new File(queryDao.getQueryDocumentFilePath(quer_doc_id));
				InputStream is = new FileInputStream(file);

				response.setContentType("application/octet-stream");

				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method created : Tejashri Zurunge
	// Method purpose : get Query history logs
	@SuppressWarnings("unchecked")
	@Override
	public String getQueryHistoryLogs(String json) {
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			JSONArray sendList = new JSONArray();
			List<Object> logs = queryDao.getQueryHistoryLogs(json);
			Iterator<Object> iterator = logs.iterator();
			while (iterator.hasNext()) {
				Object object[] = (Object[]) iterator.next();
				JSONObject data = new JSONObject();
				data.put("query_hst_id", object[0].toString());
				data.put("query_hst_comments", object[1].toString());
				data.put("query_hst_replied_date", sdfOut.format(sdfIn.parse(object[2].toString())));
				data.put("query_hst_status", object[3].toString());
				data.put("query_hst_action_tobe_performed", object[5].toString());
				data.put("query_hst_action_tobe_performed_by", object[6].toString());
				data.put("query_hst_action_assigned_to", object[7].toString() + " " + object[8].toString());
				data.put("query_hst_replied_by", object[9].toString() + " " + object[10].toString());
				data.put("query_hst_action_performed_others", object[11].toString());
				data.put("query_hst_action_performed_by_others", object[12].toString());
				data.put("query_hst_others", object[13].toString());
				data.put("query_hst_log_status", object[14].toString());
				sendList.add(data);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method created : Tejashri Zurunge
	// Method purpose : Delete legal notice
	@Override
	public int deleteQuery(int quer_id) {
		try {
			QueryMaster query = queryDao.getQueryById(quer_id);
			
			int deleted_id = queryDao.deleteQuery(quer_id);
			
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_related_to("Query");
			logs.setLog_activity("Delete");
			logs.setLog_sub_activity("Query Deleted");
			logs.setLog_activity_id(quer_id);
			logs.setLog_sub_activity_id(deleted_id);
			logs.setLog_related_name(query.getQuer_from_id());
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			queryDao.saveQueryLogs(logs);

			return deleted_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	//Method created : Tejashri Zurunge
	//Method purpose : get query history by id

	@Override
	public QueryHistory getQueryHistoryByHstId(int query_hst_id) {
		try {
			QueryHistory history = queryDao.getQueryHistoryByHstId(query_hst_id);
			
			QueryHistory reference = new QueryHistory(); 
			reference.setQuery_hst_id(history.getQuery_hst_id());
			reference.setQuery_hst_action_assigned_to(history.getQuery_hst_action_assigned_to());
			reference.setQuery_hst_action_tobe_performed_by(history.getQuery_hst_action_tobe_performed_by());
			reference.setQuery_hst_action_performed_by_others(history.getQuery_hst_action_performed_by_others());
			reference.setQuery_hst_action_tobe_performed(history.getQuery_hst_action_tobe_performed());
			reference.setQuery_hst_action_performed_others(history.getQuery_hst_action_performed_others());
			reference.setQuery_hst_comments(history.getQuery_hst_comments());
			reference.setQuery_hst_status(history.getQuery_hst_status());
			reference.setQuery_hst_others(history.getQuery_hst_others());
			reference.setQuery_hst_replied_date(history.getQuery_hst_replied_date());
			reference.setQuery_quer_id(history.getQuery_quer_id());
			reference.setQuery_hst_created_at(history.getQuery_hst_created_at());
			reference.setQuery_hst_added_by(history.getQuery_hst_added_by());
			reference.setQuery_hst_type(history.getQuery_hst_type());
			
			return reference;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created : Tejashri Zurunge
	//Method purpose : update query history
	@Override
	public QueryHistory updateQueryHistory(QueryHistory queryHistory, ArrayList<MultipartFile> hst_doc) {
		try {
			QueryHistory oldHistory = queryDao.getQueryHistoryByHstId(queryHistory.getQuery_hst_id());
			QueryHistory newHistory = new QueryHistory();
			
			newHistory.setQuery_hst_id(oldHistory.getQuery_hst_id());
			newHistory.setQuery_hst_action_assigned_to(queryHistory.getQuery_hst_action_assigned_to());
			newHistory.setQuery_hst_action_tobe_performed_by(queryHistory.getQuery_hst_action_tobe_performed_by());
			newHistory.setQuery_hst_action_performed_by_others(queryHistory.getQuery_hst_action_performed_by_others());
			newHistory.setQuery_hst_action_tobe_performed(queryHistory.getQuery_hst_action_tobe_performed());
			newHistory.setQuery_hst_action_performed_others(queryHistory.getQuery_hst_action_performed_others());
			newHistory.setQuery_hst_comments(queryHistory.getQuery_hst_comments());
			newHistory.setQuery_hst_status(queryHistory.getQuery_hst_status());
			newHistory.setQuery_hst_others(queryHistory.getQuery_hst_others());
			newHistory.setQuery_hst_replied_date(queryHistory.getQuery_hst_replied_date());
			newHistory.setQuery_hst_type(queryHistory.getQuery_hst_type());
			newHistory.setQuery_quer_id(oldHistory.getQuery_quer_id());
			newHistory.setQuery_hst_created_at(oldHistory.getQuery_hst_created_at());
			newHistory.setQuery_hst_added_by(oldHistory.getQuery_hst_added_by());

			queryDao.updateQueryHistory(newHistory);
			
			if(!queryHistory.getQuery_hst_status().equals("Save As Draft")){
				
				ActivityLogs logs = new ActivityLogs();
				logs.setLog_related_to("Query");
				logs.setLog_activity("Update");
				logs.setLog_sub_activity("Update Query History");
				logs.setLog_activity_id(queryHistory.getQuery_quer_id());
				logs.setLog_assinged_to_id(queryHistory.getQuery_hst_action_assigned_to());
				logs.setLog_sub_activity_id(oldHistory.getQuery_hst_id());
				
				String status_hst = queryHistory.getQuery_hst_status();
				
				if(status_hst.equals("Others")){
					logs.setLog_related_name(queryHistory.getQuery_hst_others());
				}else{
					logs.setLog_related_name(queryHistory.getQuery_hst_status());
				}
					
				logs.setLog_added_by(queryHistory.getQuery_hst_added_by());
				logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
				queryDao.saveQueryLogs(logs);
				
			}
			
			if(oldHistory.getQuery_hst_status().toString().equals("Save As Draft") && !queryHistory.getQuery_hst_status().equals("Save As Draft ")){
				QueryMaster queryMaster = queryDao.getQueryById(queryHistory.getQuery_quer_id());
				queryMaster.setQuer_status(queryHistory.getQuery_hst_status());
				queryDao.updateQuery(queryMaster);
			}
			
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = queryDao.getLastGeneratedValueForQueryDoc(newHistory.getQuery_quer_id());
			for (int i = 0; i < hst_doc.size(); i++) {
				MultipartFile file = hst_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "QueryDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedReplyQueryDoc_" + newHistory.getQuery_quer_id() + "_" +newHistory.getQuery_hst_id()+ "_" + lastGeneratedValueDoc
							+ "." + file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}
					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					QueryDocuments documents = new QueryDocuments();

					documents.setQuer_query_id(newHistory.getQuery_quer_id());

					documents.setQuer_doc_original_file_name(originalFileName);
					documents.setQuer_doc_generated_file_name(
							"C:/" + project_name + "/Documents/QueryDocuments/" + generatedFileName);
					documents.setQuer_doc_last_generated_value_for_query_id(lastGeneratedValueDoc);
					documents.setQuer_doc_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
					documents.setQuer_doc_created_date(utilitiesService.getCurrentDate());
					documents.setQuer_reply_doc_related_value_for_query_id(2);
					documents.setQuer_hst_doc_id(newHistory.getQuery_hst_id());
					queryDao.saveQueryDocuments(documents);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<QueryDocuments> getHistoryDocumentByQueryId(int query_hst_id) {
		try {
			return queryDao.getDocumentByQueryHistoryId(query_hst_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method created : Tejashri Zurunge
	//Method purpose : delete query history
	@Override
	public int deleteQueryHistory(int query_hst_id) {
		try {
			QueryHistory hst = queryDao.getQueryHistoryByHstId(query_hst_id);
			
			List<QueryDocuments> queryDocuments = queryDao.getDocumentByQueryHistoryId(query_hst_id);
			Iterator<QueryDocuments> iterator = queryDocuments.iterator();
			while(iterator.hasNext()){
				QueryDocuments quer = iterator.next();
				File file = new File(quer.getQuer_doc_generated_file_name());

				if (file.delete()) {
					//System.out.println(file.getName() + " is deleted!");
				} else {
					//System.out.println("Delete operation is failed.");
				}
				queryDao.deleteQueryDocument(quer);
			}
			int deleted_id = queryDao.deleteQueryHistory(query_hst_id);
			
			String status_hst = hst.getQuery_hst_status();

			if(!status_hst.equals("Save As Draft")){
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_related_to("Query");
			logs.setLog_activity("Delete");
			logs.setLog_sub_activity("Query History");
			logs.setLog_activity_id(hst.getQuery_quer_id());
			logs.setLog_sub_activity_id(query_hst_id);
			
			if(status_hst.equals("Others")){
				logs.setLog_related_name(hst.getQuery_hst_others());
			}else{
				logs.setLog_related_name(hst.getQuery_hst_status());
			}
			logs.setLog_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiesService.getCurrentDateWithTime());
			queryDao.saveQueryLogs(logs);
			}
			return deleted_id;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
