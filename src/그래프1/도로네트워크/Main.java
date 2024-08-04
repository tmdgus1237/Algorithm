package 그래프1.도로네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3176
// LCA + DP
// LCA로 푸는 경우, O(N*M) 시간 초과
// LCA + DP로 O(MlogN) 시간복잡도로 해결 가능
// 중복되는 작업을 2*H 만큼 줄인다
public class Main {
    static int N, K, H;
    static List<Node>[] graph;
    static int[] depth;
    static int[][] parent, min, max;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a, b, w;

        N = Integer.parseInt(st.nextToken());

        H = -1;
        for(int i = 1; i <= N; i*=2){
            H++;
        }

        graph = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }
        depth = new int[N+1];
        parent = new int[N+1][H+1];
        min = new int[N+1][H+1];
        max = new int[N+1][H+1];

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
            graph[b].add(new Node(a, w));
        }

        dfs(1,1);
        fillParent();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());


        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            findMinMax(a,b);
        }

        System.out.println(sb.toString());
    }

    private static void findMinMax(int a, int b) {
        int resultMin = Integer.MAX_VALUE;
        int resultMax = -1;


        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }


        for(int i = H; i >= 0; i--){
            if(Math.pow(2, i) <= (depth[a] - depth[b])){
                resultMin = Math.min(min[a][i], resultMin);
                resultMax = Math.max(max[a][i], resultMax);
                a = parent[a][i];
            }
        }

        if(a == b){
            sb.append(resultMin + " " + resultMax + "\n");
            return;
        }

        for(int i = H; i >= 0; i--){
            if(parent[a][i] != parent[b][i]){
                resultMin = Math.min(Math.min(min[a][i], min[b][i]), resultMin);
                resultMax = Math.max(Math.max(max[a][i], max[b][i]), resultMax);
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        resultMin = Math.min(Math.min(min[a][0], min[b][0]), resultMin);
        resultMax = Math.max(Math.max(max[a][0], max[b][0]), resultMax);

        sb.append(resultMin + " " + resultMax + "\n");
    }

    public static void dfs(int root, int level){
        depth[root] = level;

        for(Node child : graph[root]){
            if(depth[child.dest] == 0) { // 연결되지 않은 자식
                dfs(child.dest, level + 1);
                parent[child.dest][0] = root;
                min[child.dest][0] = child.weight;
                max[child.dest][0] = child.weight;
            }


        }
    }

    public static void fillParent(){
        for(int i = 1; i <= H; i++){
            for(int j = 1; j <= N; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
                min[j][i] = Math.min(min[parent[j][i-1]][i-1],min[j][i-1]);
                max[j][i] = Math.max(max[parent[j][i-1]][i-1],max[j][i-1]);
            }
        }
    }
    static class Node {
        int dest;
        int weight;

        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

}




