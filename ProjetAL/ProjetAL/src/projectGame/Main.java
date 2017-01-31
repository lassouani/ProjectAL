package projectGame;
import java.util.ArrayList;


import gameframework.core.GameDefaultImpl;
import gameframework.core.GameLevel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameDefaultImpl g = new GameDefaultImpl();
		ArrayList<GameLevel> levels = new ArrayList<>();

		levels.add(new GameLevelOne(g)); // only one level is available
		
		g.setLevels(levels);
		g.start();
	}

}
