package wizard_spellbook;
import java.io.*;
import java.util.*;
//TODO preparing spells
//TODO more searching tools ex by recharge, by level, etc
//TODO better optimize the spell class esp. attacks so that attacks with no attack are better stored
//TODO further testing, esp creating spell book in app
public class run {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Scanner fscan = null;
		spellbook sb = new spellbook();
		String nl = "\r\n";
		String info = "If you wish to manually build a spellbook the format of the file should be as such:"+nl+
				"name;type value;level;recharge value;keyword1, keyword2, ...;action value,range,target,attack value,text,prep"+nl+
				"type value - an integer"+nl+
				"\t0 - an attack power"+nl+
				"\t1 - a utility power"+nl+
				"recharge value - an integer between 0 and 3."+nl+
				"\t0 - at-will"+nl+
				"\t1 - encounter"+nl+
				"\t2 - daily"+nl+
				"\t3 - special"+nl+
				"action value - an integer between 0 and 4"+nl+
				"\t0 - free/no action"+nl+
				"\t1 - minor"+nl+
				"\t2 - move"+nl+
				"\t3 - standard"+nl+
				"\t4 - special"+nl+
				"attack value - a specially formatted line"+nl+
				"\tthe format should be as follows (Attack Type) vs./v (Defense Type). ex Wis vs. Will, Str v Fort"+nl+
				"\t\tThe white-space here is important, the formatting of the actual text is personal preference, for exampl St v Fo is a valid input"+nl+
				"\t\tBut StrvFort and Strv Fort are not."+nl+
				"prep - either true or false"+nl+
				"Special Note: This application uses special line detection, when entering powers into the sbook file use a standard new line for each new line in a power text."+nl+
				"Example: If a power has a Hit and and Effect line enter the hit line then simply hit enter and enter the Effect line.";
		String menu = "You're now at the main menu!"+nl+
				"Options:"+nl+
				"l - list all spells"+nl+
				"lp - list all prepared spells"+nl+
				"lc - list castable spells (spells that have yet to be cast since last rest"+nl+
				"sr - take a short rest and reset all encounter powers to uncast state"+nl+
				"er - take an extended rest and reset all encounter and daily powers to uncast state"+nl+
				"s - save the spell book to a file."+nl+
				"cast - cast a spell. Casting an at-will spell has no effect."+nl+
				"p - access options for altering which spells are prepared (NYI)";


		//welcome message
		String in;
		System.out.println("Welcome to btcraig's Wizard Spell Book Utility!"+nl+
				"To begin you may either open a spell book or create a new one."+nl+
				"This is rough thrown together application. Very little error checking is performed and user input is not verified in most cases. Be careful and use at your own risk!"+nl+
				"o - open a spell book"+nl+
				"c - create a new spell book"+nl+
				"i - info on manually building a spell book. This will provide you the information if you wish to manually construct a file for your spell book.");
		in = scan.next();
		while( !in.equals("o") && !in.equals("c") && !in.equals("i")){
			System.out.println("That is not a valid selection. Please select open or new (o, or c).");
			in = scan.next();
		}

		while(in.equals("i")){ 
			System.out.println(info);
			System.out.println("o - open a spell book"+nl+
					"c - create a new spell book"+nl+
					"i - info on manually building a spell book. This will provide you the information if you wish to manually construct a CSV file for your spell book.");
			in = scan.next();
		}

