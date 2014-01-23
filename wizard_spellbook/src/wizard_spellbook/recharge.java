package wizard_spellbook;


/**
 * @author btcraig
 * Returns an int to determine the action type.
 * @param i Integer tracking the action type. See below for information on what return types mean.
 * 0 - At-will
 * 1 - Encounter
 * 2 - Daily
 * 3 - Special (2/enc powers, etc) -- Will need to be implemented in the future for proper tracking (TODO)
 * All other values are errors and should be treated as such.
 */
public class recharge {
	int rchg;
	
	/**
	 * Default constructor. If this is called it was in error, we will never have the case where we do not have a value to store into rchg.
	 */
	public recharge(){
		rchg = -1;
	}
	
	/**
	 * Primary constructor.
	 * @param i The type of recharge for the related action.
	 */
	public recharge(int i){
		if(i<0 || i>3) i = -1;
		else rchg = i;
	}
	
	/**
	 * @return The int that signifies the action type. See class documentation for more information.
	 */
	public int getrchg(){
		return rchg;
	}
	
	/**
	 * This method will check that the input is valid, if the input is invalid rchg is set to -1.
	 * See class documentation for valid inputs and more info.
	 * @param i Set the recharge type to the given value.
	 * @return The new value of act.
	 */
	public int setrchg(int i){
		if (i<0 || i>3) i = -1; //set i to -1 and return eg error
		else rchg = i;
		return rchg;
	}
	
	public String toString(){
		if(rchg == 0) return "At-will";
		else if(rchg == 1) return "Encounter";
		else if(rchg == 2) return "Daily";
		else if(rchg == 3) return "Special";
		else return null;
	}
}