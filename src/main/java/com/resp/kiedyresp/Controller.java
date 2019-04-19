package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static String pattern = "H:mm";
    private static String aktu = "Ostatnia aktualizacja: 19 kwietnia";
    private static double odstep = 71.95;

    @GetMapping("/")
    public static String asd() {
        return
                "<center>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") + aktu + rozpiska() + poradnik() + "</center>";
    }

    private static String wyliczResp(int i, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(120);
        LocalDateTime resp = LocalDateTime.of(2019, 4, 19, 1, 31);
        while (true) {
            resp = resp.plusSeconds((long) (odstep * i * 60));
            if (resp.isAfter(now)) {
                resp = resp.minusSeconds((long) (odstep * i * 60));
                String ret = boss;
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusSeconds((long) (odstep * i * 60));
                ret = ret + "<b><font size = 5>" + resp.format(DateTimeFormatter.ofPattern(pattern)) + "</font></b>" +
                      ", ";
                resp = resp.plusSeconds((long) (odstep * i * 60));
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + ", ";
                resp = resp.plusSeconds((long) (odstep * i * 60));
                ret = ret + resp.format(DateTimeFormatter.ofPattern(pattern)) + "<br></br>";
                return ret;
            }
        }
    }

    private static String rozpiska() {
//        return "<br></br><b><font size = 5>" + "Zbijanie przywołań Avatara" + "</font></b><br></br>" +
//               "4 stycznia: Bonkers, Antypatyczny, Chapman, SilenceLife, NiePijTato, Paverell" + "<br></br>" +
//               "11 stycznia: Bonkers, Antypatyczny, Slooodziak, WojOnePLK, CeHa, SpeaDreaM" + "<br></br>" +
//               "18 stycznia: Bonkers, Antypatyczny, Eiv, Rubiks, Pimber, D4MN" + "<br></br>" +
//               "25 stycznia: Bonkers, Antypatyczny, Bagroownik, HotTreasure, BilonJP, Ropeflake" + "<br></br>" +
//               "1 lutego: Bonkers, SilenceLife, Alojz, Hikikomori, LetMeKill, NiePijTato" + "<br></br>" +
//                "Każda grupa zbija 30 przywołań. Wpisowe od osoby: 300b";

        return "<br></br><a href=https://discord.gg/d3Rf7sy>Info co do zbijania avatarów, sojuszy itd.</a>";
    }

    private static String poradnik() {
        return "<br></br><a href=poradnik.html>Wojna gildii - poradnik</a>";
    }
}
