package by.it;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceGetPagesNewsId {
    public BeanPageData serGetPagesNewsId(String id){
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return (dao.getPagesNewsId(Integer.parseInt(id)));
    }
}
