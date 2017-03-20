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
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try{
			req.setCharacterEncoding("utf-8");
			//1 获取参数
			String account=req.getParameter("username");
			String password=req.getParameter("password");
			//2 验证参数
			if(ThisSystemUtil.isNone(account)){
				throw new ThisSystemException("账户不能为空");	
			}
			if(ThisSystemUtil.isNone(password)){
				throw new ThisSystemException("密码不能为空");	
			}
			//3 业务逻辑
			UserDao dao=new UserDao();
			Users u=dao.selectById(account);
			if(u==null){
				throw new ThisSystemException("账户和密码不匹配");
			}
			if(!u.getPassword().equals(ThisSystemUtil.md5Password(password))){
				throw new ThisSystemException("账户和密码不匹配");
			}
			
		    //4 组装vo
			
			//5 页面跳转
			req.setAttribute("username", account);
			req.getRequestDispatcher("/jsp/Home.jsp").forward(req, res);
			return;
		}catch(ThisSystemException e){
			req.setAttribute("message", e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("message", "系统繁忙，请稍候再试!");
		}
		req.getRequestDispatcher("/jsp/Login.jsp").forward(req, res);
	}

}
