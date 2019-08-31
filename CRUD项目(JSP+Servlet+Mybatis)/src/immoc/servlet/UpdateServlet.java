package immoc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import immoc.bean.Command;
import immoc.service.CommandService;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String newName = request.getParameter("newname");
		String newDes = request.getParameter("newdes");
		String id = (String) request.getParameter("sendid");
		Command command = new Command();
		CommandService commandService = new CommandService();
		command.setName(newName);
		command.setDescription(newDes);
		command.setId(id);
		commandService.updateCommand(command);
		request.getRequestDispatcher("/Auto/ListServlet").forward(request, response);
	}

}
