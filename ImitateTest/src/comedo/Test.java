package comedo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/9527.do")
public class Test extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//super.service(hsrq, resp);
		//设置应答内容的类型
		res.setContentType("text/html");
		//设置应答的字符编码
		res.setCharacterEncoding("utf-8");
		//输出实际返回内容
		PrintWriter pw = res.getWriter();
		pw.println("<!DOCTYPE>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>你好帅啊！</title>");
		pw.println("</head>");
		pw.println("<body>这里有一个大帅哥！</body>");
		pw.println("</html>");
		pw.close();
	}
}
