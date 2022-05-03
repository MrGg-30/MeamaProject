package com.example.MeamaProject.exception;

import javax.validation.ConstraintViolationException;

public class ExceptionUtil {
    public static boolean checkConstraintViolation(ConstraintViolationException exception, String constraintName) {
        Throwable throwable = exception;
        do {
            String exceptionMessage = throwable.getMessage().toLowerCase();
            if (exceptionMessage.contains(constraintName)) {
                return true;
            } else {
                throwable = throwable.getCause();
            }
        } while (throwable != null);
        return false;
    }

}
