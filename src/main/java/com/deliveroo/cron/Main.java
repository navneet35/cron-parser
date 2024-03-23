package com.deliveroo.cron;

import com.deliveroo.cron.dto.CronParsedResponse;
import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.parsers.*;
import com.deliveroo.cron.parsers.manager.ParserStrategyManager;
import com.deliveroo.cron.parsers.manager.ParserStrategyManagerImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].split(" ").length != 6) {
            printUsage();
            System.err.println("ERROR: Arguments passed to the program: " + Arrays.stream(args).collect(Collectors.joining(" ")));
            return;
        }

        ParserStrategyManager parserStrategyManager = new ParserStrategyManagerImpl();
        Parser starParser = new StarParser();
        parserStrategyManager.registerStrategy(starParser);

        Parser numberParser = new NumberParser();
        parserStrategyManager.registerStrategy(numberParser);

        Parser fixedTimingsParser = new FixedTimingParser();
        parserStrategyManager.registerStrategy(fixedTimingsParser);

        Parser nthIntervalParser = new NthValueParser();
        parserStrategyManager.registerStrategy(nthIntervalParser);

        Parser boundIntervalsParser = new BoundIntervalParser();
        parserStrategyManager.registerStrategy(boundIntervalsParser);

        CronExpressionParser cronExpressionParser = new CronExpressionParser(parserStrategyManager);

        CronParsedResponse response;
        try{
            response = cronExpressionParser.parseString(args[0]);
            System.out.println(response);
        } catch (InvalidCronExpression ex) {
            System.err.println(ex.getMessage());
            System.out.println("\n");
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("USAGE: ");
        System.out.println("Pass the cron expression arguments in the same order");
        System.out.println("[minute] [hour] [day of month] [month] [day of week] [command]");
        System.out.println("Example: */15 0 1,15 * 1-5 /usr/bin/find");
        System.out.println();
    }
}
