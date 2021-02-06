package boj.boj2920음계;

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

        int[] nums = new int[9];
        String result;
        nums[1] = Integer.parseInt(st.nextToken());
        nums[2] = Integer.parseInt(st.nextToken());
        if(nums[2] > nums[1]) result = "ascending";
        else result = "descending";
        for(int i=3; i<=8; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if(
                (result.equals("ascending") && nums[i] < nums[i-1])
                || (result.equals("descending") && nums[i] > nums[i-1])
            ) {
                result = "mixed";
                break;
            }
        }
        bw.write(result);

        bw.flush();
        bw.close();
        br.close();
    }
}

