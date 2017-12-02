package com.horses.ui;

import com.horses.dbmanage.Config;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tomis on 02/12/2017.
 */
public class CurrentUserData {
    public static String userid = Config.DATABASE_USER_ID;

    public static void setCurUserid (String userid ) {
        CurrentUserData.userid = userid;
    }
    public static String getCurUserid () {
        return CurrentUserData.userid;
    }

    public static void executeSetUserId() {
        try {
            try(CallableStatement cstmt = DriverManager.getConnection(Config.connection_url, Config.DATABASE_USER_ID, Config.DATABASE_PASSWORD).prepareCall("{call setCurUserId(?)}")) {
                cstmt.setString(1, CurrentUserData.getCurUserid());
                cstmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
