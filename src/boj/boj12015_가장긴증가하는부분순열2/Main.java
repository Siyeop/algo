package boj.boj12015_가장긴증가하는부분순열2;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[] nums;
    static List<Integer> lis;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        nums = new int[N+1];
        lis = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            int idx = binarySearch(nums[i]);
            if(idx > lis.size()-1) lis.add(nums[i]);
            else lis.set(idx, nums[i]);
        }

        bw.write(Integer.toString(lis.size()));

        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int target) {
        if(lis.size() == 0) return 0;

        int s = 0;
        int e = lis.size() - 1;

        int m = (s+e)/2;
        while(s < e) {
            if(target > lis.get(m)) s = m+1;
            else e = m;
            m = (s+e)/2;
        }

        if(target > lis.get(m)) m++;
        return m;
    }
}

