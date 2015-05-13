package by.it;

import java.util.List;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceGetListCategor {
    public List<BeanCategorData> serGetListCategor() {
        PageNewsDao dao = PageNewsDao.GetMySingle();
        return (dao.getListCategor());
    }
}
