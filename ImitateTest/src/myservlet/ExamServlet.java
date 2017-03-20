package myservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExamRecordsDao;
import entity.ExamRecords;
import util.ThisSystemUtil;
@WebServlet("/exam.do")
public class ExamServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			String chooseid=req.getParameter("chooseid");
			String name=req.getParameter("name");
			String pageNoStr=req.getParameter("pageNo");
			int pageNo=ThisSystemUtil.parseInt(pageNoStr, 1);
			int pageSize=2;
			ExamRecordsDao dao=new ExamRecordsDao();
			List<ExamRecords> pageData=new ArrayList<>();
			System.out.println("ʵ��"+pageNo);
			int total=dao.selectPaginationByKey(chooseid, pageNo, pageSize, pageData);
			req.setAttribute("chooseid", chooseid);
			req.setAttribute("pageData", pageData);
			req.setAttribute("totalRows", total);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("pageSize", pageSize);
			req.setAttribute("totalPage", ThisSystemUtil.totalPage(total, pageSize));
			req.setAttribute("name", name);
		
			//5 页面跳转
			req.getRequestDispatcher("/jsp/examrecords.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
