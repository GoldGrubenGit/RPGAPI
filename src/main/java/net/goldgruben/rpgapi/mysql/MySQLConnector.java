package net.goldgruben.rpgapi.mysql;

import net.goldgruben.rpgapi.Rpgapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {


        static Connection con;
        static String host;
        static String port;
        static String database;
        static String username;
        static String password;

        static {
            host = Rpgapi.getInstance().getConfig().getString("MySql.host");
            port = Rpgapi.getInstance().getConfig().getString("MySql.port");
            database = Rpgapi.getInstance().getConfig().getString("MySql.database");
            username = Rpgapi.getInstance().getConfig().getString("MySql.user");
            password = Rpgapi.getInstance().getConfig().getString("MySql.password");
        }

        public static void connect() {
            if (!MySQLConnector.isConected()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                    System.out.println("MySQL verbunden!");
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        public static void disconnect() {
            if (MySQLConnector.isConected()) {
                try {
                    con.close();
                    System.out.println("Mysql geschlossen!");
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public static boolean isConected() {
            return con != null;
        }

        public static Connection getConnection() {
            if (MySQLConnector.isConected()) {
                return con;
            }
            return null;
        }

}
