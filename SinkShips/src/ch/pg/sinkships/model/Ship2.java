package ch.pg.sinkships.model;

/**
 * The Class Where the 2 Tile long Ship is created.
 * 
 * @author PatrickG07
 */
public class Ship2 {

	Boolean destroyd = false;
	Boolean Horizontal = true;

	int pos1X, pos2X;
	int pos1Y, pos2Y;

	int Health = 2;

	public Boolean getDestroyd() {
		return destroyd;
	}

	public void setDestroyd(Boolean destroyd) {
		this.destroyd = destroyd;
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

}
