package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {
    protected List<Integer> getCronTimings(TimeUnit timeUnit, Integer increment) {
        return getCronTimings(timeUnit.getStartRange(), timeUnit.getEndRange(), increment);
    }

    protected List<Integer> getCronTimings(Integer startRange, Integer endRange, Integer increment) {
        List<Integer> result = new ArrayList<>();
        while(startRange <= endRange) {
            result.add(startRange);
            startRange += increment;
        }
        return result;
    }

    public abstract List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression;
    public abstract String getRegex();
}
