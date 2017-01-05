/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package go;

import java.util.Scanner;

/**
 *
 * @author thibault
 */
public class Go {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Goban goban = new Goban(9);
        System.out.println("Charger partie(r) ou commencer une nouvelle partie(n)");
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            String in = scan.next();
            if( in.contains("r")){
                flag = false;
               System.out.println("Coming soon!");
            }
            else if(in.contains("n")){
                flag = false;
                goban.main_loop();
            }
        }
        
        
    }
    
}
