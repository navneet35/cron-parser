package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class FixedTimingParser extends Parser{
    @Override
    public List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        String[] fixedTiming = cronExpression.split(",");
        List<Integer> result = new ArrayList<>();

        for(String timing : fixedTiming) {
            Integer time = Integer.valueOf(timing);
            if(isValid(time, timeUnit)) {
                result.add(time);
            } else {
                throw new InvalidCronExpression(timeUnit, cronExpression, "Input given is out of range");
            }
        }
        return result;
    }

    @Override
    public String getRegex() {
        return "^\\d+(,\\d+)*$";
    }

    private boolean isValid(Integer value, TimeUnit timeUnit) {
        return value >= timeUnit.getStartRange() && value <= timeUnit.getEndRange();
    }
}
