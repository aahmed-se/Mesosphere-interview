package driver;

import java.util.Scanner;

import datamodel.ElevatorControlSystem;
import datamodel.ElevatorControlSystemImpl;

public class MainDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		if (args.length < 1) {
			System.out.println("Please specify the number of elevators :");
		}

		try {

			int n = Integer.parseInt(args[0]);
			if (n == 0 || n > ElevatorControlSystem.MAX_NUM_OF_ELEVATORS) {
				throw new Exception("Wrong number of elevators");
			}

			ElevatorControlSystem ecs = new ElevatorControlSystemImpl(n);

			boolean quit = false;

			while (!quit) {
				System.out.print("Input:> ");
				String x = in.nextLine();
				if (x.equals("quit")) {
					quit = true;
				} else if (x.equals("status")) {
					System.out.println(ecs.getStatus());
				} else if (x.contains("pickup")) {
					String[] p = x.split(" ");
					if (p.length < 3) {
						System.out
						.println("pickup command needs 2 integer arguments (current floor number and direction)");
						continue;
					}
					ecs.pickup(Integer.parseInt(p[1]), Integer.parseInt(p[2]));
				} else if (x.contains("goto")) {
					String[] p = x.split(" ");
					if (p.length < 3) {
						System.out
						.println("goto command needs 2 integer arguments (elevator ID and goal floor number)");
						continue;
					}
				} else if (x.equals("step")) {
					ecs.step();
				} else {
					System.out.println("No command found. It must be one of [status, pickup, goto, step or quit]");
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Integer expected but String found. Please restart");
		} catch (Exception e) {
			System.err.println("Something has gone wrong. Please restart.");
		}finally{
			in.close();
		}

	}

}
