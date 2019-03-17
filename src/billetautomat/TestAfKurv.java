/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billetautomat;

import java.util.Scanner;

public class TestAfKurv
{

    public static void main(String[] args)
    {
        Scanner keyboardInput = new Scanner(System.in);

        Billetautomat automat = new Billetautomat();

        automat.printIndkoebskurv();
        
        automat.printBillettyper();
        
        automat.tilfoejIndkoebskurvElement(1);
        automat.tilfoejIndkoebskurvElement(1);
        automat.tilfoejIndkoebskurvElement(2);
    }
}
