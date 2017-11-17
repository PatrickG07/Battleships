package ch.pg.sinkships.model;

import ch.pg.sinkships.model.Table;

public class Game {
	
	public static Table table1 = new Table();
	public static Table table2 = new Table();

	static String actualTable = "table1";

	public static String getActualTable() {
		return actualTable;
	}

	public void setAtualTable(String table) {
		Game.actualTable = table;
	}
}

