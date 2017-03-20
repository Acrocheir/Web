package entity;

import java.util.Date;

public class Users {
	private String id;
	private String password;
	private String usertype;
	private String userstate;
	private Date createtime;
	private Date lastlogintime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUserstate() {
		return userstate;
	}
	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}


	@Override
	public String toString() {
		return "Goods [id=" + id + ", password=" + password + ", usertype=" + usertype
				+ ", userstate=" + userstate + ", createtime=" + createtime
				+ ", lastlogtime=" + lastlogintime + "]";
	}
}
