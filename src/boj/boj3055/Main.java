package boj.boj3055;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3055

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int R; // 행
    static int C; // 열

    static int[][] safety;
    static int[][] shortest;

    static int DR;
    static int DC;

    static final int UNKNOWN = Integer.MAX_VALUE;
    static final int ROCK = -1;

    static final int START = 0;
    static final int DESTINATION = UNKNOWN-1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        readMap();      // 1. MAP 읽기
        calcSafety();   // 2. Safety count 계산
        // printSafety();

        calcShortest(); // 3. Shortest count 계산
        // printShortest();

        printSolution(); // 4. 정답 출력


        bw.close();
        br.close();
    }

    static void readMap() throws Exception {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        safety = new int[R+2][C+2];
        shortest = new int[R+2][C+2];

        for(int i=0; i<=R+1; i++) {
            safety[i] = new int[C+2];
            shortest[i] = new int[C+2];
            Arrays.fill(safety[i], UNKNOWN);
            Arrays.fill(shortest[i], UNKNOWN);
        }

        String row;
        for(int i=1; i<=R ;i++) {
            st = new StringTokenizer(br.readLine());
            row = st.nextToken();

            for(int j=1; j<=C; j++) {
                switch(row.charAt(j-1)) {
                    case '.': break;
                    case '*': safety[i][j] = 0; break;
                    case 'X': safety[i][j] = ROCK; break;

                    case 'S': shortest[i][j] = START; break;
                    case 'D':
                        safety[i][j] = DESTINATION;
                        DR = i;
                        DC = j;
                        break;

                    default: throw new Exception();
                }
            }
        }
    }

    static void calcSafety() {
        boolean isChanged = true;
        while(isChanged) {
            isChanged = false;
            for(int i=1; i<=R ;i++) {
                for (int j=1; j<=C; j++) {
                    if(safety[i][j] == DESTINATION) { continue; }
                    int newValue = UNKNOWN;
                    if(safety[i-1][j] != DESTINATION && safety[i-1][j] != UNKNOWN && safety[i-1][j] != ROCK) { newValue = safety[i-1][j] + 1; }
                    if(safety[i+1][j] != DESTINATION && safety[i+1][j] != UNKNOWN && safety[i+1][j] != ROCK) { newValue = Math.min(newValue, safety[i+1][j] + 1); }
                    if(safety[i][j-1] != DESTINATION && safety[i][j-1] != UNKNOWN && safety[i][j-1] != ROCK) { newValue = Math.min(newValue, safety[i][j-1] + 1); }
                    if(safety[i][j+1] != DESTINATION && safety[i][j+1] != UNKNOWN && safety[i][j+1] != ROCK) { newValue = Math.min(newValue, safety[i][j+1] + 1); }

                    if(newValue < safety[i][j]) {
                        safety[i][j] = newValue;
                        isChanged = true;
                    }
                }
            }
        }
    }

    static void calcShortest() {
        boolean isChanged = true;
        while(isChanged) {
            isChanged = false;
            for(int i=1; i<=R ;i++) {
                for (int j=1; j<=C; j++) {
                    int newValue = UNKNOWN;
                    if(shortest[i-1][j] != UNKNOWN && shortest[i-1][j] != ROCK && shortest[i-1][j] + 1 < safety[i][j]  ) { newValue = shortest[i-1][j] + 1; }
                    if(shortest[i+1][j] != UNKNOWN && shortest[i+1][j] != ROCK && shortest[i+1][j] + 1 < safety[i][j]  ) { newValue = Math.min(newValue, shortest[i+1][j] + 1); }
                    if(shortest[i][j-1] != UNKNOWN && shortest[i][j-1] != ROCK && shortest[i][j-1] + 1 < safety[i][j]  ) { newValue = Math.min(newValue, shortest[i][j-1] + 1); }
                    if(shortest[i][j+1] != UNKNOWN && shortest[i][j+1] != ROCK && shortest[i][j+1] + 1 < safety[i][j]  ) { newValue = Math.min(newValue, shortest[i][j+1] + 1); }

                    if(newValue < shortest[i][j]) {
                        shortest[i][j] = newValue;
                        isChanged = true;
                    }
                }
            }
        }
    }

    static void printSafety() {
        System.out.println("--- safety map ---");
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                System.out.print(safety[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printShortest() {
        System.out.println("--- shortest map ---");
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                System.out.print(shortest[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printSolution() throws IOException {
        if(shortest[DR][DC] == UNKNOWN) {
            bw.write("KAKTUS");
        } else {
            bw.write(Integer.toString(shortest[DR][DC]));
        }
        bw.flush();
    }
}
