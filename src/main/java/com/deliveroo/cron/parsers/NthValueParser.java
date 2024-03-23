package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.List;

public class NthValueParser extends Parser{
    private final String NTH_VALUE_FORMAT = "*/";

    @Override
    public List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        String nthVal = cronExpression.substring(NTH_VALUE_FORMAT.length());
        Integer val = Integer.valueOf(nthVal);
        if(!isValid(val, timeUnit)) {
            throw new InvalidCronExpression(timeUnit, cronExpression, "Input given is out of range");
        }
        return getCronTimings(timeUnit, val);
    }

    @Override
    public String getRegex() {
        return "^\\*/\\d+$";
    }

    private boolean isValid(Integer value, TimeUnit timeUnit) {
        return value >= timeUnit.getStartRange() && value <= timeUnit.getEndRange();
    }
}
