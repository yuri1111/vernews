package by.it.command;

import by.it.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ShowCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<BeanCategorData> listCategor;
		List<BeanPageData> newsListCat;

		/*ageNewsDao dao = PageNewsDao.GetMySingle();
		listCategor = dao.getListCategor();*/
        ServiceGetListCategor serPer = new ServiceGetListCategor();
        listCategor = serPer.serGetListCategor();
		
		Iterator<BeanCategorData> catIterator  = listCategor.iterator();
		StringBuffer st = new StringBuffer();
		st.append("<a href=\"AdminController?operation=add\">Add news</a>");
		st.append("<form action=\"AdminController\"><p>");
		st.append("<input type=\"hidden\" name=\"operation\" value=\"date\">");
		st.append("Enter date:<input name=\"dateSt\"><br>");
		st.append("<input type=\"submit\">");
		st.append("</p></form>");			
		st.append("<ul>");
		while(catIterator.hasNext())
		{
			BeanCategorData catData = catIterator.next();
			st.append(catData.getNameCategor());

			/*newsListCat = dao.getPagesListNewsByCat(catData.getId());*/
            ServiceGetPagesListNewsByCat serPer2 = new ServiceGetPagesListNewsByCat();
            newsListCat = serPer2.serGetPagesListNewsByCat(catData.getId());


			Iterator<BeanPageData> newsCatIterator = newsListCat.iterator();
			
			st.append("<ul>");
			while(newsCatIterator.hasNext())
			{
				BeanPageData newsPageCat = newsCatIterator.next();
				st.append("<li>");
				st.append(" <a href=\"AdminController?operation=edit&id=");
				st.append( Integer.toString(newsPageCat.getId()));
				st.append("\">" + newsPageCat.getTitle() + "</a>");
				st.append("</li>");
			}			
			st.append("</ul>");
			st.append("</li>");
		}

		st.append("</ul>");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainadmin.jsp");
		request.setAttribute("menu", st.toString());
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

}
