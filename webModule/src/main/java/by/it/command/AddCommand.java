package by.it.command;


import by.it.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AddCommand extends Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		



       /* PageNewsDao dao = PageNewsDao.GetMySingle();
		BeanPageData nId = dao.newId();

        List<BeanCategorData> listCategor = dao.getListCategor();*/

        ServiceNewID n = new ServiceNewID();
        BeanPageData nId = n.serNewID();
        ServiceGetListCategor cat = new ServiceGetListCategor();
        List<BeanCategorData> listCategor = cat.serGetListCategor();
		StringBuffer st = new StringBuffer();
		
		st.append("<form action=\"AdminController\"><p>");
		st.append("<input type=\"hidden\" name=\"operation\" value=\"addwrite\">");		
		st.append("<input type=\"hidden\" name=\"id\" value=\"" + nId.getId() + "\">");
		st.append("<select name=\"categor\">");		
		Iterator<BeanCategorData> catIterator  = listCategor.iterator();
		while(catIterator.hasNext()){
			BeanCategorData catData = catIterator.next();
			st.append("<option value =\"" + catData.getId() + "\" >" + catData.getNameCategor() + "</option>");			
		}		
		st.append("</select><br>");
		st.append("Title:<input name=\"title\"><br>");
		st.append("Anot:<input name=\"anot\"><br>");
		st.append("Author:<input name=\"autor\"><br>");
		st.append("Date:<input name=\"date\"><br>");
		st.append("Text:<br>");		
		st.append("<textarea rows=\"15\" cols=\"80\" name=\"text\"></textarea>");
		st.append("<input type=\"submit\">");
		st.append("</p></form>");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/addadmin.jsp");
		request.setAttribute("add", st.toString());
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
