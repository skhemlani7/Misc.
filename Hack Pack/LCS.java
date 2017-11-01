package com.company;

import java.io.*;
/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class LCS {

    public static void main(String [] args) throws IOException {

        System.out.println(runLCS("RACECAR", "CREAM"));
    }


    public static int runLCS(String a, String b) {

        int[][] prefixStrings = new int[a.length()+1][b.length()+1];

        for (int i=0; i<=a.length(); i++) {
            prefixStrings[i][0] = 0;
        }
        for (int i=0; i<=b.length(); i++) {
            prefixStrings[0][i] = 0;
        }

        for (int i = 1; i<=a.length(); i++) {
            for (int j = 1; j<=b.length(); j++) {
                if(a.charAt(i-1) == b.charAt(j-1)) {
                    prefixStrings[i][j] = 1 + prefixStrings[i - 1][j - 1];
                }else {
                    prefixStrings[i][j] = Math.max(prefixStrings[i][j - 1], prefixStrings[i - 1][j]);
                }
            }
        }

        return prefixStrings[a.length()][b.length()];
    }

}

