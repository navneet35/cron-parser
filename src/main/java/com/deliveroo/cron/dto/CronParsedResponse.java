package com.deliveroo.cron.dto;

import com.deliveroo.cron.models.TimeUnit;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Data
public class CronParsedResponse {
    private List<Integer> minutes;
    private List<Integer> hours;
    private List<Integer> daysOfMonth;
    private List<Integer> month;
    private List<Integer> daysOfWeek;
    private String command;

    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append(format("%-14s%s\n", TimeUnit.MINUTE.getName(), printList(minutes)));
        b.append(format("%-14s%s\n", TimeUnit.HOUR.getName(), printList(hours)));
        b.append(format("%-14s%s\n", TimeUnit.DAY_OF_MONTH.getName(), printList(daysOfMonth)));
        b.append(format("%-14s%s\n", TimeUnit.MONTH.getName(), printList(month)));
        b.append(format("%-14s%s\n", TimeUnit.DAY_OF_WEEK.getName(), printList(daysOfWeek)));
        b.append(format("%-14s%s\n", "command", command));
        return b.toString();
    }

    private String printList(List<Integer> integers) {
        return integers.stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
