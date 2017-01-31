package projectGame;

/*
 * Niveau pour tester les mouvements et les combats
 * Test de l'ajout d'unités via le framework de soldiers
 */
import gameframework.core.CanvasDefaultImpl;
import gameframework.core.Game;
import gameframework.core.GameLevelDefaultImpl;
import gameframework.core.GameMovableDriverDefaultImpl;
import gameframework.core.GameUniverseDefaultImpl;
import gameframework.core.GameUniverseViewPortDefaultImpl;
import gameframework.moves_rules.MoveBlockerChecker;
import gameframework.moves_rules.MoveBlockerCheckerDefaultImpl;
import gameframework.moves_rules.MoveStrategyKeyboard;
import gameframework.moves_rules.MoveStrategyRandom;
import gameframework.moves_rules.OverlapProcessor;
import gameframework.moves_rules.OverlapProcessorDefaultImpl;
import pacman.entity.TeleportPairOfPoints;
import pacman.entity.Wall;
import projectGame.entity.Gun;
import projectGame.entity.Shield;
import projectGame.entity.Tree;
import projectGame.rules.MoveStrategyKeyboardDefaultImpl;
import projectGame.rules.SoldierMoveBlockers;
import projectGame.rules.SoldierOverlapRules;
import projectGame.soldier.Ennemi;
import projectGame.soldier.Soldier;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.UnitSimple;
import soldier.units.UnitCenturion;

import java.awt.Canvas;
import java.awt.Point;

public class GameLevelOne extends GameLevelDefaultImpl {

	Canvas canvas;

	
	static int[][] tab = { 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 2, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 6, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 1, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 4, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 4 },
	    { 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 4 },
	    { 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 4, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 2, 5, 1, 5, 5, 5, 5, 5, 5, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 5, 5, 5, 2, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 1, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5, 5, 5, 1 },
	    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
	    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	public static final int SPRITE_SIZE = 40;
	
	@Override
	protected void init() {
		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();
		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new SoldierMoveBlockers());
		
	
		UnitSimple player = new  UnitCenturion("soldier");
		player.addCanvas(new Soldier(canvas, player));
		
		SoldierOverlapRules overlapRules = new SoldierOverlapRules(life[0], score[0], endOfGame);
		overlapProcessor.setOverlapRules(overlapRules);
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);
		
		int totalNbWeapon = 0;

		// Filling up the universe with basic non movable entities and inclusion in the universe
				for (int i = 0; i < 25; ++i) {
					for (int j = 0; j < 33; ++j) {
						if (tab[i][j] == 1) {
							universe.addGameEntity(new Wall(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
						}
						if (tab[i][j] == 2) {
							universe.addGameEntity(new Tree(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
						}
						if (tab[i][j] == 3) {
							universe.addGameEntity(new Gun(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
							totalNbWeapon++;
						}
						if (tab[i][j] == 4) {
							universe.addGameEntity( new TeleportPairOfPoints(new Point(j * SPRITE_SIZE, i * SPRITE_SIZE), new Point(16*SPRITE_SIZE, 14*SPRITE_SIZE)));
						}
						if (tab[i][j] == 6) {
							universe.addGameEntity(new Shield(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
						}
						
					}
				}
				
				overlapRules.setTotalNbGums(totalNbWeapon);
		
		GameMovableDriverDefaultImpl playerDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboard keyStr = new MoveStrategyKeyboardDefaultImpl(player.getCanvas());
		playerDriver.setStrategy(keyStr);
		playerDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		player.getCanvas().setDriver(playerDriver);
		player.getCanvas().setPosition(new Point(20 * SPRITE_SIZE, 15 * SPRITE_SIZE));
		
		//for (int i = 0; i < 8; i++) {

			 
			UnitSimple ennemi1 = new  UnitCenturion("Ennemi1"); 
			ennemi1.addCanvas(new Ennemi(canvas, ennemi1));
			//ennemi1.getCanvas().setPosition(new Point(i*SPRITE_SIZE, (i+10)*SPRITE_SIZE));
			ennemi1.getCanvas().setPosition(new Point(16*SPRITE_SIZE, 14*SPRITE_SIZE));
			//MoveStrategyRandom msr = new MoveStrategyRandom();
		
			player.getCanvas().setTypeWeapon("sword");
			universe.addGameEntity(ennemi1.getCanvas());
			universe.addGameEntity(player.getCanvas());	
		//}
	}

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}
}
