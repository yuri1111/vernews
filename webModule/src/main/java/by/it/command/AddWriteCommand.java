package by.it.command;

import by.it.Loger;
import by.it.BeanPageData;
import by.it.ServiceAddWritPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddWriteCommand extends Command {

	@Override
    // The method call to add a new news database
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BeanPageData data = new BeanPageData();
		data.setId(Integer.parseInt(request.getParameter("id")));
		data.setCategor(Integer.parseInt(request.getParameter("categor")));
		data.setTitle(request.getParameter("title"));
		data.setAnot(request.getParameter("anot"));
		data.setAutor(request.getParameter("autor"));
		data.setDate(request.getParameter("date"));
		data.setText(request.getParameter("text"));
        ServiceAddWritPage serPer = new ServiceAddWritPage();
		if (serPer.serAddWritPage(data) == 0){
			Loger logWr = new Loger();
			logWr.logWrite("Failed to add page 3");
			return;
		}

		try {
			response.sendRedirect("AdminController");
		} catch (IOException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Error redirection 3");
			return;
		}	
		

	}

}
