package by.it;

import java.io.Serializable;

public class Authors implements Serializable{
	public Authors() {
	}
	private int id;	
	private String name;
	private String fam;
	private String email;
	private String pass;
	
	public int getId (){
		return id;
	}
	public void setId (int id1){
		id = id1;
	}
	public String getName (){
		return name;
	}
	public void setName (String name1){
		name = name1;
	}
	public String getFam (){
		return fam;
	}
	public void setFam (String fam1){
		fam = fam1;
	}
	public String getEmail (){
		return email;
	}
	public void setEmail (String email1){
		email = email1;
	}
	public String getPass (){
		return pass;
	}
	public void setPass (String pass1){
		pass = pass1;
	}
}
