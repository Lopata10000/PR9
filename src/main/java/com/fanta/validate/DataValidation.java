package com.fanta.validate;

import com.fanta.entity.ActorEntity;

public interface DataValidation {
  public boolean validate(ActorEntity actorEntity);
  public void setNextCHandler(DataValidation dataValidation);
}
