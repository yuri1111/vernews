package by.it;
        import org.junit.After;
        import org.junit.Test;
        import junit.framework.TestCase;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

/**
 * Unit test for simple App.
 */
public class TestDao
        extends TestCase
{public String actual;

    @Test

    public void testApp()  {
        PageNewsDao dao = PageNewsDao.GetMySingle();
        String zpr = "INSERT INTO testtable VALUES (?,?)";
        String expected = "wordtest";
        try {
            PreparedStatement obZap = dao.conDao().prepareStatement(zpr);
            obZap.setInt(1, 1);
            obZap.setString(2, expected);
            obZap.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        BeanPageData data = new	BeanPageData();

        try {
            Statement obZap = dao.conDao().createStatement();
            ResultSet result = obZap.executeQuery("select * from testtable where id=1");
            if (result.next()){
                int id = result.getInt(1);
                actual = result.getString(2);
            }
        }catch (SQLException e) {
            Loger logWr = new Loger();
            logWr.logWrite("Error writing to the test table");
        }
        assertEquals("word aligned:", expected, actual);
    }
    @After
    public void testCleanTab()  {
        PageNewsDao dao = PageNewsDao.GetMySingle();
        try {
            Statement obZap = dao.conDao().createStatement();
            obZap.executeUpdate("TRUNCATE TABLE testtable");
            }catch (SQLException e) {
            Loger logWr = new Loger();
            logWr.logWrite("Test table is not cleared");
        }

    }

}
