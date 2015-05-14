package by.it;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class ServletCheckAdmin
 */
@WebServlet("/ServletCheckAdmin")
public class ServletCheckAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
        Authors log = new Authors();
        log.setEmail(email);
        log.setPass(pass);
		ServiceCheckUser serPer = new ServiceCheckUser();
		if (serPer.serCheckUser(log)){
			HttpSession sesEnter = request.getSession();
			sesEnter.setAttribute("adminEnter", "yes");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminController");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				Loger logWr = new Loger();
				logWr.logWrite("Error redirection");
				return;
			} catch (IOException e) {
				Loger logWr = new Loger();
				logWr.logWrite("Error redirection");
				return;
			}			
									
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admincheck.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				Loger logWr = new Loger();
				logWr.logWrite("Error redirection");
				return;
			} catch (IOException e) {
				Loger logWr = new Loger();
				logWr.logWrite("Error redirection");
				return;
			}

		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
