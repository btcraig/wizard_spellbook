package wizard_spellbook;

/**
 * Class to track attack types eg Wis v Will
 * @author btcraig
 * 
 * The class and no methods check for correctness of input.
 * It is left to the end user to input correct, or atleast legible information here.
 */
public class attack {
	String src, against;
	
	public attack(){
		src = against = "";		
	}
	
	public attack(String s, String a){
		src = s;
		against = a;
	}
	
	public attack(String s){
		String[] temp = s.split(" ");
		src = temp[0];
		against = temp[2];
	}
	
	/**
	 * Get the src of the attack (Wis, Str, etc)
	 * @return The type of the attack
	 */
	public String getSrc() {
		return src;
	}
	
	/**
	 * Alter the src of the attack
	 * @param src The new attack type (Wis, Str, etc)
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * The defense the attack targets (Will, Ref, etc)
	 * @return The type of defense.
	 */
	public String getAgainst() {
		return against;
	}
	
	/**
	 * Change the type of defense of the attack
	 * @param against The new defense
	 */
	public void setAgainst(String against) {
		this.against = against;
	}
	
	public String toString(){
		return src + " vs. " + against;
	}
}
