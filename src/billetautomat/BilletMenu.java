package billetautomat;

import java.util.Scanner;

public class BilletMenu
{
    public static void main(String[] arg)
	{
              
                Scanner sc = new Scanner(System.in); 
                Billetautomat automat = new Billetautomat(24);
                int valg;
                
                while(Boolean.parseBoolean("true"))
                {
                    System.out.println("[1] Indsæt penge\n[2] Køb billet (" + automat.getBilletpris() + " kr.)\n[3] Tjek saldo\n");
                    System.out.print("Vælg et menupunkt: ");
                    valg = sc.nextInt();
                    System.out.println();
                    switch(valg)
                    {
                        case 1:     System.out.print("Indtast hvor meget du vil indsætte: ");
                                    automat.indsætPenge(sc.nextInt());
                                    System.out.println("Din saldo er nu: " + automat.getBalance() + " kr.\n");
                                    break;
                                    
                        case 2:     automat.udskrivBillet();
                                    System.out.println("Din saldo er nu: " + automat.getBalance() + " kr.\n");
                                    break;
                                    
                        case 3:     System.out.println("Nuværende saldo: " + automat.getBalance() + " kr.\n");
                                    break;
                                    
                        default:    System.out.println("Indtast venligst kun værdier fra 1 til 10.\n");
                    }
                }
	}
}
