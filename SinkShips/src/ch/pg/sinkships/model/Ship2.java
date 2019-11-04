package ch.pg.sinkships.model;

/**
 * The Class Where the 2 Tile long Ship is created.
 * 
 * @author PatrickG07
 */
public class Ship2 {

	Boolean destroyed = false;
	Boolean Horizontal = true;

	int pos1X, pos2X;
	int pos1Y, pos2Y;

	int Health = 2;

	public Boolean getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(Boolean destroyd) {
		this.destroyed = destroyd;
	}

	public Boolean getHorizontal() {
		return Horizontal;
	}

	public void setHorizontal(Boolean diagonal) {
		Horizontal = diagonal;
	}

	public int getPos1X() {
		return pos1X;
	}

	public void setPos1X(int pos1x) {
		pos1X = pos1x;
	}

	public int getPos2X() {
		return pos2X;
	}

	public void setPos2X(int pos2x) {
		pos2X = pos2x;
	}

	public int getPos1Y() {
		return pos1Y;
	}

	public void setPos1Y(int pos1y) {
		pos1Y = pos1y;
	}

	public int getPos2Y() {
		return pos2Y;
	}

	public void setPos2Y(int pos2y) {
		pos2Y = pos2y;
	}

	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}

	/**
	 * get the exact positon in the grid by x * 10 + y
	 * 
	 * @param i
	 * @return
	 */
	public int getPos(int i) {
		if (i == 1) {
			System.out.println(i + ":" + getPos1X() + "+" + getPos1Y());
			return getPos1X() * 10 + getPos1Y();
		} else if (i == 2) {
			System.out.println(i + ":" + getPos2X() + "+" + getPos2Y());
			return getPos2X() * 10 + getPos2Y();
		} else {
			return 1000000;
		}
	}
}
