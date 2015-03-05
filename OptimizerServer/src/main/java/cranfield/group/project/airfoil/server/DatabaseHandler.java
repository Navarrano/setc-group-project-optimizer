//package cranfield.group.project.airfoil.server;
//
//import java.sql.*;
//import org.apache.commons.dbutils.DbUtils;
//
//
///**
// * The Class DatabaseHandler.
// * Handles the communication with the database
// *
// */
//public class DatabaseHandler {
//
//	/** Connection object allowing to instantiate a communication with the database. */
//	Connection connection;
//
//	/**
//	 * Database handler Constructor that create a connection to the MARS database.
//	 */
//	public DatabaseHandler(){
//		String url = "jdbc:mysql://173.194.80.4:3306/";
//		String dbName = "mars";
//		String driver = "com.mysql.jdbc.Driver";
//		String userName = "root";
//		String password = "1234";
//
//		try {
//			Class.forName(driver).newInstance();
//			connection = DriverManager.getConnection(url+dbName,userName,password);
//			System.out.println("Successful connection to DB");
//			//connection.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Determine if the user whose username is the function argument already
//	 * exists in the AstralUser table of the MARS database.
//	 *
//	 * @param username, the username which existence in the table is to be tested.
//	 * @return true, if the username is in the table. False, if it is not.
//	 */
//	public boolean existsUser(String username){
//		boolean existsUser = true;
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM AstralUsers WHERE login='" + username+"'");
//			resultSet.next();
//			int count = resultSet.getInt("ROWCOUNT");
//			if(count == 0)
//				existsUser = false;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return existsUser;
//	}
//
//	/**
//	 * Adds the new user whose username is the function argument in the AstralUser table of the database.
//	 *
//	 * @param username, the username of the user to be added in the database
//	 */
//	public void addNewUser(String username){
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			statement.executeUpdate("INSERT into AstralUsers (login) VALUES ('"+username+"')");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//		    DbUtils.closeQuietly(statement);
//		}
//	}
//
//	/**
//	 * Update user connection information of the user whose username is the function argument.
//	 * The update consists in increasing the number of times the user has connected to MARS and
//	 * to update the last connection time/date to the current one.
//	 * @param username, the username of the user the connection information has to be updated.
//	 */
//	public void updateUserConnectionInformation(String username){
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			statement.executeUpdate("UPDATE AstralUsers SET last_connection_date=NOW(), "
//					+ "nb_connections = nb_connections +1 "
//					+ "WHERE login='"+username+"'");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//		    DbUtils.closeQuietly(statement);
//		}
//	}
//
//	/**
//	 * Adds an event log to the event logs table of the database.
//	 * An event log consists in a message (description of the event that occurred), a log type (information, error) and
//	 * a log subject indicating the origin of the log.
//	 *
//	 * @param message the message describing the event that occurred
//	 * @param logType the log critical level
//	 * @param logSubject indicates the origin of the log
//	 */
//	public void addEventLog(String message, String logType, String logSubject){
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			statement.executeUpdate("INSERT into EventLogs (message, logType, logSubject) "
//					+ "VALUES ('"+message+"', '"+logType+"', '"+logSubject+"')");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//		    DbUtils.closeQuietly(statement);
//		}
//	}
//
//	/**
//	 * The main method.
//	 *
//	 * @param args the arguments
//	 */
//	public static void main(String[] args) {
//		DatabaseHandler databaseHandler = new DatabaseHandler();
//
//		databaseHandler.addEventLog("test", "error", "connection");
//	}
//}
//
