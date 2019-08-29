package ch.pg.sinkships.model;

import ch.pg.sinkships.model.Table;

/**
 * Create The Game and adds The Tables for the Players
 * 
 * @author PatrickG07
 */
public class Game {

	public static Table table1 = new Table();

	public static String actualTable = "table1";

	public static String getActualTable() {
		return actualTable;
	}

	public static void setAtualTable(String table) {
		Game.actualTable = table;
	}
}
