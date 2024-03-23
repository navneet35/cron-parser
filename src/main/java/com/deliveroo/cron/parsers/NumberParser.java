package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.Arrays;
import java.util.List;

public class NumberParser extends Parser{
    @Override
    public List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        Integer val = Integer.valueOf(cronExpression);
        if(!isValid(val, timeUnit)) {
            throw new InvalidCronExpression(timeUnit, cronExpression, "Input given is out of range");
        }
        return Arrays.asList(val);
    }

    @Override
    public String getRegex() {
        return "^\\\\d+$";
    }

    private boolean isValid(Integer value, TimeUnit timeUnit) {
        return value >= timeUnit.getStartRange() && value <= timeUnit.getEndRange();
    }
}
