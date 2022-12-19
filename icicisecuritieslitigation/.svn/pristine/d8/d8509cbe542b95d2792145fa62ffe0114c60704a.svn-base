package lcmt.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import lcmt.domain.QueryDocuments;
import lcmt.domain.QueryHistory;
import lcmt.domain.QueryMaster;

public interface QueryDao {
   public int persist(QueryMaster query);
   public List<Object> getListOfQuery(HttpSession session);
   public QueryMaster getQueryById(int query_id);
   public int persistQueryReply(QueryHistory obj);
   public void updateQuery(QueryMaster master);
   public  List<Object> searchQuery(int quer_entity_id, int quer_unit_id, int quer_function_id, int quer_assign_to, String from_date, String to_date,HttpSession session);
   public int getLastGeneratedValueForQueryDoc(int query_id);
   public void saveQueryDocuments(QueryDocuments documents);
   public List<QueryDocuments> getDocumentByQueryId(int query_id);
   public <T> List<T> getRaisedDocumentByQueryId(int query_id);
   public List<QueryDocuments> getReplyDocumentByQueryId(int query_id);
   public QueryDocuments getQueryDocumentById(int quer_doc_id);
   public int deleteQueryDocument(QueryDocuments queryDocuments);
   public List<Object> getReplyQueryById(int query_id);
   public <T> List<T> getJoinedQueryDetailsById(int query_id,HttpSession session);
   public List<Object> getAllUserByFunction(int orga_id, int loca_id, int dept_id); 
   public String getQueryDocumentFilePath(int quer_doc_id);
   public List<QueryDocuments> getDocumentByQueryHistoryId(int query_id);
   public int saveQueryLogs(Object obj);
   public <T> List<T> getQueryHistoryLogs(String json);
   public int deleteQuery(int quer_id);
   public QueryHistory getQueryHistoryByHstId(int query_hst_id);
   public void updateQueryHistory(QueryHistory history);
   public int deleteQueryHistory(int query_hst_id);
   public void update(Object obj);
   public void saveRecord(Object obj);

}
