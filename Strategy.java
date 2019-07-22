package gj.kalah.player.gonfiantini;

import java.util.Arrays;

public class Strategy {
	private Table table;
	private boolean isFirst;

	/**
	 * this is a constructor of class Strategy
	 * 
	 * @param isFirst
	 *            is the initial value
	 */
	public Strategy(boolean isFirst) {
		table = new Table();
		this.isFirst = isFirst;
	}

	/**
	 * this method returns an array of possible basins indices with which I can
	 * steal the stones
	 * 
	 * @return
	 */
	public int[] canTake() {
		int[] possibleBasin = new int[6];
		Arrays.fill(possibleBasin, -1);
		int counter = 0;
		int[] emptyBasin = table.EmptySomeConca();
		for (int i = emptyBasin.length - 1; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				int stone = table.getConca(j);
				int opponentStone = table.getOpponentConca(5 - emptyBasin[i]);
				if (stone != 0 && (stone + j) == emptyBasin[i] && opponentStone != 0) {
					possibleBasin[counter] = j;
					counter++;
				}
			}

		}
		int[] count = new int[counter];
		int d = 0;
		for (int z = 0; z < possibleBasin.length && counter > 0; z++) {
			if (possibleBasin[z] >= 0) {
				count[d] = possibleBasin[z];
				d++;
			}
		}
		return count;
	}

	/**
	 * 
	 * this method returns true if I can have another turn from a conca index
	 * 
	 * @param index
	 *            is index of basin
	 * @return
	 */
	public boolean anotherTurn(int index) {
		int distance = 6;
		int stone = table.getConca(index);
		if ((index + stone) == distance) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * this method gives me the size of the array of possible indexes from which
	 * I can have the extra turn
	 * 
	 * @return
	 */
	public int digitsTurn() {

		int distance = 6;
		int count = 0;
		for (int i = 0; i < 6; i++) {
			int stone = table.getConca(i);
			if (stone == distance) {
				count++;
			}
			distance--;
		}
		return count;
	}

	/**
	 * 
	 * this method gives me an array of indices from which I can have another
	 * turn
	 * 
	 * @return
	 */
	public int[] anotherTurnIndex() {
		int[] indexOfAnotherTurn = new int[digitsTurn()];
		int j = 0;
		for (int i = 0; i < 6; i++) {
			if (anotherTurn(i) == true) {

				indexOfAnotherTurn[j] = i;
				j++;
			}
		}
		return indexOfAnotherTurn;
	}

	/**
	 * 
	 * this method gives me back the basin with more stones
	 * 
	 * @return
	 */
	public int maxConca() {
		int maxIndex = 0;
		int max = table.getConca(0);

		for (int i = 1; i < 6; i++) {
			int stone = table.getConca(i);
			if (stone >= max) {
				max = stone;
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	/**
	 * 
	 * this method returns the index of the basin from which to distribute my
	 * stones
	 */
	public int move() {
		int[] turn = anotherTurnIndex();
		int[] basinToTake = canTake();
		int l = turn.length;
		if (isFirst) {
			isFirst = false;
			return 2;
		}
		if (turn.length > 0 && turn[l - 1] == 5) {
			return 5;
		} else {
			if (basinToTake.length != 0) {
				return chooseTake();
			} else {
				if (l != 0) {
					for (int i = l - 1; i > 0; i--) {
						if (turn[i] >= 0) {
							return turn[i];
						}
					}
				}
			}
		}
		return maxConca();
	}

	/**
	 * 
	 * this method returns the maximum hollow from which I can steal
	 * 
	 * @return
	 */
	public int chooseTake() {
		int[] catchBasin = canTake();
		int stone = table.getConca(catchBasin[0]);
		int max = table.getOpponentConca(5 - (stone + catchBasin[0]));
		int maxIndex = catchBasin[0];
		for (int i = 1; i < catchBasin.length; i++) {
			stone = table.getConca(catchBasin[i]);
			int opponentStone = table.getOpponentConca(5 - (stone + catchBasin[i]));
			if (opponentStone > max && max != 0) {
				max = opponentStone;
				maxIndex = catchBasin[i];
			}

		}
		return maxIndex;
	}

	/**
	 * 
	 * this method distributes my stones and those of my opponent
	 * 
	 * @param move
	 *            is my move or opponent move
	 * @param Player
	 *            return true if my player is my turn or return false if my
	 *            opponent turn
	 */
	public void doMove(int move, boolean Player) {
		if (Player == true) {
			table.distribute(move);
		} else {
			table.opponentDistribute(move);
		}
	}

}
