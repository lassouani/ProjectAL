package projectGame.rules;

import gameframework.core.GameUniverse;
import gameframework.core.ObservableValue;
import gameframework.moves_rules.Overlap;

import gameframework.moves_rules.OverlapRulesApplierDefaultImpl;
import pacman.entity.TeleportPairOfPoints;
import projectGame.entity.Gun;
import projectGame.soldier.Ennemi;
import projectGame.soldier.Soldier;
import projectGame.soldier.SoldierUnit;
import soldier.core.UnitSimple;

import java.util.Vector;

public class SoldierOverlapRules extends OverlapRulesApplierDefaultImpl {
	protected GameUniverse universe;
	protected Vector<SoldierUnit> vEnnemis = new Vector<SoldierUnit>();

	protected boolean managePlayerDeath;
	private final ObservableValue<Integer> score;
	private final ObservableValue<Integer> life;
	private final ObservableValue<Boolean> endOfGame;
	
	int totalNbWeapon = 0;

	public SoldierOverlapRules(ObservableValue<Integer> life, ObservableValue<Integer> score,
			ObservableValue<Boolean> endOfGame) {
		this.life = life;
		this.score = score;
		this.endOfGame = endOfGame;
	}

	public void setUniverse(GameUniverse universe) {
		this.universe = universe;
	}

	public void addEnnemis(SoldierUnit g) {
		vEnnemis.addElement(g);
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		managePlayerDeath = true;
		super.applyOverlapRules(overlappables);
	}


	public void overlapRule(Ennemi p, Soldier g) {

		if(p.isAlive()){			
		     g.Attack();	
			score.setValue(score.getValue()+3);
			System.out.println("Soldier attack() "+p.HealthPoint());
		}
		if(g.isAlive()){			
			p.Attack();
			System.out.println("Ennemi attack() "+g.HealthPoint());
			  if (g.HealthPoint()== 0) {
				  endOfGame.setValue(true);
			}
		}
	}

	public void setTotalNbGums(int totalNbWeapon) {
		this.totalNbWeapon = totalNbWeapon;
		
	}
	
	public void overlapRule(UnitSimple p, TeleportPairOfPoints teleport) {
		p.setPosition(teleport.getDestination());
	}
	
	
	public void overlapRule(UnitSimple p, Gun spg) {
		score.setValue(score.getValue() + 3);
		universe.removeGameEntity(spg);
	
		p.addEquipment(spg);
		this.totalNbWeapon--;
		
	}
}
