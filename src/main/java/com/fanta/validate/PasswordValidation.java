package com.fanta.validate;

import com.fanta.entity.ActorEntity;


public class PasswordValidation implements DataValidation{
  private DataValidation nextDataValidation;

  @Override
  public boolean validate(ActorEntity actorEntity){
    if (actorEntity.getPassword().length() < 8) {
      System.out.println("Пароль не можу містити менше 8 символів");
      return false;
    } else if (!actorEntity.getPassword().matches(".*\\d.*")) {
      System.out.println("Пароль має містити хоча-б одну цифру.");
      return false;
    }
    return true;
  }
  @Override
  public void setNextCHandler(DataValidation dataValidation){
    nextDataValidation = dataValidation;
  }
}
