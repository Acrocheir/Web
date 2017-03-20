package myservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ThisSystemUtil;

import dao.ChooseDao;
import entity.XK;

@WebServlet("/choose.do")
public class ServletTest extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		//3 鎵ц涓氬姟閫昏緫
		try{
			req.setCharacterEncoding("utf-8");	
			String username=req.getParameter("username");
			ChooseDao dao=new ChooseDao();
			String pageNoStr=req.getParameter("pageNo");
			int pageNo=ThisSystemUtil.parseInt(pageNoStr, 1);
			int pageSize=2;
			List<XK> pageData=new ArrayList<>();
			int total=dao.selectPaginationByKey(username, pageNo, pageSize, pageData);			
			//4 缁勮瑙嗗浘瀵硅薄(vo)
			req.setAttribute("pageData", pageData);
			req.setAttribute("totalRows", total);
			req.setAttribute("currentPage", pageNo);
			req.setAttribute("pageSize", pageSize);
			req.setAttribute("totalPage", ThisSystemUtil.totalPage(total, pageSize));
		
			//5 椤甸潰璺宠浆
			req.getRequestDispatcher("/jsp/test.jsp").forward(req, res);
				
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
