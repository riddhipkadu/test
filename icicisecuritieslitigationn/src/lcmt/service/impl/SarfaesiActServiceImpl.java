package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lcmt.dao.SarfaesiActDao;
import lcmt.domain.ArcManager;
import lcmt.domain.ExternalCounsel;
import lcmt.domain.SarfaesiAct;
import lcmt.domain.SarfaesiActDocument;
import lcmt.domain.SarfaesiActRef;
import lcmt.domain.SarfaesiAction;
import lcmt.domain.SarfaesiActionRef;
import lcmt.service.SarfaesiActService;
import lcmt.service.SendMailService;
import lcmt.service.UserService;
import lcmt.service.UtilitiesService;

@Service("SarfaesiActService")
public class SarfaesiActServiceImpl implements SarfaesiActService {
	@Autowired
	SarfaesiActDao sarfaesiActDao;
	@Autowired
	UtilitiesService utilitieservice;
	@Autowired
	UserService userService;
	@Autowired
	HttpSession session;
	@Autowired
	SendMailService sendMailService;

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;

	//Method Created: Pranjali Kawale
	// Method Purpose: Save Sarfaesi Act
	@Override
	public int persist(SarfaesiAct sarfaesiAct, ArrayList<MultipartFile> SarfDocument, HttpSession session) {
		// TODO Auto-generated method stub
		try { 
			System.out.println("!!!!!");
			sarfaesiAct.setSarf_added_by(utilitieservice.getCurrentSessionUserId(session));
			sarfaesiAct.setSarf_created_at(utilitieservice.getCurrentDate());
			int sarfact_id=sarfaesiActDao.persist(sarfaesiAct);

			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueSarfActDoc =sarfaesiActDao.getLastGeneratedValueBySarfActId(sarfact_id);
			sendMailService.sendSarfaesiActHandledMailToInternallyHandledPerson(sarfact_id, session);
			//System.out.println(lastGeneratedValueSarfActDoc);

			for (int i = 0; i < SarfDocument.size(); i++) {
				MultipartFile file = SarfDocument.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "SarfaesiActDocument");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueSarfActDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedSarfaesiDoc_" + sarfact_id + "_" + lastGeneratedValueSarfActDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					SarfaesiActDocument newDoc = new SarfaesiActDocument();
					newDoc.setDoc_SarfAct_id(sarfact_id);
					//System.out.println(sarfact_id);
					newDoc.setSarf_original_file_name(originalFileName);
					newDoc.setSarf_generated_file_name("C:/"+project_name+"/Documents/SarfaesiActDocument/" + generatedFileName);
					newDoc.setSarf_last_generated_value_for_act_id(lastGeneratedValueSarfActDoc);
					newDoc.setSarf_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
					newDoc.setSarf_act_created_date(utilitieservice.getCurrentDate());

					//System.out.println(newDoc);
					sarfaesiActDao.saveSarfaesiActDocument(newDoc);

				}
			}
			return sarfact_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	//Method Created: Pranjali Kawale
	// Method Purpose: list Sarfaesi Act
	@Override
	public List<SarfaesiActRef> getAll(HttpSession session) {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<SarfaesiActRef> sendList = new ArrayList<>();

			List<Object> list = sarfaesiActDao.getAllSarfaesiAct(session);
			Iterator<Object> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();

				SarfaesiActRef sarfaesiActRef = new SarfaesiActRef();

				sarfaesiActRef.setSarf_act_id(Integer.parseInt(objects[0].toString()));

				if(objects[1].toString()!=null)
					sarfaesiActRef.setSarf_loan_no(objects[1].toString());
				else
					sarfaesiActRef.setSarf_loan_no("NA");

				if(objects[2].toString()!=null)
					sarfaesiActRef.setSarf_borrower(objects[2].toString());
				else
					sarfaesiActRef.setSarf_borrower("NA");

				if(objects[3].toString()!=null)
					sarfaesiActRef.setSarf_security_type(objects[3].toString());
				else
					sarfaesiActRef.setSarf_security_type("NA");
				if(objects[4].toString()!=null)
					sarfaesiActRef.setSarf_security_Interest(objects[4].toString());
				else
					sarfaesiActRef.setSarf_security_Interest("NA");


				if(objects[5]==null)
					sarfaesiActRef.setSarf_npa_dates("NA");
				else
					sarfaesiActRef.setSarf_npa_dates(sdfOut.format(sdfIn.parse(objects[5].toString())));


				//System.out.println(objects[5].toString());

				sendList.add(sarfaesiActRef);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created: Pranjali Kawale
	//Method Purpose: get Loan Number Sarfaesi Act
	@Override
	public Set<String> getLoanNumber() {
		// TODO Auto-generated method stub
		try {

			Set<String> sendList = new HashSet<>();
			List<Object> allloanNum = sarfaesiActDao.getLoanNumber();
			Iterator<Object> itr = allloanNum.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				if (object[1] != null)
					sendList.add(object[1].toString());
			}

			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created: Pranjali Kawale
	//Method Purpose: get borrower in Sarfaesi Act
	@Override
	public Set<String> getBorrower() {
		// TODO Auto-generated method stub
		try {

			Set<String> sendList = new HashSet<>();
			List<Object> allborrow = sarfaesiActDao.getBorrower();
			Iterator<Object> itr = allborrow.iterator();
			while (itr.hasNext()) {
				Object[] object = (Object[]) itr.next();
				if (object[1] != null)
					sendList.add(object[1].toString());
			}

			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	//Method Created: Pranjali Kawale
	// Method Purpose: Search Sarfaesi Act
	@SuppressWarnings("unchecked")
	@Override
	public String searchSarfaesiAct(String json, HttpSession session) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonobj = (JSONObject) new JSONParser().parse(json);

			int sarf_orga_id= Integer.parseInt(jsonobj.get("sarf_orga_id").toString());
			//System.out.println( sarf_orga_id);
			int sarf_loca_id= Integer.parseInt(jsonobj.get("sarf_loca_id").toString());
			String sarf_security_type = jsonobj.get("sarf_security_type").toString();
			String sarf_loan_no = jsonobj.get("sarf_loan_no").toString();
			String sarf_borrower=jsonobj.get("sarf_borrower").toString();
			String sarf_security_Interest = jsonobj.get("sarf_security_Interest").toString();
			String SarfAct_from_date = jsonobj.get("SarfAct_from_date").toString();
			String SarfAct_to_date = jsonobj.get("SarfAct_to_date").toString();


			SimpleDateFormat sdfIn1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfOut1 = new SimpleDateFormat("yyyy-MM-dd");
			String sarfact_from_date = "";
			String sarfact_to_date = "";
			if (!SarfAct_from_date.equals("") && !SarfAct_to_date.equals("")) {

				sarfact_from_date = sdfOut1.format(sdfIn1.parse(SarfAct_from_date));
				sarfact_from_date = sdfOut1.format(sdfIn1.parse(SarfAct_to_date));
			}

			JSONArray senList = new JSONArray();
			List<Object> searchResult = sarfaesiActDao.searchSarfaesiAct(sarf_orga_id, sarf_loca_id, sarf_security_type, sarf_loan_no, sarf_borrower, sarf_security_Interest, 
					sarfact_from_date, sarfact_to_date);
			//System.out.println(searchResult.size());
			Iterator<Object> iterator = searchResult.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sarf_act_id", objects[0].toString());
				//System.out.println(objects[0].toString());
				if(objects[1]!=null)
					jsonObject.put("sarf_loan_no", objects[1].toString());
				else
					jsonObject.put("sarf_loan_no", "NA");

				if(objects[2]!=null)
					jsonObject.put("sarf_borrower", objects[2].toString());
				else
					jsonObject.put("sarf_borrower", "NA");

				if(objects[3]!=null)
					jsonObject.put("sarf_security_type", objects[3].toString());
				else
					jsonObject.put("sarf_security_type", "NA");

				if(objects[4]!=null)
					jsonObject.put("sarf_security_Interest", objects[4].toString());
				else
					jsonObject.put("sarf_security_Interest", "NA");

				jsonObject.put("sarf_npa_date", sdfIn1.format(sdfOut1.parse(objects[5].toString())));

				senList.add(jsonObject);
			}
			return senList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method Created : Pranjali Kawale
	// Method Purpose : get Sarfaesi Act by Id

	@Override
	public SarfaesiAct getSarfaesiActById(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {
			return sarfaesiActDao.getSarfaesiActById(sarf_act_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Method Created : Pranjali Kawale
	//Method Purpose : update Sarfaesi Act 
	@Override
	public void updateSarfaesiAct(SarfaesiAct sarfaesiAct,ArrayList<MultipartFile> sarf_act_documents,
			HttpSession session) {
		// TODO Auto-generated method stub
		try {


			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			SarfaesiAct oldSarfaesiAct=sarfaesiActDao.getSarfaesiActById(sarfaesiAct.getSarf_act_id());

			oldSarfaesiAct.setSarf_orga_id(sarfaesiAct.getSarf_orga_id());
			oldSarfaesiAct.setSarf_loca_id(sarfaesiAct.getSarf_loca_id());
			oldSarfaesiAct.setSarf_dept_id(sarfaesiAct.getSarf_dept_id());
			oldSarfaesiAct.setSarf_loan_no(sarfaesiAct.getSarf_loan_no());
			oldSarfaesiAct.setSarf_borrower(sarfaesiAct.getSarf_borrower());
			oldSarfaesiAct.setSarf_contact_no(sarfaesiAct.getSarf_contact_no());
			oldSarfaesiAct.setSarf_email(sarfaesiAct.getSarf_email());
			oldSarfaesiAct.setSarf_address(sarfaesiAct.getSarf_address());
			oldSarfaesiAct.setSarf_Executor_id(sarfaesiAct.getSarf_Executor_id());
			oldSarfaesiAct.setSarf_security_type(sarfaesiAct.getSarf_security_type());
			oldSarfaesiAct.setSarf_mgr_name(sarfaesiAct.getSarf_mgr_name());
			oldSarfaesiAct.setSarf_security_Interest(sarfaesiAct.getSarf_security_Interest());
			oldSarfaesiAct.setSarf_arc_name(sarfaesiAct.getSarf_arc_name());
			oldSarfaesiAct.setSarf_security_loc(sarfaesiAct.getSarf_security_loc());
			oldSarfaesiAct.setSarf_npa_date(sarfaesiAct.getSarf_npa_date());
			oldSarfaesiAct.setSarf_filling_date(sarfaesiAct.getSarf_filling_date());
			oldSarfaesiAct.setSarf_NotiIssue_date(sarfaesiAct.getSarf_NotiIssue_date());
			oldSarfaesiAct.setSarf_first_reminder(sarfaesiAct.getSarf_first_reminder());
			oldSarfaesiAct.setSarf_second_reminder(sarfaesiAct.getSarf_second_reminder());
			oldSarfaesiAct.setSarf_loan_repay_amount(sarfaesiAct.getSarf_loan_repay_amount());
			oldSarfaesiAct.setSarf_total_amount(sarfaesiAct.getSarf_total_amount());
			oldSarfaesiAct.setSarf_paid_amount(sarfaesiAct.getSarf_paid_amount());
			oldSarfaesiAct.setSarf_balance_amount(sarfaesiAct.getSarf_balance_amount());
			sarfaesiActDao.updateSarfaesiAct(oldSarfaesiAct);

			int sarfact_id=sarfaesiAct.getSarf_act_id();
			//Upload Document
			/*String originalFileName = null;
	String generatedFileName = null;
	int lastGeneratedValueSarfActDoc =sarfaesiActDao.getLastGeneratedValueBySarfActId(sarfact_id);

	for (int i = 0; i < sarf_act_documents.size(); i++) {
		MultipartFile file = sarf_act_documents.get(i);
		if (file.getSize() > 0) {
			File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
					+ "SarfaesiActDocument");
			if (!dir.exists())
				dir.mkdirs();

			lastGeneratedValueSarfActDoc++;

			originalFileName = file.getOriginalFilename();
			generatedFileName = "uploadedSarfaesiDoc_" + sarfact_id + "_" + lastGeneratedValueSarfActDoc + "."
					+ file.getOriginalFilename().split("\\.")[1];
			File newFile = new File(dir.getPath() + File.separator + generatedFileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}

			@SuppressWarnings("resource")
			OutputStream outputStream = new FileOutputStream(newFile);

			outputStream.write(file.getBytes());

			outputStream.write(file.getBytes());

			SarfaesiActDocument newDoc = new SarfaesiActDocument();
			newDoc.setSarf_act_id(sarfact_id);
			newDoc.setSarf_original_file_name(originalFileName);
			newDoc.setSarf_generated_file_name("C:/"+project_name+"/Documents/SarfaesiActDocument/" + generatedFileName);
			newDoc.setSarf_last_generated_value_for_act_id(lastGeneratedValueSarfActDoc);
			newDoc.setSarf_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
			newDoc.setSarf_act_created_date(utilitieservice.getCurrentDate());
			sarfaesiActDao.saveSarfaesiActDocument(newDoc);

				}
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public int deleteSarfaesiAct(int sarf_act_id) {
		// TODO Auto-generated method stub
		try {    
			SarfaesiAct sarfaesiAct =sarfaesiActDao.getSarfaesiActById(sarf_act_id);
			int delete_id = sarfaesiActDao.deleteSarfaesiAct(sarf_act_id);
			return delete_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public SarfaesiActRef getJoinedSarfaesiActDetail(int sarf_act_id) {
		// TODO Auto-generated method stubtry {
		try
		{
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			Object[] object =sarfaesiActDao.getJoinedSarfaesiAct(sarf_act_id);
			SarfaesiActRef sarfaesiActRef = new SarfaesiActRef();

			sarfaesiActRef.setSarf_act_id(sarf_act_id);
			sarfaesiActRef.setSarf_orga_name(object[1].toString());
			sarfaesiActRef.setSarf_loca_name(object[2].toString());
			sarfaesiActRef.setSarf_loan_no(object[3].toString());
			sarfaesiActRef.setSarf_borrower(object[4].toString());
			sarfaesiActRef.setSarf_contact_no(object[5].toString());
			sarfaesiActRef.setSarf_email(object[6].toString());
			sarfaesiActRef.setSarf_address(object[7].toString());
			sarfaesiActRef.setSarf_Executor_name(userService.getUserFullNameById(Integer.parseInt(object[8].toString())));
			sarfaesiActRef.setSarf_mangr_name(object[9].toString());
			sarfaesiActRef.setSarf_security_type(object[10].toString());
			sarfaesiActRef.setSarf_security_Interest(object[11].toString());
			sarfaesiActRef.setSarf_security_loc(object[12].toString());
			if(object[13]==null)
				sarfaesiActRef.setSarf_npa_dates("NA");
			else
				sarfaesiActRef.setSarf_npa_dates(sdfOut.format(sdfIn.parse(object[13].toString())));

			if(object[14]==null)
				sarfaesiActRef.setSarf_filling_dates("NA");
			else
				sarfaesiActRef.setSarf_filling_dates(sdfOut.format(sdfIn.parse(object[14].toString())));
			if(object[15]==null)
				sarfaesiActRef.setSarf_NotiIssue_dates("NA");
			else
				sarfaesiActRef.setSarf_NotiIssue_dates(sdfOut.format(sdfIn.parse(object[15].toString())));
			if(object[16]==null)
				sarfaesiActRef.setSarf_first_reminders("NA");
			else
				sarfaesiActRef.setSarf_first_reminders(sdfOut.format(sdfIn.parse(object[16].toString())));

			if(object[17]==null)
				sarfaesiActRef.setSarf_second_reminders("NA");
			else
				sarfaesiActRef.setSarf_second_reminders(sdfOut.format(sdfIn.parse(object[17].toString())));
			sarfaesiActRef.setSarf_dept_name(object[18].toString());

			return sarfaesiActRef;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SarfaesiActDocument> getALLSarfaesiActDocByid(int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			//System.out.println(sarfact_id);
			return sarfaesiActDao.getALLSarfaesiActDocByid(SarfaesiActDocument.class, sarfact_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// Method Created : Pranjali Kawale
	// Method Purpose : save Sarfaesi Act Action
	@Override
	public int saveSarfaesiAction(SarfaesiAction sarfaesiAction,HttpSession session) {
		// TODO Auto-generated method stub
		try {

			sarfaesiAction.setSarf_action_created_at(utilitieservice.getCurrentDate());
			sarfaesiAction.setSarf_action_added_by(utilitieservice.getCurrentSessionUserId(session));
			sarfaesiActDao.saveSarfaesiAction(sarfaesiAction);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	// Method Created : Pranjali Kawale
	// Method Purpose : get all Sarfaesi Act Action
	@Override
	public List<SarfaesiActionRef> getAllSarfaesiActActionItem(int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<SarfaesiActionRef> sendList = new ArrayList<>();


			List<Object> item =sarfaesiActDao.getAllSarfaesiActActionItem(sarfact_id);

			Iterator<Object> iterator = item.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();

				SarfaesiActionRef sarfaesiActionRef = new SarfaesiActionRef();

				sarfaesiActionRef.setSarf_action_id(Integer.parseInt(objects[0].toString()));
				sarfaesiActionRef.setSarf_action_status(objects[1].toString());
				if(objects[2].toString()==null)
					sarfaesiActionRef.setSarf_action_item("NA");
				else
					sarfaesiActionRef.setSarf_action_item(objects[2].toString());
				if(objects[3].toString()==null)
					sarfaesiActionRef.setSarf_nextaction_item("NA");
				else
					sarfaesiActionRef.setSarf_nextaction_item(objects[3].toString());
				if(objects[4]==null)
					sarfaesiActionRef.setSarf_action_NotiIssue_dates("NA");
				else
					sarfaesiActionRef.setSarf_action_NotiIssue_dates(sdfOut.format(sdfIn.parse(objects[4].toString())));
				if(objects[5]==null)
					sarfaesiActionRef.setSarf_action_due_dates("NA");
				else
					sarfaesiActionRef.setSarf_action_due_dates(sdfOut.format(sdfIn.parse(objects[5].toString())));
				if(objects[6]==null)
					sarfaesiActionRef.setAction_first_remind_dates("NA");
				else
					sarfaesiActionRef.setAction_first_remind_dates(sdfOut.format(sdfIn.parse(objects[6].toString())));
				/*if(objects[7]==null)
					sarfaesiActionRef.setAction_second_remind_dates("NA");
				else
				sarfaesiActionRef.setAction_second_remind_dates(sdfOut.format(sdfIn.parse(objects[7].toString())));*/

				//System.out.println(sdfOut.format(sdfIn.parse(objects[7].toString())));

				sendList.add(sarfaesiActionRef);
			}
			return sendList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Pranjali Kawale
	// Method Purpose : delete Sarfaesi Act Action
	@Override
	public int deleteSarfaesiActAction(int sarfaction_id) {
		// TODO Auto-generated method stub
		try {    
			//SarfaesiAction sarfaesiAction =sarfaesiActDao.getSarfaesiActActionById(sarfaction_id);
			int delete_id = sarfaesiActDao.deleteSarfaesiActAction(sarfaction_id);
			//System.out.println("delete_id" +delete_id);

			return delete_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	// Method Created : Pranjali Kawale
	// Method Purpose : get Sarfaesi Act Action by Id
	@Override
	public SarfaesiAction getSarfaesiActActionById(int sarf_action_id) {
		// TODO Auto-generated method stub
		try {

			return sarfaesiActDao.getSarfaesiActActionById(sarf_action_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// Method Created : Pranjali Kawale
	// Method Purpose : update Sarfaesi Act Action 
	@Override
	public void updateSarfaesiActAction(SarfaesiAction sarfaesiAction, HttpSession session) {
		// TODO Auto-generated method stub
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			SarfaesiAction oldSarfaesiAction=sarfaesiActDao.getSarfaesiActActionById(sarfaesiAction.getSarf_action_id());
			//
			//System.out.println(sarfaesiAction.getSarf_action_id());
			oldSarfaesiAction.setSarf_action_status(sarfaesiAction.getSarf_action_status());

			oldSarfaesiAction.setSarf_action_item(sarfaesiAction.getSarf_action_item());
			oldSarfaesiAction.setSarf_nextaction_item(sarfaesiAction.getSarf_nextaction_item());
			// oldSarfaesiAction.setSarf_action_NotiIssue_date(sarfaesiAction.getSarf_action_NotiIssue_date());
			oldSarfaesiAction.setSarf_action_due_date(sarfaesiAction.getSarf_action_due_date());
			oldSarfaesiAction.setAction_first_remind_date(sarfaesiAction.getAction_first_remind_date());
			//oldSarfaesiAction.setAction_second_remind_date(sarfaesiAction.getAction_second_remind_date());

			sarfaesiActDao.updateSarfaesiActAction(oldSarfaesiAction);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	

	}
	// Method Created : Pranjali Kawale
	// Method Purpose : Add Sarfaesi Act  Document 
	@Override
	public void addSarfActDocuments(int doc_SarfAct_id,String sarf_document_Type, MultipartFile SarfDocument) {
		// TODO Auto-generated method stub
		try {
			String originalFileName = null;
			String generatedFileName = null;


			int lastGeneratedValueSarfactDoc =sarfaesiActDao.getLastGeneratedValueBySarfActId(doc_SarfAct_id);
			//System.out.println(lastGeneratedValueSarfactDoc);
			if (lastGeneratedValueSarfactDoc >= 0) {
				if (SarfDocument.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents"
							+ File.separator + "SarfDocument");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueSarfactDoc++;

					originalFileName = SarfDocument.getOriginalFilename();
					generatedFileName = "uploadedLitiDoc_" + doc_SarfAct_id + "_" + lastGeneratedValueSarfactDoc + "."
							+ SarfDocument.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);
					outputStream.write(SarfDocument.getBytes());

					SarfaesiActDocument newDoc = new SarfaesiActDocument();
					newDoc.setDoc_SarfAct_id(doc_SarfAct_id);
					newDoc.setSarf_original_file_name(originalFileName);
					newDoc.setSarf_generated_file_name("C:/"+project_name+"/Documents/SarfDocument/" + generatedFileName);
					newDoc.setSarf_document_Type(sarf_document_Type);
					newDoc.setSarf_last_generated_value_for_act_id(lastGeneratedValueSarfactDoc);
					newDoc.setSarf_doc_added_by(utilitieservice.getCurrentSessionUserId(session));
					newDoc.setSarf_act_created_date(utilitieservice.getCurrentDate());
					sarfaesiActDao.saveSarfaesiActDocument(newDoc);

				}
			} else {
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// Method Created : Pranjali Kawale
	// Method Purpose : Delete Sarfaesi Act  Document 
	@Override
	public int deleteSarfaesiActDocumentById(int sarf_doc_id) {
		// TODO Auto-generated method stub
		try {
			SarfaesiActDocument sarfaesiActDocument=sarfaesiActDao.getSarfaesiActDocByid(sarf_doc_id);
			//System.out.println(sarfaesiActDocument);
			File file = new File(sarfaesiActDocument.getSarf_generated_file_name());
		if (file.delete()) {				//System.out.println(file.getName() + " is deleted!");
			} else {
			//System.out.println("Delete operation is failed.");
		}

			return sarfaesiActDao.deleteSarfaesiActDocument(sarfaesiActDocument);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
	// Method Created : Pranjali Kawale
	// Method Purpose : Download Sarfaesi Act  Document 
	@Override
	public void downloadSarfaesiActDoc(int sarf_doc_id, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (sarfaesiActDao.getSarfaesiActDocumentFilePath(sarf_doc_id)!= null) {
				File file = new File(sarfaesiActDao.getSarfaesiActDocumentFilePath(sarf_doc_id));
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
	@Override
	public int isLoanNumberExistOrNot(String sarf_loan_no) {
		// TODO Auto-generated method stub
		try {
			//System.out.println("service:"+sarf_loan_no);
			return sarfaesiActDao.isLoanNumberExistOrNot(sarf_loan_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getArcManagerByARCnameId(int arc_name_id) {
		// TODO Auto-generated method stub
		try {

			JSONArray sendList = new JSONArray();
			List<ArcManager> res = sarfaesiActDao.getArcManagerByARCnameId(arc_name_id);
			//System.out.println( res);
			Iterator<ArcManager> iterator = res.iterator();
			while (iterator.hasNext()) {
				ArcManager manager = iterator.next();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("ArcMgr_id", manager.getMgr_arc_id());
				jsonObject.put("ArcMgr_name",manager.getMgr_name());
				sendList.add(jsonObject);
			}
			return sendList.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String getSarfaesiNoticedateByid(int sarfact_id) {
		// TODO Auto-generated method stub
		try {
			String mDate = sarfaesiActDao.getSarfaesiNoticedateByid(sarfact_id);
			System.out.println(mDate);
			return mDate;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}




}










