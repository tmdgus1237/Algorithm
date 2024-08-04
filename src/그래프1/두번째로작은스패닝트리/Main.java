package 그래프1.두번째로작은스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// MST 크루스칼 + LCA
// 크루스칼 알고리즘을 통해 MST를 구한다
// 사용한 간선에 대해 표시해두고 이 외의 간선 중에 하나로 넣었을 때 빠지는 간선 최대의 값을 찾는다 > LCA

public class Main {
    static int V, E, K;
    static ArrayList<Line> lines;
    static int[] parent;

    static ArrayList<Line>[] lcaList;
    static int[] depth;
    static int[][] maxWeight, secMaxWeight, lcaParent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        parent = new int[V+1];
        lines = new ArrayList<>();
        lcaList = new ArrayList[V+1];
        for(int i = 0; i < V+1; i++){
            lcaList[i] = new ArrayList<>();
        }

        for(int i = 1; i <= V; i*=2){
            K++;
        }
        depth = new int[V+1];
        lcaParent = new int[V+1][K];
        maxWeight = new int[V+1][K];
        secMaxWeight = new int[V+1][K];


        int a, b, w;
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            lines.add(new Line(a,b,w));
        }

        int cnt = 0;
        int minimumCost = 0;
        Collections.sort(lines);

        for(int i = 0; i < E; i++){
            if(cnt == V-1) break;

            Line cur = lines.get(i);

            int rootA = find(cur.edgeA);
            int rootB = find(cur.edgeB);

            if(rootA == rootB) continue;

            union(cur.edgeA,cur.edgeB);
            minimumCost += cur.weight;
            cur.isShortest = true;
            cnt++;

            lcaList[cur.edgeA].add(new Line(cur.edgeB, cur.weight));
            lcaList[cur.edgeB].add(new Line(cur.edgeA, cur.weight));

        }

        if(cnt != V-1 || E <= V-1){
            System.out.println(-1);
            return;
        }
        
        dfs(1,1);
        fillParent();

        int max;
        Long ans = Long.MAX_VALUE;

        for(Line l : lines){
            if(l.isShortest) continue;

            max = lca(l.edgeA, l.edgeB, l.weight);

            if(max == -1) continue;

            ans = Math.min(ans, l.weight - max);

        }

        if(ans < Long.MAX_VALUE){
            System.out.println(minimumCost+ans);
        }else{
            System.out.println(-1);
        }

    }

    private static int lca(int edgeA, int edgeB, int weight) {
        // A가 무조건 깊다
        if(depth[edgeA] < depth[edgeB]){
            int temp = edgeA;
            edgeA = edgeB;
            edgeB = temp;
        }

        int result = -1;

        for(int i = K-1; i >= 0; i--){
            if(Math.pow(2,i) <= (depth[edgeA]-depth[edgeB])){
                if(maxWeight[edgeA][i] != weight){
                    result = Math.max(maxWeight[edgeA][i], result);
                }else if(secMaxWeight[edgeA][i] != -1){
                    result = Math.max(secMaxWeight[edgeA][i], result);
                }
                edgeA = lcaParent[edgeA][i];
            }
        }

        if(edgeA == edgeB) return result;

        for(int i = K-1; i >= 0; i--){
            if(lcaParent[edgeA][i] != lcaParent[edgeB][i]){
                if(maxWeight[edgeA][i] != weight){
                    result = Math.max(maxWeight[edgeA][i], result);
                }else if(secMaxWeight[edgeA][i] != -1){
                    result = Math.max(secMaxWeight[edgeA][i], result);
                }

                if(maxWeight[edgeB][i] != weight){
                    result = Math.max(maxWeight[edgeB][i], result);
                }else if(secMaxWeight[edgeB][i] != -1){
                    result = Math.max(secMaxWeight[edgeB][i], result);
                }

                edgeA = lcaParent[edgeA][i];
                edgeB = lcaParent[edgeB][i];
            }
        }

        if(maxWeight[edgeA][0] != weight){
            result = Math.max(maxWeight[edgeA][0], result);
        }else if(secMaxWeight[edgeA][0] != -1){
            result = Math.max(secMaxWeight[edgeA][0], result);
        }

        if(maxWeight[edgeB][0] != weight){
            result = Math.max(maxWeight[edgeB][0], result);
        }else if(secMaxWeight[edgeB][0] != -1){
            result = Math.max(secMaxWeight[edgeB][0], result);
        }

        return result;

    }

    private static void fillParent() {
        int max, secMax;
        int pMax, pSecMax;

        for(int i = 1; i < K;i++){
            for(int j = 1; j <= V; j++){
                int p = lcaParent[j][i-1];

                if(p != 0 && lcaParent[p][i-1] != 0){
                    max =  maxWeight[j][i-1];
                    secMax = secMaxWeight[j][i-1];

                    pMax = maxWeight[p][i-1];
                    pSecMax = secMaxWeight[p][i-1];

                    if(max > pMax){
                        maxWeight[j][i] = max;
                        secMaxWeight[j][i] = Math.max(pMax, secMax);
                    }else if(max < pMax){
                        maxWeight[j][i] = pMax;
                        secMaxWeight[j][i] = Math.max(max, pSecMax);
                    }else{
                        maxWeight[j][i] = max;
                        secMaxWeight[j][i] = Math.max(secMax, pSecMax);
                    }
                    lcaParent[j][i] = lcaParent[p][i-1];
                }

            }
        }
    }

    static void dfs(int p, int level){
        depth[p] = level;

        for(Line l : lcaList[p]){
            if(depth[l.edgeB] == 0){
                dfs(l.edgeB, level+1);
                lcaParent[l.edgeB][0] = p;
                maxWeight[l.edgeB][0] = l.weight;
                secMaxWeight[l.edgeB][0] = -1;
            }

        }
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        parent[rootB] = rootA;
    }

    static int find(int a){
        if(parent[a] == 0) return a;
        return parent[a] = find(parent[a]);
    }


    static class Line implements Comparable<Line>{
        int edgeA;
        int edgeB;
        int weight;
        boolean isShortest;
        public Line(int a, int b, int w){
            this.edgeA = a;
            this.edgeB = b;
            this.weight = w;
            this.isShortest = false;
        }

        public Line(int b, int w){
            this.edgeB = b;
            this.weight = w;
        }

        @Override
        public int compareTo(Line o) {
            return this.weight - o.weight;
        }
    }
}

