package datamodel;

import java.util.List;

public interface ElevatorControlSystem {
	
	public final static int MAX_NUM_OF_ELEVATORS = 16;

	
	/**
	 * @return
	 */
	public String getStatus();
	
	/**
	 * @param elevatorId
	 * @param floorNum
	 * @param destFloorNum
	 */
	public void update(int elevatorId, int floorNum, List<Integer> goalFloorNums);
	
	/**
	 * @param pickupFloorNum
	 * @param direction
	 */
	public void pickup(int pickupFloorNum, int direction);
	
	/**
	 * 
	 */
	public void step();
}
