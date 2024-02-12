package 조합론.카드놓기;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/5568
// 순열 문제이지만 중복이 없어야 한다.
// 순열 > dfs 깊이 : 카드 선택 개수
// 중복 X > SET 자료구조 사용
public class Main {
    static int N, K;
    static String[] card = new String[10];
    static boolean visited[] = new boolean[10];
    static Set<String> result = new HashSet<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            card[i] = br.readLine();
        }

        dfs("", K);

        System.out.println(result.size());
    }

    public static void dfs(String num, int depth){
        if(depth == 0){
            result.add(num);
            return;
        }

        for(int i = 0; i < N; i++){
           if(!visited[i]){
               visited[i] = true;;
               dfs(num+card[i],depth-1);
               visited[i] = false;
           }
        }
    }
}
