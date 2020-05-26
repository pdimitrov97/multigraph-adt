package com.github.pdimitrov97.multigraph_adt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This class reads a text description of a metro subway system and generates a
 * graph representation of the metro.
 *
 *
 * The grammar for the file is described below in BNF. A typical line in the
 * file looks like this:
 *
 * 20 NorthStation   Green 19 22  Orange 15 22
 *
 * where:
 * 20 is the StationID
 * NorthStation is the StationName
 * Green 19 22 - Green is the LineName, 19 is the StationID of the outbound station, 22 is the StationID of the inbound station
 * Orange 15 22 - Orange is the LineName, 15 is the StationID of the outbound station, 22 is the StationID of the inbound station
 *
 * Therefore, NorthStation has two outgoing lines.
 *
 * note : 0 denotes the end of a line : i.e. in this case, OakGrove would be at
 * the end of the line, as there is no other outbound station.
 *
 * metro-map ::= station-spec*
 * station-spec ::= station-id station-name station-line+
 * station-id ::= (positive integer)
 * station-name ::= string
 * station-line ::= line-name station-id station-id
 *
 */

public class MetroMapParser
{
	private MultigraphADT<Station, Line> map;

	public MetroMapParser(String filename)
	{
		map = new Multigraph<Station, Line>();

		try
		{
			generateGraphFromFile(filename);
		}
		catch (Exception e)
		{
			System.out.println("Problem reading file: " + filename);
			e.printStackTrace();
		}

	}

	/**
	 * @effects: parses the file, and generates a graph from it, unless there is a
	 *           problem reading the file, or there is a problem with the format of
	 *           the file.
	 *
	 * @throws java.io.IOException if there is a problem reading the file
	 * @throws BadFileException    if there is a problem with the format of the file
	 */
	public void generateGraphFromFile(String filename) throws IOException, BadFileException
	{
		BufferedReader fileInput = new BufferedReader(new FileReader(filename));
		String line = fileInput.readLine();
		StringTokenizer st;
		String stationID;
		String stationName;
		String lineName;
		String outboundID, inboundID;

		while (line != null)
		{
			st = new StringTokenizer(line);

			// We want to handle empty lines effectively, we just ignore them!
			if (!st.hasMoreTokens())
			{
				line = fileInput.readLine();
				continue;
			}

			// from the grammar, we know that the Station ID is the first token on the line
			stationID = st.nextToken();

			if (!st.hasMoreTokens())
				throw new BadFileException("no station name");

			// from the grammar, we know that the Station Name is the second token on the
			// line.
			stationName = st.nextToken();
			map.addNode(new Station(Integer.parseInt(stationID), stationName));

			if (!st.hasMoreTokens())
				throw new BadFileException("station is on no lines");

			while (st.hasMoreTokens())
			{
				lineName = st.nextToken();

				if (!st.hasMoreTokens())
					throw new BadFileException("poorly formatted line info");

				outboundID = st.nextToken();
				map.addEdge(new Line(lineName, Integer.parseInt(stationID), Integer.parseInt(outboundID)));

				if (!st.hasMoreTokens())
					throw new BadFileException("poorly formatted adjacent stations");

				inboundID = st.nextToken();
				map.addEdge(new Line(lineName, Integer.parseInt(stationID), Integer.parseInt(inboundID)));
			}

			line = fileInput.readLine();
		}
	}

	/**
	 * @returns the Graph generated by the file
	 */
	public MultigraphADT<Station, Line> getMap()
	{
		return this.map;
	}
}