package by.it;

import java.util.List;

/**
 * Created by NM Group on 11.05.2015.
 */
public class ServiceCategor extends Service {
    public List<BeanCategorData> serGetListCategor() {
        return (dao.getListCategor());
    }
}
