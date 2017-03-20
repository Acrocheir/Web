package myservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/index.do")
public class IndexServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");	
		String username=req.getParameter("username");
		req.setAttribute("username", username);
		System.out.println("21"+username);
		req.getRequestDispatcher("/jsp/Main.jsp").forward(req, res);
	}

}
