package com.deliveroo.cron.parsers;

import com.deliveroo.cron.exceptions.InvalidCronExpression;
import com.deliveroo.cron.models.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class NumberParserTest {
    Parser parser = new NumberParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "1", Arrays.asList(1)),
                Arguments.of(TimeUnit.HOUR, "1", Arrays.asList(1)),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "1", Arrays.asList(1)),
                Arguments.of(TimeUnit.MONTH, "1", Arrays.asList(1)),
                Arguments.of(TimeUnit.DAY_OF_WEEK,"1", Arrays.asList(1))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "70"),
                Arguments.of(TimeUnit.HOUR, "25"),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "35"),
                Arguments.of(TimeUnit.MONTH, "15"),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "8")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfNumberParserIsWorkingAsExpected(TimeUnit timeUnit, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(timeUnit, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfNumberParserThrowsExceptionForInvalidInput(TimeUnit timeUnit, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(timeUnit, cronExpression);
        });
    }
}
