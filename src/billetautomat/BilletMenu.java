/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
 */
package billetautomat;

import java.util.Scanner;

public class BilletMenu
{

    public static void main(String[] args)
    {

	Scanner keyboardInput = new Scanner(System.in);
	Billetautomat automat = new Billetautomat();
	String stringValg;
	int valg = 0;
        double pengeInd = 0;

	while (true) //Test
	{
	    //Valg af funktion
	    System.out.println("[1] Indsæt penge\n[2] Tilføj billet til indkøbskurv\n[3] Se indkøbskurv\n[4] Køb billetter i indkøbskurv\n[5] Tjek saldo");
	    System.out.println("[6] Tjek billetpriser\n[7] Afslut\n[8] Log ind som montør");
	    if (automat.getMontørtilstand())
	    {
		System.out.println("[9] Udskriv salgsbeløbet\n[10] Udskriv den komplette log\n[11] Log ud");
	    }
	    System.out.print("Vælg et menupunkt: ");
	    stringValg = keyboardInput.nextLine(); // Muliggør yderligere "fejlinput" fra brugeren af programmet

	    try
	    {
		valg = Integer.parseInt(stringValg);
	    } catch (Exception ikketal)
	    {
		ikketal.printStackTrace();
	    }
	    System.out.println();

	    switch (valg)
	    {
		//case 1, indsæt pænge
		case 1:
		    System.out.print("Indtast hvor meget du vil indsætte: ");
                    
                    stringValg = keyboardInput.nextLine();
                    try
                    {
                        pengeInd = Double.parseDouble(stringValg);
                    } catch (Exception ikketal)
                    {
                        ikketal.printStackTrace();
                    }
                    
		    automat.indsætPenge(pengeInd);
                    
		    System.out.println("Din saldo er nu: " + automat.getBalance() + " kr.\n");
		    break;

		//case 2, tilføj billet til indkøbskurven
		case 2:
		    automat.printBillettyper();
                    System.out.print("Vælg hvilken type billet du vil tilføje: ");
                    
                    stringValg = keyboardInput.nextLine();
                    try
                    {
                        valg = Integer.parseInt(stringValg);
                    } catch (Exception ikketal)
                    {
                        ikketal.printStackTrace();
                    }
                    
                    automat.tilfoejIndkoebskurvElement(valg);
		    break;

                //case 3, print indholdet af indkøbskurven
		case 3:
		    automat.printIndkoebskurv();
		    break;

		//case 4, køb og udskriv billetterne i indkøbskurven
		case 4:
		    automat.udskrivBilleter();
		    break;
                    
		//case 3, tjek saldo
		case 5:
		    System.out.println("Nuværende saldo: " + automat.getBalance() + " kr.\n");
		    break;

		//case 6, tjek billetpriser
		case 6:
		    System.out.println("De nuværende billetpriser er:");
                    automat.printBillettyper();
		    break;

		//case 7, afslut køb og print returpenge
		case 7:
		    System.out.println("Session afsluttet: " + automat.returpenge() + " nyd din rejse med Borgen Trafikselskab.");
		    break;

		//case 8, montør log ind
		case 8:
		    System.out.print("Indtast montørkode: ");
		    automat.montørLogInd(keyboardInput.nextLine());
		    break;

		//case 9, få salgsbeløb       
		case 9:
		    System.out.println("Salgsbeløbet er: " + automat.getSamletSalgsbeløb());
		    break;

		//case 10, udskriv transaktioner  
		case 10:
		    automat.udskrivTransaktioner();
		    break;
		//case 11, log montør ud            
		case 11:
		    automat.montørLogUd();
		    break;

		//default case
		default:
		    System.out.println("Indtast venligst kun værdier fra 1 til 10.\n");
	    }
	}
    }
}
