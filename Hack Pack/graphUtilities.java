import java.util.*;

/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class graphUtilities {

    static int max = 1000000000;//at 1B currently



    public static void main(String[] args) {
        int[][] test = new int[5][5];
        Scanner scan = new Scanner(System.in);
        test = new int[][]{{0, 6, max, max, 7}, {max, 0, 5, -4, 8}, {max, -2, 0, max, max}, {2, max, 7, 0, max}, {max, max, -3, 9, 0}};
        int[] res = bellmanFord(test, 0);
        pl("Bellman Ford: ");
        for (int i = 0; i < res.length; i++) {
            p(res[i] +" ");
        }
        pl("We expect to find:\n0 6 4 2 7");
        int testTwoSize = 6;
        int[][] testTwo = new int[testTwoSize][testTwoSize];
        for (int i = 0; i < testTwo.length; i++) Arrays.fill(testTwo[i], 0);
            testTwo=new int[][]{{0,1,0,0,0,1},{0,0,1,1,0,0},
            {0,0,0,0,0,0},{0,0,0,0,1,0},{0,0,0,0,0,1},{0,0,1,0,0,0}};

        int[] resTwo;
        resTwo = topo(testTwo, 1);
        pl("\nTopo Sort");
        for (Integer i : resTwo) {
            p(i +" ");
        }
        pl("We expect to find:\n0 2 5 4 3 1");

        int[][] testThree = new int[7][7];
        for(int i=0;i<testThree.length;i++)Arrays.fill(testThree[i],-1);
        testThree[0][1] = testThree[1][0] = 1;
        testThree[1][2] = testThree[2][1] = 1;
        testThree[1][3] = testThree[3][1] = 3;
        testThree[1][4] = testThree[4][1] = 2;
        testThree[2][5] = testThree[5][2] = 2;
        testThree[2][4] = testThree[4][2] = 4;
        testThree[0][5] = testThree[5][0] = 3;
        testThree[4][3] = testThree[3][4] = 3;
        testThree[5][4] = testThree[4][5] = 2;
        int[] resThree;
        resThree = dijkstra(testThree, 0);
        pl("\nDijkstra");
        for (Integer i : resThree) {
            p(i +" ");
        }
        pl("We expect to find:\n0 1 1 2 2 2 1");
        int[][] testFour = new int[4][4];
        for(int i=0;i<testFour.length;i++)Arrays.fill(testFour[i],max);
        testFour[1][0] = 1;
        testFour[0][2] = 100;
        testFour[3][1] = 100;
        testFour[2][3] = -100;
        testFour[1][2] = 300;

        int[][] resFour;
        resFour = floyds(testFour);
        pl("\nFloyds");
        for(int[] j : resFour) {
            for (int i = 0;i<1;i++) {
                p(j[i] + " ");
            }

        }
        pl("We expect to find:\n101 1 1 101");

    }

    /*
    Floyd-Warshall
    + Weights:Yes
    - Weights:Yes
    + Cycles:Yes
    - Cycles:No
    */

    static int[][] floyds(int[][] weights) {
        int i, j, k, l;
        l = weights.length;
        int distance[][] = new int[l][l];

        for (i = 0; i < l; i++) {
            for (j = 0; j < l; j++) {
                distance[i][j] = weights[i][j];
            }
        }
        for (k = 0; k < l; k++) {
            for (i = 0; i < l; i++) {
                for (j = 0; j < l; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        return distance;
    }

    /*
    Dijkstra
    + Weights: Yes
    - Weights: No
    + Cycles: Yes
    - Cycles: No
    */
    public static int[] dijkstra(int[][] graph, int start) {
        int i, j, vertex, bestseen;
        man[] detailCosts = new man[graph.length];
        for (i = 0; i < detailCosts.length; i++)
            detailCosts[i] = new man(max, start);
        detailCosts[start].distance = 0;
        for (i = 0; i < detailCosts.length - 1; i++) {
            vertex = 0;
            bestseen = max;
            for (j = 0; j < detailCosts.length; j++) {
                if (detailCosts[j].chose == false &&
                        detailCosts[j].distance < bestseen) {

                    bestseen = detailCosts[j].distance;
                    vertex = j;
                }
            }
            detailCosts[vertex].chose = true;
            for (j = 0; j < detailCosts.length; j++) {
                if (detailCosts[vertex].distance + graph[vertex][j]>0&&detailCosts[vertex].distance + graph[vertex][j] < detailCosts[j].distance) {
                    detailCosts[j].distance = detailCosts[vertex].distance + graph[vertex][j];
                    detailCosts[j].last = vertex;
                }
            }

        }
        int[] results = new int[detailCosts.length];
        for (j = 0; j < detailCosts.length; j++) results[j] = detailCosts[j].distance;
        return results;
    }


    /*
    * Topological Sort
    * Used for when you have to visit things in order
    * ex Dueling Philosophers
    */
    public static int[] topo(int[][] graph, int start) {
        int i,j,pos,index,vertNum;
        Stack<Integer> stack = new Stack<Integer>();
        vertNum = graph[start].length - 1;
        int[] result = new int[vertNum + 1];
        int[] visited = new int[vertNum + 1];
        pos = 1;
        index = start;
        i = start;
        visited[start] = 1;
        stack.push(start);
        while (!stack.isEmpty())
        {
            index = stack.peek();
            while (i <= vertNum)
            {

                if (graph[index][i] == 1 && visited[i] == 0)
                {
                    stack.push(i);
                    visited[i] = 1;
                    index = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            j = stack.pop();
            result[pos++] = j;
            i = ++j;
        }
        return result;
    }

        /*
        Bellman Ford
        Find shortest path from start to every other location
        output array of distances
        Cycle detection.
        */

    public static int[] bellmanFord(int[][] costs, int start) {
        int i, j, costsLength;
        costsLength = costs.length;
        int[] distance = new int[costsLength];
        int[] adjMatrix = new int[costsLength];
        for (i = 0; i < costsLength; i++) {
            if (i == start) {
                distance[i] = 0;
            } else {
                distance[i] = max;
                adjMatrix[i] = -1;
            }
        }
        for (i = 0; i < costsLength; i++) {
            for (j = 0; j < costsLength; j++) {
                if (costs[i][j] != max) {
                    if (distance[i] + costs[i][j] < distance[j]) {
                        distance[j] = distance[i] + costs[i][j];
                        adjMatrix[j] = i;
                    }
                }
            }
        }
        for (i = 0; i < costsLength; i++) {
            for (j = 0; j < costsLength; j++) {
                if (costs[i][j] != max) {
                    if (distance[i] + costs[i][j] < distance[j]) {
                        //we found a cycle
                    }
                }
            }
        }

        return distance;

    }

    public static void pl(Object statement) {
        System.out.println(statement);
    }

    public static void p(Object statement) {
        System.out.print(statement);
    }
    public static class man {
        int distance;
        int last;
        boolean chose;


        public man(int stop, int start) {
            distance = stop;
            last = start;
            chose = false;
        }
    }
}

