package lcmt.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import lcmt.domain.QueryDocuments;
import lcmt.domain.QueryHistory;
import lcmt.domain.QueryHistoryReference;
import lcmt.domain.Query_Reference;

public interface QueryService {

	public int persist(Query_Reference query_Reference,ArrayList<MultipartFile> query_doc,int id, HttpSession session,String status);
	public void persistReplyQuery(QueryHistory queryHistory,ArrayList<MultipartFile> hst_doc, HttpSession session);
	public List<Query_Reference> getAllQueryList(HttpSession session);
	public Query_Reference getQueryById(int query_id);
	public void updateQuery(Query_Reference query_Reference,ArrayList<MultipartFile> query_doc, String status, HttpSession session);
	public String searchQuery(int quer_entity_id, int quer_unit_id, int quer_function_id, int quer_assign_to, String from_date, String to_date,HttpSession session);
    public List<QueryDocuments> getDocumentByQueryId(int query_id);
	public int deleteQueryDocumentById(int quer_doc_id);
	public List<QueryHistoryReference> getReplyQueryById(int query_id);
	public Query_Reference getJoinedQueryDetailsById(int query_id,HttpSession session);
	public <T> List<T> getAllQueryListWithDocuments(int query_id);
	public String getAllUserByFunction(int orga_id, int loca_id, int dept_id);
	public void downloadQueryDocument(int quer_doc_id, HttpServletResponse response);
	public String getQueryHistoryLogs(String json);
	public int deleteQuery(int quer_id);
	public QueryHistory getQueryHistoryByHstId(int query_hst_id);
	public QueryHistory updateQueryHistory(QueryHistory queryHistory, ArrayList<MultipartFile> hst_doc);
	public List<QueryDocuments> getHistoryDocumentByQueryId(int query_hst_id);
	public int deleteQueryHistory(int query_hst_id);
	
}
