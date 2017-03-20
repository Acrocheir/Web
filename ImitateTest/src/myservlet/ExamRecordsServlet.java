package myservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChooseDao;
import dao.ExamRecordsDao;
import entity.ExamRecords;
import entity.XK;
import util.DBUtil;
import util.ThisSystemException;
import util.ThisSystemUtil;

@WebServlet("/examrecords.do")
public class ExamRecordsServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			System.out.println("0000");
			req.setCharacterEncoding("utf-8");
			String kssj= req.getParameter("starttime");
			String ksys = req.getParameter("timeing");
			int fs = Integer.parseInt(req.getParameter("score"));
			String xkid=req.getParameter("chooseid");
			System.out.println(kssj);
			System.out.println(xkid);
			//²åÈë¼ÇÂ¼
			ExamRecordsDao erdao = new ExamRecordsDao();
			ExamRecords er = new ExamRecords();
			er.setCjID(DBUtil.uuid());
			ChooseDao chdao = new ChooseDao();
			XK xk = chdao.selectByXKId(xkid);
			er.setXk(xk);
			er.setFs(fs);
			er.setKssj(kssj);
			er.setKsys(ksys);
			System.out.println("22");
			erdao.insert(er);
			System.out.println("33");
//			req.setAttribute(allanswer, "allanswer");
//			req.setAttribute(th, "th");
			req.setAttribute(er.getCjID(), "cjid");
			
			req.getRequestDispatcher("/jsp/TestFinsh.jsp").forward(req, res);
		}catch(ThisSystemException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
