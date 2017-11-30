package com.horses.ui;

import java.sql.*;
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

    public ResultSetTableModel(String callableStatmentString, String horseName) throws SQLException {
    	this();
    	try(CallableStatement cstmt = connection.prepareCall("{call selectHorsesLike(?)}")){
    		cstmt.setString(1, horseName);
    		resultSet = cstmt.executeQuery();
    		metaData = resultSet.getMetaData();
    		resultSet.last();
    		numberOfRows = resultSet.getRow();
    		fireTableStructureChanged();
    	};
    			

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