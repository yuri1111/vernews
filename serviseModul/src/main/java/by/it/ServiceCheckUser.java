package by.it;

/**
 * Created by NM Group on 10.05.2015.
 */
public class ServiceCheckUser {
    public boolean serCheckUser(Authors log){
        PageNewsDao adminEnter = PageNewsDao.GetMySingle();
        return (adminEnter.checkUser(log));
    }
}
