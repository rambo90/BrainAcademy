package ManageSQL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/sakila";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final SessionFactory sessionFactory = buildSessionFactory();


    public static Connection call_sql_prosedure (){
        Connection dbConnection = getDBConnection();
        return dbConnection;
    }


    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {System.out.println(e.getMessage());
        }
        try { dbConnection = DriverManager.getConnection(
                DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {System.out.println(e.getMessage());
        }
        return dbConnection;
    }



    public static SessionFactory getSessionFactory() {
        return sessionFactory;

    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();


    }




    private static SessionFactory buildSessionFactory() {
        try {
            // load from different directory
            SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .buildSessionFactory();
            return sessionFactory;

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

            /*try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException ex) {
                System.err.println("Initial sessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

        return sessionFactory;

    }*/



}
