package 그래프1.LCA2;

import java.io.*;
import java.util.*;

import static java.lang.Math.floor;

// https://www.acmicpc.net/problem/11438
// 최소공통부모 (LCA)
// 기본 (시간 복잡도 O(level))
// 1. 부모 노드를 배열 저장
// 2. 깊은 노드의 레벨을 높이며 양 노드의 레벨을 맞춘다.
// 3. 양 노드의 레벨을 맞추었을 때 같이 레벨을 높이면서 공통 부모를 찾는다.
// 단점 - N개의 숫자 보유 및 N개의 level인 경우 최악의 시간 소요
// 개선 (시간 복잡도 O(log level))
// 1. 2^k승의 부모 노드까지 2차원 배열에 저장
// 2. 깊은 노드의 레벨을 높일 때 2^k승으로 부모 노드를 확인하여 양 노드의 레벨을 맞춘다.
// 3. 양 노드의 레벨을 맞추었을 때 같이 2^k승으로 부모 노드를 확인하며 공통 부모를 찾는다.
public class Main {
    static int N, M, MAX;
    static int[][] parent;
    static int[] level;
    static ArrayList[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        MAX = -1;
        for (int i = 1; i <= N; i *= 2) {
            MAX++;
        }

        tree = new ArrayList[N+1];
        parent = new int[N+1][MAX+1];
        level = new int[N+1];
        for(int i = 1; i <= N; i++){
            tree[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 1);

        fillParent();

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(findParent(a,b)+"\n");
        }

        bw.flush();

    }

    static void dfs(int root, int cnt){
        level[root] = cnt;

        int len = tree[root].size();
        for(int i = 0; i < len; i++){
            int next = (int) tree[root].get(i);

            if(level[next] == 0){
                dfs(next, cnt+1);
                parent[next][0] = root;
            }
        }

    }

    static void fillParent() {
        // 2^K 번째 부모까지 채우기
        for (int i = 1; i<=N; i++) {
            for (int j = 1; j<=MAX; j++) {
                parent[i][j] = parent[parent[i][j-1]][j-1];
            }
        }
    }



    static int findParent(int deep, int light) {
        if(level[deep] < level[light]){
            int temp = light;
            light = deep;
            deep = temp;
        }

        if(deep == 1 || light == 1) return 1;


        for (int i = MAX; i >= 0; i--) {
            if (Math.pow(2, i) <= level[deep] - level[light]) {
                deep = parent[deep][i];
            }
        }

        if(deep == light) return deep;

        for(int i = MAX; i >= 0; i--){
            if(parent[deep][i] != parent[light][i]){
                deep = parent[deep][i];
                light = parent[light][i];
            }
        }

        return parent[deep][0];
    }
}
