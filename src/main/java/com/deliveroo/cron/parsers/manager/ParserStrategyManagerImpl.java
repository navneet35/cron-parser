package com.deliveroo.cron.parsers.manager;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;
import com.deliveroo.cron.parsers.Parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParserStrategyManagerImpl implements ParserStrategyManager{
    Set<Parser> parsers = new HashSet<>();

    @Override
    public void registerStrategy(Parser parser) {
        parsers.add(parser);
    }

    @Override
    public List<Integer> getExecutableTimings(TimeUnit timeUnit, String cronExpression) throws InvalidCronExpression {
        try {
            Parser parser = getParser(cronExpression);
            return parser.getTimings(timeUnit, cronExpression);
        } catch (InvalidCronExpression e) {
            throw new InvalidCronExpression(timeUnit, cronExpression, e.getMessage());
        }
    }

    private Parser getParser(String cronExpression) {
        for(Parser parser : parsers) {
            if(cronExpression.matches(parser.getRegex())) {
                return parser;
            }
        }
        throw new InvalidCronExpression("Invalid cron expression. Not able to parse the given input.");
    }
}
