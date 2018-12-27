package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static String pattern = "H:mm";
    private static String aktu = "Ostatnia aktualizacja: 27 grudnia";

    @GetMapping("/")
    public static String asd() {
        return
                "<center>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") +
                wyliczResp(3, "bałwana: ") + aktu + "</center>";
    }

    private static String wyliczResp(int i, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(60);
        LocalDateTime resp = LocalDateTime.of(2018, 12, 27, 7, 33);
        while (true) {
            resp = resp.plusMinutes(72 * i);
            if (resp.isAfter(now)) {
                resp = resp.minusMinutes(72 * i);
                String ret = boss;
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + "<b><font size = 5>" + resp.format(DateTimeFormatter.ofPattern(pattern)) + "</font></b>" +
                      ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusMinutes(72 * i);
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + "<br></br>";
                return ret;
            }
        }
    }
}
