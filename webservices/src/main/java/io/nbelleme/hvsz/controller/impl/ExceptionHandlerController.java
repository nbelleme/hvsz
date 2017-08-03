package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.common.exceptions.NoGameDefinedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nbelleme on 03/08/2017.
 */
@ControllerAdvice
final class ExceptionHandlerController {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoGameDefinedException.class)
  public void handleNoGameDefinedException() {
  }

}
