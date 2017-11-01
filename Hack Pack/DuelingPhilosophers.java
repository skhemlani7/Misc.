/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class duel {

    private static boolean[][] specificDependenciesArray;//tracks if the essay in row feeds the essay in column
    private static int[] dependenciesArray;//array of dependencies measured in the sum
    private static int size;//size for making arrays

    private static void generateRelationships(int n) {
        size = n;//size is the number of essays
        specificDependenciesArray = new boolean[size][size];//make a new boolean 2D array size by size dimensions
        dependenciesArray = new int[size];//make a new dependencies array of size size
        for (int i = 0; i < size; ++i) {//for the length of size
            dependenciesArray[i] = 0;//set dependencies to 0
            for (int j = 0; j < size; ++j) {//for the length of size
                specificDependenciesArray[i][j] = false;//graph gets false since j doesn't need i
            }
        }
    }

    private static boolean checkOrder() {
        for (int x : dependenciesArray) {//for the length of dependenciesArray
            if (x != -1) {//while we haven't found an x=1
                return false;//return false
            }
        }
        return true;//if not we return true
    }

    private static boolean startingChoices;

    private static int findNextFreeNode() {
        int count = 0;//current count starts at 0
        int result = -1;//result starts at -1
        for (int i = 0; i < size; ++i) {//for the max size
            if (dependenciesArray[i] == 0) {//if there are no dependencies at the current index
                result = i;//result gets the current free node
                startingChoices = startingChoices || (count > 0);//if true foundMulti stays true
                //if not but count is above 0 then it gets true as well
                ++count;//add to count
            }
        }
        return result;//returns the next free node
    }

    private static void clearDependencies(int u) {
        for (int d = 0; d < size; ++d) {//for the length of the size
            if (specificDependenciesArray[d][u]) {//if d feeds u
                --dependenciesArray[d];//d feeds one less u
            }
        }
        dependenciesArray[u] = -1;//u needs one less to begin
    }

    private static int sort() {
        startingChoices = false;//to begin we haven't found multiple starts
        while (!checkOrder()) {//while we must still assign an order
            int x = findNextFreeNode();//we get the next independent node
            if (x == -1) {//if we do not find a free node
                return 0;//there are no ways to arrange this
            }
            clearDependencies(x);//if there is a free node, add it to the list
        }
        if(startingChoices){
            return 2;
        }else{
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));//gets standard input
        for (;;) {//infinite for loop until we want to leave
            int n = in.nextInt();//n is the number of essays to make room for
            int m = in.nextInt();//m is the number of relations to make room for
            if ((n == 0) && (m == 0)) {//if we encounter "0 0" we have reached the end of the file
                break;//we are done adding
            }
            generateRelationships(n);//make a new graph for n essays to sort
            for (int i = 0; i < m; ++i) {//for the number of relations
                int d = in.nextInt() - 1;//d is the essay required for u
                int u = in.nextInt() - 1;//d is required for u
                specificDependenciesArray[d][u] = true;//we say that for [d],[u] will be true because it feeds it
                ++dependenciesArray[d];//adds to the number of dependencies on d
            }
            System.out.format("%d%n", sort());//we are done adding, we will now sort
        }
    }

}
