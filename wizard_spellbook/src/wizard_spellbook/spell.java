package wizard_spellbook;
import java.util.LinkedList;

public class spell {
	String name;
	private int level;
	private String text, range, target;
	private attack atk;
	private recharge rchg;
	private action act;
	private boolean prep;
	private type type;
	private LinkedList<String> kw; //linked list because fuck resizing arrays
	
	/**
	 * Empty constructor.
	 * Creates empty variables for all associated data in a spell.
	 * Objects such as action and recharge will be initialized to -1.
	 */
	public spell(){
		name = "";
		level = -1;
		text = "";
		range = "";
		target = "";
		type = new type();
		atk = new attack();
		rchg = new recharge();
		act = new action();
		prep = false;
		kw = new LinkedList<String>();
	}
	
	/**
	 * Primary constructor.
	 * @param name The name of the spell.
	 * @param i Level of the spell.
	 * @param a The object to use for the attack
	 * @param t The text of the spell (effect and hit lines).
	 * @param ra The range of the spell
	 * @param ta The target of the spell
	 * @param r The recharge object of the spell.
	 * @param ac The action of the spell.
	 * @param p Whether or not the spell is prepared.
	 * @param ty The type of the spell (utility, attack)
	 * @param keys The keywords of the spell.
	 */
	public spell(String s, int i, attack a, String t, String ra, 
				String ta, recharge r, action ac, boolean p, type ty, 
				LinkedList<String> keys){
		name = s;
		level = i;
		atk = a;
		text = t;
		range = ra;
		target = ta;
		rchg = r;
		act = ac;
		type = ty; //this line isnt confusing at all
		prep = p;
		kw = keys;
	}
	
	public String toString(){
		return name+";"+type.getT()+";"+level+";"+rchg.getrchg()+";"+kw+";"+act.getact()+";"+
				range+";"+target+";"+atk+";"+text+";"+prep;		
	}
	
	/* getters and setters follow */
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRange() {
		return range;
	}
	
	
	public void setRange(String range) {
		this.range = range;
	}

	public type getType() {
		return type;
	}


	public void setType(type type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public attack getAtk() {
		return atk;
	}


	public void setAtk(attack atk) {
		this.atk = atk;
	}


	public recharge getRchg() {
		return rchg;
	}


	public void setRchg(recharge rchg) {
		this.rchg = rchg;
	}


	public action getAct() {
		return act;
	}


	public void setAct(action act) {
		this.act = act;
	}


	public boolean isPrep() {
		return prep;
	}


	public void setPrep(boolean prep) {
		this.prep = prep;
	}


	public LinkedList<String> getKw() {
		return kw;
	}


	public void setKw(LinkedList<String> kw) {
		this.kw = kw;
	}	
	
}
