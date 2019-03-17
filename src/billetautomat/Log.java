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
        logFileReader.close();
    }
    
    public void tilfoej(String dato, String handling, String vaerdi) throws IOException
    {
        FileWriter logFileWriter = new FileWriter(filSti, true); //sætter append til at være true
        
        logFileWriter.write(dato + "_" + handling + "_" + vaerdi + "\n"); //Printer til filen
        logFileWriter.close();
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
        String linjeVaerdi;

        
        while(linje != null)
        {
            linjeArray = linje.split("_");
            linjeDato = linjeArray[0];
            linjeHandling = linjeArray[1];
            linjeVaerdi = linjeArray[2];

            logData.add(new LogElement(linjeDato, linjeHandling, linjeVaerdi));
            linje = logFilLaes.readLine();
        }
        logFilLaes.close();
        logFileReader.close();
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
                //case 1, printer hele loggen
                case 1:     
                    for(LogElement logLinje : logData)
                    {
                        System.out.println(logLinje.toString());
                    }
                    break;
                    
                //case 1, printer logggen ud fra beløb
                case 2:     
                    System.out.println("[1]Filtrer efter dyreste beløb\n[2]Billigste beløb\n[3]Print salg efter dato (ældste salg først)");
                    System.out.println("[4]Print salg efter dato (nyeste salg først)\n [5] Gå tilbage");
                    ArrayList<LogElement> logBeloeb = new ArrayList<LogElement>();
                    
                    //opretter en arrayliste med alle udskrevne billetter, altså alle køb
                    for(LogElement logElement : logData)
                    {
                        if(logElement.handling.contains("Der blev udskrevet en billet til"))
                        {
                            logBeloeb.add(logElement);
                            System.out.println(logElement);
                        }
                    }
                   /* 
                    switch(input)
                    {
                        case 1:  
                            Collections.sort(logBeloeb, new Comparator()
                            {
                                //sorterer arraylisten efter dyreste beløb
                                public int sammenlign(LogElement a, LogElement b)
                                {
                                    return ((LogElement) b).vaerdi.compareTo(((LogElement) a).vaerdi);
                                }
                            });
                            
                            for(LogElement logLinje : logBeloeb)
                            {
                                System.out.println(logLinje);
                            }
                            break;
                        
                        case 2:
                            Collections.sort(logBeloeb, new Comparator()
                            {
                                //sorterer arraylisten efter billigste beløb
                                public int sammenlign(LogElement a, LogElement b)
                                {
                                    return ((LogElement) a).vaerdi.compareTo(((LogElement) b).vaerdi);
                                }
                            });
                            
                            for(LogElement logLinje : logBeloeb)
                            {
                                System.out.println(logLinje);
                            }
                            break;
                        
                        case 3:
                            //da arrayet allerede er sorteret efter dato er der ingen grund til at sortere
                            for(LogElement logLinje : logBeloeb)
                            {
                                System.out.println(logLinje);
                            }
                            break;
            
                    }
                    break;
                    
*/
                    
            }
        }
    }
}

