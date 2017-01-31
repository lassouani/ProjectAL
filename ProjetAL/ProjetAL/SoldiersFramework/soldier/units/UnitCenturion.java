/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package soldier.units;

import gameframework.moves_rules.SpeedVector;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import projectGame.soldier.SoldierUnit;
import soldier.core.BehaviorSoldierStd;
import soldier.core.BreakingRuleException;
import soldier.core.UnitInfantry;
import soldier.core.Weapon;

public class UnitCenturion extends UnitInfantry {

	public UnitCenturion(String soldierName) {
		super(soldierName, new BehaviorSoldierStd(15, 100));
	}

	/**
	 * A Centurion can have at most two equipments
	 */
	@Override
	public void addEquipment(Weapon w) {
		if (nbWeapons() > 1)
			throw new BreakingRuleException();
		super.addEquipment(w);
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeedVector getSpeedVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpeedVector(SpeedVector m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oneStepMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
