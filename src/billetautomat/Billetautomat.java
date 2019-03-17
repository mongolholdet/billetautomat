package billetautomat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Model af en simpel billetautomat til enkeltbilletter med en fast pris.
 */
public class Billetautomat
{

    private double pris;    // Prisen for én billet.
    private double balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private double samletSalgsbeløb; // Hvor mange penge maskinen har solgt for
    private boolean montørtilstand; // Er en montør logget ind på maskinen
    private ArrayList<String> aktivitetslog = new ArrayList<String>();
    // Lister til indkøbskurv
    private ArrayList<IndkoebskurvElement> IndkoebskurvElementer = new ArrayList<IndkoebskurvElement>();
    private ArrayList<BilletType> billettyper = new ArrayList<BilletType>();
    
    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris.
     *
     * @param billetpris skal være større end nul
     */
    public Billetautomat()
    {
        balance = 0;
        
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Velkommen til Borgen Trafikselskab billetautomat første gangs opsætningsprocedure.");
        
        System.out.print("Mindst én billettype skal defineres.\nIndtast navnet på billettypen: ");
        String navn = keyboardInput.nextLine();
        
        System.out.print("Indtast prisen på billettypen: ");
        double pris = keyboardInput.nextDouble();
        
        billettyper.add(new BilletType(navn,pris));
        
        System.out.println("Vil du tilføje flere billettyper?\n[0] Nej\n[1] Ja");
        System.out.print("Vælg et menupunkt: ");
        int valg = keyboardInput.nextInt();
        keyboardInput.nextLine();
        if (valg == 1)
        {
            do
            {
                System.out.print("Indtast navnet på billettypen: ");
                navn = keyboardInput.nextLine();
                
                System.out.print("Indtast prisen på billettypen: ");
                pris = keyboardInput.nextDouble();
                
                billettyper.add(new BilletType(navn,pris));
                
                System.out.println("Vil du tilføje endnu en billettype?\n[0] Nej\n[1] Ja");
                System.out.print("Vælg et menupunkt: ");
                valg = keyboardInput.nextInt();
                keyboardInput.nextLine();
            } while (valg != 0);
            
            System.out.println("Første gangs opsætningsprocedure færdig.\n");
        }
        else
        {
            System.out.println("Første gangs opsætningsprocedure færdig.\n");
        }
    }

    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris
     *
     * @param billetpris skal være større end nul
     * @param startbalance mængden af penge automaten allerede indeholder
     */
    public Billetautomat(double billetpris, double startbalance)
    {
        if (billetpris > 0)
        {
            pris = billetpris;
            aktivitetslog.add(new Date() + " Billetautomat startet med billetprisen: " + pris + " og startsbeløbet: " + ".");

        } 
        else
        {
            System.out.println("Ugyldigt beløb. Pris sat til standardbeløb.");
            pris = 24.0;
        }
        if (startbalance > 0)
        {
            balance = startbalance;
        } 
        else
        {
            System.out.println("Ugyldig startbalance. Startbalance sat til 0.");
            balance = 0;
        }
    }

    /**
     * Giver prisen for en billet.
     */
    public double getBilletpris()
    {
        double resultat = pris;
        aktivitetslog.add(new Date() + " Der blev udskrevet billetpris. Billetprisen er :" + resultat);
        return resultat;
    }

    /**
     * Modtag nogle penge (i kroner) fra en kunde.
     */
    public void indsætPenge(double beløb)
    {
        if (beløb > 0)
        {
            balance = balance + beløb;
            
            aktivitetslog.add(new Date() + " Der blev indsat "+beløb+" kroner.");
        } 
        else
        {
            System.out.println("Ugyldigt beløb. Indbetaling annulleret.");
        }
    }

    /**
     * Giver balancen (beløbet maskinen har modtaget til den næste billet).
     */
    public double getBalance()
    {
        aktivitetslog.add(new Date() + " Balancen blev returneret, den var: " + balance + ".");
        return balance;
    }

    /**
     * Udskriv billeter.
     */
    public void udskrivBilleter()
    {
        if (balance >= getSamletBeloeb())
        {
            samletSalgsbeløb += getSamletBeloeb();
            balance -= getSamletBeloeb();
            
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {
                for(int i = 0; i < element.getAntal(); i++)
                {
                    System.out.println("##########B##T##########");
                    System.out.println("# Borgen Trafikselskab #");
                    System.out.println("#                      #");
                    System.out.println("#        " + element.getNavn() + "        #");
                    System.out.println("#        " + element.getBilletPris() + " kr.      #");
                    System.out.println("#                      #");
                    System.out.println("##########B##T##########");
                    System.out.println();
                } 
            }
            aktivitetslog.add(new Date() + " Der blev udskrevet billetter til i alt" + getSamletBeloeb() + " kroner.");
            
            IndkoebskurvElementer.clear();
        } 
        else
        {
            System.out.println("Saldo for lav");
        }
    }

