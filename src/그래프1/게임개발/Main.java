package 그래프1.게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1516
// 위상정렬
// 정점에 간선이 없는 것 부터 queue 넣어서 시간 계산
// 선행된 빌딩은 여러개일 수 있음을 주의하자
// 이미 선행된 빌딩의 누적된 시간과 현재 진행된 선행 빌딩의 시간 중 큰 값을 사용 
public class Main {
    static int N;
    static Queue<Integer> queue = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] time;
    static int[] d;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        d = new int[N+1];
        time = new int[N+1];
        result = new int[N+1];
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Integer>());
        }

        StringTokenizer st;
        for(int i = 1; i <= N; i++){

            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            time[i] = t;

            while(b != -1){
                graph.get(b).add(i);
                d[i]++;

                b = Integer.parseInt(st.nextToken());
            }
        }

        checkTime();
    }

    static void checkTime(){
        for(int i = 1; i <= N; i++){
            if(d[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int target = queue.poll();
            result[target] += time[target];
            for(int next : graph.get(target)){
                d[next]--;
                result[next] = Math.max(result[target], result[next]);
                if(d[next] == 0){
                    queue.offer(next);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            System.out.println(result[i]);
        }
    }
}