		/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		/*
		 * Creating a new spell book
		 * First we get the name for the character (which will also serve as the file name).
		 * Then we begin getting spells from the user line by line.
		 */
		if(in.equals("c")){
			System.out.println("So you want to create a new spell book! That's great let's get started!"+nl+
					"Let's get started and add our first spell.");

			/* now we make spells one at a time */
			//tvars
			spell temp;
			String s_name;
			int level;
			String text, range, target, atk1, atk2;
			attack atk;
			recharge rchg;
			action act;
			boolean prep;
			type t;
			LinkedList<String> kw = new LinkedList<String>();
			boolean cont=true;

			while(cont){
				//name
				System.out.println("What's spell's name?" + nl);
				s_name = scan.next();

				//type
				while(true){
					System.out.println("Is an attack or utility power (0/1)?");
					try{
						t = new type(scan.nextInt());
						break;
					} catch (InputMismatchException e){
						System.out.println("Your input was not valid! Try again.");	
					}
				}

				//level
				while(true){
					System.out.println("What level is this power?");
					try {
						level = scan.nextInt();
						if(level > 30) throw new InputMismatchException("Invalid input!");
						break;
					} catch (InputMismatchException e) {
						System.out.println("Your input was not valid! Try again.");				
					}
				}

				//recharge
				while(true){
					try{
						System.out.println("What is the recharge on this power - At-Will, Encounter, Daily, Special (0/1/2/3)");
						rchg = new recharge(scan.nextInt());
						break;
					} catch (InputMismatchException e) {
						System.out.println("Your input was not valid! Try again.");				
					}
				}

				//keywords
				System.out.println("Now we want the keywords for the spell. The application will continue accepting until you input a single s.");
				while(true){
					String kwtemp;
					kwtemp = scan.next();
					if(kwtemp.equals("s")){
						break;
					} else {
						kw.add(kwtemp);
						System.out.println("Added "+kwtemp+" to the keywords list.");
					}
				}

				//action
				while(true){
					try {
						System.out.println("What type of action does this power use - Free/No Action, Minor, Move, Standard, Special (0/1/2/3/4)");
						act = new action(scan.nextInt());
						break;
					} catch (InputMismatchException e) {
						System.out.println("Your input was not valid! Try again.");				
					}
				}

				//range
				System.out.println("What is the range on the power? Eg Close Blast 3, Ranged 10, etc");
				scan.nextLine(); //eat some input
				range = scan.nextLine();

				//target
				System.out.println("What is the target(s) for the power?");
				target = scan.nextLine();

				//attacking
				System.out.println("What does this power attack with? (Str, Wis, Cha, etc)");
				atk1 = scan.next();
				System.out.println("What defense does the power attack? (Will, AC, etc)");
				atk2 = scan.next();
				atk = new attack(atk1, atk2);

				//text
				System.out.println("What is the hit and effect lines for the power? Note: this includes all hit and effects."+nl+"Primary, secondary, miss, etc");
				scan.nextLine(); //eat some input
				text = scan.nextLine();

				//prepared
				while(true){
					System.out.println("Is this spell currently prepared (y/n)? Note: this can be changed later (NYI).");
					String s1 = scan.next();
					if(!s1.equals("y") && !s1.equals("n")) continue;
					else { //because we ensure that s1 is either y or n above we dont need to verify that s1 = n if it is not y
						if(s1.equals("y")) prep = true;
						else prep = false;
						break;
					}
				}

				while(true){
					System.out.println("Add another spell (y/n)?");
					String s1 = scan.next();
					if(!s1.equals("y)") && !s1.equals("n")) continue;
					else { //because we ensure that s1 is either y or n above we dont need to verify that s1 = n if it is not y
						if(s1.equals("y")) cont = true;
						else cont = false;
						break;
					}
				}


				temp = new spell(s_name, level, atk, text, range, target, rchg, act, prep, t, kw);
				spellbook.addSpell(temp);
				//check to add another spell if true continue else we break out and go to the main menu
				if(cont) continue;
				else break;
			}

			/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
			/* Open a spell book from file */
		} else if (in.equals("o")){
			String fname;
			System.out.println("Ok, so you want to load a spell book! Currently only fully qualified file names are guaranteed to open."+nl+
					"Supported formats are .sbook"+nl+
					"For info on how to format a .sbook file for manual creating and import press i"+nl+
					"Please enter the fully qualified path of the spell book file.");
			fname  = scan.next();
			if(fname.equals("i")) {		
				while(in.equals("i")){ 
					System.out.println(info);
					System.out.println("Please enter the fully qualified path of the spell book file or if you're feeling adventurous hit i again.");
					in = scan.next();
				}
			}
			else{
				while(!fname.contains(".sbook")){
					System.out.println("Invalid file format, try again!");
					fname = scan.next();
				}

				try { // open the file for reading
					fscan = new Scanner(new FileReader(fname));
					fscan.useDelimiter("ENDL");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				//start reading lines
				//format: name,type,level,rchg,[kwlist],act,range,target,atk,text,prep
				String read;
				String[] delim;
				while(fscan.hasNext()){
					read = fscan.next();
					delim = read.split(";"); // split on ; because there are going to be , elsewhere
					// delim (should be) : 0 name, 1 type (int), 2 level (int), 3 rchg (int),
					//						4 keywords (comma sep), 5 action (int), 6 range,
					//						7 target, 8 attack (atk, def), 9 text, 10 prep

					LinkedList<String> tll = new LinkedList<String>();
					String[] tkw = delim[4].split(",");
					//build keyword list
					for(int i=0;i<tkw.length;++i){
						tll.add(tkw[i]);							
					}

					//fuck dealing with unnamed variables
					String new_name = delim[0];
					type new_type = new type(Integer.parseInt(delim[1]));
					int new_level = Integer.parseInt(delim[2]);
					recharge new_rchg = new recharge(Integer.parseInt(delim[3]));
					action new_act = new action(Integer.parseInt(delim[5]));
					String new_range = delim[6];
					String new_target = delim[7];
					String new_atk1, new_atk2;
					String[] tatk = delim[8].split(" ");
					new_atk1 = tatk[0];
					new_atk2 = tatk[2];
					attack new_atk = new attack(new_atk1,new_atk2);
					String new_text = delim[9];
					boolean new_prep = Boolean.parseBoolean(delim[10]);

					spell new_spell = new spell(new_name, new_level, new_atk, new_text, new_range, 
							new_target, new_rchg, new_act, new_prep, new_type, tll);

					spellbook.addSpell(new_spell);
				}
				fscan.close();
			}

		}
		/*-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		System.out.println(menu);
		in = "";
		boolean tmp = true;
		while(scan.hasNext()){
			in = scan.next();
			switch(in){
			case "l": System.out.println(sb.list()); break;
			case "lp": System.out.println(sb.list_prep()); break;
			case "lc": System.out.println(sb.list_cast()); break;
			case "sr": sb.shortRest(); System.out.println("Taking a short rest\r\n"); break;
			case "er": sb.extRest(); System.out.println("Taking an extended rest\r\n"); break;
			case "s" : saveToFile(sb.toString(), scan); break;
			case "cast": castSpell(sb, scan); break;
			case "p": break;
			case "e": return;
			default: System.out.println(menu);
			break;
			}
			if(!tmp) System.out.println(menu); else tmp=false; //print the menu but not initially
		}
		return;
	}

	/**
	 * Save the spell book to a file
	 * @param data The spell book data
	 * @param s The scanner that reads System.in
	 */
	public static void saveToFile(String data, Scanner s){
		String name;
		BufferedWriter bw;
		FileWriter fw;
		System.out.println("Saving your spell book.\r\n"+
				"Where would you like to save your spell book?\r\n"+
				"Please enter a fully qualified file path (C:\\Users\\<your user>\\My Documents\\<file name>.sbook\r\nIf you don't input a fully qualified path there is no guarantee your data will be saved.\r\nAlways check before exiting to ensure data saved correctly and maintain back ups");
		name = s.next();
		if(!name.contains(".sbook")) name += ".sbook"; // make sure to put the file ext in

		//since when we build data we use the toString for a LL we know that [] will be present in the data and need to drop it off.
		data = data.replaceAll("\\[", "");
		data = data.replaceAll("]", "");

		File file = new File(name);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		try {
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cast a specified spell in the book.
	 * @param sb The spell book
	 * @param scan The system.in scanner.
	 */
	public static void castSpell(spellbook sb, Scanner scan){
		String ret;
		ret = "";
		String nl = "\r\n";
		int level;
		System.out.println("What level is the spell?");
		level = scan.nextInt();
		LinkedList<spell> ll = sb.getSpellByLevel(level);
		if(ll == null) System.out.println("No spells at that level!");
		else{
			int i = 0;
			for(spell s : ll){ //print each spell with an index
				ret += i + ". " + s.getName() + " " + s.getType().toString() + " " + s.getLevel(); //Name (Attack/Utility) Level
				if(!s.isCast()) ret += " [ ]" + nl;
				else ret+= " [X]" + nl;
				ret += s.getRchg().toString() + " ** " + s.getKw().toString() + nl; //Recharge ** Keywords
				ret += s.getAct().toString() + " " + s.getRange() + nl; //Action Range
				ret += s.getTarget() + nl;//Target
				ret += s.getAtk().toString() + nl;
				ret += s.getText() + nl;
				++i;
				System.out.println(ret);
				ret = "";
			}
			System.out.println("Which spell do you want to cast (index)?");
			level = scan.nextInt();
			if(level>=ll.size()) System.out.println("No such spell!");
			else {
				System.out.println("Casting " + ll.get(level).getName());
				ll.get(level).setCast(true);
			}
		}
	}
}
