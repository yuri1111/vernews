package by.it;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceEditWritPage {
    public int serEditWritPage(BeanPageData data){
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return (dao.editWritPage(data));
    }
}
