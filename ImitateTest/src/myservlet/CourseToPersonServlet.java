package myservlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChooseDao;
import dao.CourseDao;
import entity.Course;
import entity.XK;
import util.DBUtil;

@WebServlet("/selectcourse.do")
public class CourseToPersonServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String coursename=req.getParameter("coursename");
			String username=req.getParameter("username");
			CourseDao dao=new CourseDao();
		
			Course course=dao.selectByName(coursename) ;
			ChooseDao chooseDao=new ChooseDao();
			if(!chooseDao.selectByKCId(course.getId(),username)){
				XK xk=new XK();
				xk.setXkID(DBUtil.uuid());
				xk.setYhID(username);
				xk.setKc(course);
				xk.setXkzt("ÒÑÑ¡");
				xk.setXksj(new Timestamp(System.currentTimeMillis()));	
				System.out.println("sdgsgwdgsdag");
				chooseDao.insert(xk);
			}
			req.getRequestDispatcher("/loadusers.do?username="+username).forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
