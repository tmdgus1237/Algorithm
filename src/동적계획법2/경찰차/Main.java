package 동적계획법2.경찰차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, W;
    static int[][] issues;
    static int[][] dp; // 경찰차1이 i 번사건을 해결/ 경찰차2 가 j번 사건을 해결했을 때의 최소거리

    static int[][] path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        issues = new int[W+1][2];
        dp = new int[W+1][W+1];
        path = new int[W+1][W+1];

        for(int i = 1; i <= W; i++){
            st = new StringTokenizer(br.readLine());
            issues[i][0] = Integer.parseInt(st.nextToken());
            issues[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0,0));

        getPath();

    }

    static void getPath(){
        int a = 0;
        int b = 0;
        for(int i = 0; i < W; i++){
            int next = Math.max(a,b) + 1;
            if(path[a][b] == 1){
                System.out.println(1);
                a = next;
            }else{
                System.out.println(2);
                b = next;
            }
        }
    }

    static int dfs(int a, int b){
        if(a == W || b == W) return 0;

        if(dp[a][b] != 0) return dp[a][b];

        int next = Math.max(a,b) + 1;

        // a 이동
        int Aresult;
        if(a == 0){
            Aresult = dfs(next, b) + distance(1,1,issues[next][0],issues[next][1]);
        }else{
            Aresult = dfs(next, b) + distance(issues[a][0],issues[a][1],issues[next][0],issues[next][1]);
        }

        // b 이동
        int Bresult;
        if(b == 0){
            Bresult = dfs(a, next) + distance(N,N,issues[next][0],issues[next][1]);
        }else{
            Bresult = dfs(a, next) + distance(issues[b][0],issues[b][1],issues[next][0],issues[next][1]);
        }

        if(Aresult < Bresult){
            path[a][b] = 1;
        }else{
            path[a][b] = 2;
        }

        return dp[a][b] = Math.min(Aresult, Bresult);
    }

    static int distance(int xa, int ya, int xb, int yb){
        return Math.abs(xa - xb) + Math.abs(ya - yb);
    }
}
