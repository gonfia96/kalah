package gj.kalah.player.gonfiantini;

public class Table {
	private Basin[] myVet;
	private Basin[] otherVet;
	private Basin myWareHouse;
	private Basin otherWareHouse;

	/**
	 * this is a constructor of the class Table and initializes an array of
	 * basins with 4 stones each
	 */
	Table() {
		myVet = new Basin[6];

		for (int i = 0; i < myVet.length; i++) {
			myVet[i] = new Basin(4);
		}
		otherVet = new Basin[6];
		for (int i = 0; i < otherVet.length; i++) {
			otherVet[i] = new Basin(4);
		}

		myWareHouse = new Basin(0);
		otherWareHouse = new Basin(0);

	}

	/**
	 * this method return the number of stones in the x my basin
	 * 
	 * @param x
	 *            is the index of my basin
	 * @return
	 */
	public int getConca(int x) {
		return myVet[x].getCounter();
	}

	/**
	 * this method return the number of stones in the x opponent basin
	 * 
	 * @param x
	 *            is index of opponent basin
	 * @return
	 */

	public int getOpponentConca(int x) {
		return otherVet[x].getCounter();
	}

	/**
	 * this method distributes my stones
	 * 
	 * @param move
	 *            of my player
	 */
	public void distribute(int mossa) {
		Basin basin = myVet[mossa];
		Basin nextBasin;
		Basin opponentNextBasin;
		int stone = basin.getAllStones();
		while (stone > 0) {
			for (int i = mossa + 1; i <= myVet.length && stone > 0; i++) {
				if (i < myVet.length) {
					nextBasin = myVet[i];
					nextBasin.addStone();
					stone--;
					if (stone == 0 && nextBasin.getCounter() == 1) {
						int stoneTake = otherVet[5 - i].getAllStones();
						nextBasin.getAllStones();
						myWareHouse.addStone(stoneTake + 1);
					}
				} else {
					myWareHouse.addStone();
					stone--;
				}
			}

			for (int j = 0; j < otherVet.length && stone > 0; j++) {

				opponentNextBasin = otherVet[j];
				opponentNextBasin.addStone();
				stone--;

			}
			mossa = -1;
		}
	}

	/**
	 * this method distributes the opponent's stones
	 * 
	 * @param move
	 *            of opponent player
	 */
	public void opponentDistribute(int opponentMove) {
		Basin basin = otherVet[opponentMove];
		Basin nextBasin;
		Basin myNextBasin;
		int stone = basin.getAllStones();
		while (stone > 0) {
			for (int i = opponentMove + 1; i <= otherVet.length && stone > 0; i++) {

				if (i < otherVet.length) {
					nextBasin = otherVet[i];
					nextBasin.addStone();
					stone--;
					if (stone == 0 && nextBasin.getCounter() == 1) {
						int stoneTake = myVet[5 - i].getAllStones();
						nextBasin.getAllStones();
						otherWareHouse.addStone(stoneTake + 1);
					}
				} else {
					otherWareHouse.addStone();
					stone--;
				}
			}
			for (int j = 0; j < myVet.length && stone > 0; j++) {
				myNextBasin = myVet[j];
				myNextBasin.addStone();
				stone--;
			}
			opponentMove = -1;
		}
	}

	/**
	 * this method return an array with empty basin index
	 * 
	 * @return
	 */
	public int[] EmptySomeConca() {
		int[] empty = new int[CounterEmptyConca()];
		int j = 0;
		for (int i = 0; i < myVet.length; i++) {
			if (myVet[i].isEmpty()) {
				empty[j] = i;
				j++;
			}
		}
		return empty;
	}

	/**
	 * this method returns the size of the array before
	 * 
	 * @return
	 */
	public int CounterEmptyConca() {
		int count = 0;
		for (int i = 0; i < myVet.length; i++) {
			if (myVet[i].isEmpty()) {
				count++;
			}
		}
		return count;

	}
}
