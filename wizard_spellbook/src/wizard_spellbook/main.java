package wizard_spellbook;
import java.io.*;
import java.util.*;

public class main {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		BufferedReader br;
		BufferedWriter bw;
		String name;
		spellbook sb = new spellbook();
		String nl = "\r\n";
		/* test block
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("Arcane");
		ll.add("Evocation");
		ll.add("Implement");
		ll.add("Thunder");
		spell sp = new spell("Thunderwave",1,new attack("Int","Fort"),
							"1d6 + Int mod damage and you push each creature up to Wis mod squares.",
							"Close Blast 3",
							"Each creature in the blast",
							0,
							3,
							true,
							0,
							ll);
		s.addSpell(sp);
		System.out.print(s.list());
		 */

		String in;
		System.out.println("Welcome to btcraig's Wizard Spell Book Utility!"+nl+
				"To begin you may either open a spell book or create a new one."+nl+
				"At any time you are asked for input you can always press e to exit this program, but be careful you may lose data if you're not careful!!"+nl+
				"o - open a spell book"+nl+"c - create a new spell book"+nl+"i - info on manually building a spell book. This will provide you the information if you wish to manuall construct a CSV file for your spell book."+nl+nl);
		in = scan.next();
		while( !in.equals("o") && !in.equals("c")){
			System.out.println("That is not a valid selection. Please select open or new (o, or c)."+nl);
			in = scan.next();
		}

		/*
		 * Creating a new spell book
		 * First we get the name for the character (which will also serve as the file name).
		 * Then we begin getting spells from the user line by line.
		 */
		if(in.equals("c")){
			System.out.println("So you want to create a new spell book! That's great let's get started!"+nl+
					"First: What is the character's name? This is going to be the file name as well!");
			in = scan.next();
			name = in;
			System.out.println("Ok, so your name is going to be: " + name + nl + " Is that right (y/n)?" + nl);

			in = scan.next();
			while(!in.equals("y")){
				System.out.println("In that case what name would you like to use?" + nl);
				in = scan.next();
				System.out.println("Ok, so your name is going to be: " + name + nl + " Is that right (y/n)?" + nl);
			}
			name = in;
			System.out.println("Alright then, " + name + " let's get started putting that spell book together then!"+nl+
					"Remember you can use to e exit and manually build your spell book in a csv and load it that way!"+nl+
					"So let's take it from the top and add our first spell."+nl);

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
						System.out.println("Your input was not valid! Try again."+nl);				
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
					System.out.println("Is this spell currently prepared (y/n)? Note: this can be changed later.");
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
		} else {}
		
		//TODO l and lp actual shit
		System.out.println("You're now at the main menu!"+nl+
				"Options:"+nl+
				"l - list all spells"+nl+
				"lp - list all prepared spells");
		System.out.println(sb.list());		
		return;
	}
}
