
import java.util.*;
import java.io.*;
public class Program
{
    public static void main(String[] args) {
		
        GAI a = new GAI();
        Scanner s = new Scanner(System.in);
        String in = s.nextLine();
        String k = a.probableWord(in);
	//String lasw = k;
	for(int i =1; i<10; i++){
		//lasw = a.probableWord(lasw);
		//k+=" "+lasw;
		k += " "+a.probableWord(k);
	}
        System.out.println("====================\n"+a.beautify(in+" "+k)+"\n=========================");

	}
}


class GAI
{

private String base_data = "";
                     
    private String[] data;
    private double[] plist;	
String[] verbs = {
            "stopped", "sizing", "getting", "make", "taking", "signal", "start", "banged", "tried", 
            "lifted", "dropped", "saw", "fix", "failed", "delay", "prioritize", "woke", "realized", 
            "pursue", "rose", "took", "discuss", "drown", "result", "seems", "showing", "crumbles", 
            "want", "become", "lost", "comes", "experience", "met", "understood", "entered", "reached", 
            "prevents", "living", "survival", "hiding", "feigning", "occurred", "contest", "imagine", 
            "disobeying", "oppressed", "tortured", "killed", "oppresses", "kills", "thinks", "know", 
            "dismiss", "prefers", "lead", "resent", "crying", "feed", "arrived", "give", "scared", 
            "displaying", "recover", "improve", "repudiation", "toss", "subdue", "bore", "imitating", 
            "functioning", "happening", "figure", "calmed", "drowned", "notice", "wrapped", "leave", 
            "standing", "gripped", "waiting", "open", "agreed", "returned", "judged", "hampered", 
            "strengthening", "observe", "proven", "tried", "acquires","examine", "multiply", "live", "bear", "purpose", "condemn", "step", "perceive", "measure", "act", "accord", "remain", "kill", "desire", "consist", "repeatedly", "furnish", "stay", "understand", "cause", "doubt", "teach", "overcome", "secure", "fulfill", "remove", "soar", "appear", "influence", "change", "control", "interpret", "point", "exist", "conceal", "discover", "start", "begin", "define", "entertain", "accept", "learn", "color", "estimate", "waste", "argue", "happen", "react", "matter", "disturb", "form", "concern", "laugh", "fight", "flow", "predict", "resolve", "serve", "acquire", "kindle", "shine", "burn", "notice", "struggle", "follow", "collapse", "reach", "refuse", "return", "lean", "echo", "try", "consume", "start", "prepare", "compete", "wait", "glow", "look", "taste", "pack", "remind", "separate", "count", "climb", "grow", "pour", "crush", "tick", "hide", "apologize", "loss", "believe", "seem", "fail" 
        };

    String[] subjects = {"I","he","she","it","you","they","that","those","this","these","Cat","Dog","Tortoise","Knight","Hippo","Maggi"};
    String[] th_per_subjects = {"he","she","it","that","this","Cat","Dog","Tortoise","Maggi","Knight"};
        
