import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
    private static final String USERNAME = "cchadj01";
    private static final String PASSWORD = "whatever";

    /**
     * 1.Vale ta stored procedures manualy sto microsoft sql server.
     * (Vriskontai stored-procedures-other kai ston StroreProceduresInsert)
     * 2.Vale username kai password pou sou edwke o panchriss ( sto USERNAME kai PASSWORD tou app).
     * 3.Trekse tin main() tou APP.
     *
     */
    public static void main(String args[]) throws SQLException, ParseException, FileNotFoundException {
        String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName="+USERNAME+";user="+USERNAME+";password="+PASSWORD+";";
        //String dbConnString = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";
        //String dbConnString = "jdbc:sqlserver://localhost:1433;databaseName=Horses;user=SA;password=Password123;";
        ConnectionManager connectionManager = new ConnectionManager(dbConnString);

        SchemaCreator schemaCreator = new SchemaCreator(connectionManager);
        schemaCreator.setDropDbFile("Tables/drop-db.sql");
        schemaCreator.setCreateTablesFile("Tables/create-tables.sql");
        schemaCreator.setCreateForeignKeysFile("Tables/create-foreign-keys.sql");
        schemaCreator.setCreateDomainsFile("Tables/create-domains.sql");
        schemaCreator.setCreateTriggersFile("Tables/create-triggers.sql");
        schemaCreator.createSchema();

        RecordInserter recordInserter = new RecordInserter(connectionManager);
        recordInserter.insert();

        connectionManager.closeConnection();
    }
}
