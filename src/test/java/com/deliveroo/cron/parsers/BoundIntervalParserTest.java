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

public class BoundIntervalParserTest {
    Parser parser = new BoundIntervalParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(TimeUnit.HOUR, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(TimeUnit.MONTH, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "1-5", Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "1-2,3-5", Arrays.asList(1, 2, 3, 4, 5))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "3-2"),
                Arguments.of(TimeUnit.MINUTE, "10-61"),
                Arguments.of(TimeUnit.HOUR, "3-2"),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "3-2"),
                Arguments.of(TimeUnit.MONTH, "3-2"),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "3-2"),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "1-2,3-2")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfBoundIntervalsParserIsWorkingAsExpected(TimeUnit timeUnit, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(timeUnit, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfBoundIntervalsThrowsExceptionForInvalidInput(TimeUnit timeUnit, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(timeUnit, cronExpression);
        });
    }
}
