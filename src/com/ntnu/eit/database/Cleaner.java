package hist.drtablet.database;

/* Class offering methods for safely closing connections to database */
import java.sql.*;

public class Cleaner {
	/* Close resultset */

	public static void closeResultSet(ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			writeMessage(e, "closeResultSet()");
		}
	}

	/* Close statement */
	public static void closeStatement(Statement stm) {
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			writeMessage(e, "closeStatement()");
		}
	}

	/* Close prepared statement */
	public static void closePreparedStatement(PreparedStatement prpstm) {
		try {
			if (prpstm != null) {
				prpstm.close();
			}
		} catch (SQLException e) {
			writeMessage(e, "closePreparedStatement()");
		}
	}

	/* Close database connection */
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			writeMessage(e, "closeConnection()");
		}
	}

	/* Rollback commits */
	public static void rollback(Connection con) {
		try {
			if (con != null && !con.getAutoCommit()) {
				con.rollback();
			}
		} catch (SQLException e) {
			writeMessage(e, "rollback()");
		}
	}

	/* Set autocommit to true */
	public static void setAutoCommit(Connection con) {
		try {
			if (con != null && !con.getAutoCommit()) {
				con.setAutoCommit(true);
			}
		} catch (SQLException e) {
			writeMessage(e, "setAutoCommit()");
		}
	}

	/* Prints exceptions in log */
	public static void writeMessage(Exception e, String message) {
		System.err.println("*** Error occured: " + message + ". ***");
		e.printStackTrace(System.err);
	}
}