    package boj.boj1330두수비교하기;

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.InputStreamReader;
    import java.net.URLDecoder;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a > b) System.out.println(">");
            else if(a == b) System.out.println("==");
            else System.out.println("<");

            br.close();
        }
    }
