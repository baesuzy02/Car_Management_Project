
package view;

import Controller.BrandList;
import java.util.ArrayList;
import java.util.Scanner;



public class Menu<E> {
    private int response;
    Scanner sc = new Scanner(System.in);
    
    public int int_getchoice(ArrayList<E> options){
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. %s\n", (i+1), options.get(i));
        }
        System.out.print("Please choose from 1 to "+options.size() + ": ");
        response = sc.nextInt();
        
        return response;
    }
    
    public E ref_getChoice(ArrayList<E> options){
        do{
            response = int_getchoice(options);
        }while(response < 0 || response > options.size());
        
        return options.get(response - 1);
    }
    
    
    
    
}
