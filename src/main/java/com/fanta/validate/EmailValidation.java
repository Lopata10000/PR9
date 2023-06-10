package com.fanta.validate;


import com.fanta.entity.ActorEntity;

import java.util.regex.Pattern;

public class EmailValidation implements DataValidation{
  private DataValidation nextDataValidation;

  @Override
  public boolean validate(ActorEntity actorEntity){
    boolean isValid = true;

    if (!actorEntity.getEmail().matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
      System.out.println("Емайл введено не коректно.");
      isValid = false;
    }

    if (actorEntity.getEmail().matches(".*[А-Яа-яЁё].*")) {
      System.out.println("Емайл не має містити символи кирилиці.");
      isValid = false;
    }

    String specialCharacters="!#$%^&*()'+/:;<=>?[]`{|}~";
    if (actorEntity.getEmail().matches("[" + Pattern.quote(specialCharacters) + "]")) {
      System.out.println("Емайл містить недопустимі символи.");
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
