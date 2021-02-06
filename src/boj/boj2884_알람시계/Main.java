package boj.boj2884_알람시계;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());

        min -= 45;
        if(min < 0) {
            min += 60;
            hour -= 1;
            if(hour < 0) hour += 24;
        }

        bw.write(hour + " " + min + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

