package com.horses.ui;

import com.horses.dbmanage.Config;
import com.horses.dbmanage.RecordInserter;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    public ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private CallableStatement ctsmt;

    private boolean connectedToDatabase = false;

    private void SetDatabaseURL() throws SQLException {
        try {
            connection = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD);
        } catch (SQLException sex) {
        	sex.printStackTrace();
            System.err.println(sex.getMessage());
        }
    }

    public ResultSetTableModel(String query) throws SQLException {
    	this();
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }
    
    private  ResultSetTableModel() throws SQLException {
        SetDatabaseURL();
        connection = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }
    }

    /**
     * FOR QUERY 1
     * @param callableStatmentString
     * @param horseName
     * @throws SQLException
     */
    public ResultSetTableModel(String callableStatmentString, String horseName) throws SQLException {
    	this();
    	CallableStatement cstmt = connection.prepareCall(callableStatmentString, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	cstmt.setString(1, horseName);
    	resultSet = cstmt.executeQuery();
    	metaData = resultSet.getMetaData();
    	resultSet.last();
    	numberOfRows = resultSet.getRow();
    	fireTableStructureChanged();
    }
    
    /**
     * FOR QUERY 2
     * @param cstmtString
     * @param date1
     * @throws SQLException
     */
    public ResultSetTableModel(String cstmtString, Date date1) throws SQLException{
    	this();
    	CallableStatement cstmt = connection.prepareCall(cstmtString, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	cstmt.setDate(1, new java.sql.Date(date1.getTime()));
    	resultSet = cstmt.executeQuery();
    	metaData = resultSet.getMetaData();
    	resultSet.last();
    	numberOfRows = resultSet.getRow();
    	fireTableStructureChanged();
	}

    /**
     * FOR QUERY 3
     * @param cstmtString
     * @param minimum
     * @param maximum
     * @throws SQLException
     */
	public ResultSetTableModel(String cstmtString, int minimum, int maximum) throws SQLException{
    	this();
    	CallableStatement cstmt = connection.prepareCall(cstmtString, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	cstmt.setInt(1, minimum);
    	cstmt.setInt(2, maximum);
    	resultSet = cstmt.executeQuery();
    	metaData = resultSet.getMetaData();
    	resultSet.last();
    	numberOfRows = resultSet.getRow();
    	fireTableStructureChanged();
	}

	/**
	 * FOR QUERY 8
	 * @param cstmtString
	 * @param year
	 */
	public ResultSetTableModel(String cstmtString, int year)throws SQLException {
    	this();
    	CallableStatement cstmt = connection.prepareCall(cstmtString, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	cstmt.setInt(1, year);
    	resultSet = cstmt.executeQuery();
    	metaData = resultSet.getMetaData();
    	resultSet.last();
    	numberOfRows = resultSet.getRow();
    	fireTableStructureChanged();
	}

	
	
	/**
	 * FOR QUERY 9 C)
	 * @param cstmtString
	 * @param date
	 * @param time
	 */
	public ResultSetTableModel(String cstmtString, String date, String time) throws SQLException{
		this();
		
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat tf = new SimpleDateFormat("HH:mm:ss");
        Date dateRace;
        java.sql.Date dateInsert = null;
        java.sql.Time timeInsert = null;
        java.util.Date  runTime ;
        try {
            dateRace = df.parse(date);
            runTime = tf.parse(time);
            dateInsert = RecordInserter.convertUtilToSql(dateRace);
            timeInsert = RecordInserter.convertUtilToSqlTime(runTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
    	CallableStatement cstmt = connection.prepareCall(cstmtString, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    	cstmt.setDate(1, dateInsert);
    	cstmt.setTime(2, timeInsert);
//TODO
    	resultSet = cstmt.executeQuery();
    	metaData = resultSet.getMetaData();
    	resultSet.last();
    	numberOfRows = resultSet.getRow();
    	fireTableStructureChanged();
	}

	
	@Override
    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (ClassNotFoundException | SQLException ex) {
        	ex.printStackTrace();
            System.err.println(ex.getMessage());
        }

        return Object.class;
    }

    @Override
    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return metaData.getColumnCount();
        } catch (SQLException sex) {
        	sex.printStackTrace();
            System.out.println(sex.getMessage());
        }

        return 0;
    }

    @Override
    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException sex) {
        	sex.printStackTrace();
            System.out.println(sex.getMessage());
        }

        return "";
    }

    @Override
    public int getRowCount() throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    }

    @Override
    public Object getValueAt(int row, int column)
            throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (SQLException sex) {
        	sex.printStackTrace();
            System.out.println(sex.getMessage());
        }

        return "";
    }

    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException sex) {
            	sex.printStackTrace();
                System.out.println(sex.getMessage());
            } finally {
                connectedToDatabase = false;
            }
        }
    }
}