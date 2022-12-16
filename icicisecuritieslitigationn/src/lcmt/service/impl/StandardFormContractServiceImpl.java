package lcmt.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

import lcmt.dao.StandardFormContractDao;
import lcmt.domain.ActivityLogs;
import lcmt.domain.Sfco_reference;
import lcmt.domain.Sfco_documents;
import lcmt.domain.StandardFormContractType;
import lcmt.domain.StandardFormContracts;
import lcmt.service.StandardFormContractService;
import lcmt.service.UtilitiesService;

@Service("standardFormContractService")
public class StandardFormContractServiceImpl implements StandardFormContractService {
	@Autowired
	UtilitiesService utilitiService;

	@Autowired
	StandardFormContractDao standardFormContractDao;
	@Autowired
	HttpSession httpSession;

	private @Value("#{config['project_name'] ?: 'null'}") String project_name;
	/*-----------------------------------------"Standard Form Contract Type" methods starts from here---------------------------------------------------- */

	// Method Created By: Tejashri Zurunge
	// Method purpose: add Standard Form Contract Type
	@Override
	public void saveSfcoType(StandardFormContractType standardFormContractType) {
		try {
			standardFormContractType.setSfco_type_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			standardFormContractType.setSfco_type_created_at(utilitiService.getCurrentDate());
			standardFormContractType.setSfco_type_updated_at(utilitiService.getCurrentDate());
			standardFormContractDao.saveSfcoType(standardFormContractType);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method purpose: List Standard Form Contract Type
	@Override
	public List<StandardFormContractType> getAll() {
		try {
			return standardFormContractDao.getAllStandardFormContractType(StandardFormContractType.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created By: Tejashri Zurunge
	// Method purpose: Get Standard Form Contract Type by Id
	@Override
	public StandardFormContractType getStandardFormContractTypeById(int sfco_type_id) {
		try {
			return standardFormContractDao.getStandardFormContractTypeById(sfco_type_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method purpose: update Standard Form Contract Type
	@Override
	public void updateStandardFormContractType(StandardFormContractType standardFormContractType) {
		try {

			StandardFormContractType oldStandardFormContractType = getStandardFormContractTypeById(
					standardFormContractType.getSfco_type_id());

			StandardFormContractType newStandardFormContractType = new StandardFormContractType();

			// Set old data to new
			newStandardFormContractType.setSfco_type_id(oldStandardFormContractType.getSfco_type_id());
			newStandardFormContractType.setSfco_type_added_by(oldStandardFormContractType.getSfco_type_added_by());
			newStandardFormContractType.setSfco_type_created_at(oldStandardFormContractType.getSfco_type_created_at());

			// Set new data to new object
			newStandardFormContractType.setSfco_type_name(standardFormContractType.getSfco_type_name());
			newStandardFormContractType.setSfco_type_updated_at(utilitiService.getCurrentDate());

			standardFormContractDao.updateStandardFormContractType(newStandardFormContractType);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method Created By: Tejashri Zurunge
	// Method purpose: To Verify Whether Standard Form Contract Type is exist or
	// not
	@Override
	public int isStandardFormContractTypeExist(int sfco_type_id, String sfco_type_name) {
		try {
			return standardFormContractDao.isStandardFormContractTypeExist(sfco_type_id, sfco_type_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract Type
	@Override
	public int deleteStandardFormContractType(int sfco_type_id) {
		try {
			int deleteCount = standardFormContractDao.deleteStandardFormContractType(sfco_type_id);
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int checkDependancySFCType(int sfco_type_id) {
		try {
			return standardFormContractDao.checkDependancySFCType(sfco_type_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*-----------------------------------------"Standard Form Contract Type" method ends here---------------------------------------------------- */

	/*-----------------------------------------"Standard Form Contracts" methods starts from here---------------------------------------------------- */
	// Method Created : Tejashri Zurunge
	// Method purpose : Save Standard Form Contract
	@Override
	public int persist(StandardFormContracts standardFormContracts, ArrayList<MultipartFile> sfco_doc) {
		try {
			standardFormContracts.setSfco_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			standardFormContracts.setSfco_created_at(utilitiService.getCurrentDate());
			// standardFormContracts.setSfco_updated_at(utilitiService.getCurrentDate());

			int sfco_id = standardFormContractDao.persist(standardFormContracts);
			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = standardFormContractDao.getLastGeneratedValueForSfcDoc(sfco_id);
			for (int i = 0; i < sfco_doc.size(); i++) {
				MultipartFile file = sfco_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "StandardFormContractsDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedSfcDoc_" + sfco_id + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					Sfco_documents documents = new Sfco_documents();

					documents.setSfco_sfco_id(sfco_id);

					documents.setSfco_doc_original_file_name(originalFileName);
					documents.setSfco_doc_generated_file_name(
							"C:/"+project_name+"/Documents/StandardFormContractsDocuments/" + generatedFileName);
					documents.setSfco_doc_last_generated_value_for_sfco_id(lastGeneratedValueDoc);
					documents.setSfco_doc_added_by(utilitiService.getCurrentSessionUserId(httpSession));
					documents.setSfco_doc_created_date(utilitiService.getCurrentDate());
					standardFormContractDao.saveSfcDocuments(documents);
				}
			}
			
			//persist standard form contract logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(sfco_id);
			logs.setLog_activity("Add");
			logs.setLog_related_to("SFC");
			logs.setLog_sub_activity("Add SFC");
			logs.setLog_related_name(standardFormContracts.getSfco_name());
			logs.setLog_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiService.getCurrentDateWithTime());
			standardFormContractDao.saveLogs(logs);
			
			return sfco_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : List Standard Form Contract
	@Override
	public List<Sfco_reference> getAllStdFormContracts(HttpSession session) {
		try {
			List<Sfco_reference> sendList = new ArrayList<>();
			List<Object> result = standardFormContractDao.getListOfSfc(session);
			Iterator<Object> iterator = result.iterator();

			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				Sfco_reference master = new Sfco_reference();
				master.setSfco_id(Integer.parseInt(objects[0].toString()));
				if(Integer.parseInt(objects[7].toString()) != 0)
				master.setSfco_type_name(objects[1].toString());
				else
				master.setSfco_type_name("NA");	
				master.setSfco_name(objects[2].toString());
				master.setSfc_doc_list(standardFormContractDao.getDocumentBySfcId(Integer.parseInt(objects[0].toString())));
				if(objects[5] != null)
				master.setSfco_abbreviation(objects[5].toString());
				else
					master.setSfco_abbreviation("NA");	
				if(objects[6] != null)
				master.setSfco_contract_type(objects[6].toString());
				else
					master.setSfco_contract_type("NA");
				
				sendList.add(master);
			}
			return sendList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : List Standard Form Contract by Id
	@Override
	public StandardFormContracts getStandardFormContractsById(int sfco_id) {
		try {
			return standardFormContractDao.getStandardFormContractsById(sfco_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Update Standard Form Contract
	@Override
	public void updateStandardFormContracts(StandardFormContracts standardFormContracts,
			ArrayList<MultipartFile> sfco_doc) {
		try {
			StandardFormContracts oldData = getStandardFormContractsById(standardFormContracts.getSfco_id());
			StandardFormContracts newData = new StandardFormContracts();

			newData.setSfco_id(oldData.getSfco_id());
			// new data set
			newData.setSfco_entity_id(standardFormContracts.getSfco_entity_id());
			newData.setSfco_unit_id(standardFormContracts.getSfco_unit_id());
			newData.setSfco_function_id(standardFormContracts.getSfco_function_id());
			newData.setSfco_name(standardFormContracts.getSfco_name());
			newData.setSfco_type_id(standardFormContracts.getSfco_type_id());
			newData.setSfco_description(standardFormContracts.getSfco_description());
			newData.setSfco_abbreviation(standardFormContracts.getSfco_abbreviation());
			newData.setSfco_contract_type(standardFormContracts.getSfco_contract_type());
			newData.setSfco_added_by(oldData.getSfco_added_by());
			newData.setSfco_created_at(oldData.getSfco_created_at());

			standardFormContractDao.updateStandardFormContracts(newData);

			// Upload Documents
			String originalFileName = null;
			String generatedFileName = null;
			int lastGeneratedValueDoc = standardFormContractDao.getLastGeneratedValueForSfcDoc(oldData.getSfco_id());
			for (int i = 0; i < sfco_doc.size(); i++) {
				MultipartFile file = sfco_doc.get(i);
				if (file.getSize() > 0) {
					File dir = new File("C:" + File.separator + project_name + File.separator + "Documents" + File.separator
							+ "StandardFormContractsDocuments");
					if (!dir.exists())
						dir.mkdirs();

					lastGeneratedValueDoc++;

					originalFileName = file.getOriginalFilename();
					generatedFileName = "uploadedSfcDoc_" + oldData.getSfco_id() + "_" + lastGeneratedValueDoc + "."
							+ file.getOriginalFilename().split("\\.")[1];
					File newFile = new File(dir.getPath() + File.separator + generatedFileName);
					if (!newFile.exists()) {
						newFile.createNewFile();
					}

					@SuppressWarnings("resource")
					OutputStream outputStream = new FileOutputStream(newFile);

					outputStream.write(file.getBytes());

					Sfco_documents documents = new Sfco_documents();

					documents.setSfco_sfco_id(oldData.getSfco_id());

					documents.setSfco_doc_original_file_name(originalFileName);
					documents.setSfco_doc_generated_file_name(
							"C:/"+project_name+"/Documents/StandardFormContractsDocuments/" + generatedFileName);
					documents.setSfco_doc_last_generated_value_for_sfco_id(lastGeneratedValueDoc);
					documents.setSfco_doc_added_by(utilitiService.getCurrentSessionUserId(httpSession));
					documents.setSfco_doc_created_date(utilitiService.getCurrentDate());
					standardFormContractDao.saveSfcDocuments(documents);
				}
			}
			//persist standard form contract logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(standardFormContracts.getSfco_id());
			logs.setLog_activity("Update");
			logs.setLog_related_to("SFC");
			logs.setLog_sub_activity("Update SFC");
			logs.setLog_related_name(standardFormContracts.getSfco_name());
			logs.setLog_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiService.getCurrentDateWithTime());
			standardFormContractDao.saveLogs(logs);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract
	@Override
	public int deleteStandardFormContracts(int sfco_id) {
		try {
			StandardFormContracts stdContract = standardFormContractDao.getStandardFormContractsById(sfco_id);
			int deleteCount = standardFormContractDao.deleteStandardFormContracts(sfco_id);
			//persist standard form contract logs 
			ActivityLogs logs = new ActivityLogs();
			logs.setLog_activity_id(sfco_id);
			logs.setLog_activity("Delete");
			logs.setLog_related_to("SFC");
			logs.setLog_sub_activity("Delete SFC");
			logs.setLog_related_name(stdContract.getSfco_name());
			logs.setLog_added_by(utilitiService.getCurrentSessionUserId(httpSession));
			logs.setLog_created_at(utilitiService.getCurrentDateWithTime());
			standardFormContractDao.saveLogs(logs);
			
			return deleteCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : listing Standard Form Contract
	@Override
	public List<Sfco_documents> getDocumentBySfcId(int sfco_id) {
		try {
			return standardFormContractDao.getDocumentBySfcId(sfco_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method Created : Tejashri Zurunge
	// Method purpose : Delete Standard Form Contract by id
	@Override
	public int deleteSfcoDocumentById(int sfco_doc_id) {
		try {
			Sfco_documents sfco_documents = standardFormContractDao.getSfcoDocumentById(sfco_doc_id);
			File file = new File(sfco_documents.getSfco_doc_generated_file_name());

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

			return standardFormContractDao.deleteSfcoDocument(sfco_documents);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Method Created : Tejashri Zurunge
	// Method Purpose : Download document by id
	@Override
	public void downloadSfcoDocument(int sfco_doc_id, HttpServletResponse response) {
		try {
			if (standardFormContractDao.getSfcoDocumentFilePath(sfco_doc_id) != null) {
				File file = new File(standardFormContractDao.getSfcoDocumentFilePath(sfco_doc_id));
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

	@SuppressWarnings("unchecked")
	@Override
	public String getAllSFCTypejson() {
		try {
			 JSONArray sendList = new JSONArray();
			 List<StandardFormContractType> res = standardFormContractDao.getAllStandardFormContractType(StandardFormContractType.class);
			 //System.out.println("Count "+res.size());
			 Iterator<StandardFormContractType> iterator = res.iterator();
			 while(iterator.hasNext()){
				 StandardFormContractType sfc = iterator.next();
				 
				 JSONObject jsonObject = new JSONObject();
				 jsonObject.put("label",sfc.getSfco_type_name());
				 jsonObject.put("value",sfc.getSfco_type_name());
				 sendList.add(jsonObject);
			 }
			 return sendList.toJSONString();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
