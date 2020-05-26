package com.github.pdimitrov97.multigraph_adt;

import java.util.*;

public interface MultigraphADT<N extends INode, E extends IEdge>
{
	public void addNode(N node);

	public N getNode(int nodeId);

	public N getNode(String nodeName);

	public void addEdge(E edge);

	public List<E> findpath(N origin, N destination);

}
