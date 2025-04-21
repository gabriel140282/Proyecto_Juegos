package org.example.db;

import java.sql.*;

public class DBManager {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver no encontrado", e);
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/juegos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection conn;

    public DBManager() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
    }

    public void saveHanoi(int n, int movimientos) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO t_hanoi(n,movimientos) VALUES(?,?)");
        ps.setInt(1,n);
        ps.setInt(2,movimientos);
        ps.executeUpdate();
    }

    public void saveQueens(int n, int soluciones, int operaciones) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO t_reinas(n,soluciones,operaciones) VALUES(?,?,?)");
        ps.setInt(1,n);
        ps.setInt(2,soluciones);
        ps.setInt(3,operaciones);
        ps.executeUpdate();
    }

    public void saveKnight(int d, int movimientos, int reintentos) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO t_knight(d,movimientos,reintentos) VALUES(?,?,?)");
        ps.setInt(1,d);
        ps.setInt(2,movimientos);
        ps.setInt(3,reintentos);
        ps.executeUpdate();
    }
}