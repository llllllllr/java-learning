package immoc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import immoc.bean.Command;
import immoc.service.CommandService;

@SuppressWarnings("serial")
public class InsertOServlet extends HttpServlet {

	
	public InsertOServlet() {
		super();
	}
	
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    	this.doPost(request,response);
    	
    }
    
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
    
    	request.getRequestDispatcher("/WEB-INF/jsp/back/insert.jsp").forward(request, response);
    }
}
