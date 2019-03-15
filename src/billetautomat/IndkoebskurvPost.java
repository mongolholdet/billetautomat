package billetautomat;

public class IndkoebskurvPost
{
    private String navn;
    private double pris;
    private int antal;
    
    public void IndkoebskurvPost(String n, double p)
    {
	navn = n;
	pris = p;
    }
    
    public String getNavn()
    {
	return navn;
    }
    
    public double getBilletPris()
    {
	return pris;
    }
    
    public int getAntal()
    {
        return antal;
    }
    
    public double getSamletPris()
    {
        return pris*antal;
    }
}
