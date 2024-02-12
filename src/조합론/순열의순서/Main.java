package 조합론.순열의순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1722
// 팩토리얼 계산을 이용해서 순서 예측
// long 타입에 유의
public class Main {
    static int N, type;
    static long k;
    static long[] fac;
    static int[] num;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        fac = new long[N+1];
        fac[0] = 1;
        num = new int[N];
        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++) {
            fac[i] = i * fac[i - 1];
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        type = Integer.parseInt(st.nextToken());
        if(type == 1){
            k = Long.parseLong(st.nextToken());
            getKthArr(k);
        } else if(type == 2){
            for(int i = 0; i < N; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }
            getNth(num);
        }
    }

    public static void getKthArr(long k){
        for(int i = 0; i < N;i++){
            for(int j = 1; j <= N; j++){
                if(visited[j]) continue;
                if(k > fac[N-i-1]){
                    k -= fac[N-i-1];
                }else{
                    num[i] = j;
                    visited[j] = true;
                    break;
                }
            }
        }

        for(int i = 0; i < N; i++){
            System.out.print(num[i]+" ");
        }
    }

    public static void getNth(int[] arr){
        long Nth = 1L;
        for(int i = 0; i < N; i++){
            for(int j = 1; j < arr[i]; j++){
                if(visited[j]) continue;
                Nth += fac[N-i-1];
            }
            visited[arr[i]] = true;
        }
        System.out.println(Nth);
    }
}
