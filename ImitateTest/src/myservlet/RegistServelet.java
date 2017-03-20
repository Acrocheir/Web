package myservlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.Users;
import util.ThisSystemException;
import util.ThisSystemUtil;
@WebServlet("/regist.do")
public class RegistServelet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			req.setCharacterEncoding("utf-8");
			//1 ��ȡ����
			String account=req.getParameter("registname");
			String password1=req.getParameter("password1");
			String password2=req.getParameter("password2");
			//2 ��֤����
			if(ThisSystemUtil.isNone(account)){
				throw new ThisSystemException("�˻�����Ϊ��");	
			}
			if(ThisSystemUtil.isNone(password1)){
				throw new ThisSystemException("���벻��Ϊ��");	
			}
			if(ThisSystemUtil.isNone(password2)){
				throw new ThisSystemException("���벻��Ϊ��");	
			}
			if(!password1.equals(password2)){
				throw new ThisSystemException("�������������ͬ");	
			}
			//3 ҵ���߼�
			UserDao dao=new UserDao();
			Users u=new Users();
			u.setId(account);
			u.setPassword(ThisSystemUtil.md5Password(password1));
			u.setUserstate("��Ծ");
			u.setUsertype("��ͨ�û�");
			u.setCreatetime(new Timestamp(System.currentTimeMillis()));
			u.setLastlogintime(new Timestamp(System.currentTimeMillis()));
			dao.insert(u);	
		    //4 ��װvo
			
			//5 ҳ����ת
			req.getRequestDispatcher("/jsp/Login.jsp").forward(req, res);
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
