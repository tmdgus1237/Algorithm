package 그래프1.네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1922
// 최소신장트리 (크루스칼 알고리즘)
// 1. 최소 비용의 간선 가져오기
// 2. 간선 연결 시 Cycle 여부 확인
// 2-1. Cycle 아닌 경우, 연결
// 2-2. Cycle인 경우, 제외
// 2-3. N-1 개의 간선이 될때까지 반복
public class Main {

    static int N, M;
    static PriorityQueue<WeightInfo> pq = new PriorityQueue<WeightInfo>();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new WeightInfo(start, end, weight));
        }

        arr = new int[N+1];
        for(int i = 1; i < N+1; i++){
            arr[i] = i;
        }

        int cnt = 0;
        int resultWeight = 0;

        while(!pq.isEmpty()){
            if(cnt == N-1) break;
            WeightInfo wi = pq.poll();

            if(find(wi.start) != find(wi.end)){ // 사이클 여부 확인
                union(wi.start, wi.end);
                cnt++;
                resultWeight += wi.weight;
            }
        }

        System.out.println(resultWeight);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        arr[pa] = pb;
    }

    static int find(int a){
        if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }
}

class WeightInfo implements Comparable<WeightInfo> {
    int start, end, weight;

    public WeightInfo(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightInfo o) {
        return this.weight - o.weight;
    }
}