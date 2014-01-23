wizard_spellbook
================

A simple command line application for tracking a Wizard spell book in D&D 4E

Currently supports several features.
Users may:
- Create a spell book in the application or create a .sbook file and load it (see below for formatting the file).
- Save a spell book that has been changed or created out to a fille.
- View the entire spell book, only prepared spells, and only spells that may be cast (prepared and not used yet).
- Take a short or extended rest to reset the recharge of Encounter and Daily powers.
- (NYI) Change which spells are prepared.

When first running the application you will be prompted to either open (o) a spell book, create (c) a spell book
or given info (i) on creating a .sbook (see below for the same information). After loading or creating a spell book
you will taken to the main menu which provides options for the features listed above.


MENU OPTIONS
=======================
- These options are also displayed while running.
l - list all spells
lp - list all prepared spells
lc - list castable spells (spells that have yet to be cast since last rest
sr - take a short rest and reset all encounter powers to uncast state
er - take an extended rest and reset all encounter and daily powers to uncast state
s - save the spell book to a file
cast - cast a spell. Casting an at-will spell has no effect
p - access options for altering which spells are prepared (NYI)

CREATING A .sbook FILE
======================
To create a .sbook file the following format should be used:
name;type value;level;recharge value;keyword1,keyword2,...;action value,range,target,attack value,text,prep
type value - an integer
	0 - an attack power
	1 - a utility power
recharge value - an integer between 0 and 3.
	0 - at-will
	1 - encounter
	2 - daily
	3 - special
action value - an integer between 0 and 4
	0 - free/no action
	1 - minor
	2 - move
	3 - standard
	4 - special
attack value - a specially formatted line
	the format should be as follows (Attack Type) vs./v (Defense Type). ex Wis vs. Will, Str v Fort
		The white-space here is important, the formatting of the actual text is personal preference, for exampl St v Fo is a valid input
		But StrvFort and Strv Fort are not.
prep - either true or false
Special Note: This application uses special line detection, when entering powers into the sbook file use a standard new line for each new line in a power text.
Example: If a power has a Hit and and Effect line enter the hit line then simply hit enter and input the Effect line.
