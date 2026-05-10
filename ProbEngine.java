
import java.util.*;
import java.io.*;

public class ProbEngine
{

public String base_data = "";
                     
    private String[] data;
    private double[] plist;	
	Random rand=new Random();

public String getRandomWord() {
    // Pick a random sentence from your base_data
    String randomSentence = data[rand.nextInt(data.length)];
    String[] words = randomSentence.trim().split(" ");
    // Return a random word from that sentence
    return words[rand.nextInt(words.length)].replaceAll("[^a-zA-Z]", "");
}

    ProbEngine(){

	//accessing data	    
	File file = new File("corpus.txt");
	try(BufferedReader br = new BufferedReader(new FileReader(file))){
		String line;
		while((line = br.readLine())!=null)
			base_data+=line+" ";
	}catch(Exception e){System.err.println("Error reading file: " + e.getMessage());}

    //segregating base data
    data = base_data.toLowerCase().split("\\.");
    plist  = new double[data.length];
    }

    


    public String probableWord(String s)
    {
        // gives next probable word after s probablity list filling..
        for(int i=0; i<data.length; i++)
        {
            String x = data[i];
            double p = similarity(s,x);
           plist[i] = p;
        }
	
	int mxint = 0;
	for(int i=0; i<plist.length; i++)
	{
		if(plist[mxint]<plist[i])
		{	
			mxint=i;
		}    
	}	

	String probsentence = data[mxint];
	String[] pwords = probsentence.split(" ");
	String word_in_input_last = s.split(" ")[s.split(" ").length-1];
	double[] p2 = new double[pwords.length]; 
	for(int i=0; i<pwords.length; i++)
        {
            String x = pwords[i];
            double p = similarity(word_in_input_last,x);
           p2[i] = p;
        }

	int mxint2 = 0;
	for(int i=0; i<p2.length; i++)
	{
		if(p2[mxint2]<p2[i])
		{	
			mxint2=i;
		}    
	}
/*
	System.out.println(probsentence);

	if(mxint2==pwords.length-1)
		return "."+data[mxint];
	else if(!s.endsWith(pwords[mxint2+1]) && p2[mxint2]<0.9 )
		return pwords[mxint2+1];
	else
		return pwords[new Random().nextInt(pwords.length)];
    */
	int dec = rand.nextInt(100);
	 if(dec<50 && !s.endsWith(pwords[mxint2+1]) && p2[mxint2]<0.05 )
		return pwords[mxint2+1];
	else
		return pwords[new Random().nextInt(pwords.length)];

		
	}


    //checks similarity between two strings
    public double similarity(String s1, String s2){
        // s1 is to s2:
        double p = 0.0d;

        int e=0, m=Math.min(s1.length(),s2.length());

        for(int i=0; i<m; i++){
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            if(a==b){
                ++e;
            }
        }
        
        p = e/(double)s2.length();

        return p;

    }


   
    
}
