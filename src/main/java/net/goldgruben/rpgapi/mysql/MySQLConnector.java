package net.goldgruben.rpgapi.mysql;

import net.goldgruben.rpgapi.RpgApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {

    /* Variables */

    private static Connection connection;

    /* Methods */
    public static void connect(String host, String database, int port, String password, String user) {
        if (host == null || database == null || password == null || user == null) return;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&maxReconnects=10", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (isConnected()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("§cError while closing connection to database");
            e.printStackTrace();
        }
    }

    public static boolean isConnected() {
        if (connection != null)
            try {
                return !connection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    public static boolean isConected() {
        return connection != null;
    }

    public static Connection getConnection() {
        if (MySQLConnector.isConected()) {
            return connection;
        }
        return null;
    }
}