    String[] helpVerbs = {"is","am","are","was","were","have","has","had","will",""};
    String[] nouns = {
            "wolves", "tracks", "mother", "cubs", "meal", "risks", "decision", "choice", "pack", 
            "computer", "side", "table", "fist", "irony", "machine", "violence", "dream", "action", 
            "excuse", "project", "crossroads", "courage", "step", "implications", "phrase", "semantics", 
            "determination", "underpants", "underpinnings", "cookie", "happiness", "age", "detachment", 
            "circumstances", "accusation", "law", "intentions", "pleasure", "indifference", "crook", 
            "dislike", "cardinal", "mud", "tussle", "saddle", "barge", "knees", "thread", "labyrinth", 
            "monster", "heart", "kids", "dark", "sleep", "lollipop", "cavities", "tooth", "trophy", 
            "shortcomings", "limitations", "shortcomings", "personalities", "flaws", "suicide", "celebration", 
            "towel", "heartbreak", "slave", "sunrise", "heroes", "war", "discoveries", "greed", "disgust", 
            "flesh", "blood", "skin", "hair", "depersonalization", "erasure", "reality", "ice", "glass", 
            "freezer", "sweaters", "breakfast", "helicopter", "actions", "imagination", "doubt", "self-esteem", 
            "secret", "friendship", "impression", "beliefs", "misgivings", "suspicion", "superstition", "nexus","life", "one", "necessity", "meaning", "purpose", "aim", "end", "existence", "man", "river", "world", "measure", "thing", "maxim", "time", "law", "God", "problem", "suicide", "liberty", "desire", "chain", "knowledge", "experience", "excellence", "act", "habit", "mind", "idea", "flux", "virtue", "reason", "cause", "doubt", "origin", "wisdom", "death", "dogma", "birth", "morality", "history", "philosophy", "example", "religion", "opium", "people", "enemy", "pleasure", "doctrine", "happiness", "science", "freedom", "removal", "poverty", "parent", "revolution", "crime", "society", "beast", "number", "eye", "heart", "function", "prayer", "nature", "slave", "passion", "thinking", "talking", "soul", "job", "perfection", "work", "emotion", "philosopher", "point", "core", "belief", "limit", "language", "cunning", "art", "defect", "weakness", "justice", "institution", "truth", "system", "thought", "atom", "space", "opinion", "wonder", "feeling", "definition", "term", "product", "sense", "animal", "journey", "mile", "step", "power", "beauty", "tyranny", "mark", "music", "mistake", "fact", "interpretation", "war", "person", "hour", "play", "year", "color", "estimate", "foundation", "state", "education", "youth", "master", "principle", "notion", "geometry", "humming", "string", "friend", "root", "fruit", "hope", "dream", "battle", "wealth", "behavior", "source", "seed", "genius", "seeker", "difficulty", "part", "capacity", "vessel", "fire", "sun", "permission", "bridge", "pride", "weight", "star", "dark", "drum", "air", "branch", "sky", "mountain", "pebble", "thief", "moment", "book", "block", "wood", "page", "shadow", "wind", "tree", "echo", "memory", "home", "anchor", "depth", "hunger", "glass", "horizon", "backyard", "rain", "compass", "moon", "footprint", "sand", "ghost", "map", "porch", "salt", "sugar", "difference", "baggage", "stuff", "thunder", "universe", "key", "lock", "dirt", "surface", "mirror", "face", "action", "spark", "hearth", "forest", "distance", "bird", "answer", "song", "winter", "season", "recovery", "kindness", "debt", "interest", "sender", "view", "climb", "word", "garden", "graveyard", "cup", "ship", "harbor", "forgiveness", "scent", "flower", "heel", "clock", "rhythm", "cloud", "secret", "diamond", "coal", "pressure", "ocean", "wave", "candle", "sauce", "meal", "sea", "sailor", "path", "blueprint", "patience", "courage", "absence", "fear", "decision", "rust", "age", "edge", "use", "ladder", "wall", "simplicity", "sophistication", "ego", "shield", "cage", "perspective", "puddle", "portal", "growth", "architect", "exit", "entry", "ink", "scholar", "blood", "martyr", "sound", "orchestra", "crowd", "luck", "preparation", "opportunity", "character", "rainbow", "integrity", "servant", "direction", "destination", "discipline", "solution", "obstacle", "vibe", "tribe", "imagination", "failure", "enthusiasm", "talent", "creativity", "intelligence"
        };    
    String[] irr_verbs = {"eat","sleep","run","fight","be", "speak", "have", "can", "think", "am", "is", "are", "was", "were", "become", "go", "do", "know", "make", "fly", "see", "feel", "ought", "put", "find", "lie", "die", "say", "teach", "break", "leave", "keep", "buy", "sing", "win", "set", "meet", "choose", "read", "stumble", "beat", "get", "take"};
        
    String[] preps = {"over","under","with","above","between","at","in","through","of", "beyond", "to", "in", "according", "at", "him", "behind", "with", "by", "from", "backward", "forward", "than", "near", "under", "after", "through", "on", "into", "between", "across", "along", "against", "toward", "outside", "inside"};
    
