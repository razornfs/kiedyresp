package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private String pattern = "H:mm";
    private String aktu = "Ostatnia aktualizacja: 4 sierpnia";
    private double odstep = 60;
    private ServerStartupTime sst = new ServerStartupTime();

    @GetMapping("/")
    public String asd() {
        return
                "<body bgcolor=#262626><center><font color = #bebebe>" + wyliczResp(1, "bossów: ") +
                wyliczResp(2, "żab: ") +
                wyliczResp(3, "bałwanów: ") +
                wyliczResp(4, "herobriny, goblinów: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") + sst.getAktu() +
                "</center></font></body>";
    }

    @GetMapping("/test")
    public String test() {
        return LocalDateTime.now() + "<br></br>" + sst.getLastUpdate();
    }

    private String wyliczResp(int czas, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(60);
        LocalDateTime resp = sst.getStartupTime();/*LocalDateTime.of(2019, 8, 4, 13, 45);*/
        while (true) {
            resp = resp.plusSeconds((long) (odstep * czas * 60));
            if (resp.isAfter(now)) {
                resp = resp.minusSeconds((long) (odstep * czas * 60));
                String ret = boss;
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusSeconds((long) (odstep * czas * 60));
                ret = ret + "<b><font size = 5>" + resp.format(DateTimeFormatter.ofPattern(pattern)) + "</font></b>" +
                      ", ";
                for (int i = 0; i < 4; i++) {
                    resp = resp.plusSeconds((long) (odstep * czas * 60));
                    ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                }
                resp = resp.plusSeconds((long) (odstep * czas * 60));
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern));
                return ret + "<br></br>";
            }
        }
    }

    private String poradnik() {
        return "<br></br><a href=poradnik.html>Wojna gildii - poradnik</a>";
    }
}
