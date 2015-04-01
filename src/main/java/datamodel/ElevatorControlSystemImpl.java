package datamodel;

import java.util.ArrayList;
import java.util.List;

import datamodel.Elevator.Direction;

public class ElevatorControlSystemImpl implements ElevatorControlSystem {

	private List<Elevator> elevators;
	private int numOfElevators;

	@Override
	public String getStatus() {
		StringBuilder sb = new StringBuilder();
		for (Elevator elv : elevators)
			sb.append(elv.toString() + "\n");

		return sb.toString();
	}

	@Override
	public void update(int elevatorId, int floorNum, List<Integer> goalFloorNums) {
		Elevator updatedElevator = elevators.get(elevatorId);
		updatedElevator.reset();
		updatedElevator.setCurrentFloor(floorNum);
		for (Integer goalFloor : goalFloorNums) {
			updatedElevator.addFloor(goalFloor);
		}
		updatedElevator.setCurrentDestinationFloor(updatedElevator.nextFloor());
	}

	@Override
	public void pickup(int pickupFloorNum, int direction) {
		// Find shortest route among all elevators
		Elevator candidateElevator = null;
		Elevator idleCandiateElevator = null;
		int idleCandidateCost = Integer.MAX_VALUE;

		// Idle Elevator Search

		for (Elevator elv : elevators) {
			if (elv.getCurrentDirection() == Direction.IDLE) {
				if (elv.getCurrentFloor() == pickupFloorNum) {
					return; // Do nothing Idle Elevator found at required floor
				}
				else{
					int tempCost = Math.abs(elv.getCurrentFloor() - pickupFloorNum);
					if (tempCost < idleCandidateCost){
						idleCandidateCost = tempCost;
						idleCandiateElevator = elv;
					}
				}
			}
		}

		// Directional Search
		int candidateCost = Integer.MAX_VALUE;
		if (direction == -1) {
			for (Elevator elv : elevators) {
				if (elv.getCurrentDirection() == Direction.DOWN
						&& elv.getCurrentFloor() > pickupFloorNum) {
					int tempCost = elv.getCurrentFloor() - pickupFloorNum;
					if (tempCost < candidateCost) {
						candidateElevator = elv;
						candidateCost = tempCost;
					}
				}
			}
			
			if (candidateElevator == null){
				for (Elevator elv : elevators) {
					if (elv.getCurrentDirection() == Direction.UP
							&& elv.getCurrentFloor() < pickupFloorNum) {
						int tempCost = pickupFloorNum - elv.getCurrentFloor();
						if (tempCost < candidateCost) {
							candidateElevator = elv;
							candidateCost = tempCost;
						}
					}
				}	
			}
			
			
		} else {
			for (Elevator elv : elevators) {
				if (elv.getCurrentDirection() == Direction.UP
						&& elv.getCurrentFloor() < pickupFloorNum) {
					int tempCost = pickupFloorNum - elv.getCurrentFloor();
					if (tempCost < candidateCost) {
						candidateElevator = elv;
						candidateCost = tempCost;
					}
				}
			}
			
			if (candidateElevator == null){
				for (Elevator elv : elevators) {
					if (elv.getCurrentDirection() == Direction.DOWN
							&& elv.getCurrentFloor() > pickupFloorNum) {
						int tempCost = elv.getCurrentFloor() - pickupFloorNum;
						if (tempCost < candidateCost) {
							candidateElevator = elv;
							candidateCost = tempCost;
						}
					}
				}
			}
		}
		
		if(idleCandidateCost <= candidateCost && idleCandidateCost < Integer.MAX_VALUE){
			System.out.println(idleCandiateElevator.getCurrentDestinationFloor());
			idleCandiateElevator.addFloor(pickupFloorNum);
			idleCandiateElevator.setCurrentDestinationFloor(idleCandiateElevator.nextFloor());
		}
		else{
			if(candidateCost < Integer.MAX_VALUE){
			  System.out.println(candidateElevator.getCurrentDestinationFloor());
			  candidateElevator.addFloor(pickupFloorNum);
			  candidateElevator.setCurrentDestinationFloor(candidateElevator.nextFloor());
			}
		}
	}

	@Override
	public void step() {
		for (Elevator elv : elevators) {
			elv.nextStep();
		}
	}

	public List<Elevator> getElevators() {
		return elevators;
	}

	public void setElevators(List<Elevator> elevators) {
		this.elevators = elevators;
	}

	public int getNumOfElevators() {
		return numOfElevators;
	}

	public void setNumOfElevators(int numOfElevators) {
		this.numOfElevators = numOfElevators;
	}

	/**
	 * @param numOfElevators
	 */
	public ElevatorControlSystemImpl(int numOfElevators) {
		super();
		this.numOfElevators = numOfElevators;
		elevators = new ArrayList<Elevator>(numOfElevators);
		for (int i = 0; i < numOfElevators; ++i) {
			elevators.add(new Elevator(i));
		}
	}

}
