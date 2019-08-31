package immoc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import immoc.bean.Command;


@WebServlet("/UpdateJServlet")
public class UpdateJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateJServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
		    String name = request.getParameter("name");
		    String id = request.getParameter("id");
		     request.setAttribute("id", id);
             request.getRequestDispatcher("/WEB-INF/jsp/back/update.jsp").forward(request, response);
	}

}
