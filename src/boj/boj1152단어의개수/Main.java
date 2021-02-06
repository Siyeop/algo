    package boj.boj1152단어의개수;

    import java.io.*;
    import java.net.URLDecoder;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            System.out.println(st.countTokens());
            br.close();
        }
    }
