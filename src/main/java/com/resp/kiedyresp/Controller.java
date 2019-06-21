package com.resp.kiedyresp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class Controller {

    private static int viewCount = 444;

    private static int readViewCount() {
        try (BufferedReader br = new BufferedReader(new FileReader("views.txt"))) {
            String line = br.readLine();
            int i = Integer.parseInt(line);
            if (i < viewCount) {
                return viewCount;
            }
            return i;
        } catch (Exception e) {
            return viewCount;
        }
    }

    private static void writeViewCount() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("views.txt"))) {
            bw.write(String.valueOf(viewCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String pattern = "H:mm";
    private static String aktu = "Ostatnia aktualizacja: 21 czerwca";
    private static double odstep = 60;

    @GetMapping("/")
    public static String asd() {
        viewCount++;
        writeViewCount();
        viewCount = readViewCount();
        return
                "<body bgcolor=#262626><center><font color = #bebebe>" + wyliczResp(1, "bossów: ") +
                wyliczResp(4, "herobriny: ") +
                wyliczResp(6, "minosów: ") +
                wyliczResp(12, "avatara: ") +
                wyliczResp(2, "żab: ") +
                wyliczResp(3, "goblinów: ") /*+ liczbaWyswietlen()*/ + aktu + rozpiska() + poradnik() +
                "</center></font></body>";
    }

    private static String wyliczResp(int czas, String boss) {
        LocalDateTime now = LocalDateTime.now().plusMinutes(120);
        LocalDateTime resp = LocalDateTime.of(2019, 6, 21, 6, 0);
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

    private static String rozpiska() {
//        return "<br></br><b><font size = 5>" + "Zbijanie przywołań Avatara" + "</font></b><br></br>" +
//               "4 stycznia: Bonkers, Antypatyczny, Chapman, SilenceLife, NiePijTato, Paverell" + "<br></br>" +
//               "11 stycznia: Bonkers, Antypatyczny, Slooodziak, WojOnePLK, CeHa, SpeaDreaM" + "<br></br>" +
//               "18 stycznia: Bonkers, Antypatyczny, Eiv, Rubiks, Pimber, D4MN" + "<br></br>" +
//               "25 stycznia: Bonkers, Antypatyczny, Bagroownik, HotTreasure, BilonJP, Ropeflake" + "<br></br>" +
//               "1 lutego: Bonkers, SilenceLife, Alojz, Hikikomori, LetMeKill, NiePijTato" + "<br></br>" +
//                "Każda grupa zbija 30 przywołań. Wpisowe od osoby: 300b";

        return "<br></br><a href=https://discord.gg/d3Rf7sy>Discord</a>";
    }

    private static String poradnik() {
        return "<br></br><a href=poradnik.html>Wojna gildii - poradnik</a>";
    }

    private static String liczbaWyswietlen() {
        return "Liczba wyświetleń: " + viewCount + "<br></br>";
    }
}
