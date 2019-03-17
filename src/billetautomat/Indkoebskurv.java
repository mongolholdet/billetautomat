package billetautomat;

import java.util.ArrayList;

public class Indkoebskurv
{
    private ArrayList<Object> poster = new ArrayList<Object>();
    private ArrayList<Object> billettyper = new ArrayList<Object>();
    
    public void Indkoebskurv()
    {
        
    }
    
    public void tilfoejPost(String navn, double pris)
    {
        poster.add(new IndkoebskurvElement(navn,pris));
    }
    
    public void printIndkoebskurv()
    {
        
    }
    
    private double getSamletBeloeb()
    {
        
    }
}
