package billetautomat;

import java.util.*;
import java.io.*;

public class LogTester 
{
    public static void main(String[] args) throws IOException
    {
        Log logTest = new Log("aktivitetslog.txt");
        logTest.tilfoej(new Date().toString(), "Der blev udskrevet en billet til:", "400");
        logTest.filtrerLogMenu();
        
    }
}
