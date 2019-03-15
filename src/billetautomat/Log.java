package billetautomat;

import java.io.*;
import java.util.*;


public class Log
{
    private String filSti;
    
    public Log(String filStiInit) throws IOException
    {
        filSti = filStiInit;
        FileReader logFileReader = new FileReader(filStiInit);
        BufferedReader logFilLaes = new BufferedReader(logFileReader);
        try
        {
            logFilLaes.ready();
        }
        catch (Exception filAabning)
        {
            filAabning.printStackTrace();
        }
        logFilLaes.close();
    }
    
    public void tilfoej(String data) throws IOException
    {
        FileWriter logFileWriter = new FileWriter(filSti);
        PrintWriter logFilSkriv = new PrintWriter(logFileWriter);
        
        logFilSkriv.write(data + "\n"); //Printer til filen
        logFilSkriv.close();
    }
    
    public void filtrer(int type) throws IOException
    {
        FileReader logFileReader = new FileReader(filSti);
        BufferedReader logFilLaes = new BufferedReader(logFileReader);
        
        ArrayList<String> dataArrayList = new ArrayList<String>();
        String linje = logFilLaes.readLine();
        while(linje != null)
        {
            dataArrayList.add(linje);
            linje = logFilLaes.readLine();
        }
        
        while(Boolean.parseBoolean("true")) //lol
        {
            System.out.println("[1] Print hele loggen\n[2] Print loggens køb");
            switch(type)
            {
                
                //case 0, printer hele loggen
                case 0:     for(String printLinje : dataArrayList)
                            {
                                System.out.println(printLinje);
                            }
                //case 1, printer logggen ud fra beløb
                case 1:     System.out.println("Filtrer efter dyreste beløb[1]\n[2]Billigste beløb\nPrint efter dato");
                            Scanner keyboardInput = new Scanner(System.in);
                            int input = keyboardInput.nextInt();
                            switch(input)
                            {
                                case 1:     ArrayList<int> sorteredArrayList = new ArrayList<int>();
                                            for(String logLinje : dataArrayList)
                                            {
                                                if(logLinje.contains("Der blev indsat"))
                                                {
                                                    
                                                    sorteredArrayList.add(Integer.parseInt(logLinje.split(" ")[2]));
                                                }
                                                sorteredArrayList.sort();
                                                
                                            }
                                            
                                            
                            }
            }
        }
    }
}

