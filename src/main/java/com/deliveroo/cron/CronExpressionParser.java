package com.deliveroo.cron;

import com.deliveroo.cron.dto.CronParsedResponse;
import com.deliveroo.cron.models.TimeUnit;
import com.deliveroo.cron.parsers.manager.ParserStrategyManager;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CronExpressionParser {
    private ParserStrategyManager parserStrategyManager;

    public CronParsedResponse parseString(String cronExpression) {
        CronParsedResponse response = new CronParsedResponse();
        String[] cronSplitted = cronExpression.split(" ");

        response.setMinutes(parserStrategyManager.getExecutableTimings(TimeUnit.MINUTE, cronSplitted[0]));
        response.setHours(parserStrategyManager.getExecutableTimings(TimeUnit.HOUR, cronSplitted[1]));
        response.setDaysOfMonth(parserStrategyManager.getExecutableTimings(TimeUnit.DAY_OF_MONTH, cronSplitted[2]));
        response.setMonth(parserStrategyManager.getExecutableTimings(TimeUnit.MONTH, cronSplitted[3]));
        response.setDaysOfWeek(parserStrategyManager.getExecutableTimings(TimeUnit.DAY_OF_WEEK, cronSplitted[4]));
        response.setCommand(cronSplitted[5]);

        return response;
    }
}
