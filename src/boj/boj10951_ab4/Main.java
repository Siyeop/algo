package boj.boj10951_ab4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        while(true) {
            System.out.println(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()));
            try {
                st = new StringTokenizer(br.readLine());
            } catch(Exception e) {
                break;
            }
        }

        br.close();
    }
}
