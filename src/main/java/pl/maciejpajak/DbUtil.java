package pl.maciejpajak;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
    
    private static Connection connection;
    
    private DbUtil() {}
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Context ctx = new InitialContext();
                DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/school");    // TODO add context
                connection = ds.getConnection();
            } catch (NamingException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
}
 