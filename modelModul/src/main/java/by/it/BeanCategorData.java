package by.it;

import java.io.Serializable;

public class BeanCategorData implements Serializable {
public BeanCategorData() {		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getNameCategor() {
		return nameCategor;
	}
	public void setNameCategor(String nameCategor) {
		this.nameCategor = nameCategor;
	}	
	private int id;
	private String nameCategor;	

}
