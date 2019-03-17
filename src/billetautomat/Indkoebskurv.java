package billetautomat;

import java.util.ArrayList;
import java.util.Scanner;

public class Indkoebskurv
{
    private ArrayList<IndkoebskurvElement> IndkoebskurvElementer = new ArrayList<IndkoebskurvElement>();
    private ArrayList<BilletType> billettyper = new ArrayList<BilletType>();
    
    public Indkoebskurv()
    {
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
            
            System.out.println("Første gangs opsætningsprocedure færdig.");
        }
        else
        {
            System.out.println("Første gangs opsætningsprocedure færdig.");
        }
    }
    
    public void printBillettyper()
    {       
        int i = 1;
        
        System.out.println("Billettyper:");
        
        for (BilletType billettype : billettyper)
        {
            System.out.println("[" + i + "] " + billettype.getNavn() + " : " + billettype.getPris() + " kr.");
            i++;
        }
        
        System.out.println();
    }
    
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
        
    private double getSamletBeloeb()
    {
        double sum = 0.0;
            
        for (IndkoebskurvElement element : IndkoebskurvElementer)
        {
            sum += element.getSamletPris();
        }
            
        return sum;
    }
}
