package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static String pattern = "H:mm";
    private static String aktu = "Ostatnia aktualizacja: 7 stycznia";

    @GetMapping("/")
    public static String asd() {
        return
                "<center>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") +
                wyliczResp(3, "bałwana: ") + aktu + rozpiska() + "</center>";
    }

    private static String wyliczResp(int i, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(60);
        LocalDateTime resp = LocalDateTime.of(2019, 1, 7, 10, 46);
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
}
