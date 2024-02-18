package 동적계획법2.LCS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9252
// LCS DP
// https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
public class Main {
    static String str1, str2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();

        dp = new int[len1+1][len2+1];
        int max = 0;
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[len1][len2]);

        String result = "";
        int i = len1;
        int j = len2;
        while(dp[i][j] != 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                result = str1.charAt(i-1) + result;
                i--;
                j--;
            }
            else if(dp[i-1][j] == dp[i][j]){
                i--;
            }else if(dp[i][j-1] == dp[i][j]){
                j--;
            }
        }

        System.out.println(result);

    }
}
