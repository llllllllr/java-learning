package immoc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import immoc.service.CommandService;


@WebServlet("/FinalInsert")
public class FinalInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FinalInsert() {
        super();    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String name = request.getParameter("commandname");
	    String description = request.getParameter("description");
	    CommandService   commandService = new  CommandService();
	    commandService.insertOne(name, description);
	    request.setAttribute("name", null);
		request.getRequestDispatcher("/Auto/ListServlet").forward(request, response);
	}

}
