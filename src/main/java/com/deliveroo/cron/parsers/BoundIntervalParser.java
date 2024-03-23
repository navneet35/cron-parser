package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class BoundIntervalParser extends Parser{

    @Override
    public List<Integer> getTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        String[] boundIntervals = cronExpression.split(",");
        List<Integer> result = new ArrayList<>();

        for(String intervals: boundIntervals) {
             String[] range = intervals.split("-");
             Integer start = Integer.valueOf(range[0]);
             Integer end = Integer.valueOf(range[1]);

             if(isValid(start, end, timeUnit)) {
                result.addAll(getCronTimings(start, end, 1));
             } else {
                 throw new InvalidCronExpression(timeUnit, cronExpression, "Input is out of range.");
             }
        }

        return result;
    }

    @Override
    public String getRegex() {
        return "^\\d+-\\d+(,\\d+-\\d+)*$";
    }

    private boolean isValid(Integer start, Integer end, TimeUnit timeUnit) {
        return start >= timeUnit.getStartRange()
                && start <= timeUnit.getEndRange()
                && end >= timeUnit.getStartRange()
                && end <= timeUnit.getEndRange()
                && start <= end;
    }
}
