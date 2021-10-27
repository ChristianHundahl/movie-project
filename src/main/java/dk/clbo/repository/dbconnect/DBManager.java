package dk.clbo.repository.dbconnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    //Fields
    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;

    //Methods
    public static Connection getConnection(){
        if (connection != null)//'if' statement her forhindrer at connection skal etableres hver gang metoden bruges
            return connection;//Singleton: returner connection hvis allerede oprettet
        //Hent database url, brugernavn og password fra 'resources'
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
