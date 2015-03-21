package database;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibatis.common.jdbc.ScriptRunner;


/**
 * Class that provides connections to the database
 * to be used by this server
 * @author Lars Meijer
 */
public abstract class DatabaseHelper {

	//Strings for database user, host and password
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_USER = "root";
	private static final String DB_HOST = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "mydb";
	private static final String DB_PASS = "y2S96Fny";
	
	//Realative path to SQL create statements, created by mysql workbench
	private static final String CREATE_SQL_STATEMENTS = "createstatements.sql";
	
	//If true drops database scheme uNews to create a new one
	private static final boolean FORCE_DROP = false;

	/**
	 * Creates a database connection that can be used to execute queries
	 * @return a connection
	 * @throws SQLException When a problem occurs with connecting to a database
	 */
	public static Connection getConnection() throws SQLException {
		
		try {
			// Driver init
			Class.forName(DB_DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new SQLException("Failed to connect to a database, problem with JDBC driver");
		}

		//creating a connection with table  name (
		Connection connection = DriverManager.getConnection(DB_HOST + DB_NAME, DB_USER, DB_PASS);

		//checking if connection is valid
		if (connection == null || connection.isClosed() || connection.isReadOnly() || !connection.isValid(2)) {
			throw new SQLException("Failed to connect to a database, connection was invallid");
		}
		
		return connection;
	}

	
	/**
	 * Method that creates tables from a sql file
	 * SQL file can only contain drop and create tables
	 * Source http://www.mkyong.com/jdbc/how-to-run-a-mysql-script-using-java/ 
	 */
	public static void createDatabase() {

		try {
			// Create MySql Connection withoud table name
			Class.forName(DB_DRIVER).newInstance();
			Connection con = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
			
			if(FORCE_DROP) {
				con.createStatement().execute("DROP DATABASE `mydb`;");
			}
			
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);

			// Give the input file to Reader
			URL url = DatabaseHelper.class.getResource(CREATE_SQL_STATEMENTS);
			
			String ul = URLDecoder.decode(url.getPath(), "UTF-8");;
			System.out.println(ul);
			File file = new File(ul);
			
			Reader reader = new BufferedReader(new FileReader(file));

			// Exctute script
			sr.runScript(reader);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to create database");
		}
		
	}
	
	public static void reset() throws SQLException{
		getConnection().createStatement().execute("DROP DATABASE "+ DB_NAME);
		createDatabase();
	}
	
	/**
	 * create a default statement to execute SQL on
	 * With a default timeout of 3 seconds
	 * @return a statment to execute SQL on
	 * @throws SQLException when a connection to the database fails
	 */
	public static Statement createDefaultStatement() throws SQLException {
		Statement st = DatabaseHelper.getConnection().createStatement();
		st.setQueryTimeout(3);
		return st;
	}
}
