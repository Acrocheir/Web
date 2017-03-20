package entity;

import util.DBUtil;

public class Question {
	public Question() {
		// TODO Auto-generated constructor stub
		id=DBUtil.uuid();
	}
	
	
	public String kcid;
	public String id;
	public String stem;
	public String answer;
	public int sta;
	public String sec1;
	public String sec2;
	public String sec3;
	public String sec4;
	
	@Override
	public String toString() {
		return "Question [kcid=" + kcid + ", id=" + id + ", stem=" + stem
				+ ", answer=" + answer + ", sta=" + sta + ", sec1=" + sec1
				+ ", sec2=" + sec2 + ", sec3=" + sec3 + ", sec4=" + sec4 + "]";
	}
	
	public int getSta() {
		return sta;
	}

	
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public String getSec1() {
		return sec1;
	}

	public void setSec1(String sec1) {
		this.sec1 = sec1;
	}

	public String getSec2() {
		return sec2;
	}

	public void setSec2(String sec2) {
		this.sec2 = sec2;
	}

	public String getSec3() {
		return sec3;
	}

	public void setSec3(String sec3) {
		this.sec3 = sec3;
	}

	public String getSec4() {
		return sec4;
	}

	public void setSec4(String sec4) {
		this.sec4 = sec4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getKcid() {
		return kcid;
	}

	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	
}
