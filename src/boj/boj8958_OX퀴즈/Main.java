package boj.boj8958_OX퀴즈;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) {
            String quiz = br.readLine();
            int result = 0;
            int point = 0;
            for(int i=0; i<quiz.length(); i++) {
                if(quiz.charAt(i) == 'O') result += ++point;
                else point = 0;
            }
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

