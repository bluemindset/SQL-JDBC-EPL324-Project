import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tomis on 26/11/2017.
 */
public class SchemaCreator {
    public void setCreateTablesFile(String createTablesFile) throws FileNotFoundException {
        this.createTablesReader = FileParser.getReaderForFile(createTablesFile);
    }

    public void setCreateForeignKeysFile(String createForeignKeysFile) throws FileNotFoundException {
        this.createForeignKeysReader = FileParser.getReaderForFile(createForeignKeysFile);
    }

    public void setCreateDomainsFile(String createDomainsFile) throws FileNotFoundException {
        this.createDomainsReader = FileParser.getReaderForFile(createDomainsFile);
    }

    public void setCreateTriggersFile(String createTriggersReader) throws FileNotFoundException {
        this.createTriggersReader = FileParser.getReaderForFile(createTriggersReader);
    }

    public void setCreateStoredProceduresDirectory(String createStoredProceduresDirectory) throws FileNotFoundException {
        this.createStoredProceduresDirectory = FileParser.getReaderForFile(createStoredProceduresDirectory);
    }

    public void setDropDbFile(String createStoredProceduresDirectory) throws FileNotFoundException {
        this.dropDbReader = FileParser.getReaderForFile(createStoredProceduresDirectory);
    }
    private Reader dropDbReader;
    private Reader createTablesReader;
    private Reader createForeignKeysReader;
    private Reader createDomainsReader;
    private Reader createTriggersReader;
    private Reader createStoredProceduresDirectory;



    private ConnectionManager connectionManager;
    private Connection conn;
    private ScriptRunner scriptRunner;

    SchemaCreator(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.conn = connectionManager.getDBConnection();
        scriptRunner = new ScriptRunner(conn,false,true);
        scriptRunner.setDelimiter("GO", false);
    }

    public void dropSchema() {
        dropDb();
    }

    void createSchema() {

        dropDb();
        createTables();
        createForeignKeys();
        createDomains();
        createTriggers();
        createStoredProcedures();

        connectionManager.closeConnection();

    }
    private void runScript(Reader reader) {
        try {
            scriptRunner.runScript(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void dropDb() {
        runScript(dropDbReader);
    }

    private void createTables() {
        runScript(createTablesReader);
    }
    private void createForeignKeys() {
        runScript(createForeignKeysReader);
    }

    private void createDomains() {
        runScript(createDomainsReader);
    }

    //TODO
    private void createTriggers() {
        runScript(createTriggersReader);
    }
    //TODO
    private void createStoredProcedures() {
    }

    public static void main(String args[]) throws FileNotFoundException {
        //final String DEFAULT_DB_CONNSTRING = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";
        final String DEFAULT_DB_CONNSTRING = "jdbc:sqlserver://localhost:1433;databaseName=Horses;user=SA;password=Password123;";
        SchemaCreator schemaCreator = new SchemaCreator(new ConnectionManager(DEFAULT_DB_CONNSTRING));

        schemaCreator.setDropDbFile("Tables/drop-db.sql");
        schemaCreator.setCreateTablesFile("Tables/create-tables.sql");
        schemaCreator.setCreateForeignKeysFile("Tables/create-foreign-keys.sql");
        schemaCreator.setCreateDomainsFile("Tables/create-domains.sql");
        schemaCreator.setCreateTriggersFile("Tables/create-triggers.sql");

        //schemaCreator.dropSchema();
        schemaCreator.createSchema();
    }
}
