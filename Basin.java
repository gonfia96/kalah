package gj.kalah.player.gonfiantini;

public class Basin {
	private int stoneCounter;

	/**
	 * this is a constructor of class Basin
	 * 
	 * @param initial
	 *            is number of stones at the start of the match
	 */
	Basin(int initial) {
		stoneCounter = initial;
	}

	/**
	 * this method return a number of stone
	 * 
	 * @return
	 */

	public int getCounter() {
		return stoneCounter;
	}

	/**
	 * this method return true if basin is empty
	 * 
	 * @return
	 */

	public boolean isEmpty() {
		return (stoneCounter == 0);
	}

	/**
	 * this method adds a stone
	 */

	public void addStone() {
		stoneCounter++;
	}

	/**
	 * this method add n stone
	 * 
	 * @param n
	 *            is a number of stones to add
	 * 
	 */
	public void addStone(int n) {
		stoneCounter += n;
	}

	/**
	 * this method removes all stones
	 * 
	 * @return
	 */
	public int getAllStones() {
		if (isEmpty())
			return 0;
		int tmp = stoneCounter;
		stoneCounter = 0;
		return tmp;
	}

}
