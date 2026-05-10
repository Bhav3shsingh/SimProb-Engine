/*



A PRIMITIVE RANDOM SENTENCE GENERATOR



*/

import java.util.*;

public class SentenceMaker
{

    String senType = "";
    Random rand = new Random();
    String sent = "";
    int sento = 0;

	GrammarEngine grm = new GrammarEngine();
    ProbEngine pen = new ProbEngine();
    

    public String sengen()
    {
        String h  = generate();
        if(h.length()<=1){h=generate();}
        h += ((sento==1)?".":(sento==2)?"?":"!");
       h=h.toLowerCase();
       char[] n = h.toCharArray();
       n[0]=(n[0]+"").toUpperCase().toCharArray()[0];
       h="";
       for(int i=0; i<n.length; i++){
       
           if(n[i]=='i' && i>0 && n[i-1]==' ' && n[i+1]==' ')
           n[i]='I';
           
           h+=n[i];
           
       }
        return h;
    }
    
    private String generate()
    {
        setType();
        
        switch(senType)
        {
            case "Dec": sent=decSen(); break;//1
            case "Que": sent=queSen(); break;//2
        }
        return sent;
    }
    
    
    
    private String decSen()
    {
        String sen = "";
        char sub_c;

        String firstword =pen.getRandomWord();
        
        sen+=firstword+" ";
    
        
        for (int i = 0; i < rand.nextInt(10); i++) {
        // Ask the engine: "What is likely to follow the string so far?"
        String nextWord = pen.probableWord(sen.toString().trim());
        
        // If the engine fails to find a match, grab a random one to keep moving
        if (nextWord == null || nextWord.isEmpty()) {
            nextWord = pen.getRandomWord();
        }
        
        sen+=(nextWord)+(" ");
    }
        
        
        sen=grm.beautify(sen);
        if(complex())
        {
            String new_sen=decSen();
            sen+=new String[]{"and ","but ","or "}[rand.nextInt(3)];
            sen+=new_sen;
        }
        sento=1;
        return sen;
    }
    
    
    // QUESTION ?????????????????????
    private String queSen()
    {
	String baseStatement = decSen();
	String[] words = baseStatement.split(" ");
    	String question = grm.forceQuestion(words);
    	sento = 2; // Sets the "?" punctuation
    	return question;
   }
    
    
    
    private String impSen()
    {
        sento=3;
        return "!";
    }
    
    
    
    
    
    private boolean complex()
    {
        int r = rand.nextInt(2);
        boolean b = false;
        if(r==0)
            b=true;
        return b;
    }
    
    private void setType()
    {
        String[] type = {"Dec","Que","Imp"};
         int r = rand.nextInt(8);
         if(r>4)
             senType = "Dec";
         else if(r>=0)
             senType = "Que";
         else
            senType = "Imp";
        
    }
    
    
}
