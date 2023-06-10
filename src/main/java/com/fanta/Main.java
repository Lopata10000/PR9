package com.fanta;


import com.fanta.userinterface.Menu;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Menu.menu();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}