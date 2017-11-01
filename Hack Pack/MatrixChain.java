package com.company;
/**
 * Team Weinermobile
 * Dan Lott & Sarvesh Khemlani
 */
public class MatrixChain {

    public static void main(String[] args){

        //test case
        int [] array = {2,4,2,3,1,4};

        int [][] array2d = new int [array.length - 1][array.length - 1];

        System.out.print(mcm(array, array2d, array.length - 1));
    }


    static int mcm(int[] a, int[][] b, int c) {

        for(int i =0;i<c;i++){
            for(int j=0;j<c;j++){
                b[i][j]= Integer.MAX_VALUE;
            }
            b[i][i]=0;
        }

        for(int i = 0;i<=c;i++){
            for(int j =0;j<=(c-i);j++){
                for(int k = j; k < (j+i-1);k++){
                    if(b[j][j+i-1]> b[j][k]+b[k+1][j+i-1]+(a[j]*a[k+1]*a[i+j])){
                        b[j][j+i-1] = b[j][k]+b[k+1][j+i-1]+(a[j]*a[k+1]*a[i+j]);
                    }
                }
            }
        }

        return b[0][c-1];

    }





}