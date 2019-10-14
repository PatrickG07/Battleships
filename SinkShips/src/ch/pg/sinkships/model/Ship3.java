package ch.pg.sinkships.model;

/**
 * The Class Where the 3 Tile long Ship is created.
 * 
 * @author PatrickG07
 */
public class Ship3 {

	Boolean destroyd = false;
	Boolean Horizontal = true;

	int pos1X, pos2X, pos3X;
	int pos1Y, pos2Y, pos3Y;

	int Health = 3;

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

	public int getPos3X() {
		return pos3X;
	}

	public void setPos3X(int pos3x) {
		pos3X = pos3x;
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

	public int getPos3Y() {
		return pos3Y;
	}

	public void setPos3Y(int pos3y) {
		pos3Y = pos3y;
	}

	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}

	public int getPos(int i) {
		if(i == 1) {
			return getPos1X() * 10 + getPos1Y();
		}else if(i == 2){
			return getPos2X() * 10 + getPos2Y();
		}else if(i == 3){
			return getPos3X() * 10 + getPos3Y();
		}else {
			return 2000;
		}
	}

}
