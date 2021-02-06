package boj.boj10809_알파벳찾기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();

        Map<Character, Integer> idxMap = new HashMap<>();
        for(char c='a'; c<='z'; c++) idxMap.put(c, -1);
        for(int i=0; i<S.length(); i++) {
            char curr = S.charAt(i);
            if(idxMap.get(curr) == -1) idxMap.put(curr, i);
        }

        StringBuilder sb = new StringBuilder();
        for(char c='a'; c<='z'; c++) sb.append(idxMap.get(c)).append(" ");
        bw.write(sb.toString().trim());

        bw.flush();
        bw.close();
        br.close();
    }
}

