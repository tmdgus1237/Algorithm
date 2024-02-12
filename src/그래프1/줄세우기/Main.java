package 그래프1.줄세우기;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2252
// 위상 정렬 문제
// A와 B 학생의 키 비교를 그래프로 표현
// 정점 0인 학생을 Queue 에 넣고 빼면서 그 다음 학생과의 연결고리 삭제
// 다시 정점 0인 학생을 Queue 넣으면서 반복
// 답이 여러개 나올 수 있으며 키 비교가 된 학생들 끼리의 순서가 중요
public class Main {
    static int N, M;
    static int[] d;
    static Queue<Integer> q = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N+1];
        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int studentA = Integer.parseInt(st.nextToken());
            int studentB = Integer.parseInt(st.nextToken());

            graph.get(studentA).add(studentB);
            d[studentB]++;
        }

        // 정점 0인 경우 q에 insert
        for(int i = 1; i <= N; i++){
            if(d[i] == 0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int num = q.remove();
            wr.write(num+" ");

            // 간선 제거
            for(int nextNum : graph.get(num)){
                d[nextNum]--;
                if(d[nextNum] == 0){
                    q.add(nextNum);
                }
            }
        }

        wr.flush();
    }



}
