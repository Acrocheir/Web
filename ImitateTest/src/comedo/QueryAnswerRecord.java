package comedo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerRecordDao;
import entity.AnswerRecord;
import util.ThisSystemUtil;

@WebServlet("/record.do")
public class QueryAnswerRecord extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//1 获取参数
			req.setCharacterEncoding("utf-8");
			//关键字
			String key = req.getParameter("key");
			//页码
			String pageNOStr = req.getParameter("pageNo");
			int pageNo = ThisSystemUtil.parseInt(pageNOStr, 1);
			System.out.println(pageNo);
			//每页大小
			int pageSize = 3;
			//2 验证参数
			if(!ThisSystemUtil.isNone(key)) {
				key = key.trim();
			}
			//3 执行业务逻辑
			/*AnswerRecordDao ardao = new AnswerRecordDao();
			List<AnswerRecord> pageData = new ArrayList<>(pageSize);
			int total = ardao.selectPaginationByKey(key, pageNo, pageSize, pageData);
			//4 组装视图对象（vo）
			req.setAttribute("pageData", pageData);
			req.setAttribute("totalRows", total);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("pageSize", pageSize);
			req.setAttribute("totalPage", ThisSystemUtil.totalPage(total, pageSize));
			//5 页面跳转
*/			req.getRequestDispatcher("/jsp/SerchList.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
