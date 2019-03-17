package billetautomat;

public class LogElement 
{
    private String dato;
    private String handling;
    private String vaerdi;
    private String sekundaerVaerdi;

    public LogElement(String datoC, String handlingC, String vaerdiC, String sekundaerVaerdiC) 
    {
        dato = datoC;
        handling = handlingC;
        vaerdi = vaerdiC;
        sekundaerVaerdi = sekundaerVaerdiC;
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
        
    public String getSekundaerVaerdi()
    {
        return sekundaerVaerdi;
    }
    
    public String toString()
    {
        return (dato + " " + handling + " " + vaerdi + " " + sekundaerVaerdi);   
    }
}
