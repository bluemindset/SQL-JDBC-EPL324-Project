
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
		//String dbConnString = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";

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





	public static void  insert() throws SQLException, ParseException{

		/*HORSES*/
		String fileHorses = "DataTables/Horses.txt";
		String recordsHorses = FileParser.getFileContentAsString(fileHorses);
		String HorseStorProcVal= "{call horseInsert(?,?,?,?,?,?,?,?,?,?,?)}";
		HorsesInsertion(insertData(recordsHorses), HorseStorProcVal);

		/*TRAINERS*/
		String fileTrainers="DataTables/Trainer.txt";
		String recordsTrainers = FileParser.getFileContentAsString(fileTrainers);
		String TrainersStorProcVal= "{call dbo.trainersInsert(?,?,?)}";
		TrainersInsertion(insertData(recordsTrainers), TrainersStorProcVal);


		/*BREEDERS*/
		String fileBreeders="DataTables/Breeders.txt";
		String recordsBreeders = FileParser.getFileContentAsString(fileBreeders);
		String BreedersStorProcVal= "{call dbo.breedersInsert(?,?,?)}";
		BreedersInsertion(insertData(recordsBreeders), BreedersStorProcVal);


		/*RACES*/
		String fileRaces="DataTables/Races.txt";
		String recordsRaces = FileParser.getFileContentAsString(fileRaces);
		String RacesStorProcVal= "{call dbo.racesInsert(?,?,?,?,?,?,?,?,?)}";
		RacesInsertion(insertData(recordsRaces), RacesStorProcVal);


		/*OWNERS*/
		String fileOwners="DataTables/Owners.txt";
		String recordsOwners = FileParser.getFileContentAsString(fileOwners);
		String OwnersStorProcVal= "{call dbo.onwersInsert(?,?,?,?,?,?,?)}";
		onwersInsertion(insertData(recordsOwners), OwnersStorProcVal);



		/*MEETINGS*/
		String fileMeetings = "DataTables/Meetings.txt";
		String MeetingsStorProcVal= "{call dbo.meetingsInsert(?)}";
		String recordsMeetings = FileParser.getFileContentAsString(fileMeetings);
		meetingsInsertion(insertData(recordsMeetings), MeetingsStorProcVal);



		/*JOCKEYS*/
		String fileJockeys = "DataTables/Jockeys.txt";
		String recordsJockeys = FileParser.getFileContentAsString(fileJockeys);
		String JockeysStorProcVal = "{call dbo.jockeysInsert(?,?,?)}";
		jockeysInsertion(insertData(recordsJockeys), JockeysStorProcVal);


		/*HORSE SEX*/	
		String fileHorsesSex="DataTables/HorsesSex.txt";
		String HorsesSexStorProcVal= "{call dbo.horseSexInsert(?)}";
		String recordsHorsesSex = FileParser.getFileContentAsString(fileHorsesSex);
		horseSexInsertion(insertData(recordsHorsesSex), HorsesSexStorProcVal);


		/*FAMILIES*/
		String fileFamilies = "DataTables/Families.txt";
		String recordsFamilies = FileParser.getFileContentAsString(fileFamilies);
		String FamiliesStorProcVal= "{call dbo.familyHorseInsert(?,?)}";
		familyInsertion(insertData(recordsFamilies), FamiliesStorProcVal);


		/*RACE DISTANCE*/
		String fileRacesDistances = "DataTables/RacesDistances.txt";
		String recordsRaceDistances = FileParser.getFileContentAsString(fileRacesDistances);
		String RaceDistancesStorProcVal= "{call dbo.raceDistancesInsert(?)}";
		racesDistanceInsertion(insertData(recordsRaceDistances), RaceDistancesStorProcVal);

		/*HORSE COLOR*/
		String fileHorsesColor = "DataTables/HorsesColors.txt";
		String recordsHorseColor = FileParser.getFileContentAsString(fileHorsesColor);
		String HorseColorStorProcVal= "{call dbo.horseColorInsert(?)}";
		horseColorInsertion(insertData(recordsHorseColor), HorseColorStorProcVal);


		/*RACE RESULT*/
		String fileRacesResults = "DataTables/RacesResults.txt";
		String recordsRaceResults = FileParser.getFileContentAsString(fileRacesResults);
		String RaceResultsStorProcVal= "{call dbo.RaceResultsInsert(?,?,?,?,?,?,?)}";
		racesResultInsertion(insertData(recordsRaceResults ), RaceResultsStorProcVal);



		/*--RACES TYPES --*/
		String fileRacesTypes = "DataTables/RacesTypes.txt";
		String RaceTypesStorProcVal = "{call dbo.raceTypesInsert(?)}";
		String recordsRaceTypes = FileParser.getFileContentAsString(fileRacesTypes);
		raceTypesInsertion(insertData(recordsRaceTypes), RaceTypesStorProcVal);



		/*RACE FIELD TYPE*/
		String fileRacesFields = "DataTables/RacesFieldTypes.txt";
		String RaceFieldStorProcVal = "{call dbo.raceFieldInsert(?)}";
		String recordsRaceField = FileParser.getFileContentAsString(fileRacesFields);
		raceFieldInsertion(insertData(recordsRaceField), RaceFieldStorProcVal);



	}
	public static ArrayList<ArrayList<String>>insertData(String  records) throws SQLException, ParseException{


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


		if(DEBUG){
			for (int i = 0 ;i <= storeProcData.size()-1; i++){
				for (int j = 0 ;j <= storeProcData.get(0).size()-1; j++){
					System.out.print(storeProcData.get(i).get(j));
					System.out.print(" ");
				}
				System.out.print("\n");
			}
		}
		return storeProcData;


	}


	
	public static void 	raceFieldInsertion (ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){
			
			//RACE FIELD
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}

	
	public static void 	raceTypesInsertion (ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){
			
			//RACE TYPE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}

	

	


	public static void 	racesResultInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//MEETING DATE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DATE);
			else{
				DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
				java.util.Date jDate = df.parse((storeProcData.get(i).get(j)));
				java.sql.Date sDate = convertUtilToSql(jDate);
				cstmt.setDate(++k, sDate);
			}
			j++;

			//RACE TIME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.TIME);	
			else{
				DateFormat time = new SimpleDateFormat("HH:mm:ss");
				java.util.Date jDate = time.parse((storeProcData.get(i).get(j)));
				java.sql.Time sDate = convertUtilToSqlTime(jDate);
				cstmt.setTime(++k, sDate);	
			}
			j++;

			//HORSE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//JOCKEY
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//TRAINER
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//START POS
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DECIMAL);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//FINISH POS
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DECIMAL);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			

			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}

	
	
	
	
	
	
	
	
	public static void 	horseColorInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){
			
			//HORSECOLOR
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}

	
	
	
	
	public static void 	racesDistanceInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){
			
			//DISTANCE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			


			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}
	
	
	public static void 	horseSexInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){
			
			//HORSESEX
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}


	public static void 	jockeysInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//FIRSTNAME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//SURNAME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}





	public static void 	meetingsInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}
		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//DATE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DATE);
			else{
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date jDate = df.parse((storeProcData.get(i).get(j)));
				java.sql.Date sDate = convertUtilToSql(jDate);
				cstmt.setDate(++k, sDate);
			}

			cstmt.execute();
			k=0;

		}
		cstmt.close();
	}


	public static void 	onwersInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//FIRSTNAME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//SURNAME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//TITLE	
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//UNIFORM
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//OWNER FAMILY ***SOS***NOT INCLUDED
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));

			//FAMILY ID ***SOS*** APLOS COUNTER
			cstmt.setInt(++k,i);

			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}


	public static void 	RacesInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//DATE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DATE);
			else{
				DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
				java.util.Date jDate = df.parse((storeProcData.get(i).get(j)));
				java.sql.Date sDate = convertUtilToSql(jDate);
				cstmt.setDate(++k, sDate);
			}
			j++;

			//TIME
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.TIME);	
			else{
				DateFormat time = new SimpleDateFormat("HH:mm:ss");
				java.util.Date jDate = time.parse((storeProcData.get(i).get(j)));
				java.sql.Time sDate = convertUtilToSqlTime(jDate);
				cstmt.setTime(++k, sDate);	
			}
			j++;

			//TYPE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//FIELD
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//DISTANCE
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.INTEGER);	
			else
				cstmt.setInt(++k, Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//PRIZE 1
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DECIMAL);	
			else
				cstmt.setDouble(++k, Double.parseDouble(storeProcData.get(i).get(j)));
			j++;

			//PRIZE 2
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DECIMAL);	
			else
				cstmt.setDouble(++k, Double.parseDouble(storeProcData.get(i).get(j)));
			j++;

			//PRIZE 3
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.DECIMAL);	
			else
				cstmt.setDouble(++k, Double.parseDouble(storeProcData.get(i).get(j)));


			//MEETING ID ***SOS*** APLOS COUNTER
			cstmt.setInt(++k,i);

			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}


	public static void 	BreedersInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID INT
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(k,java.sql.Types.INTEGER);
			else
				cstmt.setInt(++k,Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//FIRST NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//LAST NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));

			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}


	public static void 	TrainersInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID INT
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(k,java.sql.Types.INTEGER);
			else
				cstmt.setInt(++k,Integer.parseInt(storeProcData.get(i).get(j)));
			j++;

			//FIRST NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));
			j++;

			//LAST NAME STRING
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);	
			else
				cstmt.setString(++k, storeProcData.get(i).get(j));

			cstmt.execute();
			k=0;
			j=0;

		}
		cstmt.close();
	}


	public static void 	familyInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

		for (int i = 0 ;i <= storeProcData.size()-1; i++){

			//ID INT
			cstmt.setInt(++k,i);
			
			
			//FAMILY NAME 	
			if(	(storeProcData.get(i).get(j).equals("")))
				cstmt.setNull(++k,java.sql.Types.NCHAR);
			else
				cstmt.setString(++k,storeProcData.get(i).get(j));


			cstmt.execute();
			k=0;
			

		}
		cstmt.close();
	}


	public static void  HorsesInsertion(ArrayList<ArrayList<String>> storeProcData,String storProcName) throws SQLException, ParseException{
		int k=0;
		int j=0;


		CallableStatement cstmt = null;
		//	Prepare Call for StoreProcedure
		try{
			cstmt = conn.prepareCall (storProcName);
		}catch(Exception e){
			e.printStackTrace();
		}

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

	private static java.sql.Time convertUtilToSqlTime(java.util.Date uDate) {
		java.sql.Time tDate = new java.sql.Time(uDate.getTime());
		return tDate;
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


		Insert_Records anObj = new Insert_Records();
		conn = anObj.getDBConnection();

		if (conn == null) {
			return;
		}

		insert();

	}


}
