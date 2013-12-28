package test;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class HelloServlet extends HttpServlet {

  	public void doGet (HttpServletRequest req,
                     HttpServletResponse res) throws ServletException, IOException
  	{
		PrintWriter out = res.getWriter();

		out.println("<h1 style=\"color: red;\">Hello, world!</h1>");

		out.close();
  	}
}