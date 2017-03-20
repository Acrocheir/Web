package entity;

import java.util.Date;

public class XK {
	private String xkID;
	private String yhID;
	private Course kc;
	private String xkzt;
	private Date xksj;
	
	public String getXkID() {
		return xkID;
	}

	public void setXkID(String xkID) {
		this.xkID = xkID;
	}
	public String getYhID() {
		return yhID;
	}

	public void setYhID(String yhID) {
		this.yhID = yhID;
	}

	public Course getKc() {
		return kc;
	}

	public void setKc(Course kc) {
		this.kc = kc;
	}

	public String getXkzt() {
		return xkzt;
	}

	public void setXkzt(String xkzt) {
		this.xkzt = xkzt;
	}

	public Date getXksj() {
		return xksj;
	}

	public void setXksj(Date xksj) {
		this.xksj = xksj;
	}
	
	@Override
	public String toString() {
		return "xk [xkID="+ xkID +"yhID=" + yhID + ", xkzt=" + xkzt
				+ ", xksj=" + xksj + "]";
	}
 
}
