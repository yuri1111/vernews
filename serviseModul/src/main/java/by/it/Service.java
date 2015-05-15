package by.it;

/**
 * Created by NM Group on 14.05.2015.
 */
public class Service
{   PageNewsDao dao;
    public Service(){
      dao = PageNewsDao.GetMySingle();
    }
}
