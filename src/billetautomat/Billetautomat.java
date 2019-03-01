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
    private ArrayList<String> transaktioner = new ArrayList<String>();

    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris.
     *
     * @param billetpris skal være større end nul
     */
    public Billetautomat(double billetpris)
    {
        if (billetpris > 0)
        {
            pris = billetpris;
        } else
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
        } else
        {
            System.out.println("Ugyldigt beløb. Pris sat til standardbeløb.");
            pris = 24.0;
        }
        if (startbalance > 0)
        {
            balance = startbalance;
        } else
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
            
            transaktioner.add(new Date() + " der blev indsat "+beløb+" kroner");
        } else
        {
            System.out.println("Ugyldigt beløb. Indbetaling annulleret.");
        }
    }

    /**
     * Giver balancen (beløbet maskinen har modtaget til den næste billet).
     */
    public double getBalance()
    {
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
            System.out.println("#        " + pris + " kr.        #");
            System.out.println("#                      #");
            System.out.println("# Du har " + balance + " kr til gode #");
            System.out.println("##########B##T##########");
            System.out.println();
            
            transaktioner.add(new Date() + " der blev udskrevet en billet");
        } else
        {
            System.out.println("Saldo for lav");
        }
    }

    public void setBilletpris(String montørkode, double nyPris)
    {
        if (montørkode.equals("1234"))
        {
            pris = nyPris;
        } else
        {
            System.err.println("Kunne ikke sætte pris - forkert kode");
        }
    }

    public double getSamletSalgsbeløb(String montørkode)
    {
        if (montørtilstand)
        {
            return samletSalgsbeløb;
        } else
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
        } else
        {
            System.out.println("Forkert montørkode. Log ind afvist!");
        }
    }

    public void montørLogUd()
    {
        montørtilstand = false;
        System.out.println("Logget ud.");
    }
    
    public void udskrivTransaktioner()
    {
        if (montørtilstand)
        {
            System.out.print("========== transaktioner pr " + new Date());
            for (String transaktion : transaktioner)
            {
                System.out.println(transaktion);
            }
            System.out.println("==========");
        } else
        {
            System.out.println("Afvist. Log ind først.");
        }
    }
}
