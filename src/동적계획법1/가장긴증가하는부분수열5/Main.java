package 동적계획법1.가장긴증가하는부분수열5;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14003
// 분할정복 > binary tree
// LIS 알고리즘
public class Main {
    static int N;
    static int[] num, index;
    static ArrayList<Integer> lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new int[N];
        index = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        if(N == 1){
            System.out.println("1\n"+num[0]);
            return;
        }

        lis = new ArrayList<>();
        lis.add(num[0]);
        for(int i = 1; i < N; i++){
            if(num[i] > lis.get(lis.size()-1)){
                lis.add(num[i]);
                index[i] = lis.size() - 1;
            }else{
                search(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lis.size() + "\n");
        // 역추적 경로를 저장할 stack
        Stack<Integer> stack = new Stack();
        int findCnt = lis.size() - 1;
        for(int i = N-1; findCnt>=0 && i >= 0; i--){
            if(index[i] == findCnt){
                findCnt--;
                stack.push(num[i]);
            }
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb.toString());
    }

    private static void search(int i) {
        int start, end, mid;
        start = 0;
        end = lis.size() - 1;

        while(start < end){
            mid = (end + start)/2;
            if(lis.get(mid) >= num[i]){
                end = mid;
            }else{
                start = mid + 1;
            }
        }

        lis.set(start, num[i]);
        index[i] = start;
    }
}
