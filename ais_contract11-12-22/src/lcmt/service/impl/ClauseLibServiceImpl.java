package lcmt.service.impl;



import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lcmt.dao.ClauseLibDao;
import lcmt.domain.ActivityLogs;
import lcmt.domain.Clause;
import lcmt.domain.ClauseLib;
import lcmt.domain.ClauseLib_Reference;
import lcmt.domain.QueryDocuments;
import lcmt.domain.QueryMaster;
import lcmt.domain.Query_Reference;
import lcmt.service.ClauseLibService;
import lcmt.service.UtilitiesService;
import lcmt.domain.ClauseLib_Reference;


@Service(value = "clauseLibService")

public class ClauseLibServiceImpl implements ClauseLibService{
	
	@Autowired
	UtilitiesService utilitiesService;
	@Autowired
	HttpSession session;

	@Autowired
	ClauseLibDao clauseLibDao;
	
	@Autowired
	HttpSession httpSession;


	@Override
	public void persist(ClauseLib clauseLib) {
		try {
			clauseLib.setLib_added_by(utilitiesService.getCurrentSessionUserId(httpSession));
			clauseLib.setLib_created_At(utilitiesService.getCurrentDate());
			clauseLib.setLib_updated_At(utilitiesService.getCurrentDate());
			
			clauseLibDao.persist(clauseLib);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ClauseLib getClauseLibById(int clause_Lib_Id) {
		try {
			return clauseLibDao.getClauseLibById(clause_Lib_Id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
		


	



		

		@Override
		public List<ClauseLib> getJoinedAll() {
			try {
				return clauseLibDao.getJoinedAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public List<ClauseLib> getAll() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public int deleteClauseLib(int clause_Lib_Id) {
			try {
				int deleteCount = clauseLibDao.deleteClauseLib(clause_Lib_Id);
				return deleteCount;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		

		
		
		@Override
		public void updateClauseLib(ClauseLib clauseLib) {
			try {

				ClauseLib oldClauseLib = getClauseLibById(clauseLib.getClause_Lib_Id());
				
				ClauseLib newClauseLib = new ClauseLib();

				// Set old Clause Library data to new Clause Library data
				newClauseLib.setClause_Lib_Id(oldClauseLib.getClause_Lib_Id());
				newClauseLib.setLib_added_by(oldClauseLib.getLib_added_by());
				newClauseLib.setLib_created_At(oldClauseLib.getLib_created_At());
				newClauseLib.setLib_updated_At(utilitiesService.getCurrentDate());

				// Set new data to Clause Library data object
				newClauseLib.setClause_Id(clauseLib.getClause_Id());
				newClauseLib.setLib_owner_Id(clauseLib.getLib_owner_Id());
				newClauseLib.setLib_paragraph(clauseLib.getLib_paragraph());
				newClauseLib.setLib_review_date(clauseLib.getLib_review_date());

				clauseLibDao.updateClauseLib(newClauseLib);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	

		
		@Override
		public ClauseLib_Reference getClauseLibById_ref(int clause_Lib_Id) {
			try {

				SimpleDateFormat sdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

				ClauseLib master = clauseLibDao.getClauseLibById_ref(clause_Lib_Id);
				ClauseLib_Reference clauseLib_Reference = new ClauseLib_Reference();
				
				
				
				clauseLib_Reference.setClause_Lib_Ids(clause_Lib_Id);
				clauseLib_Reference.setClause_Ids(master.getClause_Id());
				clauseLib_Reference.setLib_owner_Id(master.getLib_owner_Id());
				clauseLib_Reference.setLib_paragraph(master.getLib_paragraph());
		
				Date reviewDate = sdfIn.parse(master.getLib_review_date().toString());
				String lib_review_dates = sdfOut.format(reviewDate);
				clauseLib_Reference.setLib_review_dates(lib_review_dates);
				
				clauseLib_Reference.setLib_added_by(master.getLib_added_by());
				clauseLib_Reference.setLib_created_Ats(sdfOut.format(sdfIn.parse(master.getLib_created_At().toString())));
				clauseLib_Reference.setLib_updated_Ats(sdfOut.format(sdfIn.parse(master.getLib_updated_At().toString())));
				return clauseLib_Reference;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void updateClauseLib(ClauseLib_Reference clauseLib_Reference, HttpSession session) {
			try {
				ClauseLib oldClauseLib = clauseLibDao.getClauseLibById(clauseLib_Reference.getClause_Lib_Ids());
				ClauseLib newClauseLib = new ClauseLib();
				//SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MM-yyyy");
				/*
				 * Date lib_review_date = null;
				 * if(!clauseLib_Reference.getLib_review_dates().toString().equals("")){
				 * lib_review_date =
				 * dateFormatOut.parse(clauseLib_Reference.getLib_review_dates()); }
				 */
				
				newClauseLib.setClause_Id(clauseLib_Reference.getClause_Ids());
				newClauseLib.setLib_owner_Id(clauseLib_Reference.getLib_owner_Id());
				newClauseLib.setLib_paragraph(clauseLib_Reference.getLib_paragraph());
				newClauseLib.setLib_review_date(clauseLib_Reference.getLib_review_date());
				//newClauseLib.setLib_review_date(lib_review_date);
				
				// Set old Clause Library data to new Clause Library data
				//newClauseLib.setClause_Lib_Id(oldClauseLib.getClause_Lib_Id());
				// newClauseLib.setLib_added_by(oldClauseLib.getLib_added_by());
			//	newClauseLib.setLib_created_At(oldClauseLib.getLib_created_At());
				newClauseLib.setLib_updated_At(utilitiesService.getCurrentDate());

			
				clauseLibDao.updateClauseLib(newClauseLib);
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
	

	
	

}
