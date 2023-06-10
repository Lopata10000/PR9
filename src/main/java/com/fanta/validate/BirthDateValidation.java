package com.fanta.validate;

import com.fanta.entity.ActorEntity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BirthDateValidation implements DataValidation {
    private static final int MIN_AGE = 16;
    private DataValidation nextDataValidation;

    @Override
    public boolean validate(ActorEntity actorEntity) {
        boolean isValid = true;

        if (actorEntity.getBirthDate() == null) {
            System.out.println("Дата народження не може бути порожньою");
            isValid = false;
        }
        if (!isValidFormat(actorEntity.getBirthDate())) {
            System.out.println("Неправильний формат дати! Використовуйте формат день-місяць-рік.");
            isValid = false;
        }
        if (!isValidAge(actorEntity.getBirthDate())) {
            System.out.println("Актор повинен бути старше " + MIN_AGE + " років.");
            isValid = false;
        }

        if (nextDataValidation != null) {
            isValid = nextDataValidation.validate(actorEntity) && isValid;
        }

        return isValid;
    }

    private boolean isValidFormat(LocalDate birthDate) {
        try {
            birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= MIN_AGE;
    }

    @Override
    public void setNextCHandler(DataValidation dataValidation) {
        nextDataValidation = dataValidation;
    }
}
