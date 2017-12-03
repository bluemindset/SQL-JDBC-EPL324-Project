package com.horses.dbmanage;

public class Config {

	 	public static final String DATABASE_NAME = "ntzior01";
	    public static final String DATABASE_SERVER = "apollo.in.cs.ucy.ac.cy:1433";
	    public static final String DATABASE_USER_ID = "ntzior01";
	    public static final String DATABASE_PASSWORD = "swuYezukA6RE";
//
//    public static final String DATABASE_NAME = "Horses";
//    public static final String DATABASE_SERVER = "localhost:1433";
//    public static final String DATABASE_USER_ID = "SA";
//    public static final String DATABASE_PASSWORD = "Password123";

    public static final String connection_url = "jdbc:sqlserver://" + DATABASE_SERVER + ";databaseName=" + DATABASE_NAME + ";user="+DATABASE_USER_ID+";password="+DATABASE_PASSWORD+";";


}
