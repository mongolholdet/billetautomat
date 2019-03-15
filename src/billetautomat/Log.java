package billetautomat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;



public class Log
{
    private FileWriter logFil;
    
    public Log(String filSti) throws IOException
    {
        logFil = new FileWriter(filSti);
    }
    
    public void tilfoej(String data)
    {
        
    }
    
    public void filtrer(String type)
    {
        
    }
    
    
}

