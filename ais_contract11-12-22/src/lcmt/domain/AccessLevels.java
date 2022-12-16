package lcmt.domain;

public class AccessLevels {
	private int umap_id;
    private int orga_id;
    private int loca_id;
    private int dept_id;
    private String orga_name;
    private String loca_name;
    private String dept_name;
	public int getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(int orga_id) {
		this.orga_id = orga_id;
	}
	public int getLoca_id() {
		return loca_id;
	}
	public void setLoca_id(int loca_id) {
		this.loca_id = loca_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getLoca_name() {
		return loca_name;
	}
	public void setLoca_name(String loca_name) {
		this.loca_name = loca_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public int getUmap_id() {
		return umap_id;
	}
	public void setUmap_id(int umap_id) {
		this.umap_id = umap_id;
	}
    
}
