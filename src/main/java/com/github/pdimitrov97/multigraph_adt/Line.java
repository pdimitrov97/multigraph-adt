package com.github.pdimitrov97.multigraph_adt;

public class Line implements IEdge
{
	private String name;
	private int leftChildId;
	private int rightChildId;

	public Line(String name, int leftChildId, int rightChildId)
	{
		this.name = name;
		this.leftChildId = leftChildId;
		this.rightChildId = rightChildId;
	}

	public String getName()
	{
		return this.name;
	}

	public int getLeftChildId()
	{
		return this.leftChildId;
	}

	public int getRightChildId()
	{
		return this.rightChildId;
	}
}
