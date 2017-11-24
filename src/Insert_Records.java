
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Arrays ;
import java.lang.Object;

import org.omg.PortableInterceptor.SUCCESSFUL;

public class Insert_Records {
	private static final boolean DEBUG=false;
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
				cs.registerOutParameter(++i, java.sql.Types.NVARCHAR);
			}
			rs = cs.executeQuery();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}


	/*
		public static void input_data() throws IOException, SQLException {

			List<String> files = new ArrayList<String>();

			files.add("Horses.txt");
			files.add("Trainer.txt");
			files.add("RaceTypes.txt");
			files.add("Horses.txt");
			files.add("Breeders.txt");
			files.add("Races.txt");
			files.add("Owners.txt");
			files.add("Meetings.txt");
			files.add("Jockeys.txt");
			files.add("HorsesSex.txt");
			files.add("Fanilies.txt");
			files.add("RacesDistances.txt");
			files.add("HorsesColor.txt");


			CallableStatement cstmt = null;
			cstmt = conn.prepareCall ("{call dbo.GetManager(?, ?)}");



			ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file, Charset.defaultCharset());
			ArrayList<String []> columns = new ArrayList<>();	


			for(String line : lines){
			    columns.add(line.split("  "));
			}

			for(String [] s : columns){ 
			    System.out.println(Arrays.toString(s));
			}



		//	cstmt.setInt(1, x);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.execute();
			System.out.println("MANAGER ID: " + cstmt.getInt(2));
			cstmt.close();


		}

		/*
		public static String unEscapeString(String s){
		    StringBuilder sb = new StringBuilder();
		    for (int i=0; i<s.length(); i++)
		        switch (s.charAt(i)){
		            case '\n': sb.append("\\n"); break;
		            case '\t': sb.append("\\t"); break;
		            // ... rest of escape characters
		            default: sb.append(s.charAt(i));
		        }
		    return sb.toString();
		}
	 */


	public static void  insertData(String File,String storProcName ) throws SQLException, ParseException{
		CallableStatement cstmt = null;
		PreparedStatement smt= null;
		FileParser Fp = null;

		String records = Fp.getFileContentAsString(File);
		// System.out.print(unEscapeString(records));

		//	 System.out.println(records);
		ArrayList<ArrayList<String>> storeProcData = new ArrayList<ArrayList<String>>() ;

		/*On each /n character split it to a new vector*/
		ArrayList<String> oneLine  = new ArrayList<String>();  
		StringBuilder oneRecord = new StringBuilder();

		for (int i =0; i <= records.length()-1 ; ++i){
			/*On each /n character split it to a new vector*/
			if ( '\n' == records.charAt(i) ){
				oneLine.add(oneRecord.toString());
				ArrayList<String> newLine = new ArrayList<String>(oneLine);
				storeProcData.add(newLine);
				oneLine.clear();
				oneRecord.setLength(0);
			}
			else if ( '\t' == records.charAt(i)  ){
				oneLine.add(oneRecord.toString());
				oneRecord.setLength(0);
			}
			else{
				/*On each /t character split it to a new line*/
				oneRecord.append(records.charAt(i));
			}

		}
		storeProcData.remove(0);
		//System.out.print(storeProcData);


		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		if(DEBUG){
			for (int i = 0 ;i <= storeProcData.size()-1; i++){
				for (int j = 0 ;j <= storeProcData.get(0).size()-1; j++){
					System.out.print(storeProcData.get(i).get(j));
					System.out.print(" ");
				}
				System.out.print("\n");
			}
		}
	}


	public  void  HorsesInsertion(ArrayList<ArrayList<String>> storeProcData,CallableStatement cstmt) throws SQLException, ParseException{
		int k=0;
		int j=0;
		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID INT
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);
			else
				cstmt.setInt(++k,Integer.parseInt(storeProcData.get(i).get(j)));

			j++;

			//NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, (String)storeProcData.get(i).get(j));
			j++;

			//SEX STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//COLOR NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k,storeProcData.get(i).get(j));
			j++;

			//DATE OF BIRTH DATE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DATE);
			else{
				//	System.out.println(storeProcData.get(i).get(j));
				DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
				java.util.Date startDate = df.parse((storeProcData.get(i).get(j)));
				java.sql.Date sDate = convertUtilToSql(startDate);
				cstmt.setDate(++k, sDate);
			}
			j++;

			//WEIGHT FLOAT 
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);
			else
				cstmt.setFloat(++k, Float.parseFloat((String)storeProcData.get(i).get(j)));
			j++;

			//TRAINER ID CHAR
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.CHAR);
			else
				cstmt.setString(++k,storeProcData.get(i).get(j));
			j++;

			//ONWER ID CHAR
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.CHAR);
			else
				cstmt.setString(++k,storeProcData.get(i).get(j));
			j++;

			//BREEDER ID CHAR
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.CHAR);
			else
				cstmt.setString(++k,storeProcData.get(i).get(j));
			j++;

			//DAD ID
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//MOTHER ID 
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));

			cstmt.execute();

			k=0;
			j=0;
		}

		cstmt.close();

	}

	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	public static String unEscapeString(String s){
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i++)
			switch (s.charAt(i)){
			case '\n': sb.append("\\n"); break;
			case '\t': sb.append("\\t"); break;
			// ... rest of escape characters
			default: sb.append(s.charAt(i));
			}
		return sb.toString();
	}


	public void closeConnection(Connection conn){
		try {
			if (!conn.isClosed()) {
				System.out.print("Disconnecting from database...");
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("Cannot Close connection");

		}
	}	



	public static void main(String[] args) throws IOException, SQLException, ParseException {

		Vector<Vector<String>> Table = new Vector<Vector<String>>();

		String HorseStorProcVal= "{call dbo.horseInsert(?,?,?,?,?,?,?,?,?,?,?)}";
		String HorseStorProcVal1= "{call dbo.horseInsert(?,?,?)}";

		Insert_Records anObj = new Insert_Records();
		conn = anObj.getDBConnection();

		if (conn == null) {
			return;
		}

		insertData("HorsesMod.txt",HorseStorProcVal);

	}


}
