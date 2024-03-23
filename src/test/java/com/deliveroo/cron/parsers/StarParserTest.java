package com.deliveroo.cron.parsers;

import com.deliveroo.cron.models.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StarParserTest {
    Parser parser = new StarParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "*", IntStream.range(0, 60).boxed().collect(Collectors.toList())),
                Arguments.of(TimeUnit.HOUR, "*", IntStream.range(0, 24).boxed().collect(Collectors.toList())),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "*", IntStream.range(1, 32).boxed().collect(Collectors.toList())),
                Arguments.of(TimeUnit.MONTH, "*", IntStream.range(1, 13).boxed().collect(Collectors.toList())),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "*", IntStream.range(1, 8).boxed().collect(Collectors.toList()))
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfStarParserThrowsExceptionForInvalidInput(TimeUnit timeUnit, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(timeUnit, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }
}
