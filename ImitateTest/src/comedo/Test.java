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
		//����Ӧ�����ݵ�����
		res.setContentType("text/html");
		//����Ӧ����ַ�����
		res.setCharacterEncoding("utf-8");
		//���ʵ�ʷ�������
		PrintWriter pw = res.getWriter();
		pw.println("<!DOCTYPE>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>���˧����</title>");
		pw.println("</head>");
		pw.println("<body>������һ����˧�磡</body>");
		pw.println("</html>");
		pw.close();
	}
}
