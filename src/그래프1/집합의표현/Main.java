package 그래프1.집합의표현;

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1717
// union - find
public class Main {
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken()); // 최대 1,000,000
        int m = Integer.parseInt(st.nextToken()); // 최대 100,000

        init(n);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine()," ");

            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(type == 0){ // 합집합
                union(a, b);
            }else if(type == 1){ // 동일 집합 여부 확인
                if(a == b || find(a) == find(b)){
                    bw.write("YES\n");
                }else{
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

    public static void init(int n){
        arr = new int[n+1];
        for(int i = 0; i <= n; i++){
            arr[i] = i;
        }
    }

    public static void union(int a, int b){
        int node1 = find(a);
        int node2 = find(b);
        arr[node1] = node2;
    }

    public static int find(int num){
        if(arr[num] == num) return num;
        return arr[num] = find(arr[num]);
    }
}
