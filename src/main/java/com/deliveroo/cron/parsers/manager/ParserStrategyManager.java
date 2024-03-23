package com.deliveroo.cron.parsers.manager;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;
import com.deliveroo.cron.parsers.Parser;

import java.util.List;

public interface ParserStrategyManager {
    void registerStrategy(Parser parser);
    List<Integer> getExecutableTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression;
}
