package 그래프1.교수님은기다리지않는다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3830
// 유니온-파인드
// 연결된 경우 차이를 구할 수 있고 연결되지 않은 경우 차이를 구할 수 없다
// root와의 차이를 기준으로 무게 차이를 구한다
// index 값 실수하기 쉬움

public class Main {
    static int N, M;
    static int[] parent;
    static int[] dist;
    static int a, b, w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N+1];
            parent = new int[N+1];

            if(N == 0 && M == 0) break;

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                String action = st.nextToken();
                if(action.equals("!")){
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                }else if(action.equals("?")){
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    if(find(a) == find(b)) sb.append(dist[b] - dist[a]+"\n");
                    else sb.append("UNKNOWN\n");
                }
            }

        }
        System.out.println(sb.toString());
    }

    public static void union(int x, int y, int w){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return;

        dist[rootY] = dist[x] - dist[y] + w;
        parent[rootY] = rootX;
    }

    public static int find(int x){
        if(parent[x] == 0) return x;

        int p = find(parent[x]);
        dist[x] += dist[parent[x]]; // x의 root 무게를 더하여 갱신한다

        return parent[x] = p;
    }
}
