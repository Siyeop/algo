package boj.boj9251_LCS;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        String str2 = br.readLine();

//        int[][] dp = new int[str1.length()+1][str2.length()+1];
//        for(int i=1; i<=str1.length(); i++) {
//            for(int j=1; j<=str2.length(); j++) {
//                dp[i][j] = (str1.charAt(i-1) == str2.charAt(j-1))
//                        ? dp[i-1][j-1] + 1
//                        : Math.max(dp[i-1][j], dp[i][j-1]);
//            }
//        }
//        bw.write(dp[str1.length()][str2.length()] + "\n");

        int[][] dp = new int[2][str2.length()+1];
        for(int i=1; i<=str1.length(); i++) {
            dp[0] = Arrays.copyOf(dp[1], str2.length()+1);
            for(int j=1; j<=str2.length(); j++) {
                dp[1][j] = (str1.charAt(i-1) == str2.charAt(j-1)) ? dp[0][j-1] + 1 : Math.max(dp[0][j], dp[1][j-1]);
            }
        }
        bw.write(dp[1][str2.length()] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

