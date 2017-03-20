package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import entity.Users;
import util.DBUtil;
public class UserDao {
	public void insert(Users g) throws Exception {
		System.out.println("1");
		Connection connection = DBUtil.getConnection();
		System.out.println("2");
		// 3sql语句的构建
		StringBuilder sql = new StringBuilder();
		sql.append("insert into yh")
				.append(" (yhID,mm,yhlx,yhzt,zcsj,zhdlsj)")
				.append(" values").append(" (?,?,?,?,?,   ?)");
		// 4传入sql参数，执行sql
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		int i = 1;
		ps.setString(i++, g.getId());
		ps.setString(i++, g.getPassword());
		ps.setString(i++, g.getUsertype());
		ps.setString(i++, g.getUserstate());
		// java.sql.Date
		ps.setTimestamp(i++, new Timestamp(g.getCreatetime().getTime()));
		ps.setTimestamp(i++, new Timestamp(g.getLastlogintime().getTime()));
		// 执行
		System.out.println(sql.toString());
		ps.executeUpdate();// 增删改
	}
	public void delete(String id) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from yh where yhID=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, id);
		System.out.println(sql.toString());
		ps.executeUpdate();
	}
	public void update(Users g) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("update yh")
				.append(" set mm=?,yhlx=?,yhzt=? ")
				.append(" where yhID=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		int i = 1;
		ps.setString(i++, g.getPassword());
		ps.setString(i++, g.getUsertype());
		ps.setString(i++, g.getUserstate());
		// ps.setDate(i++,DBUtil.toSqlDate(g.getCreateTime()));
		ps.setString(i++, g.getId());
		ps.executeUpdate();
	}
	/**
	 * 通过商品id查找用户
	 * 
	 * @param id
	 *           用户id
	 * @return 用户对象
	 * @throws Exception
	 */
	public Users selectById(String id) throws Exception {
		Connection connection = DBUtil.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from yh where yhID=?");
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return this.row2entity(rs);
		}
		return null;
	}
	public int selectPaginationByKey(String key, int pageNo, int pageSize,
			List<Users> pageData) {
		return 0;
	}
	private Users row2entity(ResultSet rs) throws SQLException {
		Users g = new Users();
		g.setId(rs.getString("yhID"));
		g.setCreatetime(rs.getDate("zcsj"));
		g.setPassword(rs.getString("mm"));
		g.setLastlogintime(rs.getDate("zhdlsj"));
		g.setUsertype(rs.getString("yhlx"));
		g.setUserstate(rs.getString("yhzt"));
		return g;
	}
}
