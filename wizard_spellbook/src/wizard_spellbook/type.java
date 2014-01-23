package wizard_spellbook;

/**
 * Track the type of an spell (utility or attack).
 * @author btcraig
 * 0 - Attack
 * 1 - Utility 
 */
public class type {
	int t;
	
	public type(){
		t=-1;
	}
	public type(int i){
		if(i<0 || i>1) t=-1;
		else t=i;
	}
	
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public String toString(){
		if(t == 0) return "Attack";
		else if(t==1) return "Utility";
		else return null;
	}
}
