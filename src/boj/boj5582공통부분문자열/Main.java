package boj.boj5582공통부분문자열;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;

// https://www.acmicpc.net/problem/5582

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static String STR_1;
    static String STR_2;

    static int[][] DP;
    static int MAX_LENGTH;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        STR_1 = " " + br.readLine();
        STR_2 = " " + br.readLine();
        DP = new int[STR_1.length() + 1][STR_2.length() + 1];

        // 2. DP 계산
        calcDp();
        printDp();
        bw.write(String.valueOf(MAX_LENGTH));


        // 5. 끝
        bw.flush();
        bw.close();
        br.close();
    }

    static void calcDp() {
        // 1. 초기화
        final int NOT_YET = -1;
        DP = new int[STR_1.length() + 1][STR_2.length() + 1];
        for (int i = 0; i <= STR_1.length(); i++) {
            Arrays.fill(DP[i], NOT_YET);
        }

        // 2. DP 계산
        int length = 0;

        for (int i = 1; i <= STR_1.length() - 1; i++) {
            for (int j = 1; j <= STR_2.length() - 1; j++) {
                // 2-2. 새로 계산
                if (DP[i - 1][j - 1] < 2) {
                    length = 0;
                    try {
                        while (STR_1.charAt(i + length) == STR_2.charAt(j + length)) {
                            length++;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                    } finally {
                        DP[i][j] = length;
                        MAX_LENGTH = Math.max(MAX_LENGTH, DP[i][j]);
                    }
                }

                // 2-1. 기존 계산한 것 재활용
                else {
                    DP[i][j] = DP[i - 1][j - 1] - 1;
                }

            }
        }
    }

    static void printDp() {
        for (int i = 1; i <= STR_1.length() - 1; i++) {
            for (int j = 1; j <= STR_2.length() - 1; j++) {
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
    }
}
