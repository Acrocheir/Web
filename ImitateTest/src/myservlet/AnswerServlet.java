package myservlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerRecordDao;
import dao.QuestionDao;
import entity.AnswerRecord;
import entity.Question;
@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			String allanswer=req.getParameter("allanswer");
			String rightAnswer = req.getParameter("rightAnswer");
			String th=req.getParameter("th");
			String cjid=req.getParameter("cjid");
			System.out.println(allanswer);
			System.out.println(rightAnswer);
			System.out.println(th);
			System.out.println(cjid);
			AnswerRecordDao ardao = new AnswerRecordDao();
			AnswerRecord ar = new AnswerRecord();
			QuestionDao dao=new QuestionDao();
			Question q = new Question();
			for(int i=0;i<rightAnswer.length();i++) {
				
				//ar.setQuestion(question);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
