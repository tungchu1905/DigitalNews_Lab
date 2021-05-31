/*
 * Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version                AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487         First Implement
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Represent link to connect to database
 *
 * @author TungCTHE141487
 */
public class DBContext {

    /**
     * Create attribute
     */
    private static InitialContext initial;
    private static Context context;
    private static String dbname;
    private static String servername;
    private static String portnumber;
    private static String username;
    private static String password;

    /**
     * Contains information to create the connection to database
     */
    public DBContext() {
        // check the properties to connect database 
        try {
            initial = new InitialContext();
            Object obj = initial.lookup("java:comp/env");
            context = (Context) obj;
            servername = context.lookup("serverName").toString();
            dbname = context.lookup("dbName").toString();
            portnumber = context.lookup("portNumber").toString();
            username = context.lookup("username").toString();
            password = context.lookup("password").toString();

        } catch (NamingException e) {

        }
    }

    /**
     * Represent the connection, support the other class get connection to the
     * database
     *
     * @return connection of database
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + servername + ":" + portnumber + ";databaseName=" + dbname;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * when you are done with your connection, you need to close to release
     * other database resources
     *
     * @param con is the Connection
     * @throws SQLException
     */
    public void closeConnection(Connection con) throws SQLException {

        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * when you are done with your connection, you need to close to release
     * other database resources
     *
     * @param rs is the ResultSet
     * @throws SQLException
     */
    public void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }

    /**
     * when you are done with your connection, you need to close to release
     * other database resources
     *
     * @param ps is the PreparedStatement
     * @throws SQLException
     */
    public void closePrepareStateMent(PreparedStatement ps) throws SQLException {

        if (ps != null && !ps.isClosed()) {
            ps.close();
        }

    }
}
