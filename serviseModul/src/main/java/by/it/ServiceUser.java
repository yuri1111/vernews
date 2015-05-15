package by.it;

/**
 * Created by NM Group on 10.05.2015.
 */
public class ServiceUser extends Service {
    public boolean serCheckUser(Authors log){
        return (dao.checkUser(log));
    }
}
