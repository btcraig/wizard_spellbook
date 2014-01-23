package wizard_spellbook;
import java.util.*;
import java.util.Map.Entry;

public class spellbook {
	private static  HashMap<Integer, LinkedList<spell>> hm;
	public spellbook(){
		hm = new HashMap<Integer, LinkedList<spell>>(30);
		for(int i=0;i<30;++i){
			hm.put(i, new LinkedList<spell>());
		}
	}

	/**
	 * Add the spell to the spell book.
	 * @param s The constructed spell to add.
	 * @return The spell added.
	 */
	public static void addSpell(spell s){
		hm.get(s.getLevel()).add(s);	
	}


	/**
	 * See spell documentation for info.
	 * Use the main class to get keywords and insert them to a LL to pass to this.
	 * {@link wizard_spellbook.spell}
	 */
	public void createSpell(String s, int i, attack a, String t, String ra, String ta, 
			recharge r, action ac, boolean p, type ty, LinkedList<String> keys){

		spell sp = new spell(s, i, a, t, ra, ta, r, ac, p, ty, keys);
	}

	/**
	 * List all spells in the spell book.
	 * @return A really big string that contains all the powers.
	 */
	public String list(){
		String ret;
		String nl = "\r\n";
		ret = "";
		for(LinkedList<spell> ll : hm.values()){
			for(spell s : ll){
				//construct the 'power card'
				ret += s.getName() + " " + s.getType().toString() + " " + s.getLevel() + nl; //Name (Attack/Utility) Level
				ret += s.getRchg().toString() + " ** " + s.getKw().toString() + nl; //Recharge ** Keywords
				ret += s.getAct().toString() + " " + s.getRange() + nl; //Action Range
				ret += s.getTarget() + nl;//Target
				ret += s.getAtk().toString() + nl;
				ret += s.getText() + nl;
				ret += "Prepared: " + s.isPrep() + nl + nl;
			}
		}
		return ret;
	}

	/**
	 * List only the prepared spells in the spell book.
	 * @return
	 */
	public String list_prep(){
		String ret;
		String nl = "\r\n";
		ret = "";
		for(LinkedList<spell> ll : hm.values()){
			for(spell s : ll){
				if(s.isPrep()){
					//construct the 'power card'
					ret += s.getName() + " " + s.getType().toString() + " " + s.getLevel() + nl; //Name (Attack/Utility) Level
					ret += s.getRchg().toString() + " ** " + s.getKw().toString() + nl; //Recharge ** Keywords
					ret += s.getAct().toString() + " " + s.getRange() + nl; //Action Range
					ret += s.getTarget() + nl;//Target
					ret += s.getAtk().toString() + nl;
					ret += s.getText() + nl;
					ret += "Prepared: " + s.isPrep() + nl + nl;
				} else continue;
			}
		}
		return ret;
	}
}
