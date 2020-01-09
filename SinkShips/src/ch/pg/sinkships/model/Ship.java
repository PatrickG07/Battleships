package ch.pg.sinkships.model;

/**
 * The Class Where the 2 Tile long Ship is created.
 * 
 * @author PatrickG07
 */
public class Ship {

	int lenth = 0;

	Boolean destroyed = false;
	Boolean Horizontal = true;

	int[][] pos = new int[5][2];

	int Health = lenth;

	public boolean getDestroyed;

	public Ship(int i) {
		Health = i;
	}

	public int getLenth() {
		return lenth;
	}

	public void setLenth(int lenth) {
		this.lenth = lenth;
	}

	public Boolean getDestroyed() {
		return destroyed;
	}

	public void setDestroyed(Boolean destroyed) {
		this.destroyed = destroyed;
	}

	public Boolean getHorizontal() {
		return Horizontal;
	}

	public void setHorizontal(Boolean horizontal) {
		Horizontal = horizontal;
	}

	public int getPos(int x, int y) {
		return pos[x][y];
	}

	public void setPos(int x, int y, int num) {
		this.pos[x][y] = num;
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
		if (i <= lenth) {
			switch (i) {
			case 1:
				return getPos(0, 0) * 10 + getPos(0, 1);
			case 2:
				return getPos(1, 0) * 10 + getPos(1, 1);
			case 3:
				return getPos(2, 0) * 10 + getPos(2, 1);
			case 4:
				return getPos(3, 0) * 10 + getPos(3, 1);
			case 5:
				return getPos(4, 0) * 10 + getPos(4, 1);
			}
		}
		return 1000000;
	}
}
