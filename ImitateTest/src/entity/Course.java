package entity;

import java.util.Date;

import util.DBUtil;

public class Course {
	public Course() {
		// TODO Auto-generated constructor stub
		id=DBUtil.uuid();
	}
	private String id;
	private String name;
	private String introduction;
	private int state;
	private Date testtime;
	private int duration;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getTesttime() {
		return testtime;
	}
	public void setTesttime(Date testtime) {
		this.testtime = testtime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", introduction="
				+ introduction + ", state=" + state + ", testtime=" + testtime
				+ ", duration=" + duration + "]";
	}
}
