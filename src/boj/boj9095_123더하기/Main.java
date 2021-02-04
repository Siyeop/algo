    package boj.boj9095_123더하기;

    import java.io.*;
    import java.net.URLDecoder;
    import java.util.Arrays;

    public class Main {

        static int T;
        static int N; // N <= 10
        static int[][] DP = new int[11][4];

        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 1. DP[i][j] = Math.max(1, DP[i-j][1]+DP[i-j][2]+DP[i-j][3])
            Arrays.fill(DP[0], 0);
            for(int i=1; i<=10; i++) {
                for(int j=1; j<=3; j++) {
                    if(i-j<0) DP[i][j] = 0;
                    else DP[i][j] = Math.max(1, DP[i-j][1]+DP[i-j][2]+DP[i-j][3]);
                }
            }

            // 2. 출력
            T = Integer.parseInt(br.readLine());
            for(int i=1; i<=T; i++) {
                int target = Integer.parseInt(br.readLine());
                bw.write(DP[target][1] + DP[target][2] + DP[target][3] + "\n");
            }

            bw.flush();
            bw.close();
            br.close();
        }
    }
