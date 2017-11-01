import java.io.File;
import java.util.*;

/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class bfsDfs {
    static public class Node{
        int value=0;
        boolean visit = false;
        ArrayList<Node> kids = new ArrayList<Node>();
        void Node(int in){
            this.value=in;
        }
        void setKids(Node kid){


            kids.add(kid);
        }
        ArrayList<Node> getKids(){
            return kids;
        }
        boolean hasKids(){
            if (kids.isEmpty()){
                return false;
            }else{
                return true;
            }
        }
    }


    public static void main(String args[]){
        int tCases=0,i=0,j=0,k=0;
        Scanner scan = new Scanner(System.in);
        //File input = new File("");
        //Scanner scan = new Scanner(input);
        Node base = new Node();
        base.value=6;
        Node temp=new Node();

        Node tempTwo=new Node();
        tempTwo.value=4;
        for(i =0;i<10;i++){
            Node me = new Node();
            me.value=7;
            tempTwo.setKids(me);
        }
        base.setKids(tempTwo);
        Node tempThree=new Node();
        tempThree.value=9;
        base.setKids(tempThree);
        Node tempFour=new Node();
        tempFour.value=8;
        base.setKids(tempFour);
        Node nest = new Node();
        Node tempFive=new Node();
        nest.value=3;
        tempFive.value=99;
        for(i =0;i<10;i++){
            Node me = new Node();
            me.value=17;
            nest.setKids(me);
        }
        tempFive.setKids(nest);
        base.setKids(tempFive);

        dfs(base);

        bfs(base);

        pl("As you can see they are different, Computing has occurred!");

    }

    public static void bfs(Node root){
        Queue bfsQueue = new LinkedList();
        bfsQueue.add(root);
        pl("bfs starting:\n");
        p(root.value);
        root.visit=true;
        while(!bfsQueue.isEmpty()){
            Node temp = (Node) bfsQueue.remove();
            Node young = null;
            while((young=getKid(temp))!=null){
                young.visit=true;
                p(young.value);
                bfsQueue.add(young);
            }
        }
        reset(root);
    }

    public static void dfs(Node root){
        Stack<Node> stack = new Stack();
        stack.push(root);
        root.visit=true;
        pl("dfs starting:\n");
        p(root.value);
        while(!stack.isEmpty()){
            Node temp = (Node)stack.peek();
            Node young = null;
            young=getKid(temp);
            if(young!=null){
                young.visit=true;
                p(young.value);
                stack.push(young);
            }else{
                stack.pop();
            }

        }
        reset(root);

    }

    public static Node getKid(Node parent){
        if (parent.hasKids()){
            ArrayList<Node> kids = parent.getKids();
            for(int i = 0;i<kids.size();i++){
                if (!kids.get(i).visit){
                    return kids.get(i);
                }
            }
        }
            return null;


    }
    public static void reset(Node parent){
        parent.visit=false;
        if (parent.hasKids()){
            ArrayList<Node> kids = parent.getKids();
            for(int i = 0;i<kids.size();i++){
                kids.get(i).visit=false;
                if(kids.get(i).hasKids()){
                    reset(kids.get(i));
                }


            }
        }
        pl("");



    }
    public static void pl(Object statement){
        System.out.println(statement);
    }
    public static void p(Object statement){
        System.out.print(statement);
    }
}