    String[] adverbs = {"good","well","nice","bad","neatly","mouth-wateringly","quickly","sweetly","not", "whereof", "thereof", "almost", "therefore", "twice", "only", "whereby", "everywhere", "here", "repeatedly", "then", "alone", "still", "else", "not", "backward", "forward", "rather", "well", "so", "never", "no", "more", "uselessly", "at least", "as far as", "possibly", "afterwards", "very", "just", "simply", "often", "always", "beautifully", "eventually", "natively", "fast", "far"};

private Map<String, String> irrMap = new HashMap<>();

private void initIrrVerbs() {
    // Adding the specific irregulars from the earlier list
    irrMap.put("be", "been");
    irrMap.put("speak", "spoken");
    irrMap.put("have", "had");
    irrMap.put("think", "thought");
    irrMap.put("become", "become");
    irrMap.put("go", "gone");
    irrMap.put("do", "done");
    irrMap.put("know", "known");
    irrMap.put("make", "made");
    irrMap.put("fly", "flown");
    irrMap.put("see", "seen");
    irrMap.put("feel", "felt");
    irrMap.put("put", "put");
    irrMap.put("find", "found");
    irrMap.put("lie", "lain");
    irrMap.put("die", "died");
    irrMap.put("say", "said");
    irrMap.put("teach", "taught");
    irrMap.put("break", "broken");
    irrMap.put("leave", "left");
    irrMap.put("keep", "kept");
    irrMap.put("buy", "bought");
    irrMap.put("sing", "sung");
    irrMap.put("win", "won");
    irrMap.put("set", "set");
    irrMap.put("meet", "met");
    irrMap.put("choose", "chosen");
    irrMap.put("read", "read");
    irrMap.put("stumble", "stumbled");
    irrMap.put("beat", "beaten");
    irrMap.put("get", "gotten");
    irrMap.put("take", "taken");
    irrMap.put("eat", "eaten");
    irrMap.put("sleep", "slept");
    irrMap.put("run", "run");
    irrMap.put("fight", "fought");
}

	Random rand=new Random();



    GAI(){

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
initIrrVerbs();
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




    ////// SENTENCE'S BEAUTY PARLOUR //////
    public String beautify(String sen)
    {
        String new_sen = "";
    
        String[] words = sen.split(" ");
        
        
        
        //verb grammar correction

        int i=0;
            if(words[i].equals("I"))
            {
                if(Arrays.asList(helpVerbs).contains(words[i+1]))
                {String w = words[i+2];
                    String[] ver = {"am","have","was","had",""};
                    if(!Arrays.asList(ver).contains(words[i+1]))
                    {
                        words[i+1]=ver[rand.nextInt(ver.length)];
                        }

                }
            }else if(words[i].equals("you")||                                      
   words[i].equals("they")||
   words[i].equals("those")||
   words[i].equals("these"))
            {
                if(Arrays.asList(helpVerbs).contains(words[i+1])){
                
                String[] ver = new String[]{"were","are","have","had",""};
                
                if(!Arrays.asList(ver).contains(words[i+1]))
                {
                    words[i+1]=ver[rand.nextInt(ver.length)];
    
                }
        
                    
             }
                
                
            }
            else if(words[i].equals("he")||
                    words[i].equals("it")||
                   words[i].equals("she")||
                  words[i].equals("this")||
                  words[i].equals("that")||
                  Arrays.asList(nouns).contains(words[i])
                   )
           {
               
               String[] ver = {"is","has","had","was",""};
               if(!Arrays.asList(ver).contains(words[i+1]))
               {
                   words[i+1]=ver[rand.nextInt(ver.length)];
               }

           }
        
        
        
        
        
       //verb tense correction
            String hv = words[1];
            String v = words[2];
            if(!Arrays.asList(new String[]{"and","or","but"}).contains(v)){
            switch(hv)
            {
                case "has":
                case "had":
                case "have":
                
       { if (irrMap.containsKey(v)) {
		    v = irrMap.get(v);
             }else if(v.charAt(v.length()-1)=='e'){
                     v+="d";
                }else {
                    v+="ed";
                 }
                 words[2]=v;
                 break;
                }
                
                case "is":
                case "are":
                case "was":
                case "were":
                case "am":
                {
                   
                  String s = v;
                if(!v.contains("ing")){
                 if(s.charAt(s.length()-1)=='e')
                s = v.substring(0,v.length()-1);
                if(v.equals("run"))
                    s+="n";
                
                s+="ing";
                
                words[2]=s;}
                break;
            
                }
                case "":{
                    
             if(Arrays.asList(th_per_subjects).contains(words[0])){
                    
                if(!v.substring(v.length()-2,v.length()-1).equals("h"))
                    v+="s";
                else v+="es";
                words[2]=v;
            }
                }
                
            }}
            
        
        
        
        
        for(String word:words)
        {
            if(!word.equals(""))
                new_sen+=word+" ";
        }
        
        return new_sen;
        
    }
    
    
    
    
    
}
