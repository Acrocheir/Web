package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DBUtil;
import util.ThisSystemUtil;
import entity.Course;
import entity.XK;

public class ChooseDao {

	public void insert(XK xk)throws Exception{
		Connection connection = (Connection) DBUtil.getConnection();
		//sql璇彞鐨勫叧鏋勫缓
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xk ")
		.append(" (xkID,yhID,kcID,xkzt,xksj) ")
		.append("values").append(" (?,?,?,?,?) ");
		
		//浼犲叆sql'鍙傛暟锛屾墽琛宻ql
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		int i=1;
		ps.setString(i++,xk.getXkID());
		ps.setString(i++,xk.getYhID());
		ps.setString(i++,xk.getKc().getId());
		ps.setString(i++,xk.getXkzt());
		ps.setDate(i++,new Date(xk.getXksj().getTime()));
		
		ps.executeUpdate();// 澧炲垹鏀�
	}
	
	

//	public void delete(String xkID)throws Exception{
//		Connection connection = (Connection) DBUtil.getConnection();
//		StringBuilder sql = new StringBuilder();
//		sql.append("delete from xk where xkID=?");
//		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
//		ps.setString(1, xkID);
//		ps.executeUpdate();
//	}
//	
//	public void update(XK xk) throws Exception {
//		Connection connection = (Connection) DBUtil.getConnection();
//		StringBuilder sql = new StringBuilder();
//		sql.append("update xk")
//				.append(" set kcID=?,xkzt=? ")
//				.append(" where yhID=?");
//		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
//		int i = 1;
//		ps.setString(i++,xk.getYhID());
//		ps.setString(i++,xk.getKc().getId());
//		ps.setString(i++,xk.getXkzt());
////		ps.setDate(i++,new Date(cd.getCreateTime().getTime()));
//		ps.executeUpdate();
//	}

	/**
	 * 閫氳繃鐢ㄦ埛id鏌ユ壘閫夎淇℃伅	
	 * 
	 * @param id
	 *            鐢ㄦ埛id
	 * @return 閫夎淇℃伅
	 * @throws Exception
	 */
	public XK selectById(String yhID) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xk where yhID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, yhID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.row2entity(rs);
		}

		return null;
	}
	
	public XK selectByXKId(String xkID) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xk where xkID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, xkID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.row2entity(rs);
		}
		return null;
	}
	
	public int selectPaginationByKey(String key, int pageNo, int pageSize,
			List<XK> pageData) throws Exception{
			System.out.println("进入"+pageNo);
			Connection connection = (Connection) DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			
			//鏌ヨ鎬昏褰曟暟
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement("select count(*) from xk where yhID=?");
//			if(!ThisSystemUtil.isNone(key)){
//				ps.setString(1, key);
//			}
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			int total=0;
			if(rs.next()){
				total=rs.getInt(1);
			}
			sql.append("select * from xk where yhID=?");
			sql.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
			System.out.println(sql.toString());
			ps = (PreparedStatement) connection.prepareStatement(sql.toString());
			ps.setString(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				pageData.add(this.row2entity(rs));
			}
			return total;
		}
	
	public int selectallPaginationByKey(String key,
			List<XK> pageData) throws Exception{
		    Connection connection = (Connection) DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from xk where yhID=?");
			int total=0;
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pageData.add(this.row2entity(rs));
				total++;
			}
			return total;
		}
	private XK row2entity(ResultSet rs)throws Exception {
		XK xk = new XK();
		xk.setXkID(rs.getString("xkID"));
		xk.setYhID(rs.getString("yhID"));
		xk.setXkzt(rs.getString("xkzt"));
		xk.setXksj(rs.getDate("xksj"));
		
		CourseDao dao=new CourseDao();
		Course course=dao.selectById(rs.getString("kcID"));
		xk.setKc(course);
		return xk;
	}
	
	public boolean selectByKCId(String kcID,String username) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from xk where kcID=? and yhID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, kcID);
		ps.setString(2, username);
		ResultSet rs = ps.executeQuery();
		int total=0;
		if (rs.next()) {
			total=rs.getInt(1);
		}
		System.out.println(total);
		if(total>0){
			return true;
		}
		else
			return false;
	}

}
