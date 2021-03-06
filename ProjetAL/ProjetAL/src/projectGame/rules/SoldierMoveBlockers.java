package projectGame.rules;

import gameframework.core.Movable;
import gameframework.moves_rules.IllegalMoveException;
import gameframework.moves_rules.MoveBlocker;
import gameframework.moves_rules.MoveBlockerRulesApplierDefaultImpl;

import java.lang.reflect.Method;
import java.util.Vector;

import pacman.entity.Wall;
import soldier.core.UnitSimple;

public class SoldierMoveBlockers extends MoveBlockerRulesApplierDefaultImpl {

	public void moveBlockerRule(UnitSimple g, Wall w) throws IllegalMoveException {
		// The default case is when a ghost is active and not able to cross a
		// wall
		if (g.isActive()) {
			throw new IllegalMoveException();
			// When a ghost is not active, it is able to cross a wall
		}
	}
	
	
	
	public boolean moveValidationProcessing(Vector<MoveBlocker> moveBlockers,
			Movable m) {
		for (MoveBlocker moveBlocker : moveBlockers) {
			try {
				moveBlockerRuleApply(m, moveBlocker);
			} catch (Exception e) {
				/*
				 * by default the moveBlocker implies the invalidation of the
				 * move (in particular, if no method has been found by
				 * moveBlockerRuleApply)
				 */
				return false;
			}
		}
		return true;
	}

	private void moveBlockerRuleApply(Movable e1, MoveBlocker e2)
			throws Exception {
		Method m = null;
		m = (getClass()).getMethod("moveBlockerRule", e1.getClass(),
				e2.getClass());
		m.invoke(this, e1, e2);
	}
}
