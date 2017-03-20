package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import com.mysql.jdbc.Connection;

import entity.Course;
import util.DBUtil;



public class CourseDao {
//	public int insert(Adminstrator a,Course c) throws Exception{
//		java.sql.Connection con=DBUtil.getConnection();
//		StringBuffer sql=new StringBuffer();
//		sql.append("insert into kcxx")
//		.append(" (kcid,kcm,jj,kczt,zskssj,yyfz)")
//		.append(" values")
//		.append(" (?,?,?,?,?, ?)");
//		int i=1;
//		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
//		ps.setString(i++, c.getId());
//		ps.setString(i++, c.getName());
//		ps.setString(i++, c.getIntroduction());
//		ps.setInt(i++, c.getState());
//		ps.setDate(i++, new Date(c.getTesttime().getTime()));
//		ps.setInt(i++, c.getDuration());
//		
//		CourseManager cm=new CourseManager();
//		cm.setCourseid(c.getId());
//		cm.setUserid(a.getId());
//		cm.setDescribe("鎻掑叆");
//		CourseManagerdao cmd=new CourseManagerdao();
//		
//		i=ps.executeUpdate();
//		cmd.insert(cm);
//		
//		return i;
//	}
//	public int update(Adminstrator a,Course c) throws Exception{
//		java.sql.Connection con=DBUtil.getConnection();
//		StringBuffer sql=new StringBuffer();
//		sql.append("update kcxx")
//		.append(" set kcm=?,jj=?,kczt=?,zskssj=?,yyfz=?")
//		.append(" where kcid=?");
//		int i=1;
//		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
//		ps.setString(i++, c.getName());
//		ps.setString(i++, c.getIntroduction());
//		ps.setInt(i++, c.getState());
//		ps.setDate(i++, new Date(c.getTesttime().getTime()));
//		ps.setInt(i++, c.getDuration());
//		ps.setString(i++, c.getId());
//		
//		
//		
//		i=ps.executeUpdate();
//		
//		CourseManager cm=new CourseManager();
//		cm.setCourseid(c.getId());
//		cm.setUserid(a.getId());
//		cm.setDescribe("淇敼");
//		CourseManagerdao cmd=new CourseManagerdao();
//		
//		
//		cmd.insert(cm);
//		
//		return i;
//	}
	public List<Course> select() throws Exception{
		java.sql.Connection con=DBUtil.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select kcid,kcm,jj,kczt,zskssj,yyfz from kcxx");
		//.append(" where kcglczsj=?");
		
		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
		ResultSet rs=ps.executeQuery();
		
		List<Course> list=new ArrayList<Course>();
		while(rs.next()){
			list.add(selectByResultSet(rs));
		}
		return list;
	}
//	public Course selectById(Course c) throws Exception{
//		java.sql.Connection con=DBUtil.getConnection();
//		StringBuffer sql=new StringBuffer();
//		sql.append("select kcid,kcm,jj,kczt,zskssj,yyfz from kcxx")
//		.append(" where kcid=?");
//		
//		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
//		ps.setString(1, c.getId());
//		
//		//System.out.println(ps);
//		ResultSet rs=ps.executeQuery();
//		
//		if(rs.next()){
//			return selectByResultSet(rs);
//		}
//		throw new Exception("娌℃湁鏌ュ埌锛屽揩婊�");
//	}
	public Course selectByName(String name) throws Exception{
		java.sql.Connection con=DBUtil.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select kcid,kcm,jj,kczt,zskssj,yyfz from kcxx")
		.append(" where kcm=?");
		
		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
		ps.setString(1, name);
		
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()){
			return selectByResultSet(rs);
		}
		throw new Exception("娌℃湁鏌ュ埌锛屽揩婊�");
	}
	private static Course selectByResultSet(ResultSet rs) throws Exception{
		Course c=new Course();
		c.setId(rs.getString(1));
		c.setName(rs.getString(2));
		c.setIntroduction(rs.getString(3));
		c.setState(rs.getInt(4));
		c.setTesttime(rs.getDate(5));
		c.setDuration(rs.getInt(6));
		return c;
	}
	public int selectByCourseName(int pageNo,int pageSize,List<Course> l) throws Exception{
		java.sql.Connection con=DBUtil.getConnection();
		int total=0;
		StringBuffer sql=new StringBuffer();
		
		
		sql=new StringBuffer();
		sql.append("select * from kcxx limit ?,?");
		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
		int i=1;
		ps.setInt(i++, (pageNo-1)*pageSize);
		ps.setInt(i++, pageSize);
		
		//System.out.println(ps);
		ResultSet rs=ps.executeQuery();
		
		//l=new ArrayList<>();
		while(rs.next()){
			l.add(selectByResultSet(rs));
			total++;
		}
		return total;
	}
	
	public Course selectById(String c) throws Exception{
		java.sql.Connection con=DBUtil.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select kcid,kcm,jj,kczt,zskssj,yyfz from kcxx")
		.append(" where kcid=?");
		
		java.sql.PreparedStatement ps=con.prepareStatement(sql.toString());
		ps.setString(1, c);
		
		//System.out.println(ps);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()){
			return selectByResultSet(rs);
		}
		throw new Exception("娌℃湁鏌ュ埌锛屽揩婊�");
	}
}
