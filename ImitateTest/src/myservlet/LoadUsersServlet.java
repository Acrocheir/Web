package myservlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChooseDao;
import dao.UserDao;
import entity.Users;
import entity.XK;
import util.ThisSystemUtil;
@WebServlet("/loadusers.do")
public class LoadUsersServlet extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	try{
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");		
		ChooseDao dao=new ChooseDao();
		String pageNoStr=req.getParameter("pageNo");
		int pageNo=ThisSystemUtil.parseInt(pageNoStr, 1);
		int pageSize=2;
		List<XK> pageData=new ArrayList<>();
		int total=dao.selectPaginationByKey(username, pageNo, pageSize, pageData);	
		UserDao udao=new UserDao();
		Users g=udao.selectById(username);
		//4 组装视图对象(vo)
		req.setAttribute("pageData", pageData);
		req.setAttribute("totalRows", total);
		req.setAttribute("currentPage", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("totalPage", ThisSystemUtil.totalPage(total, pageSize));
		
		req.setAttribute("vo", g);
		req.getRequestDispatcher("/jsp/users.jsp").forward(req, res);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}

