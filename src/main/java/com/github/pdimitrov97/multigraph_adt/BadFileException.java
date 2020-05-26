package com.github.pdimitrov97.multigraph_adt;

public class BadFileException extends Exception
{
	public BadFileException()
	{
		super();
	}

	public BadFileException(String error)
	{
		super(error);
	}
}
