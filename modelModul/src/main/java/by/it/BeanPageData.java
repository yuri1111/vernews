package by.it;

import java.io.Serializable;

public class BeanPageData implements Serializable{
	public BeanPageData() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategor() {
		return categor;
	}
	public void setCategor(int categor) {
		this.categor = categor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnot() {
		return anot;
	}
	public void setAnot(String anot) {
		this.anot = anot;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private int id;
	private int categor;
	private String title;
	private String anot;
	private String autor;
	private String date;
	private String text;
	
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		if(obj instanceof BeanPageData)
//		{
//			BeanPageData pd = (BeanPageData)obj;
//			if(id.equals(pd.id) && parentid.equals(pd.parentid))
//				return true;
//			else
//				return false;
//		}
//		else
//			return false;
//		
//		//return super.equals(obj);
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return id.hashCode();
//		//return super.hashCode();
//	}
}
