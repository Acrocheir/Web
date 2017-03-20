package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import util.DBUtil;
import entity.ExamRecords;
import entity.XK;

public class ExamRecordsDao {

	/**
	 * 插入数据
	 * @param er
	 * @throws Exception
	 */
	public void insert(ExamRecords er)throws Exception{
		Connection connection = (Connection) DBUtil.getConnection();
		//sql语句的关构建
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ksjl")
		.append(" (cjID,xkID,fs,kssj,ksys) ")
		.append(" values ").append("(?,?,?,?,?)");
		
		//传入sql'参数，执行sql
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		int i=1;
		ps.setString(i++, er.getCjID());
		ps.setString(i++, er.getXk().getXkID());
		ps.setDouble(i++, er.getFs());
		ps.setString(i++,er.getKssj());
		ps.setString(i++, er.getKsys());
		
		ps.executeUpdate();// 增删改
	}
	
	

	public void delete(String yhID)throws Exception{
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xk where yhID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, yhID);
		ps.executeUpdate();
	}
	
	public void update(ExamRecords ks) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("update ksjl")
				.append(" set kssj=?,fs=?,xkID=? ")
				.append(" where cjID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		int i = 1;
		ps.setDouble(i++,ks.getFs());
		ps.setString(i++,ks.getCjID());
		ps.setString(i++,ks.getXk().getXkID());
//		ps.setDate(i++,new Date(cd.getCreateTime().getTime()));
		ps.executeUpdate();
	}

	/**
	 * 通过用户id查找选课信息	
	 * 
	 * @param id
	 *            用户id
	 * @return 选课信息
	 * @throws Exception
	 */
	public ExamRecords selectById(String xkID) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ksjl where xkID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setString(1, xkID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.rowsEntity(rs);
		}
		return null;
	}
	
	public int selectPaginationByKey(List<XK> chooseData, int pageNo, int pageSize,int total,
			List<ExamRecords> pageData) throws Exception {
		Connection connection = (Connection) DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();		
		StringBuilder sqlwhere= new StringBuilder();
		sqlwhere.append("select count(*) from ksjl where xkID=?");
		for(int i=1;i<total;i++)
			sqlwhere.append(" or xkID=?");
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlwhere.toString());
		System.out.println(sqlwhere.toString());
		for(int i=1;i<=total;i++){
		    ps.setString(i, chooseData.get(i-1).getXkID());
		}
		System.out.println(12);
		ResultSet rs = ps.executeQuery();
		int tot=0;
		if(rs.next()){
			tot=rs.getInt(1);
		}
		//按页码查询
		sql.append("select * from ksjl where xkID=?");
		for(int i=1;i<total;i++)
			sql.append(" or xkID=?");
		sql.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
		
		ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		for(int i=1;i<=total;i++)
		ps.setString(i, chooseData.get(i-1).getXkID());
		rs = ps.executeQuery();
		while(rs.next()) {
			pageData.add(this.rowsEntity(rs));
		}
//		System.out.println(pageData.get(0).toString());
		return tot;
	}
	
	public int selectPaginationByKey(String key, int pageNo, int pageSize,
			List<ExamRecords> pageData) throws Exception {
		int total =0;
		try {
			System.out.println("考试"+key);
			Connection connection = (Connection) DBUtil.getConnection();
			StringBuilder sqlwhere= new StringBuilder();
			sqlwhere.append("select count(*) from ksjl where xkID=?");			
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlwhere.toString());
			ps.setString(1,key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				total=rs.getInt(1);
			}
			StringBuilder sql = new StringBuilder();
			sql.append("select *  ");
			sql.append("from ksjl where xkID=?");
			sql.append(" limit ").append((pageNo-1)*pageSize)
			.append(",").append(pageSize);
			System.out.println(sql.toString());
			ps = (PreparedStatement) connection.prepareStatement(sql.toString());
			ps.setString(1,key);
			rs = ps.executeQuery();
			while(rs.next()){
				pageData.add(this.rowsEntity(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(total);
		return total;
	}

		private ExamRecords rowsEntity(ResultSet rs)throws Exception {
			ExamRecords er = new ExamRecords();
			er.setCjID(rs.getString("cjID"));
			er.setFs(rs.getInt("fs"));
			er.setKssj(rs.getString("kssj"));
			er.setKsys(rs.getString("ksys"));
			ChooseDao dao=new ChooseDao();
			XK xk=dao.selectById(rs.getString("xkID"));
			er.setXk(xk);
			
			return er;
		}
}