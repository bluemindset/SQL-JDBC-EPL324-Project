package com.horses.dbobjects;

/**
 * Created by tomis on 26/11/2017.
 */
public enum SystemUserRole {
    SA ("SA"),
    R1 ("R1");

    private final String roleName;

    SystemUserRole(String roleName) {
        this.roleName = roleName;
    }

    public boolean equalsRole(String otherRole) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return roleName.equals(otherRole);
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
