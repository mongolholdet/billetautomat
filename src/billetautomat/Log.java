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

        while (linje != null) 
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
    
    public static Comparator<LogElement> sammenlignBeloebStigende = new Comparator<LogElement>() 
    {
	public int compare(LogElement a, LogElement b) 
        {
	   return Integer.valueOf(a.vaerdi) - Integer.valueOf(b.vaerdi);
        }
    };
    
    public static Comparator<LogElement> sammenlignBeloebFaldende = new Comparator<LogElement>() 
    {
	public int compare(LogElement a, LogElement b) 
        {
	   return Integer.valueOf(b.vaerdi) - Integer.valueOf(a.vaerdi);

        }
    };
    
    public static Comparator<LogElement> sammenlignDatoFaldende = new Comparator<LogElement>() 
    {
	public int compare(LogElement a, LogElement b) 
        {
	   return a.dato.compareTo(b.dato);

        }
    };
    
    public void filtrerLog() throws IOException 
    {
        logLoad();
        Scanner keyboardInput = new Scanner(System.in);
        int input;
        while (Boolean.parseBoolean("true")) //lol
        {
            System.out.println("[1] Print hele loggen\n[2] Print loggens køb");
            input = keyboardInput.nextInt();
            boolean filtrering = true;

            switch (input) 
            {
                //case 1, printer hele loggen
                case 1:
                    for (LogElement logLinje : logData) 
                    {
                        System.out.println(logLinje.toString());
                    }
                    break;

                //case 2, printer logggen ud fra beløb
                case 2:
                    
                    ArrayList<LogElement> logBeloeb = new ArrayList<LogElement>();
                    //opretter en arrayliste med alle udskrevne billetter, altså alle køb
                    for (LogElement logElement : logData) 
                    {
                        if (logElement.handling.contains("Der blev udskrevet en billet til")) 
                        {
                            logBeloeb.add(logElement);
                        }
                    }
                    
                    while (filtrering) 
                    {
                        System.out.println("[1]Filtrer efter faldende beløb\n[2]Filtrer efter stigende beløb\n[3]Print salg efter dato (ældste salg først)");
                        System.out.println("[4]Print salg efter dato (nyeste salg først)\n[5]Gå tilbage\n");
                        input = keyboardInput.nextInt();

                        switch (input) 
                        {
                            //case 1, sorter efter dyreste beløb
                            case 1:
                                //Collections.sort(logBeloeb, new sammenlignBeloeb());
                                Collections.sort(logBeloeb, sammenlignBeloebStigende);

                                for (LogElement logElement : logBeloeb) 
                                {
                                    System.out.println(logElement);
                                }
                                break;

                            //case 2, sorter efter billigste beløb    
                            case 2:
                                Collections.sort(logBeloeb, sammenlignBeloebFaldende);
                                for (LogElement logElement : logBeloeb) 
                                {
                                    System.out.println(logElement);
                                }
                                break;

                            //case 3, sorter faldende dato
                            case 3:
                                Collections.sort(logBeloeb, sammenlignDatoFaldende);
                                
                                for (LogElement logLinje : logBeloeb) 
                                {
                                    System.out.println(logLinje);
                                }
                                break;

                            //case 4, sorter efter stigende dato    
                            case 4:
                                //den samme sortering bruges som i faldende, men der bruges blot reverse() på det
                                Collections.sort(logBeloeb, sammenlignDatoFaldende);
                                Collections.reverse(logBeloeb);
                                
                                for (LogElement logLinje : logBeloeb) 
                                {
                                    System.out.println(logLinje);
                                }
                                break;
                                
                            case 5: 
                                filtrering = false;
                                break;

                            default:
                                System.out.println("Indtast et gyldigt nummer:");
                                break;
                        }
                    }
                break;
            }
        }
    }
}
