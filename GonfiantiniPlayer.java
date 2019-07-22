package gj.kalah.player.gonfiantini;

import gj.kalah.player.Player;

public class GonfiantiniPlayer implements Player {
	private Strategy strategy;

	@Override
	public int move() {
		int move = strategy.move();
		strategy.doMove(move, true);
		return move;
	}

	@Override
	public void start(boolean isFirst) {
		strategy = new Strategy(isFirst);
	}

	@Override
	public void tellMove(int opponentMove) {

		strategy.doMove(opponentMove, false);

	}

}
