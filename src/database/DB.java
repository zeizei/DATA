package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DB {
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/nba";
	private String dbUser = "root";
	private String dbPass = "1234";
	private Connection connection;
	private Statement statement;
	private static DB db = null;

	public static DB getInstance() {
		synchronized (DB.class) {
			if (db == null) {
				db = new DB();
			}
		}
		return db;
	}

	private DB() {
		this.connection = this.getConn();
		try {
			this.statement = (Statement) this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return conn;
	} // 连接数据库

	public void update(String sql) {
		try {
			this.statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}// 更新表格

	public ResultSet find(String sql) {
		try {
			ResultSet result = this.statement.executeQuery(sql);
			return result;// 得到符合条件的集合
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			return null;// 数据库连接错误
		}// 根据表格名称和语句查找
	}
}
