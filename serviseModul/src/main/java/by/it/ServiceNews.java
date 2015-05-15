package by.it;

import java.util.List;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceNews extends Service
{
    public BeanPageData serNewID () {
        return (dao.newId());
    }
    public BeanPageData serGetPagesNewsId(String id){
        return (dao.getPagesNewsId(Integer.parseInt(id)));
    }
    public int serEditWritPage(BeanPageData data){
        return (dao.editWritPage(data));
    }
    public List<BeanPageData>  serDatePage (BeanPageData data) {
        return (dao.datePage(data));
    }
    public int serAddWritPage(BeanPageData data){
        return(dao.addWritPage(data));
    }
    public List<BeanPageData> serGetPagesListNewsByCat(int id) {
        return (dao.getPagesListNewsByCat(id));
    }

}
