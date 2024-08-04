package 그래프2.단절선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선이므로 root 여부를 확인하지 않아도 된다
// 역전이 불가능하다면 단절선
// 이미 방문한 정점이라면 visited로 비교
public class Main {
    static int V, E;
    static ArrayList<Integer>[] edgeList;
    static int[] visited;
    static PriorityQueue<Edge> result;
    static int order;

    static class Edge implements Comparable<Edge>{
        int a, b;
        public Edge(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Edge o) {
            return (this.a - o.a) == 0? this.b-o.b : this.a-o.a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        result = new PriorityQueue<>();

        edgeList = new ArrayList[V+1];
        visited = new int[V+1];
        for(int i = 1; i < V+1; i++){
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edgeList[a].add(b);
            edgeList[b].add(a);
        }

        order = 1;
        for(int i = 1; i < V+1; i++){
            if(visited[i] == 0)
                dfs(i, 0);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()+"\n");
        while(!result.isEmpty()){
            Edge cur = result.poll();
            sb.append(cur.a+" "+cur.b+"\n");
        }

        System.out.println(sb.toString());
    }

    private static int dfs(int root, int parent) {
        visited[root] = order;
        order++;
        int retR = visited[root];
        for(int next : edgeList[root]){
            if(parent == next) continue;
            if(visited[next] == 0){
                int retC = dfs(next, root);

                if(retC > visited[root]){
                    if(next > root){
                        result.add(new Edge(root, next));
                    }else{
                        result.add(new Edge(next, root));
                    }
                }
                retR = Math.min(retR, retC);
            }else{ // 이미 방문, 사이클 O
                retR = Math.min(retR, visited[next]);
            }
        }

        return retR;
    }


}
