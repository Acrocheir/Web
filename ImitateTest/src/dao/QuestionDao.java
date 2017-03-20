package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import entity.Question;
import util.DBUtil;
import util.ThisSystemUtil;

public class QuestionDao {

    /**
     * 根据题号查询
     * @param id
     * @return
     * @throws SQLException
     */
	public Question qubyid(String id) throws SQLException {
		Connection connection = DBUtil.getConnection();;
		StringBuilder sql = new StringBuilder();
		String bname = "tm";
		sql.append("select * from ").append(bname).append(" where th=?");
		PreparedStatement sq = connection.prepareStatement(sql.toString());
		sq.setString(1, id);
		ResultSet re = sq.executeQuery();

		if (re.next()) {
			return this.rowsEntity(re);
		}

		return null;
	}
	
	public Question selectById(String th) throws Exception{
		Connection connection = DBUtil.getConnection();;

		StringBuilder sql = new StringBuilder();
		sql.append("select * from timu where th=?");
		
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, th);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.rowsEntity(rs);
		}

		return null;
	}
	
	//根据课程ID查询全部题目
	public List<Question> queryByCourseId(String id) throws SQLException {
		List<Question> questionList = new LinkedList<>();
		Connection connection = DBUtil.getConnection();;
		StringBuilder sql = new StringBuilder();
		String bname = "tm";
		sql.append("select * from ").append(bname).append(" where kcID=?");
		PreparedStatement sq = connection.prepareStatement(sql.toString());
		sq.setString(1, id);
		ResultSet re = sq.executeQuery();

		while(re.next()){
			questionList.add(this.rowsEntity(re));
		}
		return questionList;
	}
	
	//随机函数0~10
	private int RandomTest() {
		Random rand = new Random();
		return rand.nextInt(10);
	}
	
	//根据课程ID查询10道题
	public List<Question> queryByCourseIdToTen(String id) throws SQLException {
		List<Question> questionList = new LinkedList<>();
		Connection connection = DBUtil.getConnection();;
		StringBuilder sql = new StringBuilder();
		String bname = "tm";
		sql.append("select * from ").append(bname).append(" where kcID=? limit "+RandomTest()+",10 ");
		PreparedStatement sq = connection.prepareStatement(sql.toString());
		sq.setString(1, id);
		ResultSet re = sq.executeQuery();

		while(re.next()){
			questionList.add(this.rowsEntity(re));
		}
		return questionList;
	}
	
	public static int selectByCourseName(String key,int pageNo,int pageSize,List<Question> l) throws Exception{
		java.sql.Connection con=DBUtil.getConnection();
		int total=0;
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from tm as a");
		if(!ThisSystemUtil.isNone(key)){
			sql.append(",(select kcid from kcxx where kcm like ? or jj like ?)as b where a.kcid=b.kcid");
		}
		
		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
		
		if(!ThisSystemUtil.isNone(key)){
			ps.setString(1,"%"+key+"%");
			ps.setString(2,"%"+key+"%");
		}
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			total=rs.getInt(1);
			if(total==0){
				return total;
			}
		}
		
		
		sql=new StringBuffer();
		sql.append("select * from tm as a");
		if(!ThisSystemUtil.isNone(key)){
			sql.append(",(select kcid from kcxx where kcm like ? or jj like ?)as b where a.kcid=b.kcid");
		}
		sql.append(" limit ?,?");
		ps=con.prepareStatement(sql.toString());
		int i=1;
		if(!ThisSystemUtil.isNone(key)){
			ps.setString(i++, "%"+key+"%");
			ps.setString(i++, "%"+key+"%");
			
		}
		ps.setInt(i++, (pageNo-1)*pageSize);
		ps.setInt(i++, pageSize);
		
		//System.out.println(ps);
		rs=ps.executeQuery();
		
		Question question;
		//l=new ArrayList<>();
		while(rs.next()){
			question=new Question();
			i = 1;
			question.setKcid(rs.getString(i++));
			question.setId(rs.getString(i++));
			question.setStem(rs.getString(i++));
			question.setAnswer(rs.getString(i++));
			question.setSta(rs.getInt(i++));
			question.setSec1(rs.getString(i++));
			question.setSec2(rs.getString(i++));
			question.setSec3(rs.getString(i++));
			question.setSec4(rs.getString(i++));
			//System.out.println(question);
			l.add(question);
		}
		return total;
	}
    
	/**
	 * 输出
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Question rowsEntity(ResultSet rs) throws SQLException {
		Question q = new Question();
		q.setId(rs.getString("th"));
		q.setKcid(rs.getString("kcID"));
		q.setStem(rs.getString("zt"));
		q.setAnswer(rs.getString("daxx"));
		q.setSta(rs.getInt("tmzt"));
		q.setSec1(rs.getString("A"));
		q.setSec2(rs.getString("B"));
		q.setSec3(rs.getString("C"));
		q.setSec4(rs.getString("D"));
		return q;
	}
}
