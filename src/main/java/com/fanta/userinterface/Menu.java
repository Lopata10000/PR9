package com.fanta.userinterface;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";


  public static void menu() throws SQLException {

    UserService userService = new UserService();

    int mainMenu;
    Scanner mainInput = new Scanner(System.in);
    do{
      System.out.println("\uD83C\uDD73\uD83C\uDD70\uD83C\uDD83\uD83C\uDD70 \uD83C\uDD71\uD83C\uDD70\uD83C\uDD82\uD83C\uDD74\n");
      System.out.println(ANSI_BLUE + "1. Add" + ANSI_RESET);
      System.out.println(ANSI_YELLOW + "2. Edit" + ANSI_RESET);
      System.out.println(ANSI_BLUE + "3. Read" + ANSI_RESET);
      System.out.println(ANSI_YELLOW + "4. Delete" + ANSI_RESET);
      System.out.println(ANSI_BLUE + "5. Search by ID" + ANSI_RESET);
      System.out.println(ANSI_YELLOW + "6. Exit" + ANSI_RESET);

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

