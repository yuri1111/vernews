package by.it.command;

import by.it.Loger;
import by.it.BeanPageData;
import by.it.ServiceDatePage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DateCommand extends Command {

	@Override
    //The method call find news on a given date and the creation date of the selected page news
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BeanPageData data = new BeanPageData();		
		data.setDate(request.getParameter("dateSt"));
        ServiceDatePage serPer = new ServiceDatePage();
        List<BeanPageData> newsPageDate = serPer.serDatePage(data);


		StringBuffer st = new StringBuffer();
		Iterator<BeanPageData> datePageIterator = newsPageDate.iterator();
		st.append("<ul>");
		while(datePageIterator.hasNext()){

			BeanPageData newsPage = datePageIterator.next();
			st.append("<li>");
			st.append(newsPage.getTitle());			
			st.append("</li>");
		}			
		st.append("<ul>");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/datepage.jsp");
		request.setAttribute("datapage", st.toString());
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Error redirection 4 ");
			return;
		} catch (IOException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Error redirection 4");
			return;
		}		

	}

}
