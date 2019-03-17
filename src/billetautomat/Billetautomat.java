package billetautomat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

/**
 * Model af en simpel billetautomat til enkeltbilletter med en fast pris.
 */
public class Billetautomat
{

    private double pris;    // Prisen for én billet.
    private double balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private double samletSalgsbeløb; // Hvor mange penge maskinen har solgt for
    private boolean montørtilstand; // Er en montør logget ind på maskinen
    Log aktivitetslog; 
    // Lister til indkøbskurv
    private ArrayList<IndkoebskurvElement> IndkoebskurvElementer = new ArrayList<IndkoebskurvElement>();
    private ArrayList<BilletType> billettyper = new ArrayList<BilletType>();
    
    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris.
     *
     * @param billetpris skal være større end nul
     */
    public Billetautomat() throws IOException
    {
        aktivitetslog = new Log("aktivitetslog.txt");
        
        int valg;
        double pris;
        
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Velkommen til Borgen Trafikselskab billetautomat første gangs opsætningsprocedure.");
        
        System.out.println("Vil du sætte en startsaldo til testformål?\n[0] Nej\n[1] Ja");
        System.out.print("Vælg et menupunkt: ");
        valg = keyboardInput.nextInt();
        keyboardInput.nextLine();
        if (valg == 1)
        {
            System.out.print("Indtast startsaldo: ");
            pris = keyboardInput.nextDouble();
            keyboardInput.nextLine();
            if (pris > 0)
            {
                balance = pris;
                System.out.println("Startsaldo sat til: " + pris + " kr.");
            }
            else
            {
                System.out.println("Ugyldig startbalance. Startbalance sat til 0.");
                balance = 0;
            }
        }
        else
        {
            balance = 0;
        }
        
        System.out.print("Mindst én billettype skal defineres.\nIndtast navnet på billettypen: ");
        String navn = keyboardInput.nextLine();
        
        System.out.print("Indtast prisen på billettypen: ");
        pris = keyboardInput.nextDouble();
        
        billettyper.add(new BilletType(navn,pris));
        
        System.out.println("Vil du tilføje flere billettyper?\n[0] Nej\n[1] Ja");
        System.out.print("Vælg et menupunkt: ");
        valg = keyboardInput.nextInt();
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
    public Billetautomat(double billetpris, double startbalance) throws IOException
    {
        if (billetpris > 0)
        {
            pris = billetpris;
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
        aktivitetslog.tilfoej(new Date().toString(), "Billetautomat startet med billetprisen:", Double.toString(pris), Double.toString(balance));
    }

    /**
     * Giver prisen for en billet.
     */
    public double getBilletpris() throws IOException
    {
        double resultat = pris;
        aktivitetslog.tilfoej(new Date().toString(), "Der blev udskrevet billetpris. Billetprisen er :", Double.toString(resultat), "");
        return resultat;
    }

    /**
     * Modtag nogle penge (i kroner) fra en kunde.
     */
    public void indsætPenge(double beløb) throws IOException
    {
        if (beløb > 0)
        {
            balance = balance + beløb;

            aktivitetslog.tilfoej(new Date().toString(), "Der blev indsat antal kroner:", Double.toString(beløb), "");
        } 
        else
        {
            System.out.println("Ugyldigt beløb. Indbetaling annulleret.");
        }
    }

    /**
     * Giver balancen (beløbet maskinen har modtaget til den næste billet).
     */
    public double getBalance() throws IOException
    {
        aktivitetslog.tilfoej(new Date().toString(), "Balancen blev returneret, den var:", Double.toString(balance), "");
        return balance;
    }

    /**
     * Udskriv billeter.
     */
    public void udskrivBilleter() throws IOException
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
                    aktivitetslog.tilfoej(new Date().toString(), "Der blev udskrevet en billet til:", Double.toString(element.getBilletPris()), "af" + element.getNavn() + "varianten");

                } 
            }
            aktivitetslog.tilfoej(new Date().toString(), "Der blev udskrevet billetter til i alt", Double.toString(getSamletBeloeb()), "");
            
