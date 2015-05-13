package by.it;

import java.util.List;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceNewID {
       public BeanPageData serNewID () {
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return (dao.newId());
    }

}
