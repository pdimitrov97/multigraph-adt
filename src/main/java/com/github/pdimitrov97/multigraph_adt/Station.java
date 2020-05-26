package com.github.pdimitrov97.multigraph_adt;

public class Station implements INode
{
	private int id;
	private String name;

	public Station(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return this.id;
	}

	public String getName()
	{
		return this.name;
	}
}
