package com.fanta.validate;

import com.fanta.entity.ActorEntity;

public class LastNameValidation implements DataValidation{
    private DataValidation nextDataValidation;

    @Override
    public boolean validate(ActorEntity actorEntity){
        boolean isValid = true;

        if (actorEntity.getLastName().length() < 2 || actorEntity.getLastName().length() > 50) {
            System.out.println("Прізвище не може містити менше 2 символів, або більше 50-ти");
            isValid = false;
        }

        if (!actorEntity.getLastName().matches("[a-zA-Z]+")) {
            System.out.println("Прізвище повинно містити тільки букви");
            isValid = false;
        }

        String specialCharacters = "!#$%^&*()'+,-./:;<=>?@\\[\\]\\\\^_`{|}";
        if (actorEntity.getLastName().matches(".*[" + specialCharacters + "].*")) {
            System.out.println("Прізвище містить недопустимі символи.");
            isValid = false;
        }

        if (nextDataValidation != null) {
            isValid = nextDataValidation.validate(actorEntity) && isValid;
        }

        return isValid;
    }

    @Override
    public void setNextCHandler(DataValidation dataValidation){
        nextDataValidation = dataValidation;
    }
}
