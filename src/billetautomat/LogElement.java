package billetautomat;

public class LogElement 
{

    public String dato;
    public String handling;
    public String gamleVaerdi;
    public String nyeVaerdi;

    public LogElement(String datoC, String handlingC, String gamleVaerdiC, String nyeVaerdiC) 
    {
        dato = datoC;
        handling = handlingC;
        gamleVaerdi = gamleVaerdiC;
        nyeVaerdi = nyeVaerdiC;
    }

    public String toString()
    {
        return (dato + handling + gamleVaerdi + nyeVaerdi);
    }
}
