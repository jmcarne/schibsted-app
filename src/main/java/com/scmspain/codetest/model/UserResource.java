package com.scmspain.codetest.model;

/**
 * Created by josep.carne on 12/02/2017.
 */
public class UserResource {
    private final String code;
    private final String name;
    private final String surname;
    private final String password;
    private final String role;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public UserResource(String code, String name, String surname, String password, String role) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
    }

    public UserResource() {
        this.code = null;
        this.name = null;
        this.surname = null;
        this.password = null;
        this.role = null;
    }
}
