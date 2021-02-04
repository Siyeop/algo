    package boj.boj1003피보나치함수;

    import java.io.*;
    import java.net.URLDecoder;

    public class Main {

        static int T;
        static int N; // N <= 40
        static int[][] DP = new int[41][2];

        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            T = Integer.parseInt(br.readLine());

            // 1. DP 계산
            DP[0][0] = 1; DP[0][1] = 0; DP[1][0] = 0; DP[1][1] = 1;
            for(int i=2; i<=40; i++) {
                DP[i][0] = DP[i-1][0] + DP[i-2][0];
                DP[i][1] = DP[i-1][1] + DP[i-2][1];
            }

            // 2. TC별 답 계산
            for(int i=1; i<=T; i++) {
                int t = Integer.parseInt(br.readLine());
                bw.write(DP[t][0] + " " + DP[t][1] + "\n");
            }

            bw.flush();
            bw.close();
            br.close();
        }
    }
