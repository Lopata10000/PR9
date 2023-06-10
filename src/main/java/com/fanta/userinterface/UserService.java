package com.fanta.userinterface;

import com.fanta.entity.ActorEntity;
import com.fanta.services.ActorService;
import com.fanta.validate.BirthDateValidation;
import com.fanta.validate.DataValidation;
import com.fanta.validate.EmailValidation;
import com.fanta.validate.LastNameValidation;
import com.fanta.validate.NameValidation;
import com.fanta.validate.PasswordValidation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private ActorService actorService = new ActorService();


    DataValidation firstNameValidation = new NameValidation();
    DataValidation lastNameValidation = new LastNameValidation();
    DataValidation emailValidation = new EmailValidation();
    DataValidation passwordValidation = new PasswordValidation();
    DataValidation birthDateValidation = new BirthDateValidation();

    public void listActors() throws SQLException {
        List<ActorEntity> actors = actorService.getAllActors();
        if (actors.isEmpty()) {
            System.out.println("Акторів не знайдено! :(");
            Menu.menu();
            return;
        }
        for (ActorEntity actor : actors) {
            System.out.println("ID: " + actor.getId() + ", Ім'я: " + actor.getFirstName() + " " + actor.getLastName() + ", Емайл: " + actor.getEmail());
        }
    }

    public void addActor() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        ActorEntity actor = new ActorEntity();

        System.out.println("Введіть ім'я актора");
        actor.setFirstName(getValidatedActorInput(scanner, firstNameValidation, "Некоректне ім'я. Спробуйте ще раз:", actor, ActorEntity::setFirstName));

        System.out.println("Введіть прізвище актора");
        actor.setLastName(getValidatedActorInput(scanner, lastNameValidation, "Некоректне прізвище. Спробуйте ще раз:", actor, ActorEntity::setLastName));

        System.out.println("Ведіть електронну пошту");
        actor.setEmail(getValidatedActorInput(scanner, emailValidation, "Некоректний емейл. Спробуйте ще раз:", actor, ActorEntity::setEmail));

        System.out.println("Введіть пароль");
        actor.setPassword(getValidatedActorInput(scanner, passwordValidation, "Некоректний пароль. Спробуйте ще раз:", actor, ActorEntity::setPassword));

        System.out.println("Введіть дату народження (день-місяць-рік)");
        LocalDate birthDate = getValidatedActorDateInput(scanner, birthDateValidation, "Некоректна дата народження(dd.MM.yyyy). Спробуйте ще раз:", actor);
        actor.setBirthDate(birthDate);

        actorService.addNewActor(actor);
        System.out.println("Актора додано успішно!");
        Menu.menu();
    }

    public void updateActor() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть ID актора, якого потрібно оновити:");
        int actorId = scanner.nextInt();
        scanner.nextLine(); // Читаємо решту рядка після nextInt()

        ActorEntity actor = actorService.getActorById(actorId);
        if (actor == null) {
            System.out.println("Актора з ID " + actorId + " не знайдено");
            Menu.menu();
            return;
        }

        System.out.println("Оновлення актора з ID " + actorId);

        System.out.println("Введіть нове ім'я актора");
        actor.setFirstName(getValidatedActorInput(scanner, firstNameValidation, "Некоректне ім'я. Спробуйте ще раз:", actor, ActorEntity::setFirstName));

        System.out.println("Введіть нове прізвище актора");
        actor.setLastName(getValidatedActorInput(scanner, lastNameValidation, "Некоректне прізвище. Спробуйте ще раз:", actor, ActorEntity::setLastName));

        System.out.println("Введіть нову електронну пошту");
        actor.setEmail(getValidatedActorInput(scanner, emailValidation, "Некоректний емейл. Спробуйте ще раз:", actor, ActorEntity::setEmail));

        System.out.println("Введіть новий пароль");
        actor.setPassword(getValidatedActorInput(scanner, passwordValidation, "Некоректний пароль. Спробуйте ще раз:", actor, ActorEntity::setPassword));

        System.out.println("Введіть нову дату народження (день-місяць-рік)");
        LocalDate birthDate = getValidatedActorDateInput(scanner, birthDateValidation, "Некоректна дата народження(dd.MM.yyyy). Спробуйте ще раз:", actor);
        actor.setBirthDate(birthDate);

        actorService.updateActorById(actorId, actor);
        System.out.println("Актора оновлено! :)");
        Menu.menu();
    }

    public void deleteActor() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть ID актора, якого потрібно видалити:");
        int actorId = scanner.nextInt();
        scanner.nextLine(); // Читаємо решту рядка після nextInt()

        ActorEntity actor = actorService.getActorById(actorId);
        if (actor == null) {
            System.out.println("Актора з ID " + actorId + " не знайдено!");
            Menu.menu();
            return;
        }

        System.out.println("Видалення актора з ID " + actorId);
        actorService.deleteActorById(actor.getId());
        System.out.println("Актора видалено! :)");
        Menu.menu();
    }

    public void searchActorById() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть ID актора:");
        int actorId = scanner.nextInt();
        scanner.nextLine();
        ActorEntity actor = actorService.getActorById(actorId);
        if (actor == null) {
            System.out.println("Актора з ID " + actorId + " не знайдено!");
        } else {
            System.out.println("ID: " + actor.getId() + ", Ім'я: " + actor.getFirstName() + " " + actor.getLastName() + ", Емайл: " + actor.getEmail());
        }
        Menu.menu();
    }

    private String getValidatedActorInput(Scanner scanner, DataValidation validation, String errorMsg, ActorEntity actor, ActorFieldSetter fieldSetter) {
        String input = scanner.nextLine();
        fieldSetter.setField(actor, input);
        while (!validation.validate(actor)) {
            System.out.println(errorMsg);
            input = scanner.nextLine();
            fieldSetter.setField(actor, input);
        }
        return input;
    }

    private LocalDate getValidatedActorDateInput(Scanner scanner, DataValidation validation, String errorMsg, ActorEntity actor) {
        String input = scanner.nextLine();
        LocalDate date = null;
        boolean isValid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        while (!isValid) {
            try {
                date = LocalDate.parse(input, formatter);
                actor.setBirthDate(date);
                if (validation.validate(actor)) {
                    isValid = true;
                } else {
                    System.out.println(errorMsg);
                    input = scanner.nextLine();
                }
            } catch (DateTimeParseException e) {
                System.out.println(errorMsg);
                input = scanner.nextLine();
            }
        }

        return date;
    }

    @FunctionalInterface
    public interface ActorFieldSetter {
        void setField(ActorEntity actor, String value);
    }
}
