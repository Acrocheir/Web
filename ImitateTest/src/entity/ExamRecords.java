package entity;

public class ExamRecords {
	private String kssj;
	private int fs;
	private String cjID;
	private String ksys;
	private XK xk;
	
	public String getKsys() {
		return ksys;
	}
	public void setKsys(String ksys) {
		this.ksys = ksys;
	}
	
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	
	public int getFs() {
		return fs;
	}
	public void setFs(int fs) {
		this.fs = fs;
	}
	
	public String getCjID() {
		return cjID;
	}
	public void setCjID(String cjID) {
		this.cjID = cjID;
	}
	
	
	public XK getXk() {
		return xk;
	}
	public void setXk(XK xk) {
		this.xk = xk;
	}
	
	@Override
	public String toString() {
		return "ksjl [kssj=" + kssj + ", fs=" + fs + ", cjID=" + cjID
				+ ksys +"]";
	}
	

}