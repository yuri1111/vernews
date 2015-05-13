package by.it.command;


import by.it.Loger;
import by.it.BeanPageData;
import by.it.ServiceGetPagesNewsId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		/*PageNewsDao dao = PageNewsDao.GetMySingle();
		BeanPageData pageId = dao.getPagesNewsId(Integer.parseInt(id));*/
        ServiceGetPagesNewsId serPer = new ServiceGetPagesNewsId();
        BeanPageData pageId = serPer.serGetPagesNewsId(id);

		StringBuffer st = new StringBuffer();
		st.append("<form action=\"AdminController\"><p>");
		st.append("<input type=\"hidden\" name=\"operation\" value=\"editwrite\">");
		st.append("<input type=\"hidden\" name=\"id\" value=\"" + pageId.getId() + "\">");
		st.append("<input type=\"hidden\" name=\"categor\" value=\"" + pageId.getCategor() + "\">");
		st.append("Title:<input name=\"title\" value=\"" + pageId.getTitle() + "\"><br>");
		st.append("Anot:<input name=\"anot\" value=\"" + pageId.getAnot() + "\"><br>");
		st.append("Author:<input name=\"autor\" value=\"" + pageId.getAutor() + "\"><br>");
		st.append("Date:<input name=\"date\" value=\"" + pageId.getDate() + "\"><br>");
		st.append("Text:<br>");		
		st.append("<textarea rows=\"15\" cols=\"80\" name=\"text\">" + pageId.getText() + "</textarea>");
		st.append("<input type=\"submit\">");
		st.append("</p></form>");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/editadmin.jsp");
		request.setAttribute("edit", st.toString());
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
