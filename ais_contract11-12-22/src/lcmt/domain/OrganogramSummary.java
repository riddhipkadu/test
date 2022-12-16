package lcmt.domain;

import java.util.List;

public class OrganogramSummary {
	
	private String entityName;
	private String unitName;
	private String functionName;
	private List<Object> executorList;
	private List<Object> evaluatorList;
	private List<Object> functionHeadList;
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public List<Object> getExecutorList() {
		return executorList;
	}
	public void setExecutorList(List<Object> executorList) {
		this.executorList = executorList;
	}
	public List<Object> getEvaluatorList() {
		return evaluatorList;
	}
	public void setEvaluatorList(List<Object> evaluatorList) {
		this.evaluatorList = evaluatorList;
	}
	public List<Object> getFunctionHeadList() {
		return functionHeadList;
	}
	public void setFunctionHeadList(List<Object> functionHeadList) {
		this.functionHeadList = functionHeadList;
	}
	
	

}
