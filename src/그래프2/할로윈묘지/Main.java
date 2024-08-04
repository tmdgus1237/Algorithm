package 그래프2.할로윈묘지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 벨만포드 알고리즘
// 정점 : N, 간선 : E
// N-1 회 만큼 E 간선을 돌면서 최소 값 확인
// 마지막 한번 더 E 간선을 돌았을 때 최소 값이 바뀌면 무한 사이클이 있음
// 없다면 값 출력
public class Main {
    static int W, H, G, E;
    static int[][] map;
    static long[][] dist;

    static ArrayList<Edge> list;
    static int[] dx = new int[] {0, -1, 0, 1};
    static int[] dy = new int[] {1, 0, -1, 0};

    static StringBuilder sb;
    static Boolean isLoop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while(true){
            isLoop = false;
            StringTokenizer st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0){
                break;
            }
            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());

            map = new int[W][H];
            dist = new long[W][H];

            int a, b;
            for(int i = 0; i < G; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                map[a][b] = -1;
            }

            st = new StringTokenizer(br.readLine());
            E = Integer.parseInt(st.nextToken());

            int c, d, t;
            list = new ArrayList<>();
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());
                map[a][b] = 1;
                list.add(new Edge(new int[] {a,b}, new int[] {c,d}, t));
            }



            getGraph();
            BellmanFord();


        }


        System.out.println(sb.toString());

    }

    private static void BellmanFord() {
        for(int i = 0; i < W; i++){
            for(int j = 0; j < H; j++){
                dist[i][j] = Long.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        for(int i = 0; i < (W*H)-1; i++){
            for(Edge e : list){
                int a = e.start[0];
                int b = e.start[1];
                int c = e.end[0];
                int d = e.end[1];

                if(dist[a][b] != Long.MAX_VALUE && dist[c][d] > dist[a][b] + e.t){
                    dist[c][d] = dist[a][b] + e.t;
                }
            }
        }

        boolean isCycle = false;
        for(Edge e : list){
            int a = e.start[0];
            int b = e.start[1];
            int c = e.end[0];
            int d = e.end[1];

            if(dist[a][b] != Long.MAX_VALUE && dist[c][d] > dist[a][b] + e.t){
                isCycle = true;
                break;
            }
        }

        if(isCycle){
            sb.append("Never\n");
        }else if(dist[W-1][H-1] == Long.MAX_VALUE){
            sb.append("Impossible\n");
        }else{
            sb.append(dist[W-1][H-1]+"\n");
        }

    }

    private static void getGraph() {
        for(int i = 0; i < W; i++){
            for(int j = 0; j < H; j++){
                if(map[i][j] != 0 || (i == W-1 && j == H-1)) continue;
                for(int k = 0; k < 4; k++){
                    int x = i+dx[k];
                    int y = j+dy[k];
                    if(x >=0 && x < W && y >= 0 && y < H){ // 진출 가능
                        if(map[x][y] >= 0){ // 잔디 혹은 구멍
                            list.add(new Edge(new int[] {i,j}, new int[] {x,y}, 1));
                        }
                    }
                }
            }
        }
    }

    static class Edge{
        int[] start;
        int[] end;
        int t;
//        boolean isLoop = false; // 자기 자신으로 가는 음수간선인지
        public Edge(int[] start, int[] end, int t){
            this.start = start;
            this.end = end;
            this.t = t;

        }
    }
}
