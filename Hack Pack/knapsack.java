import java.io.File;
import java.util.*;

/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class knapsack {



    public static void main(String args[]){
        int tCases=0,i=0,j=0,k=0;
        Scanner scan = new Scanner(System.in);
        //File input = new File("");
        //Scanner scan = new Scanner(input);
        int[] cost = {1,2,3,2,2};
        int[] reward =   {8,4,0,5,3};
        int size = 4;
        pl("Knapsack with coded inputs");
        pl(knapsack(size,cost,reward));
        pl("We expect to get 13");


    }


    /*
    * knapsack without replacement
    */
    public static int knapsack(int size,int[] costs ,int[] reward){
        int i,j;
        int availSize = costs.length;
        int[][] result= new int[availSize+1][size+1];
        for(i=1;i<=availSize;i++){
            for(j=0;j<=size;j++){
                if(costs[i-1]<=j){
                    result[i][j]=Math.max(result[i-1][j],reward[i-1]+result[i-1][j-costs[i-1]]);
                }else{
                    result[i][j]=result[i-1][j];
                }
            }
        }
        return result[availSize][size];
    }
    public static void pl(Object statement){
        System.out.println(statement);
    }
}
