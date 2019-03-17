package billetautomat;

import java.util.ArrayList;

public class Indkoebskurv
{
    private ArrayList<IndkoebskurvElement> IndkoebskurvElementer = new ArrayList<IndkoebskurvElement>();
    private ArrayList<BilletType> billettyper = new ArrayList<BilletType>();
     
    public void tilfoejElement(String navn, double pris)
    {
        IndkoebskurvElementer.add(new IndkoebskurvElement(navn,pris));
    }
    
    public void printIndkoebskurv()
    {
        if(IndkoebskurvElementer.isEmpty())
        {
            System.out.println("Indkøbskurven er tom.");
        }
        else
        {
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {
                System.out.println(element.getAntal() + " x " + element.getNavn() + " : " + element.getSamletPris() + " kr.");
            }
        }
    }
    
    private double getSamletBeloeb()
    {
        if(IndkoebskurvElementer.isEmpty())
        {
            return 0.0;
        }
        else
        {
            double sum = 0.0;
            
            for (IndkoebskurvElement element : IndkoebskurvElementer)
            {
                sum += element.getSamletPris();
            }
            
            return sum;
        }
    }
}
