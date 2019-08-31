package immoc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import immoc.service.CommandService;


@WebServlet("/DeleteBenchServlet")
public class DeleteBenchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteBenchServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String[] idList = request.getParameterValues("id");
		CommandService commandService = new CommandService();
		commandService.deleteBench(idList);
		request.getRequestDispatcher("/Auto/ListServlet").forward(request, response);
	}

}
