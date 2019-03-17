package billetautomat;

import java.io.*;
import java.util.*;


public class Log
{
    private String filSti;
    private ArrayList<LogElement> logData;
    
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
    
    public void tilfoej(String dato, String handling, String gamleVaerdi, String nyeVaerdi) throws IOException
    {
        FileWriter logFileWriter = new FileWriter(filSti);
        PrintWriter logFilSkriv = new PrintWriter(logFileWriter);
        
        logFilSkriv.write(dato + "_" + handling + "_" + gamleVaerdi + "_" + nyeVaerdi + "\n"); //Printer til filen
        logFilSkriv.close();
    }
    
    private void logLoad() throws IOException
    {
        FileReader logFileReader = new FileReader(filSti);
        BufferedReader logFilLaes = new BufferedReader(logFileReader);
        
        logData = new ArrayList<LogElement>();
        String linje = logFilLaes.readLine();
        
        String[] linjeArray; 
        String linjeDato; 
        String linjeHandling; 
        String linjeGamleVaerdi;
        String linjeNyeVaerdi;
        
        while(linje != null)
        {
            linjeArray = linje.split("_");
            linjeDato = linjeArray[0];
            linjeHandling = linjeArray[1];
            linjeGamleVaerdi = linjeArray[2];
            linjeNyeVaerdi = linjeArray[3];
            logData.add(new LogElement(linjeDato, linjeHandling, linjeGamleVaerdi, linjeNyeVaerdi));
            linje = logFilLaes.readLine();
        }
    }
    
    public void filtrerLog() throws IOException
    {
        logLoad();
        Scanner keyboardInput = new Scanner(System.in);
        int input;
        while(Boolean.parseBoolean("true")) //lol
        {
            System.out.println("[1] Print hele loggen\n[2] Print loggens køb");
            input = keyboardInput.nextInt();
            
            switch(input)
            {
                
                //case 0, printer hele loggen
                case 0:     
                    for(LogElement logLinje : logData)
                    {
                        System.out.println(logLinje.toString());
                    }
                    break;
                    
                //case 1, printer logggen ud fra beløb eller dato
                case 1:     
                    System.out.println("Filtrer efter dyreste beløb[1]\n[2]Billigste beløb\n[3]Print efter dato");
                    input = keyboardInput.nextInt();
                    
                    switch(input)
                    {
                        case 1:  
                            
                            break;
                        
                        case 2:
                            
                            break;
                        
                        case 3:
                            
                            break;
            
                    }
                    break;
            }
        }
    }
}

