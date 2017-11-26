package com.horses.dbobjects;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by tomis on 26/11/2017.
 */
public class SystemUser {
    private String id;
    private static final String STORED_PROCEDURE_NAME_SA = "{call insertSystemAdministrator(?,?,?,?)}";
    private static final String STORED_PROCEDURE_NAME_R1 = "{call insertR1User(?,?,?,?)}";

    public String getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getPassword() { return password; }

    public SystemUserRole getRole() { return role; }


    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private SystemUserRole role;

    public SystemUser(String id, String username, String password, String firstName,String lastName, SystemUserRole role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public CallableStatement getCallableStatement(Connection conn) throws SQLException {
        String storProcName = getRole() == SystemUserRole.SA ? STORED_PROCEDURE_NAME_SA : STORED_PROCEDURE_NAME_R1;

        CallableStatement cstmt = null;
        cstmt = conn.prepareCall(storProcName);
        cstmt.setString(1, getId());
        cstmt.setString(2, getUsername());
        cstmt.setString(3, getFirstName());
        cstmt.setString(4, getLastName());

        return cstmt;
    }
}