    public void setBilletpris(double nyPris)
    {
        if (montørtilstand)
        {
            pris = nyPris;
            aktivitetslog.add(new Date() + " Billetprisen er sat til:" + pris);
        } 
        else
        {
            System.err.println("Kunne ikke sætte pris - forkert kode");
        }
    }

    public double getSamletSalgsbeløb()
    {
        if (montørtilstand)
        {
            aktivitetslog.add(new Date() + " Det samlede salgsbeløb blev printet. Salgsbekøbet var: " + samletSalgsbeløb + ".");
            return samletSalgsbeløb;
        } 
        else
        {
            System.out.println("Afvist. Log ind først.");
            return -1;
        }
    }

    public void montørLogInd(String montørkode)
    {
        if (montørkode.equals("1234"))
        {
            montørtilstand = true;
            System.out.println("Korrekt montørkode. Husk at logge ud igen!");
            aktivitetslog.add(new Date() + " Montør loggede ind.");
        } 
        else
        {
            System.out.println("Forkert montørkode. Log ind afvist!");
        }
    }
    
    public boolean getMontørtilstand()
    {
        return montørtilstand;
    }

    public void montørLogUd()
    {
        montørtilstand = false;
        System.out.println("Logget ud.");
        aktivitetslog.add(new Date() + " Montør logget ud.");
    }
    
    public void udskrivTransaktioner()
    {
        if (montørtilstand)
        {
            System.out.print("========== log pr " + new Date());
            for (String transaktion : aktivitetslog)
            {
                System.out.println(transaktion);
            }
            System.out.println("==========");
            aktivitetslog.add(new Date() + " Hele loggen blev udskrevet.");
        } 
        else
        {
            System.out.println("Afvist. Log ind først.");
        }
    }
    
    public double returpenge()
    {
        double returbeløb = balance;
        aktivitetslog.add(new Date() + " Kunden fik "+returbeløb+" kr retur");
        balance = 0;
	System.out.println("Du får "+returbeløb+" kr retur");
	return returbeløb;
    }
    
    // Alt herunder hører til indkøbskurv funktionaliteten
    
    
    // Udskriver de mulige billettyper der kan købes
    public void printBillettyper()
    {       
        int i = 1;
               
        for (BilletType billettype : billettyper)
        {
            System.out.println("[" + i + "] " + billettype.getNavn() + " : " + billettype.getPris() + " kr.");
            i++;
        }
        
        System.out.println();
    }
    
    // Tilføjer en type af billet til indkøvskurven
    public void tilfoejIndkoebskurvElement(int type)
    {              
        if (IndkoebskurvElementer.isEmpty())
        {
            IndkoebskurvElementer.add(new IndkoebskurvElement(billettyper.get(type-1).getNavn(), billettyper.get(type-1).getPris()));
        }
        else
        {
            String billettypenavn = billettyper.get(type-1).getNavn();
            boolean findesAllerede = false;
            
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {     
                if (billettypenavn.equals(element.getNavn()))
                {
                    findesAllerede = true;
                    element.incrementAntal();
                }
            }
            
            if(!findesAllerede)
            {
                IndkoebskurvElementer.add(new IndkoebskurvElement(billettyper.get(type-1).getNavn(), billettyper.get(type-1).getPris()));
            }
        }
        printIndkoebskurv();
    }
    
    // Udskriver indholdet af indkøbskurven
    public void printIndkoebskurv()
    {
        System.out.println("Din indkøbskurv:");
        
        if(IndkoebskurvElementer.isEmpty())
        {
            System.out.println("Indkøbskurven er tom.");
        }
        else
        {
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {
                System.out.println(element.getAntal() + " x " + element.getNavn() + " : " + element.getAntal() + " x " + element.getBilletPris() + " kr.");
            }
            System.out.println("Samlet beløb: " + getSamletBeloeb() + " kr.");
        }
        
        System.out.println();
    }
     
    // Giver det samlede købsbeløv
    private double getSamletBeloeb()
    {
        double sum = 0.0;
        
        if (IndkoebskurvElementer.isEmpty())
        {
            return sum;
        }
        else
        {
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {
                sum += element.getSamletPris();
            } 
            
            return sum;
        }
    }
}
