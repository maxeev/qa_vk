package com.example.auth;

public class DefaultAuthService implements AuthService {
    @Override
    public String getLogin() {
        String login = System.getenv("LOGIN");
        if (login == null) {
            throw new IllegalStateException("env-переменная LOGIN не задана!");
        }
        return login;
    }

    @Override
    public String getPassword() {
        String password = System.getenv("PASSWORD");
        if (password == null) {
            throw new IllegalStateException("env-переменная PASSWORD не задана!");
        }
        return password;
    }
}