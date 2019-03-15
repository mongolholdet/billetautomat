package billetautomat;

import java.util.ArrayList;
import java.util.Date;

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

    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris.
     *
     * @param billetpris skal være større end nul
     */
    public Billetautomat(double billetpris)
    {
        balance = 0;
        if (billetpris > 0)
        {
            pris = billetpris;
            aktivitetslog.add(new Date() + " Billetautomat startet med billetprisen: " + pris + ".");
        } 
        else
        {
            System.out.println("Ugyldigt beløb. Pris sat til standardbeløb.");
            pris = 24.0;
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
     * Udskriv en billet.
     */
    public void udskrivBillet()
    {
        if (balance >= pris)
        {
            samletSalgsbeløb = samletSalgsbeløb + pris;
            balance = balance - pris;

            System.out.println("##########B##T##########");
            System.out.println("# Borgen Trafikselskab #");
            System.out.println("#                      #");
            System.out.println("#        Billet        #");
            System.out.println("#        " + pris + " kr.      #");
            System.out.println("#                      #");
            System.out.println("##########B##T##########");
            System.out.println();
            
            aktivitetslog.add(new Date() + " Der blev udskrevet en billet til " + pris + " kroner.");
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
            System.out.print("========== transaktioner pr " + new Date());
            for (String transaktion : aktivitetslog)
            {
                System.out.println(transaktion);
            }
            System.out.println("==========");
            aktivitetslog.add(new Date() + " Alle transaktioner blev udskrevet.");
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
}