            IndkoebskurvElementer.clear();
        } 
        else
        {
            System.out.println("Saldo for lav");
        }
    }

    public void setBilletpris(int type, double pris) throws IOException
    {
        if (montørtilstand)
        {
            billettyper.get(type-1).setPris(pris);
            
            aktivitetslog.tilfoej(new Date().toString(), "Prisen på billettypen", billettyper.get(type-1).getNavn(), "er sat til" + pris);
        } 
        else
        {
            System.err.println("Kunne ikke sætte pris - montør ikke logget ind");
        }
    }

    public double getSamletSalgsbeløb() throws IOException
    {
        if (montørtilstand)
        {
            aktivitetslog.tilfoej(new Date().toString(), "Det samlede salgsbeløb blev printet. Salgsbeløbet var:", Double.toString(samletSalgsbeløb),"");
            return samletSalgsbeløb;
        } 
        else
        {
            System.out.println("Afvist. Log ind først.");
            return -1;
        }
    }

    public void montørLogInd(String montørkode) throws IOException
    {
        if (montørkode.equals("1234"))
        {
            montørtilstand = true;
            System.out.println("Korrekt montørkode. Husk at logge ud igen!");
            aktivitetslog.tilfoej(new Date().toString(), "Montør loggede ind med kode", montørkode,"");
        } 
        else
        {
            System.out.println("Forkert montørkode. Log ind afvist!");
        }
    }
    
    public boolean getMontørtilstand() throws IOException
    {
        aktivitetslog.tilfoej(new Date().toString(), "Montørtilstand tjekket. Montøren var logget ind:", Boolean.toString(montørtilstand),"");
        return montørtilstand;
    }

    public void montørLogUd() throws IOException
    {
        montørtilstand = false;
        System.out.println("Logget ud.");
        aktivitetslog.tilfoej(new Date().toString(), "Montør logget ud. Logintilstand:", Boolean.toString(montørtilstand),"");
    }
    
    public void udskrivTransaktioner() throws IOException
    {
        if (montørtilstand)
        {
            aktivitetslog.tilfoej(new Date().toString(), "Loggen tilgået. Montøren var logget ind:", Boolean.toString(montørtilstand),"");
            aktivitetslog.filtrerLogMenu();
        } 
        else
        {
            System.out.println("Afvist. Log ind først.");
        }
    }
    
    public double returpenge() throws IOException
    {
        double returbeløb = balance;
        aktivitetslog.tilfoej(new Date().toString(), "Kunden fik antal kroner retur:", Double.toString(returbeløb),"");
        balance = 0;
	System.out.println("Du får "+returbeløb+" kr retur");
	return returbeløb;
    }
    
    // Alt herunder hører til indkøbskurv funktionaliteten
    
    
    // Udskriver de mulige billettyper der kan købes
    public void printBillettyper() throws IOException
    {       
        int i = 1;
               
        for (BilletType billettype : billettyper)
        {
            aktivitetslog.tilfoej(new Date().toString(), "Kunden tjekkede alle billetter. Aktuel billet:", billettype.getNavn(),"Til antal kroner:" + Double.toString(billettype.getPris()));
            System.out.println("[" + i + "] " + billettype.getNavn() + " : " + billettype.getPris() + " kr.");
            i++;
        }
        
        System.out.println();
    }
    
    // Tilføjer en type af billet til indkøvskurven
    public void tilfoejIndkoebskurvElement(int type) throws IOException
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
                aktivitetslog.tilfoej(new Date().toString(), "Kunden tilføjede billetype til kurven:", billettyper.get(type-1).getNavn(),"Til antal kroner:" + Double.toString(billettyper.get(type-1).getPris()));
            }
        }
        printIndkoebskurv();
    }
    
    // Udskriver indholdet af indkøbskurven
    public void printIndkoebskurv() throws IOException
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
                aktivitetslog.tilfoej(new Date().toString(), "Kunden tjekkede sin kurv. I kurven er:" + Double.toString(element.getAntal()) + " x " + element.getNavn() + " : " + Double.toString(element.getAntal()) + " x " + Double.toString(element.getBilletPris()) + " kr.","","");
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
