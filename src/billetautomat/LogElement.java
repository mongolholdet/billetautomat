package billetautomat;

public class LogElement 
{
    private String dato;
    private String handling;
    private String vaerdi;

    public LogElement(String datoC, String handlingC, String vaerdiC) 
    {
        dato = datoC;
        handling = handlingC;
        vaerdi = vaerdiC;
    }
    
    public String getDato()
    {
        return dato;
    }
     
    public String getHandling()
    {
        return handling;
    }
    
    public String getVaerdi()
    {
        return vaerdi;
    }
        

    
    public String toString()
    {
        return (dato + " " + handling + " " + vaerdi);   
    }
}
