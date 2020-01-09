package ch.pg.sinkships.model;

import ch.pg.sinkships.model.Ship;

/**
 * Create Table for each Player and adding the Sips.
 * 
 * @author PatrickG07
 */
public class Table {

	int ships = 5;

	public Ship[] ship = new Ship[] { new Ship(2), new Ship(3), new Ship(3), new Ship(4), new Ship(5) };

	public String ip;
	
	/**
	 * it checks if one Ship is hit and returns the checkforhit as true.
	 * 
	 * @param X
	 * @param Y
	 */
	public boolean checkforhit(final int X, final int Y) {
		for(Ship i : ship) {
			if(i.getDestroyed == false) {
				for(int l = 0; l <= 4; l++) {
					if(i.getPos(l,0) == X && i.getPos(l,1) == Y) {
						i.setHealth(i.getHealth() -1);
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getShips() {
		return ships;
	}

	public void setShips(int ships) {
		this.ships = ships;
	}
}
