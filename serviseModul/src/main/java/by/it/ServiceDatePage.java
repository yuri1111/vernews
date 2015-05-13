package by.it;

import java.util.List;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceDatePage {
    public List<BeanPageData>  serDatePage (BeanPageData data) {
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return (dao.datePage(data));
    }
}
