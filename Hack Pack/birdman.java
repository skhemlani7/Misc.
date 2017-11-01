/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */

import java.util.*;
import java.awt.geom.Line2D;

public class birdman {

    public static void main(String[] args) {
	// write your code here
        Scanner s = new Scanner(System.in);
        int numCases = s.nextInt();
        int birdX, birdY, sunX, sunY;
        int p1x, p1y, p2x, p2y;

        for(int i = 0; i<numCases; i++){

            birdX = s.nextInt();
            birdY = s.nextInt();

            sunX = s.nextInt();
            sunY = s.nextInt();

            Line2D line1 = new Line2D.Double();
            line1.setLine(birdX, birdY, sunX, sunY);

            p1x = s.nextInt();
            p1y = s.nextInt();
            p2x = s.nextInt();
            p2y = s.nextInt();

            Line2D line2 = new Line2D.Double();
            line2.setLine(p1x, p1y, p2x, p2y);

            if(line1.intersectsLine(line2)){
                System.out.println("Move to the left or right!");
            }else{
                System.out.println("Good picture, Birdman!");
            }

        }


    }
}
