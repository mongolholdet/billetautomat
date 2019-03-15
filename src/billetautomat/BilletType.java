package billetautomat;

public class BilletType
{
    private String navn;
    private double pris;
    private byte ID;
    
    public void BilletType(String n, double p, byte id)
    {
	navn = n;
	pris = p;
	ID = id;
    }
    
    public String getNavn()
    {
	return navn;
    }
    
    public double getPris()
    {
	return pris;
    }
    
    public byte getID()
    {
	return ID;
    }
    
    public void setPris(double p)
    {
	pris = p;
    }
}
