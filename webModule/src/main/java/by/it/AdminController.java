package by.it;

import by.it.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;       
	
    public AdminController() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpSession sesEnter = request.getSession();		
		if (sesEnter.getAttribute("adminEnter") == null){		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admincheck.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				Loger logWr = new Loger();
				logWr.logWrite("lll");
				return;
			} catch (IOException e) {
				Loger logWr = new Loger();
				logWr.logWrite("lll");
				return;
			}
		}
				
		String operation = request.getParameter("operation");
		
		Command com = null;
		
		if(operation == null){
			com = new ShowCommand();
		}				
		else if(operation.equals("edit")){
			com = new EditCommand();
		}
		else if(operation.equals("editwrite")){
			com = new EditWriteCommand();			
		}
		else if(operation.equals("add")){
			com = new AddCommand();			
		}
		else if(operation.equals("addwrite")){
			com = new AddWriteCommand();			
		}
		else if(operation.equals("date")){
			com = new DateCommand();			
		}
		
		com.execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
