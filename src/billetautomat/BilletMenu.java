/*
    Lavet af: C. Bjørner, U. Esbjørn, M. Repnak, H. Warncke
*/

package billetautomat;

import java.util.Scanner;

public class BilletMenu
{
    public static void main(String[] arg)
	{
              
                Scanner sc = new Scanner(System.in); 
                Billetautomat automat = new Billetautomat(24);
                int valg;
                
                while(true) //Test
                {
                    //Valg af funktion
                    System.out.println("[1] Indsæt penge\n[2] Køb billet (" + automat.getBilletpris() + " kr.)\n[3] Tjek saldo\n");
                    System.out.print("Vælg et menupunkt: ");
                    valg = sc.nextInt();
                    System.out.println();
                    
                    switch(valg)
                    {
                        //case 1, indsæt pænge
                        case 1:     System.out.print("Indtast hvor meget du vil indsætte: ");
                                    automat.indsætPenge(sc.nextDouble());
                                    System.out.println("Din saldo er nu: " + automat.getBalance() + " kr.\n");
                                    break;
                                    
                        //case 2, køb billet
                        case 2:     automat.udskrivBillet();
                                    System.out.println("Din saldo er nu: " + automat.getBalance() + " kr.\n");
                                    break;

                        //case 3, tjek saldo
                        case 3:     System.out.println("Nuværende saldo: " + automat.getBalance() + " kr.\n");
                                    break;
                        
                        //case 4, tjek billetpris
                        case 4:     System.out.println("Den nuværende pris er: " + automat.getBilletpris());
                                    break;
                        
                        //case 5, afslut køb (returpenge)  
                        case 5:     System.out.println("Session afsluttet: " + automat.returpenge() + " nyd din rejse med Borgen Trafikselskab.");
                                    break;                                    
                                    
                        //case 6, montør log ind
                        case 6:     System.out.print("Indtast montørkode: ");
                                    automat.montørLogInd(sc.nextLine());
                                    break;
                        
                        //case 7, få salgsbeløb       
                        case 7:     System.out.println("Salgsbeløbet er: " + automat.getSamletSalgsbeløb());
                                    break;
                                    
                        //case 8, udskriv transaktioner  
                        case 8:     automat.udskrivTransaktioner();
                                    break;
                                    
                        //default case
                        default:    System.out.println("Indtast venligst kun værdier fra 1 til 10.\n");
                    }
                }
	}
}
