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

        Indkoebskurv Indkøbskurv = new Indkoebskurv();

        Indkøbskurv.printIndkoebskurv();
        
        Indkøbskurv.tilfoejElement("Test", 24.0);
        Indkøbskurv.tilfoejElement("Test2", 25.0);
        
        Indkøbskurv.printIndkoebskurv();
    }
}
