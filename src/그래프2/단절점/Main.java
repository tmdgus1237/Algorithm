package 그래프2.단절점;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11266
// 단절점 찾는 방법을 잘 구현하는 것이 문제
// 단절점 기준
// 1. root인 경우, 2개 이상의 간선이 존재하는 경우 단절점
// 2. root가 아닌 경우, 자식 노드가 갈 수 있는 최소 방문 순서가 내 방문 순서보다 큰 경우 단절점
// 주의할 점
// 양방향 간선이므로 왔던 방향 다시 갈 수 있다는 점 주의
// 최소 방문 순서 확인할 때 조건 비교 주의
// 출력 초과 > 로직 결과가 틀린 것
public class Main {
    static int V, E, order;
    static int[] visitOrder;
    static boolean[] result;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for(int i = 0; i < V+1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visitOrder = new int[V+1];
        result = new boolean[V+1];
        order = 1;

        for(int i = 1; i <= V; i++){
            if(visitOrder[i] == 0){
                dfs(i, 0, true);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = 0;
        for(int i = 1; i <= V; i++){
            if(result[i]) {
                bw.write(i+" ");
                count++;
            }
        }
        System.out.println(count);
        bw.flush();
    }

    static int dfs(int target, int parent, boolean isRoot){
        visitOrder[target] = order++;

        int minVisitOrder = visitOrder[target];
        int cnt = 0;
        for(int next : graph[target]){
            if(parent == next) continue;
            if(visitOrder[next] == 0){
                cnt++;
                int minVisiteOrderForChind = dfs(next, target, false);
                if(!isRoot && minVisiteOrderForChind >= visitOrder[target]){
                    result[target] = true;
                }
                minVisitOrder = Math.min(minVisitOrder, minVisiteOrderForChind);
            }else{
                minVisitOrder = Math.min(visitOrder[next], minVisitOrder);
            }
        }

        if(isRoot && cnt > 1){
            result[target] = true;
        }

        return minVisitOrder;
    }
}
