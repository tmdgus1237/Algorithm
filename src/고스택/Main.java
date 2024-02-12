package 고스택;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static Stack<Long> stack = new Stack<>();
    static String[] program = new String[100000];
    static long[] arrayForNum = new long[100000];
    static int indexForNum = 0;
    static final int MAX = 1000000000;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int p = 0;
        int x = 0;

        while(true) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

            String cmd = stk.nextToken();
            if (cmd.equals("END")) {
                // 숫자 입력 받기 시작
                StringTokenizer cntVal = new StringTokenizer(br.readLine(), " ");
                int cnt = Integer.parseInt(cntVal.nextToken());


                for (int i = 0; i < cnt; i++) { // 숫자 별 독립적으로 프로그램 처리
                    StringTokenizer numVal = new StringTokenizer(br.readLine(), " ");
                    long num = Integer.parseInt(numVal.nextToken());

                    // 초기화
                    Boolean status = true;
                    indexForNum = 0;
                    stack.clear();
                    stack.push(num);

                    for(int j = 0; j < p; j++){
                        if(!process(program[j], num)){
                            status = false;
                            break;
                        }
                    }
                    if(!status || stack.size() != 1){
                        bw.write("ERROR\n");
                    } else if(status){
                        bw.write(stack.pop()+"\n");
                    }
                }

                // 작업 완료 후 index 값 초기화
                p = 0;
                x = 0;

                bw.write("\n");
                br.readLine();
                continue;
            } else if (cmd.equals("QUIT")) { break;
            } else if (cmd.equals("NUM")) { // 명령어 숫자 추가 입력
                arrayForNum[x] = Integer.parseInt(stk.nextToken());
                x++;
            }

            // 프로그램 저장
            program[p] = cmd;
            p++;
        }

        bw.flush();
        bw.close();
        br.close();

    }

    private static Boolean process(String pro, long num) {
        if(pro.equals("NUM")){ // 0보다 큰 값
            stack.push(arrayForNum[indexForNum++]);
            return true;
        }
        
        if(stack.empty()){
            return false;
        }
        long temp1 = stack.pop();

        switch(pro) {
            case "POP":
                break;
            case "INV":
                stack.push(temp1 * -1);
                break;
            case "DUP":
                stack.push(temp1);
                stack.push(temp1);
                break;
            default:
                if(stack.empty()){
                    return false;
                }
                long temp2 = stack.pop();
                switch(pro) {
                    case "SWP" :
                        stack.push(temp1);
                        stack.push(temp2);
                        break;
                    case "ADD" :
                        stack.push(temp1+temp2);
                        break;
                    case "SUB" :
                        stack.push(temp2-temp1);
                        break;
                    case "MUL" :
                        stack.push(temp1*temp2);
                        break;
                    default :
                        if (temp1 == 0) return false;
                        switch(pro) {
                            case "DIV":
                                int m1 = 1;
                                if((temp1 < 0 && temp2 > 0) || (temp1 > 0 && temp2 < 0)) m1 = -1;
                                stack.push((Math.abs(temp2) / Math.abs(temp1)) * m1);
                                break;
                            case "MOD":
                                int m2 = 1;
                                if(temp2 < 0) m2 = -1;
                                stack.push((Math.abs(temp2) % Math.abs(temp1)) * m2);
                                break;
                        }
                }
        }

        if(!stack.empty() && Math.abs(stack.peek()) > MAX){
            return false;
        }

        return true;
    }


}
