package com.rainbowtraveller.simple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleJDBCMySql {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost:3306/saavn";
    String user = "admin";
    String password = "dbpass";
    try {
        connection = DriverManager.getConnection(cs, user, password);
        statement = con.prepareStatement("SELECT listid, listname, contents FROM playlists LIMIT 5");
        results = statement.executeQuery();

        while(rs.next()) {
            int id = results.getInt("listid");
            String listname = results.getString("listname");
            String contents = results.getString("contents");
            System.out.println("List Id: " + id + "Name: " + listname + "Contents: " + contents);
        }
    } catch (SQLException sqle) {
        sqle.printStacktrace();
    } finally {
        try {
            if(results != null) {
                results.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
             sqle.printStacktrace();
        }
    }
}
