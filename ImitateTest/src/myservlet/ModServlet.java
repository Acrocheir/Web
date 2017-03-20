package myservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import entity.Users;
import util.ThisSystemException;
import util.ThisSystemUtil;
@WebServlet("/change.do")
public class ModServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			// 1 获取参数
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		//2 验证参数
		if(ThisSystemUtil.isNone(id)){
			throw new ThisSystemException("账户不能为空");	
		}
		if(ThisSystemUtil.isNone(password)){
			throw new ThisSystemException("密码不能为空");	
		}
		String newpassword = req.getParameter("newpassword");
		//3 业务逻辑
		System.out.println("修改语句未执行2");
		UserDao dao=new UserDao();
		Users u=dao.selectById(id);
		if(u==null){
			throw new ThisSystemException("账户和原密码不匹配，修改失败，请重登!");
		}
		if(!u.getPassword().equals(password)){
			throw new ThisSystemException("账户和原密码不匹配，修改失败，请重登!");
		}
		u.setPassword(newpassword);
		dao.update(u);
		req.setAttribute("message", "修改成功,请重新登录!");
			// 4组装vo
		} catch (ThisSystemException e) {
			req.setAttribute("message", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "系统繁忙，请稍候再试!");
		}
		// 5 页面跳转
		req.getRequestDispatcher("/jsp/Login.jsp").forward(req, resp);
	}
}
