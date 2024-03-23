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

public class NthValueParserTest {
    Parser parser = new NthValueParser();

    private static Stream<Arguments> dataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "*/15", Arrays.asList(0, 15, 30, 45)),
                Arguments.of(TimeUnit.HOUR,   "*/12", Arrays.asList(0,12)),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "*/10", Arrays.asList(1,11,21,31)),
                Arguments.of(TimeUnit.MONTH,"*/2", Arrays.asList(1,3,5,7,9,11)),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "*/1", Arrays.asList(1,2,3,4,5,6,7))
        );
    }

    private static Stream<Arguments> exceptionDataForParser() {
        return Stream.of(
                Arguments.of(TimeUnit.MINUTE, "*/70"),
                Arguments.of(TimeUnit.HOUR,   "*/25"),
                Arguments.of(TimeUnit.DAY_OF_MONTH, "*/45"),
                Arguments.of(TimeUnit.MONTH,"*/22"),
                Arguments.of(TimeUnit.DAY_OF_WEEK, "*/10")
        );
    }

    @ParameterizedTest
    @MethodSource("dataForParser")
    void testIfNthIntervalParserIsWorkingAsExpected(TimeUnit timeUnit, String cronExpression, List<Integer> expectedList) {
        List<Integer> actualList = parser.getTimings(timeUnit, cronExpression);
        Assertions.assertTrue(actualList.equals(expectedList));
    }

    @ParameterizedTest
    @MethodSource("exceptionDataForParser")
    void testIfNthIntervalParserThrowsExceptionForInvalidInput(TimeUnit timeUnit, String cronExpression) {
        Assertions.assertThrows(InvalidCronExpression.class, () -> {
            parser.getTimings(timeUnit, cronExpression);
        });
    }
}
