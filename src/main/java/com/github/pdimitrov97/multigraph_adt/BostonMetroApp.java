package com.github.pdimitrov97.multigraph_adt;

import java.util.List;
import java.util.Scanner;

public class BostonMetroApp
{
	private static final String file = "bostonmetro.txt";

	public static void main(String args[])
	{
		MetroMapParser mapParser = new MetroMapParser(file);
		MultigraphADT<Station, Line> graph = mapParser.getMap();

		/*
		 * TESTS
		 * graph.print_stations();
		 * graph.print_lines();
		 * graph.getNeighbours(1);
		 * System.out.println();
		 * graph.getNeighbours(2);
		 * System.out.println();
		 * graph.getNeighbours(3);
		 * graph.findpath(graph.getNode(1), graph.getNode(6));
		 * graph.findpath(graph.getNode(64), graph.getNode(6));
		 * graph.findpath(graph.getNode(15), graph.getNode(24));
		 */

		System.out.println("Welcome to the Boston Metro search program!");
		System.out.println("To stop the program, write 'exit' at any input point!\n\n");

		Scanner input = new Scanner(System.in);
		String enteredStation = "";
		Boolean enteredStationFound = false;
		Station originStation = null;
		Station destinationStation = null;

		do
		{
			enteredStationFound = false;

			do
			{
				System.out.print("Enter origin station: ");
				enteredStation = input.next();

				if (enteredStation.equals("exit"))
				{
					input.close();
					System.exit(0);
				}

				// St.PaulStreet check
				if (enteredStation.equals("St.PaulStreet"))
				{
					do
					{
						System.out.print("St.PaulStreet on Line B or C: ");
						enteredStation = input.next();

						if (enteredStation.equals("exit"))
						{
							input.close();
							System.exit(0);
						}

						enteredStation = enteredStation.toUpperCase();

						if (enteredStation.equals("B"))
						{
							originStation = graph.getNode(38);
							enteredStationFound = true;
						}
						else if (enteredStation.equals("C"))
						{
							originStation = graph.getNode(61);
							enteredStationFound = true;
						}
						else
							System.out.println("Invalid line!");
					}
					while (!enteredStationFound);
				}
				else
				{
					originStation = graph.getNode(enteredStation);

					if (originStation != null)
						enteredStationFound = true;
					else
						System.out.println("Station not found!");
				}
			}
			while (!enteredStationFound);

			enteredStationFound = false;

			do
			{
				System.out.print("Enter destination station: ");
				enteredStation = input.next();

				if (enteredStation.equals("exit"))
				{
					input.close();
					System.exit(0);
				}

				// St.PaulStreet check
				if (enteredStation.equals("St.PaulStreet"))
				{
					do
					{
						System.out.print("St.PaulStreet on Line B or C: ");
						enteredStation = input.next();

						if (enteredStation.equals("exit"))
						{
							input.close();
							System.exit(0);
						}

						enteredStation = enteredStation.toUpperCase();

						if (enteredStation.equals("B"))
						{
							destinationStation = graph.getNode(38);
							enteredStationFound = true;
						}
						else if (enteredStation.equals("C"))
						{
							destinationStation = graph.getNode(61);
							enteredStationFound = true;
						}
						else
							System.out.println("Invalid line!");
					}
					while (!enteredStationFound);
				}
				else
				{
					destinationStation = graph.getNode(enteredStation);

					if (destinationStation != null)
						enteredStationFound = true;
					else
						System.out.println("Station not found!");
				}
			}
			while (!enteredStationFound);

			System.out.println();

			List<Line> path = graph.findpath(originStation, destinationStation);

			if (path == null)
			{
				System.out.println("There is no path between those stations.");
				System.out.println();
				continue;
			}

			String currentLine = "";

			for (Line l : path)
			{
				if (!l.getName().equals(currentLine))
				{
					System.out.println("Take " + graph.getNode(l.getLeftChildId()).getName() + " towards " + graph.getNode(l.getRightChildId()).getName() + " on the " + l.getName() + " line.");
					currentLine = l.getName();
				}

				// To print every step:
				// System.out.println(graph.getNode(l.getLeftChildId()).getName() + " towards " + graph.getNode(l.getRightChildId()).getName() + " through " + l.getName() + ".");
			}

			System.out.println("Exit on " + destinationStation.getName() + ".");
			System.out.println();
		}
		while (true);
	}
}
