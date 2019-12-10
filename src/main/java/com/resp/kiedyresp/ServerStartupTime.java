package com.resp.kiedyresp;

import kong.unirest.Unirest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerStartupTime {

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getAktu() {
        return aktu;
    }

    private LocalDateTime lastUpdate = LocalDateTime.MIN;
    private LocalDateTime lastKnownStartupTime;
    private Pattern pattern = Pattern.compile("([0-9]{2}:[0-9]{2} [0-9]{2}-[0-9]{2}-[0-9]{2})");
    private String dateFormat = "HH:mm dd-MM-yy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    private String aktu = "";

    public LocalDateTime getStartupTime() {
        LocalDateTime now = LocalDateTime.now();
        if (lastUpdate.until(now, ChronoUnit.MINUTES) > 10 || lastKnownStartupTime == null) {
            lastUpdate = now;
            String body = Unirest.get("https://pangeayt2.eu/?require=server_status").asString().getBody();
            Matcher matcher = pattern.matcher(body);
            matcher.find();
            String date = matcher.group(0);
            LocalDateTime newStartupTime = LocalDateTime.parse(date, formatter);
            lastKnownStartupTime = newStartupTime;
            aktu = String.format("Ostatni reset serwera: %02d-%02d-%02d %d:%02d", newStartupTime.getDayOfMonth(),
                                 newStartupTime.getMonthValue(), newStartupTime.getYear(), newStartupTime.getHour(),
                                 newStartupTime.getMinute());
            return newStartupTime;
        } else {
            return lastKnownStartupTime;
        }
    }
}
