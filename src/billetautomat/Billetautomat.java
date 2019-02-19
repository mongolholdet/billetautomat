package billetautomat;

/**
 * Model af en simpel billetautomat til enkeltbilletter med en fast pris.
 */
public class Billetautomat
{

    private double pris;    // Prisen for én billet.
    private double balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt

    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris.
     *
     * @param billetpris skal være større end nul (p.t. bliver det ikke tjekket)
     */
    public Billetautomat(double billetpris)
    {
        pris = billetpris;
    }

    /**
     * Opret en billetautomat, der sælger billetter til en given billetpris
     *
     * @param billetpris skal være større end nul (p.t. bliver det ikke tjekket)
     * @param startbalance mængden af penge automaten allerede indeholder
     */
    public Billetautomat(double billetpris, double startbalance)
    {
        double pris = billetpris;
        balance = startbalance;
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
        balance = balance + beløb;
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
        antalBilletterSolgt = antalBilletterSolgt + 1;
        balance = 0;             // Nulstil balance

        System.out.println("##########B##T##########");
        System.out.println("# Borgen Trafikselskab #");
        System.out.println("#                      #");
        System.out.println("#        Billet        #");
        System.out.println("#        " + pris + " kr.        #");
        System.out.println("#                      #");
        System.out.println("# Du har " + balance + " kr til gode #");
        System.out.println("##########B##T##########");
        System.out.println();
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
        if (montørkode.equals("1234"));
        return pris * antalBilletterSolgt;
    }
}
