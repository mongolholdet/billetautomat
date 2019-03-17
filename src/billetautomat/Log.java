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

    public void tilfoej(String dato, String handling, String vaerdi, String sekundaerVaerdi) throws IOException 
    {
        FileWriter logFileWriter = new FileWriter(filSti, true); //sætter append til at være true
        
        if (sekundaerVaerdi == "")
        {
            logFileWriter.write(dato + "_" + handling + "_" + vaerdi + "\n"); //Printer til filen
        }
        else
        {
            logFileWriter.write(dato + "_" + handling + "_" + vaerdi + "_" + sekundaerVaerdi + "\n"); //Printer til filen
        }
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
        String linjeSekundaerVaerdi = "";

        while (linje != null) 
        {
            linjeArray = linje.split("_");
            linjeDato = linjeArray[0];
            linjeHandling = linjeArray[1];
            linjeVaerdi = linjeArray[2];
            
            if(linjeArray.length >= 4)
            {
                linjeSekundaerVaerdi = linjeArray[3];
            }
            logData.add(new LogElement(linjeDato, linjeHandling, linjeVaerdi, linjeSekundaerVaerdi));
            linje = logFilLaes.readLine();
        }
        logFilLaes.close();
        logFileReader.close();
    }
    
    //sammenligner beloeb
    //runder desværre kommatallet op eller ned afhængigt af størrelsen. Dette giver en unøjagtighed op 1 krone i filtreringen
    public static Comparator<LogElement> sammenlignBeloebStigende = new Comparator<LogElement>() 
    {
	public int compare(LogElement a, LogElement b) 
        {
	   return Integer.valueOf(Math.round(Float.parseFloat(a.getVaerdi()))) - Integer.valueOf(Math.round(Float.parseFloat(b.getVaerdi())));
        }
    };
    
    //sammenligner Date() strenge 
    public static Comparator<LogElement> sammenlignDatoStigende = new Comparator<LogElement>() 
    {
	public int compare(LogElement a, LogElement b) 
        {
	   return a.getDato().compareTo(b.getDato());

        }
    };
    
    public void filtrerLogMenu() throws IOException 
    {
        logLoad();
        Scanner keyboardInput = new Scanner(System.in);
        int input;
        System.out.println("Menuen ti loggen er åbnet. vælg fra på kommandoerne:");
        boolean logSelection = true;
        
        while (logSelection)
        {
            System.out.println("[1] Print hele loggen\n[2] Filtrer loggens elemeter\n[3] Slet logdata\n[4] Gå tilbage");
            input = keyboardInput.nextInt();
            boolean beloeb = true;

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
                        if (logElement.getHandling().contains("Der blev udskrevet en billet til")) 
                        {
                            logBeloeb.add(logElement);
                        }
                    }
                    
                    //en løkke til at vælge mellem de forskellige muligheder i "undermenuen" beløb eller køb
                    while (beloeb) 
                    {
                        System.out.println("[1] Filtrer efter stigende beløb\n[2] Filtrer efter faldende beløb\n[3] Print salg efter dato (ældste salg først)");
                        System.out.println("[4] Print salg efter dato (nyeste salg først)\n[5] print montør information\n[6] Gå tilbage");
                        input = keyboardInput.nextInt();

                        switch (input) 
                        {
                            //case 1, sorter efter dyreste beløb
                            case 1:
                                Collections.sort(logBeloeb, sammenlignBeloebStigende);

                                for (LogElement logElement : logBeloeb) 
                                {
                                    System.out.println(logElement);
                                }
                                break;

                            //case 2, sorter efter billigste beløb    
                            case 2:
                                //den samme sortering bruges som i stigende, men der bruges blot reverse() på det for at få faldende
                                Collections.sort(logBeloeb, sammenlignBeloebStigende);
                                Collections.reverse(logBeloeb);
                                
                                for (LogElement logElement : logBeloeb) 
                                {
                                    System.out.println(logElement);
                                }
                                break;

                            //case 3, sorter faldende dato
                            case 3:
                                Collections.sort(logBeloeb, sammenlignDatoStigende);
                                
                                for (LogElement logLinje : logBeloeb) 
                                {
                                    System.out.println(logLinje);
                                }
                                break;

                            //case 4, sorter efter stigende dato    
                            case 4:
                                //den samme sortering bruges som i stigende, men der bruges blot reverse() på det for at få faldende
                                Collections.sort(logBeloeb, sammenlignDatoStigende);
                                Collections.reverse(logBeloeb);
                                
                                for (LogElement logLinje : logBeloeb) 
                                {
                                    System.out.println(logLinje);
                                }
                                break;
                                
                             //case 5, andre log muligheder
                            case 5:
                                ArrayList<LogElement> logMontoer = new ArrayList<LogElement>();
                                //opretter en arrayliste med alle logbeskeder som omhandler montøren
                                for (LogElement logElement : logData) 
                                {
                                    if (logElement.getHandling().toUpperCase().contains("MONTØR")) 
                                    {
                                        logMontoer.add(logElement);
                                    }
                                }
                                    
                                for (LogElement logLinje : logMontoer) 
                                {
                                    System.out.println(logLinje);
                                }
                                break;
                                
                            //case 6, afslut køb loopet   
                            case 6: 
                                beloeb = false;
                                break;
                        }
                    }
                break;
                //case 3, loggen kan slettes        
                case 3: System.out.println("Du har valgt at slette den nuværende log. Er du sikker at du vil slette loggen? \n[0] Nej \n[1] Ja");
                    if(keyboardInput.nextInt() == 1)
                    {
                        FileWriter logSletter = new FileWriter(filSti);
                        logSletter.write("");
                        logSletter.close();
                        System.out.println("Loggen er slettet.");
                    }
                    break;
                    
                //case 4 gå ud af menuen        
                case 4:
                    logSelection = false;
                    break;
            }
        }   
    }
}
    

