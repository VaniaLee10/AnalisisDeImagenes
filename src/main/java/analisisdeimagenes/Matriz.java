package analisisdeimagenes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */

import java.awt.Color;


public class Matriz {
    
    public static void main(String[] args){
        int n = 10;
        String matriz[][] = new String [n][n];
        
        // Draw the square in the matrix.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                    matriz[i][j] = "X";
                }else{
                    matriz[i][j] = ".";
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }    
        
        Color p = new Color(173,216,230);
        
    }
    
}
