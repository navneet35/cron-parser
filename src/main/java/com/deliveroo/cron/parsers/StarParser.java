package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.List;

public class StarParser extends Parser{
    @Override
    public List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        return getCronTimings(timeUnit, 1);
    }

    @Override
    public String getRegex() {
        return "^\\*$";
    }
}
