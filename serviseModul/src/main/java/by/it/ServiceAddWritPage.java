package by.it;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceAddWritPage {
    public int serAddWritPage(BeanPageData data){
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return(dao.addWritPage(data));
    }

}
