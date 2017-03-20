package entity;

public class AnswerRecord {
	private String problemid;
	private Question question;
	private String scoreid;
	private String personchoose;
	private boolean istrue;
	
	public String getProblemid() {
		return problemid;
	}
	public void setProblemid(String problemid) {
		this.problemid = problemid;
	}
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String getScoreid() {
		return scoreid;
	}
	public void setScoreid(String scoreid) {
		this.scoreid = scoreid;
	}
	
	public String getPersonchoose() {
		return personchoose;
	}
	public void setPersonchoose(String personchoose) {
		this.personchoose = personchoose;
	}
	
	public boolean getIstrue() {
		return istrue;
	}
	public void setIstrue(boolean istrue) {
		this.istrue = istrue;
	}

}
