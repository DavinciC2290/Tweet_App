package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tweet_app", "sauldavinci", "entra");
            

        } catch (SQLException e) {
            System.out.println(e);

        }

        return connection;
    }
}