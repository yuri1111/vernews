package by.it;



import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public  class PageNewsDao {
	public Connection myConnection;	
	private PageNewsDao(){
		FileInputStream fl;
		Properties props = new Properties();
		try {
            fl = new FileInputStream("D:\\kurs\\news\\daoModul\\src\\main\\resources\\config.properties");
			props.load(fl);
		} catch (IOException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Error reading file parameters");
			return;
		}
		String path = props.getProperty("path");
		String name = props.getProperty("name");
		String pass = props.getProperty("pass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			Loger logWr = new Loger();
			logWr.logWrite("Driver Error");
			return;
		}  
	
		try {
			myConnection = DriverManager.getConnection(path, name, pass);
		} catch (SQLException e1) {
			Loger logWr = new Loger();
			logWr.logWrite("No connection");
			return;
		}		
	}
	
	private static PageNewsDao inst;
	public static PageNewsDao GetMySingle(){
		if (inst ==null){
			inst = new PageNewsDao();
		}
		return inst;		
	}	
	
	public boolean checkUser(Authors log){
		Statement obZap = null;
		try {
			obZap = myConnection.createStatement();
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Do not set up the request object");
		}
		String zapros = "SELECT email,pass FROM autors";
		ResultSet result = null;
		try {
			result = obZap.executeQuery(zapros);
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Do not set up an inquiry zapros");
			try {
				obZap.close();
			} catch (SQLException e1) {
				logWr.logWrite("Not closed zapros");
			}
			return false;
		}
		try {
			while (result.next()){
				if (log.getEmail().equals(result.getString(1)) && log.getPass().equals(result.getString(2))){
					return true;
				}				
			}
			return false;
			
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("Can not obtain results");
			try {
				obZap.close();
			} catch (SQLException e1) {
				logWr.logWrite("Not closed zapros");
			}
			return false;
		}	
				
	}
	
	
	public BeanCategorData getCategor(int id)
	{
		BeanCategorData data = null;
		Statement obZap = null;
		try {
			obZap = myConnection.createStatement();
			String zapros = "SELECT * FROM namecat where id=" + id;
			ResultSet result = obZap.executeQuery(zapros);
			if (result.next()){
				data = new	BeanCategorData();
				data.setId(result.getInt(1));
				data.setNameCategor(result.getString(2));
			}
			else 
				return null;			
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}		
		return data;
	}
	
	public List<BeanCategorData> getListCategor()
	{
		ArrayList<BeanCategorData> listCat = new ArrayList<BeanCategorData>();
		try {
			Statement obZap = myConnection.createStatement();
			ResultSet result = obZap.executeQuery("select * from namecat");
			while(result.next()){
				BeanCategorData data = new	BeanCategorData();
				data.setId(result.getInt(1));
				data.setNameCategor(result.getString(2));
				listCat.add(data);
				
			}
		}catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}
		
		return listCat;	
	}	
	
	
	public List<BeanPageData> getPagesListNewsByCat(int numCat)
	{
		ArrayList<BeanPageData> pagesByCat = new ArrayList<BeanPageData>();
		try {
			Statement obZap = myConnection.createStatement();
			ResultSet result = obZap.executeQuery("select * from news where categor=" + numCat);
			while(result.next()){
				BeanPageData data = new	BeanPageData();
				data.setId(result.getInt(1));
				data.setCategor(result.getInt(2));
				data.setTitle(result.getString(3));
				data.setAnot(result.getString(4));
				data.setAutor(result.getString(5));
				data.setDate(result.getString(6));
				data.setText(result.getString(7));
				
				pagesByCat.add(data);
				
			}
		}catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}
		
		return pagesByCat;		
	}
	
	public BeanPageData getPagesNewsId(int id)
	{
		BeanPageData data = new	BeanPageData();
		try {
			Statement obZap = myConnection.createStatement();
			ResultSet result = obZap.executeQuery("select * from news where id=" + id);
			if (result.next()){
				
				data.setId(result.getInt(1));
				data.setCategor(result.getInt(2));
				data.setTitle(result.getString(3));
				data.setAnot(result.getString(4));
				data.setAutor(result.getString(5));
				data.setDate(result.getString(6));
				data.setText(result.getString(7));							
			}
		}catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}		
		return data;		
	}
	
	public int editWritPage(BeanPageData editPage)
	{	String zpr = "UPDATE news SET categor=?,title=?,anot=?,autor=?,date=?,text=? where id=?";
	
		try {
			PreparedStatement obZap = myConnection.prepareStatement(zpr);
			obZap.setInt(1, editPage.getCategor());
			obZap.setString(2, editPage.getTitle());
			obZap.setString(3, editPage.getAnot());
			obZap.setString(4, editPage.getAutor());
			obZap.setString(5, editPage.getDate());
			obZap.setString(6, editPage.getText());			
			obZap.setInt(7, editPage.getId());
			obZap.executeUpdate();
			
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return 0;
		}	
		return 1;
	}
	
	public int addWritPage(BeanPageData editPage)
	{	String zpr = "INSERT INTO news VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement obZap = myConnection.prepareStatement(zpr);
			obZap.setInt(1, editPage.getId());
			obZap.setInt(2, editPage.getCategor());
			obZap.setString(3, editPage.getTitle());
			obZap.setString(4, editPage.getAnot());
			obZap.setString(5, editPage.getAutor());
			obZap.setString(6, editPage.getDate());
			obZap.setString(7, editPage.getText());				
			obZap.executeUpdate();
			
		} catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return 0;
		}
		return 1;
	}
	

	
	public BeanPageData newId()
	{
		BeanPageData data = new	BeanPageData();
		int id;
		try {
			Statement obZap = myConnection.createStatement();
			ResultSet result = obZap.executeQuery("SELECT max(id) FROM news");
			if (result.next()){	
				id = result.getInt(1) + 1;
				data.setId(id);										
			}
		}catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}		
		return data;		
	}
	
	public List<BeanPageData> datePage(BeanPageData pageD)
	{
		ArrayList<BeanPageData> pagesByDate = new ArrayList<BeanPageData>();
		String date = pageD.getDate();
		try {
			Statement obZap = myConnection.createStatement();
			ResultSet result = obZap.executeQuery("select * from news where date=" + "'" + date + "'");
			while(result.next()){
				BeanPageData data = new	BeanPageData();
				data.setId(result.getInt(1));
				data.setCategor(result.getInt(2));
				data.setTitle(result.getString(3));
				data.setAnot(result.getString(4));
				data.setAutor(result.getString(5));
				data.setDate(result.getString(6));
				data.setText(result.getString(7));
				
				pagesByDate.add(data);
				
			}
		}catch (SQLException e) {
			Loger logWr = new Loger();
			logWr.logWrite("?? ?????? ?????? ???????");
			return null;
		}
		
		return pagesByDate;	
	}
    public Connection conDao(){
        return (myConnection);
    }
	
}
