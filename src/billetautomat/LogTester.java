package billetautomat;

import java.util.*;
import java.io.*;

public class LogTester 
{
    public static void main(String[] args) throws IOException
    {
        //System.out.println(new File(".").getAbsoluteFile());
        
        Log logTest = new Log("aktivitetslog.txt");
        //logTest.tilfoej("1337", "Dabbede paa hatere", "420");
        logTest.tilfoej(new Date().toString(), "Der blev udskrevet en billet til:", "421","Af test varianten");
        logTest.filtrerLogMenu();
        
    }
}
