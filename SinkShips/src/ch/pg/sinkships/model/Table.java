package ch.pg.sinkships.model;

import ch.pg.sinkships.model.Ship2;
import ch.pg.sinkships.model.Ship3;
import ch.pg.sinkships.model.Ship4;
import ch.pg.sinkships.model.Ship5;

public class Table {

	public Ship2 Ship1 = new Ship2();
	public Ship3 Ship2 = new Ship3();
	public Ship3 Ship3 = new Ship3();
	public Ship4 Ship4 = new Ship4();
	public Ship5 Ship5 = new Ship5();

	boolean checkforhit;

	public void checkforhit(final int X, final int Y) {
		ship2(X, Y);
		ship3(X, Y);
		ship4(X, Y);
		ship5(X, Y);
	}

	public void ship2(final int X, final int Y) {
		if (Ship1.getPos1X() == X && Ship1.getPos1Y() == Y) {
			checkforhit = true;
			Ship1.setHealth(-1);
		} else if (Ship1.getPos2X() == X && Ship1.getPos2Y() == Y) {
			checkforhit = true;
			Ship1.setHealth(-1);
		}
		if (Ship1.getHealth() == 0) {
			Ship1.setDestroyd(true);
		}
	}

	public void ship3(final int X, final int Y) {
		if (Ship2.getPos1X() == X && Ship2.getPos1Y() == Y) {
			checkforhit = true;
			Ship2.setHealth(-1);
		} else if (Ship2.getPos2X() == X && Ship2.getPos2Y() == Y) {
			checkforhit = true;
			Ship2.setHealth(-1);
		} else if (Ship2.getPos3X() == X && Ship2.getPos3Y() == Y) {
			checkforhit = true;
			Ship2.setHealth(-1);
		}
		if (Ship2.getHealth() == 0) {
			Ship2.setDestroyd(true);
		}

		if (Ship3.getPos1X() == X && Ship3.getPos1Y() == Y) {
			checkforhit = true;
			Ship3.setHealth(-1);
		} else if (Ship3.getPos2X() == X && Ship3.getPos2Y() == Y) {
			checkforhit = true;
			Ship3.setHealth(-1);
		} else if (Ship3.getPos3X() == X && Ship3.getPos3Y() == Y) {
			checkforhit = true;
			Ship3.setHealth(-1);
		}
		if (Ship3.getHealth() == 0) {
			Ship3.setDestroyd(true);
		}
	}

	public void ship4(final int X, final int Y) {
		if (Ship4.getPos1X() == X && Ship4.getPos1Y() == Y) {
			checkforhit = true;
			Ship4.setHealth(-1);
		} else if (Ship4.getPos2X() == X && Ship4.getPos2Y() == Y) {
			checkforhit = true;
			Ship4.setHealth(-1);
		} else if (Ship4.getPos3X() == X && Ship4.getPos3Y() == Y) {
			checkforhit = true;
			Ship4.setHealth(-1);
		} else if (Ship4.getPos4X() == X && Ship4.getPos4Y() == Y) {
			checkforhit = true;
			Ship4.setHealth(-1);
		}
		if (Ship4.getHealth() == 0) {
			Ship4.setDestroyd(true);
		}
	}

	public void ship5(final int X, final int Y) {
		if (Ship5.getPos1X() == X && Ship5.getPos1Y() == Y) {
			checkforhit = true;
			Ship5.setHealth(-1);
		} else if (Ship5.getPos2X() == X && Ship5.getPos2Y() == Y) {
			checkforhit = true;
			Ship5.setHealth(-1);
		} else if (Ship5.getPos3X() == X && Ship5.getPos3Y() == Y) {
			checkforhit = true;
			Ship5.setHealth(-1);
		} else if (Ship5.getPos4X() == X && Ship5.getPos4Y() == Y) {
			checkforhit = true;
			Ship5.setHealth(-1);
		} else if (Ship5.getPos5X() == X && Ship5.getPos5Y() == Y) {
			checkforhit = true;
			Ship5.setHealth(-1);
		}
		if (Ship5.getHealth() == 0) {
			Ship5.setDestroyd(true);
		}
	}

	public boolean isCheckforhit() {
		return checkforhit;
	}

	public void setCheckforhit(boolean checkforhit) {
		this.checkforhit = checkforhit;
	}

}
