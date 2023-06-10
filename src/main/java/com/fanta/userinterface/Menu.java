package com.fanta.userinterface;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

  public static void menu() throws SQLException {

    UserService userService = new UserService();

    int mainMenu;
    Scanner mainInput = new Scanner(System.in);
    do{
      System.out.println("1. Add");
      System.out.println("2. Edit");
      System.out.println("3. Read");
      System.out.println("4. Delete");
      System.out.println("5. Search by ID");
      System.out.println("6. Exit");

      mainMenu = mainInput.nextInt();
      switch (mainMenu){
        case 1:
          userService.addActor();
          break;
        case 2:
          userService.updateActor();
          break;
        case 3:
          userService.listActors();
          break;
        case 4:
          userService.deleteActor();
          break;
        case 5:
          userService.searchActorById();
          break;
        case 6:
          System.exit(6);
          break;
      }
      if(mainMenu > 5){
        System.out.println();
        System.out.println("Введено хибні дані");
      }
    }while (mainMenu != 5);
  }

}

