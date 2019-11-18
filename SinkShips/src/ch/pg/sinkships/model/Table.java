package ch.pg.sinkships.model;

import ch.pg.sinkships.model.Ship2;
import ch.pg.sinkships.model.Ship3;
import ch.pg.sinkships.model.Ship4;
import ch.pg.sinkships.model.Ship5;

/**
 * Create Table for each Player and adding the Sips.
 * 
 * @author PatrickG07
 */
public class Table {

	int ships = 5;

	public Ship2 Ship1 = new Ship2();
	public Ship3 Ship2 = new Ship3();
	public Ship3 Ship3 = new Ship3();
	public Ship4 Ship4 = new Ship4();
	public Ship5 Ship5 = new Ship5();

	public String ip;
	
	boolean checkforhit;
	
	/**
	 * it checks if one Ship is hit and returns the checkforhit as true.
	 * 
	 * @param X
	 * @param Y
	 */
	public void checkforhit(final int X, final int Y) {

		// Ship1
		if (Ship1.getDestroyed() == false) {
			if (Ship1.getPos1X() == X && Ship1.getPos1Y() == Y) {
				checkforhit = true;
				Ship1.setHealth(Ship1.getHealth() - 1);
			} else if (Ship1.getPos2X() == X && Ship1.getPos2Y() == Y) {
				checkforhit = true;
				Ship1.setHealth(Ship1.getHealth() - 1);
			}
			if (Ship1.getHealth() == 0) {
				Ship1.setDestroyed(true);
			}
		}

		// Ship2
		if (Ship2.getDestroyed() == false) {
			if (Ship2.getPos1X() == X && Ship2.getPos1Y() == Y) {
				checkforhit = true;
				Ship2.setHealth(Ship2.getHealth() - 1);
			} else if (Ship2.getPos2X() == X && Ship2.getPos2Y() == Y) {
				checkforhit = true;
				Ship2.setHealth(Ship2.getHealth() - 1);
			} else if (Ship2.getPos3X() == X && Ship2.getPos3Y() == Y) {
				checkforhit = true;
				Ship2.setHealth(Ship2.getHealth() - 1);
			}
			if (Ship2.getHealth() == 0) {
				Ship2.setDestroyed(true);
				ships = -1;
			}
		}

		// Ship3
		if (Ship3.getDestroyed() == false) {
			if (Ship3.getPos1X() == X && Ship3.getPos1Y() == Y) {
				checkforhit = true;
				Ship3.setHealth(Ship3.getHealth() - 1);
			} else if (Ship3.getPos2X() == X && Ship3.getPos2Y() == Y) {
				checkforhit = true;
				Ship3.setHealth(Ship3.getHealth() - 1);
			} else if (Ship3.getPos3X() == X && Ship3.getPos3Y() == Y) {
				checkforhit = true;
				Ship3.setHealth(Ship3.getHealth() - 1);
			}
			if (Ship3.getHealth() == 0) {
				Ship3.setDestroyed(true);
				ships = -1;
			}
		}
		// Ship4
		if (Ship4.getDestroyed() == false) {
			if (Ship4.getPos1X() == X && Ship4.getPos1Y() == Y) {
				checkforhit = true;
				Ship4.setHealth(Ship4.getHealth() - 1);
			} else if (Ship4.getPos2X() == X && Ship4.getPos2Y() == Y) {
				checkforhit = true;
				Ship4.setHealth(Ship4.getHealth() - 1);
			} else if (Ship4.getPos3X() == X && Ship4.getPos3Y() == Y) {
				checkforhit = true;
				Ship4.setHealth(Ship4.getHealth() - 1);
			} else if (Ship4.getPos4X() == X && Ship4.getPos4Y() == Y) {
				checkforhit = true;
				Ship4.setHealth(Ship4.getHealth() - 1);
			}
			if (Ship4.getHealth() == 0) {
				Ship4.setDestroyed(true);
				ships = -1;
			}
		}

		// Ship5
		if (Ship5.getDestroyed() == false) {
			if (Ship5.getPos1X() == X && Ship5.getPos1Y() == Y) {
				checkforhit = true;
				Ship5.setHealth(Ship5.getHealth() - 1);
			} else if (Ship5.getPos2X() == X && Ship5.getPos2Y() == Y) {
				checkforhit = true;
				Ship5.setHealth(Ship5.getHealth() - 1);
			} else if (Ship5.getPos3X() == X && Ship5.getPos3Y() == Y) {
				checkforhit = true;
				Ship5.setHealth(Ship5.getHealth() - 1);
			} else if (Ship5.getPos4X() == X && Ship5.getPos4Y() == Y) {
				checkforhit = true;
				Ship5.setHealth(Ship5.getHealth() - 1);
			} else if (Ship5.getPos5X() == X && Ship5.getPos5Y() == Y) {
				checkforhit = true;
				Ship5.setHealth(Ship5.getHealth() - 1);
			}
			if (Ship5.getHealth() == 0) {
				Ship5.setDestroyed(true);
				ships = -1;
			}
		}
	}

	public boolean isCheckforhit() {
		return checkforhit;
	}

	public void setCheckforhit(boolean checkforhit) {
		this.checkforhit = checkforhit;
	}

	public int getShips() {
		return ships;
	}

	public void setShips(int ships) {
		this.ships = ships;
	}
}
