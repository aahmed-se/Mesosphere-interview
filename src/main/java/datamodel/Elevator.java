package datamodel;

import java.util.TreeSet;

public class Elevator {

	public static enum Direction {
		UP, DOWN, IDLE
	}

	/**
	 * @return
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * @param currentFloor
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * @return
	 */
	public int getCurrentDestinationFloor() {
		return currentDestinationFloor;
	}

	/**
	 * @param currentDestinationFloor
	 */
	public void setCurrentDestinationFloor(int currentDestinationFloor) {
		this.currentDestinationFloor = currentDestinationFloor;
	}

	/**
	 * @return
	 */
	public int getElevatorId() {
		return elevatorId;
	}

	/**
	 * @return the currentDirection
	 */
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	/**
	 * @param currentDirection
	 *            the currentDirection to set
	 */
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	private TreeSet<Integer> upFloorDestinations;// floors above currentFloor
	private TreeSet<Integer> downFloorDestinations; // floors below currentFloor
	private final int elevatorId;
	private Direction currentDirection;
	private int currentFloor;
	private int currentDestinationFloor;

	/**
	 * @param elevatorId
	 */
	public Elevator(int elevatorId) {
		super();
		upFloorDestinations = new TreeSet<>();
		downFloorDestinations = new TreeSet<>();
		this.elevatorId = elevatorId;
		this.currentFloor = 1;
		this.currentDestinationFloor = this.currentFloor;
		currentDirection = Direction.IDLE;
	}

	/**
	 * @return the upFloorDestinations
	 */
	public TreeSet<Integer> getUpFloorDestinations() {
		return upFloorDestinations;
	}

	/**
	 * @param upFloorDestinations
	 *            the upFloorDestinations to set
	 */
	public void setUpFloorDestinations(TreeSet<Integer> upFloorDestinations) {
		this.upFloorDestinations = upFloorDestinations;
	}

	/**
	 * @return the downFloorDesinations
	 */
	public TreeSet<Integer> getDownFloorDestinations() {
		return downFloorDestinations;
	}

	/**
	 * @param downFloorDesinations
	 *            the downFloorDesinations to set
	 */
	public void setDownFloorDestinations(TreeSet<Integer> downFloorDesinations) {
		this.downFloorDestinations = downFloorDesinations;
	}

	public void addFloor(int f) {
		if (f < currentFloor) {
			downFloorDestinations.add(f);
		} else if (f > currentFloor) {
			upFloorDestinations.add(f);
		}
		// else f == currentFloor, so don't add the floor to either queue
	}

	public void nextStep() {

		if (this.getCurrentFloor() == this.getCurrentDestinationFloor()) {

			if (this.currentDirection != Direction.IDLE) {

				Integer nextDestination = nextFloor();

				if (nextDestination == null) {
					this.currentDestinationFloor = this.currentFloor;
					this.currentDirection = Direction.IDLE;
				} else {
					this.currentDestinationFloor = nextDestination;
				}
			}
		} else {

			if (this.currentDirection == Direction.UP) {
				this.currentFloor++;
			} else {
				this.currentFloor--;
			}
		}
	}

	public Integer nextFloor() {

		if (downFloorDestinations.isEmpty() && upFloorDestinations.isEmpty()
				&& currentFloor == currentDestinationFloor) {
			this.currentDirection = Direction.IDLE;
			return null;
		}

		if (downFloorDestinations.isEmpty()) {
			currentDirection = Direction.UP;
		} else if (upFloorDestinations.isEmpty()) {
			currentDirection = Direction.DOWN;
		}

		if (currentDirection == Direction.DOWN) {
			return downFloorDestinations.pollLast(); // highest floor in down,
														// or
														// null if empty
		} else {
			return upFloorDestinations.pollFirst(); // lowest floor in up, or
													// null if empty
		}
	}

	public void reset() {
		this.currentFloor = 1;
		this.currentDestinationFloor = this.currentFloor;
		currentDirection = Direction.IDLE;
		upFloorDestinations = new TreeSet<>();
		downFloorDestinations = new TreeSet<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Elevator [elevatorId=" + elevatorId + ", currentDirection="
				+ currentDirection + ", currentFloor=" + currentFloor
				+ ", currentDestinationFloor=" + currentDestinationFloor + "]";
	}

}
