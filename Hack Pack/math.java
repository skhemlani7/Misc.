import java.io.File;
import java.util.*;

/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class math {

    public static void main(String[] args){
        int[] key=new int[]{4,7,6};
        int[] num ={2,4,6,8};
        int[] pool = new int[key.length];
        boolean visited[] = new boolean[num.length];
        pl("The permutations for 4,7,6");
        permutation(pool,visited,0,key);
        pl("\nThe combinations for 4,7,6");
        combination(0, key.length, visited, key);
        pl("\nThe GCD of 48 and 180 is...");
        pl(gcd(48,180));
        pl("The LCM of 48 and 180 is...");
        pl(lcm(48,180));
        pl("The Prime Factors for 120 are...");
        for(Long i : primeFactors(120)){
            p(i+" ");
        }



    }

    /*
    * Finds the greatest common divisor of two numbers
    * */

     public static int gcd(int first, int second){
        int temp=0;

        while(first!=0&&second!=0){
            temp=second;
            second=first%second;
            first=temp;
        }
        if (first==0){
            return second;
        }
        return first;
    }

    /*
    * Finds the least common multiple of two numbers
    * */

    public static int lcm(int first, int second){
        return first*second/gcd(first,second);
    }

    /*
    * Used to find the prime factorization of numbers
    * returns an ArrayList of the prime factors
    */

    public static ArrayList<Long> primeFactors(long number){


        ArrayList<Long> result = new ArrayList<Long>();
        long i=0;
        long temp = number;
        for(i=2;i*i<=temp;i++){
            while(temp%i==0){
                result.add(i);
                temp/=i;

            }
        }

        if(temp>1){
            result.add(temp);
        }
        return result;
    }


    /*
    * Takes in Array of length
    * visited array
    * current index
    * array of elements to permute
    *
    * generates numbers inbound of the array to be used, then prints them in that order
    * */
    public static void permutation(int[] stuff,boolean[] visited,int current,int[] print){
        if(current==stuff.length) {
            for(Integer i : stuff)
                System.out.print(print[i]);
            System.out.print(" ");
            return;
        }


        for(int i = 0; i < stuff.length; i++){

            if(!visited[i]){
                stuff[current] = i;
                visited[i] = true;
                permutation(stuff, visited, current+1,print);
                visited[i] = false;
            }
        }
    }

    /*
    *Takes in
    * Start index
    * Stop index
    * visited array
    * array of values to print
    *
    * We find all the combinations of numbers between start and stop
    * Then we print the values at that index
    * */
    public static void combination(int start, int stop, boolean[] visited,int[] print){
        if(start == stop){
            for(int i = 0; i < visited.length; i++)
                if(visited[i])
                    System.out.print(print[i]);
            System.out.print(" ");
            return;
        }

        visited[start] = true;
        combination( start + 1, stop,visited,print);
        visited[start] = false;
        combination( start +1, stop,visited,print);
    }

/*
* Short hand for printing
* */
    public static void pl(Object statement){
        System.out.println(statement);
    }
    public static void p(Object statement){
        System.out.print(statement);
    }
}
