    package boj.boj1546평균;

    import java.io.*;
    import java.net.URLDecoder;
    import java.util.*;

    public class Main {
        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int M = 0;
            int[] nums = new int[N+1];
            double[] convNums = new double[N+1];
            for(int i=1; i<=N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                if(nums[i] > M) M = nums[i];
            }

            double result = 0;
            for(int i=1; i<=N; i++) {
                convNums[i] = (double)nums[i]/M * 100;
                result += convNums[i];
            }

            System.out.println(result/N);

            br.close();
        }
    }
