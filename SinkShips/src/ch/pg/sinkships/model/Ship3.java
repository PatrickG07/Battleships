package ch.pg.sinkships.model;

public class Ship3 {

	Boolean destroyd;
	Boolean Diagonal;

	int pos1X = 5, pos2X, pos3X;
	int pos1Y = 5, pos2Y, pos3Y;

	int Health = 3;

	public Boolean getDestroyd() {
		return destroyd;
	}

	public void setDestroyd(Boolean destroyd) {
		this.destroyd = destroyd;
	}

	public Boolean getDiagonal() {
		return Diagonal;
	}

	public void setDiagonal(Boolean diagonal) {
		Diagonal = diagonal;
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

}
