package com.horses.dbmanage;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Default {
	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;
	// handling the keyboard inputs through a BufferedReader
	// This variable became global for your convenience.
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * A method that returns a connection to MS SQL server DB
	 *
	 * @return The connection object to be used.
	 */
	private Connection getDBConnection() {
		String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName=sioann12;user=sioann12;password=cRaVasPa6uhu;";
		

		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot load DB driver!");
				return null;
			}

		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);
			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * A method that selects columns Col1 and Col2 from SAMPLE table and prints all rows
	 */
	public void printSampleTable() {
		ResultSet rs = null;

		// Query to be executed.
		String query = "SELECT Col1, Col2 FROM HO";
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				// retrieve the columns in any order
				double col2 = rs.getDouble("Col2");
				String col1 = rs.getString("Col1");
				// printing out a row
				System.out.println(col1 + "," + col2);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method finds all records from table ANOTHER_SAMPLE which were made in a specific year It uses a user defined
	 * function to extract the year from a datetime field Sample QUERY: SELECT * FROM ANOTHER_SAMPLE WHERE
	 * dbo.myUserDefinedFunction('year', DateMade) = someYear
	 */
	public void printAnotherSampleTable() {
		ResultSet rs = null;

		// Query to be executed.
		String query = " SELECT * FROM ANOTHER_SAMPLE WHERE dbo.myUserDefinedFunction('year', yearMade)='";

		try {
			System.out.print("\tEnter year:");
			String fyear = in.readLine();
			query += fyear + ";";

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);

			// executing the query
			rs = stmt.executeQuery(query);

			// iterating through the resultset
			while (rs.next()) {
				// retrieve the columns in any order
				String partId = rs.getString("PartID");
				Time dateMade = rs.getTime("DateMade");
				double price = rs.getDouble("Price");

				// printing out a row
				System.out.println(partId + "," + dateMade + "," + price);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 */
	/**
	 * A generic method that returns a READ ONLY ResultSet containing some rows of a table
	 *
	 * @param sqlTable
	 *            The name of the database table to read (used in SQL FROM clause)
	 * @param sqlWhereClause
	 *            The conditions to be used in the SQL WHERE clause
	 * @return A {@link ResultSet} object containing the results of the executed query
	 */
	public ResultSet getReadOnlyRecordSet(String sqlTable, String sqlWhereClause) {
		ResultSet rs = null;

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			rs = stmt.executeQuery("SELECT * FROM " + sqlTable + " WHERE " + sqlWhereClause);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * A methods that counts the records stored in a table
	 *
	 * @param sqlTable
	 *            The name of the database table to read (used in SQL FROM clause)
	 * @return The number of records stored in the given table
	 */
	public int countDBTableRecords(String sqlTable) {
		ResultSet rs = null;
		int recCount = 0;

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			rs = stmt.executeQuery("SELECT COUNT(*) FROM " + sqlTable);
			if (rs.first())
				recCount = rs.getInt(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		return recCount;
	}

	/**
	 * A methods that deletes some records stored in a table
	 *
	 * @param sqlTable
	 *            The name of the database table to read (used in SQL FROM clause)
	 * @param sqlWhereClause
	 *            The conditions to be used in the SQL WHERE clause
	 * @return <code>true</code> if the deletion was successful, <code>false</code> otherwise
	 */
	public boolean deleteDBTableRecords(String sqlTable, String sqlWhereClause) {
		boolean success = true;

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DELETE FROM " + sqlTable + " WHERE " + sqlWhereClause);
		} catch (SQLException e) {
			success = false;
			System.out.println(e.getMessage());
		}
		return success;
	}

	/**
	 * A method that INSERTS a new row in the sample table (giving values to Col1 and Col2)
	 *
	 */
	public void insertSample() {
		// Query to be executed.
		String query = "INSERT INTO " + "SAMPLE (Col1, Col2) " + "VALUES ('Col1Value', 35694999);";
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.print("Got error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
	}

	/**
	 * A method that returns an empty updatable ResultSet
	 *
	 * @param sqlTable
	 *            The name of the database table to read (used in SQL FROM clause)
	 * @param sqlTableIDColumn
	 *            The key column of the database table
	 * @return An empty updatable {@link ResultSet} object for the given table
	 */
	public ResultSet getEmptyUpdatableRecordSet(String sqlTable, String sqlTableIDColumn) {
		ResultSet rs = null;

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			rs = stmt.executeQuery("SELECT * FROM " + sqlTable + " WHERE " + sqlTableIDColumn + " IS NULL");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * A method that executes the store procedure sample that has two IN and one OUT parameters
	 *
	 * @return A {@link ResultSet} object containing the results of the executed stored procedure
	 */
	public ResultSet executeSampleStoreProc() {
		ResultSet rs = null;

		try {
			CallableStatement cs = conn.prepareCall("{? = sample(?,?)}");
			cs.setString(1, "This is a nice day");
			cs.setLong(2, Long.parseLong("845632133"));
			cs.registerOutParameter(3, Types.BIGINT);

			rs = cs.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/**
	 * A generic method that executes a store procedure that may have IN and OUT parameters of String type
	 *
	 * @param storProcName
	 *            The name of the database stored procedure to call
	 * @param storeProcINParams
	 *            A {@link Vector} containing the values (as {@link String} objects) of all IN parameters for the stored
	 *            procedure to be call
	 * @param getOUTParameter
	 *            Indicates whether we the stored procedure has an OUT parameter
	 * @return A {@link ResultSet} object containing the results of the executed stored procedure
	 */
	public ResultSet executeStoreProc(String storProcName, Vector storeProcINParams, boolean getOUTParameter) {
		ResultSet rs = null;
		int i = 0;

		try {
			CallableStatement cs = conn.prepareCall(storProcName);
			// Note that storProcName value must follow the JDBC escape syntax
			// e.g. "{? = storProc(?,?)}"
			if (storeProcINParams != null) {
				for (Iterator iterator = storeProcINParams.iterator(); iterator.hasNext();) {
					cs.setString(++i, (String) iterator.next());
				}
			}
			if (getOUTParameter) {
				cs.registerOutParameter(++i, Types.NVARCHAR);
			}
			rs = cs.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}

	/* ========================================================================== */

	/**
	 * Reads the users choice given from the keyboard
	 *
	 * @return The read character
	 */
	public static int readChoice() {
		int input;
		// returns only if a correct value is given.
		do {
			System.out.print("Please make your choice: ");
			try {
				// read the integer, parse it and break.
				input = Integer.parseInt(in.readLine());
				break;
			} catch (Exception e) {
				System.out.println("Your input is invalid!");
				continue;
			}
		} while (true);
		return input;
	}

	/**
	 * Main Module
	 *
	 * @param args
	 *            A {@link String} array containing the command line arguments passed to the program
	 */
	public static void main(String[] args) {

		Default anObj = new Default();
		conn = anObj.getDBConnection();

		if (conn == null) {
			return;
		}
		System.out.println("WELCOME TO JDBC sample program ! \n\n");

		// keeps the main loop busy
		boolean keepon = true;

		while (keepon) {
			// These are sample SQL statements
			System.out.println("MAIN MENU");
			System.out.println("---------");
			System.out.println("1. Call method printSampleTable");
			System.out.println("2. Call method countDBTableRecords on table SAMPLE");
			System.out.println("2. Call method printAnotherSampleTable");
			System.out.println("4. Call method executeSampleStoreProc");
			System.out.println("0. < EXIT");

			switch (readChoice()) {
			case 1:
				anObj.printSampleTable();
				break;
			case 2:
				anObj.countDBTableRecords("SAMPLE");
				break;
			case 3:
				anObj.printAnotherSampleTable();
				break;
			case 4:
				anObj.executeSampleStoreProc();
				break;
			case 0:
				keepon = false;
				break;
			default:
				System.out.println("Unrecognized choice!");
				break;
			}
		}

		try {
			if (!conn.isClosed()) {
				System.out.print("Disconnecting from database...");
				conn.close();
				System.out.println("Done\n\nBye !");
			}
		} catch (Exception e) {
			// Ignore the error and continues
		}
	}
}
