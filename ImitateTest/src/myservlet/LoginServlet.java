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
			//1 ��ȡ����
			String account=req.getParameter("username");
			String password=req.getParameter("password");
			//2 ��֤����
			if(ThisSystemUtil.isNone(account)){
				throw new ThisSystemException("�˻�����Ϊ��");	
			}
			if(ThisSystemUtil.isNone(password)){
				throw new ThisSystemException("���벻��Ϊ��");	
			}
			//3 ҵ���߼�
			UserDao dao=new UserDao();
			Users u=dao.selectById(account);
			if(u==null){
				throw new ThisSystemException("�˻������벻ƥ��");
			}
			if(!u.getPassword().equals(ThisSystemUtil.md5Password(password))){
				throw new ThisSystemException("�˻������벻ƥ��");
			}
			
		    //4 ��װvo
			
			//5 ҳ����ת
			req.setAttribute("username", account);
			req.getRequestDispatcher("/jsp/Home.jsp").forward(req, res);
			return;
		}catch(ThisSystemException e){
			req.setAttribute("message", e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("message", "ϵͳ��æ�����Ժ�����!");
		}
		req.getRequestDispatcher("/jsp/Login.jsp").forward(req, res);
	}

}
