package comedo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerRecordDao;
import entity.AnswerRecord;

@WebServlet("/loading")
public class Load extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("dtID");
			AnswerRecordDao ardao = new AnswerRecordDao();
			AnswerRecord ar = ardao.selectById(id);
			req.setAttribute("vo", ar);
			req.getRequestDispatcher("/jsp/AnswerRecord.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
