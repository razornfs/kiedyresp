package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static String pattern = "H:mm";

    @GetMapping("/")
    public static String asd() {
        return
                "<center>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") + "</center>";
    }

    private static String wyliczResp(int i, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(60);
        LocalDateTime resp = LocalDateTime.of(2018, 11, 24, 16, 1);
        while (true) {
            resp = resp.plusMinutes(72 * i);
            if (resp.isAfter(now)) {
                resp = resp.minusMinutes(72 * i);
                String ret = boss;
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + "<br></br>";
                return ret;
            }
        }
    }
}
