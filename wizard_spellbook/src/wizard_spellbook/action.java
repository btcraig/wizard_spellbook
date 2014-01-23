package wizard_spellbook;


/**
 * @author btcraig
 * Returns an int to determine the action type.
 * @param i Integer tracking the action type. See below for information on what return types mean.
 * 0 - Free/No action (seperate these???)
 * 1 - Minor
 * 2 - Move
 * 3 - Standard
 * 4 - Other
 * All other values are errors and should be treated as such.
 */
public class action {
	int act;
	
	/**
	 * Default constructor.
	 * If called it signifies an error and act is set to -1 to note this.
	 */
	public action(){
		act = -1;
	}
	
	/**
	 * Primary constructor.
	 * Checks for valid input and sets act to -1 if input is invalid.
	 * @param i The type of action, see class documentation for more information.
	 */
	public action(int i){
		if(i<0 || i>4) i = -1;
		else act = i;
	}
	
	/**
	 * Return the action type
	 * @return The type of action, see class docs for more info.
	 */
	public int getact(){
		return act;
	}
	
	/**
	 * Set the action type, see class doc for more info.
	 * Will check for valid input and set accordingly.
	 * @param i The value to set act to.
	 * @return The new value of act.
	 */
	public int setact(int i){
		if(i<0 || i>4) i = -1;
		else act = i;
		return act;
	}
	
	public String toString(){
		if(act == 0) return "Free";
		else if(act == 1) return "Minor";
		else if(act == 2) return "Move";
		else if(act == 3) return "Standard";
		else if(act == 4) return "Other";
		else return null;
	}
}
