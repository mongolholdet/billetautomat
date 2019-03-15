package billetautomat;

public class BilletType
{
    private String navn;
    private double pris;
    
    public BilletType(String n, double p)
    {
	navn = n;
	pris = p;
    }
    
    public String getNavn()
    {
	return navn;
    }
    
    public double getPris()
    {
	return pris;
    }
    
    public void setPris(double p)
    {
	pris = p;
    }
}
