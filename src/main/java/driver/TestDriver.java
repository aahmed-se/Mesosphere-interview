package driver;

import java.util.Arrays;

import datamodel.ElevatorControlSystem;
import datamodel.ElevatorControlSystemImpl;

public class TestDriver {

	public static void simpleMultiElevatorTest(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(16);
		ecs.pickup(3, 1);
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(2, -1);
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		
		System.out.println("------ End simpleMultiElevatorTest" + "\n");
		
	}
	
	public static void simpleSingleElevatorTest(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(1);
		ecs.pickup(9, 1);
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		ecs.step();
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		
		System.out.println("------ End simpleSingleElevatorTest" + "\n");

	}
	
	public static void simpleUpdateCommandTest(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(2);
		ecs.pickup(3, 1);
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.update(0, 2, Arrays.asList(3,4));
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		
		System.out.println("------ End simpleUpdateCommandTest" + "\n");

	}
	
	public static void simpleDuplicateCommandTest(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(1);
		ecs.pickup(3, 1);
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(3, 1);
		System.out.println(ecs.getStatus());
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		System.out.println(ecs.getStatus());
		
		System.out.println("------ End simpleDuplicateCommandTest" + "\n");

	}
	
	public static void compoundTest1(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(2);
		ecs.pickup(3, 1);
		ecs.pickup(2, 1);
		ecs.pickup(4, 1);
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(5, 1);
		ecs.pickup(6, 1);
		ecs.pickup(3, 1);
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(2, -1);
		ecs.pickup(1, -1);
		System.out.println(ecs.getStatus());
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());

		
		System.out.println("------ End compoundTest" + "\n");

	}
	
	public static void compoundTest2(){
		ElevatorControlSystem ecs = new ElevatorControlSystemImpl(1);
		ecs.pickup(3, 1);
		ecs.pickup(2, -1);
		ecs.pickup(5, 1);
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(5, 1);
		ecs.pickup(6, 1);
		ecs.pickup(3, -1);
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.pickup(2, -1);
		ecs.pickup(1, -1);
		System.out.println(ecs.getStatus());
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());
		ecs.step();
		ecs.step();
		System.out.println(ecs.getStatus());

		
		System.out.println("------ End compoundTest" + "\n");

	}
	
	
	public static void main(String[] args) {
		
		simpleSingleElevatorTest();
		simpleMultiElevatorTest();
		simpleUpdateCommandTest();
		simpleDuplicateCommandTest();
		compoundTest1();
//		compoundTest2();
		
	}
}
