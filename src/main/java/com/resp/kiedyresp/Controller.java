package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {
    @GetMapping("/")
    public static String asd() {
        return
                "<center>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") + "</center>";
    }

    private static String wyliczResp(int i, String boss) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime resp = LocalDateTime.of(2018, 11, 24, 16, 1);
        while (true) {
            resp = resp.plusMinutes(72 * i);
            if (resp.isAfter(now)) {
                return (boss + resp.format(DateTimeFormatter.ofPattern("d-MM H:mm")) + "<br></br>");
            }
        }
    }
}
