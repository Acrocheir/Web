package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.AnswerRecord;
import entity.Question;
import entity.XK;
import util.DBUtil;

public class AnswerRecordDao {
	/**
	 * 插入数据
	 * @param ar
	 * @throws Exception
	 */
	public void insert(AnswerRecord ar) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into dtjl ")
		.append("(dtID, cjID, th, grxz, sfzq)")
		.append("values ").append("(?, ?, ?, ?, ?)");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		int i = 1;
		ps.setString(i++, ar.getProblemid());  //答题ID
		ps.setString(i++, ar.getScoreid());  //成绩ID
		ps.setString(i++, ar.getQuestion().getId());  //题号
		ps.setString(i++, ar.getPersonchoose());  //个人选择
		ps.setBoolean(i++, ar.getIstrue());  //是否正确
		System.out.println(sql.toString());
		ps.executeUpdate();  //执行增/删/改
	}
	
	/**
	 * 删除数据
	 */
	public void delete(String dtid) throws Exception{
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from dtjl where dtID=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, dtid);
		ps.executeUpdate();
	}
	
	/**
	 * 查找
	 * @return 
	 */
	public AnswerRecord selectById(String dtid) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from dtjl where dtID=? ");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, dtid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return this.rowsEntiry(rs);
		}
		return null;
	}
	
	public int selectPaginationByKey(String key,
			List<AnswerRecord> pageData) throws Exception{
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from dtjl where cjID=? ");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, key);
		ResultSet rs = ps.executeQuery();
		int total=0;
		while(rs.next()){
			pageData.add(this.rowsEntiry(rs));
			total++;
		}
		return total;
	}
	
	private AnswerRecord rowsEntiry(ResultSet rs) throws Exception {
		AnswerRecord ar = new AnswerRecord();
		ar.setProblemid(rs.getString("dtID"));
		ar.setScoreid(rs.getString("cjID"));
//		ar.setProblemnumber(rs.getString("th"));
		ar.setPersonchoose(rs.getString("grxz"));
		ar.setIstrue(rs.getBoolean("sfzq"));
		
		QuestionDao dao=new QuestionDao();
		Question question=dao.selectById(rs.getString("th"));
		ar.setQuestion(question);
		return ar;
	}
}


