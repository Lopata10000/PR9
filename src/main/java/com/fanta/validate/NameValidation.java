package com.fanta.validate;

import com.fanta.entity.ActorEntity;

public class NameValidation implements DataValidation{
  private DataValidation nextDataValidation;

  @Override
  public boolean validate(ActorEntity actorEntity){
    boolean isValid = true;

    if (actorEntity.getFirstName().length() < 2) {
      System.out.println("Ім'я не може містити менше 2 символів");
      isValid = false;
    }

    if (actorEntity.getFirstName().length() > 50) {
      System.out.println("Ім'я не може містити більше 50-ти символів");
      isValid = false;
    }

    if (!actorEntity.getFirstName().matches("[a-zA-Z]+")) {
      System.out.println("Ім'я повинно містити тільки букви");
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
