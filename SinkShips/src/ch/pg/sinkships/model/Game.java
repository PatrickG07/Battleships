package ch.pg.sinkships.model;

public class Game {
	
	public static Table table1 = new Table();
	public static Table table2 = new Table();
	
	public static Player play1 = new Player();
	public static Player play2 = new Player();

	String actualTable = "table1";

	public String getActualTable() {
		return actualTable;
	}

	public void setAtualTable(String table) {
		this.actualTable = table;
	}
}

