package billetautomat;

import java.util.*;
import java.io.*;

public class LogTester 
{
    public static void main(String[] args) throws IOException
    {
        //System.out.println(new File(".").getAbsoluteFile());
        
        Log logTest = new Log("test.txt");
        //logTest.tilfoej("1337", "Dabbede paa hatere", "420");
        //logTest.tilfoej("1338", "Der blev udskrevet en billet til", "421");
        logTest.filtrerLog();
        
    }
}
