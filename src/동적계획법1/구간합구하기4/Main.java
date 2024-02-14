package 동적계획법1.구간합구하기4;

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11659
// DP
// 구간합
public class Main {
    static int N, M;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            num[i] = Integer.parseInt(st.nextToken()) + num[i-1];
        }

        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end =Integer.parseInt(st.nextToken());

            sb.append(num[end]-num[start-1] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
