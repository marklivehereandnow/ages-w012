package com.livehereandnow.ages.biz;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author mark
 */
public class Server {

    static Connection conn;
    static Statement st;

    public GameLive getGameLive() {
        GameLive gl = new GameLive();
        conn = getConnection();
        try {
            String sql = "SELECT * FROM GAME_LIVE WHERE ID=1";
            st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("checking result...SELECT * FROM GAME_LIVE");
            while (rs.next()) {
                gl.setId(rs.getInt("ID"));
                gl.setCardRow(rs.getString("CARD_ROW"));
                gl.setP1Hand(rs.getString("P1_HAND"));
                gl.setP2Hand(rs.getString("P2_HAND"));
                System.out.println(" " + gl.toString());
            }
            conn.close();
            return gl;
        } catch (Exception ex) {
            System.out.println("fail to query...");
            System.out.println(ex.toString());
            ex.printStackTrace();
        } finally {
        }
        return null;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动  
            conn = DriverManager.getConnection(
                    "jdbc:mysql://2nd2go.org:3306/AGES", "max", "Taipei2014");// 创建数据连接  
        } catch (Exception e) {
            System.out.println("数据库连接失败" + e.getMessage());
        }
        return conn; //返回所建立的数据库连接  
    }
}
