package main.java.backend;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class IOMysql {
    Connection connect;
    public IOMysql() throws SQLException {
        createConnection();
    }
    public void createConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                "jdbc:mysql://address=(protocol=tcp)(host=2402:800:61c3:f3ad:9422:cf86:201d:a01f)(port=3306)/myDictionary",
                "client",
                "CN@#2020btl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String removePHPScript(String s) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        while(true) {
            while(index <s.length() && s.charAt(index) == '<') {
                while(s.charAt(index) != '>') index ++;
                if(s.charAt(index - 1) == '/') result.append('\n');
                index++;
            }
            if(index >= s.length()) break;
            result.append(s.charAt(index));
            index ++;
        }
        return result.toString();
    }
    public void importOnline(DictionaryManagement dic) {
        try {
            Dictionary online = dic.getDictionary();
            Statement query = connect.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM english_to_vietnamese");
            while(result.next()) {
                String target = result.getString("word");
                String explain = removePHPScript(result.getString("detail"));
                Word newWord = new Word(target,explain);
                online.add(newWord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
