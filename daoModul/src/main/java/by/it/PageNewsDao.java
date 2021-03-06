package by.it;


import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public  class PageNewsDao {
    private static final Logger logError = Logger.getLogger(PageNewsDao.class);
	public Connection myConnection;	
	private PageNewsDao(){
		FileInputStream fl;
		Properties props = new Properties();
		try {
            fl = new FileInputStream("D:\\kurs\\news\\daoModul\\src\\main\\resources\\config.properties");
			props.load(fl);
		} catch (IOException e) {
            logError.error("Error reading file parameters");
			return;
		}
		String path = props.getProperty("path");
		String name = props.getProperty("name");
		String pass = props.getProperty("pass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
            logError.error("Driver Error");
			return;
		}  
	
		try {
			myConnection = DriverManager.getConnection(path, name, pass);
		} catch (SQLException e1) {
            logError.error("No connection");
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
	
	public boolean checkUser(Authors log){ //check the login and password
		Statement obZap = null;
		try {
			obZap = myConnection.createStatement();
		} catch (SQLException e) {
            logError.error("Do not set up the request object");
		}
		String zapros = "SELECT email,pass FROM autors";
		ResultSet result = null;
		try {
			result = obZap.executeQuery(zapros);
		} catch (SQLException e) {
            logError.error("Do not set up an inquiry zapros");
			try {
				obZap.close();
			} catch (SQLException e1) {
                logError.error("Not closed zapros");
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
            logError.error("Can not obtain results");
			try {
				obZap.close();
			} catch (SQLException e1) {
                logError.error("Not closed zapros");
			}
			return false;
		}	
				
	}
	
	
	public BeanCategorData getCategor(int id)//Reading from the database category
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
            logError.error("Do not set up the request object");
			return null;
		}		
		return data;
	}
	
	public List<BeanCategorData> getListCategor()//Read the list of categories
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
            logError.error("Do not set up the request object");
			return null;
		}
		
		return listCat;	
	}	
	
	
	public List<BeanPageData> getPagesListNewsByCat(int numCat)
    //Read the news list in the category of database
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
            logError.error("Do not set up the request object");
			return null;
		}
		
		return pagesByCat;		
	}
	
	public BeanPageData getPagesNewsId(int id)//Read news from the base
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
            logError.error("Do not set up the request object");
			return null;
		}		
		return data;		
	}
	
	public int editWritPage(BeanPageData editPage)//changing news database
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
            logError.error("Do not set up the request object");
			return 0;
		}	
		return 1;
	}
	
	public int addWritPage(BeanPageData editPage)//Add a new news database
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
            logError.error("Do not set up the request object");
			return 0;
		}
		return 1;
	}
	

	
	public BeanPageData newId()//Getting a new id for news
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
            logError.error("Do not set up the request object");
			return null;
		}		
		return data;		
	}
	
	public List<BeanPageData> datePage(BeanPageData pageD)//
    //Reading the news list from the database
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
            logError.error("Do not set up the request object");
			return null;
		}
		
		return pagesByDate;	
	}
    public Connection conDao(){
        return (myConnection);
    }
	
}
