package billetautomat;

public class LogElement 
{
    public String dato;
    public String handling;
    public String vaerdi;

    public LogElement(String datoC, String handlingC, String vaerdiC) 
    {
        dato = datoC;
        handling = handlingC;
        vaerdi = vaerdiC;
    }

    public String toString()
    {
        return (dato + handling + vaerdi);
    }
}
