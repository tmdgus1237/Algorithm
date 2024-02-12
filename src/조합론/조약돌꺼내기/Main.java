package 조합론.조약돌꺼내기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13251
// 확률 값을 제대로 계산해주자
// double 타입에 주의
public class Main {
    static int M, K;
    static double[] colors;
    static double cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        colors = new double[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
            cnt += colors[i];
        }

        K = Integer.parseInt(br.readLine());


        double result = 0;
        for(int i = 0; i < M; i++){
            double percentage = 1;
            double now = cnt;
            for(int j = 0; j < K; j++){
                percentage *= colors[i]--/now--;
            }
            result += percentage;
        }

        System.out.println(result);
    }
}
