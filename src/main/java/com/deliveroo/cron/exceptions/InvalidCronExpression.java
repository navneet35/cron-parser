package com.deliveroo.cron.exceptions;

import com.deliveroo.cron.models.TimeUnit;

public class InvalidCronExpression extends RuntimeException{
    public InvalidCronExpression(String message) {
        super(message);
    }

    public InvalidCronExpression(TimeUnit timeUnit, String cronExpression, String message) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("The expresssion " + cronExpression + " passed for time unit " + timeUnit.getName() + " is invalid. \n");
        errorMessage.append("Accepted range for time unit " + timeUnit.getName() + " is [" + timeUnit.getStartRange() +"-" + timeUnit.getEndRange() +"]. \n");
        errorMessage.append("Error message is : " + message);
        throw new InvalidCronExpression(errorMessage.toString());
    }
}
