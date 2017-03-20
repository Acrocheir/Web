package myservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;
import entity.Question;
import util.ThisSystemException;

@WebServlet("/question.do")
public class QuestionServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try{
			req.setCharacterEncoding("utf-8");
			String kcid=req.getParameter("id");
			String chooseid=req.getParameter("xkid");
			QuestionDao qdao = new QuestionDao();
			List<Question> listQue = new ArrayList<>();
			listQue = qdao.queryByCourseIdToTen(kcid);
			
			for(int i=0;i<4;i++){
				System.out.println(listQue.get(i).toString());
			}
			System.out.println(listQue.get(3).toString());
			req.setAttribute("vo", listQue);
			req.setAttribute("xkid", chooseid);
			req.getRequestDispatcher("/jsp/Exam.jsp").forward(req, res);
		}catch(ThisSystemException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
