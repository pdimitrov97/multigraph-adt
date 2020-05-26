package com.github.pdimitrov97.multigraph_adt;

import java.util.*;

public class Multigraph<N extends INode, E extends IEdge> implements MultigraphADT<N, E>
{
	private List<N> nodes;
	private List<List<E>> edges;

	public Multigraph()
	{
		nodes = new ArrayList<N>();
		edges = new ArrayList<List<E>>();
	}

	public void addNode(N node)
	{
		this.nodes.add(node);
		this.edges.add(new ArrayList<E>());
	}

	public N getNode(int nodeId)
	{
		if (nodeId <= 0 || nodes.size() < nodeId)
			throw new IndexOutOfBoundsException();

		return nodes.get(nodeId - 1);
	}

	public N getNode(String nodeName)
	{
		if (nodeName.isEmpty())
			throw new NullPointerException();

		for (N node : nodes)
		{
			if (node.getName().equals(nodeName))
				return node;
		}

		return null;
	}

	public void addEdge(E edge)
	{
		if (edge.getLeftChildId() == 0 || edge.getRightChildId() == 0)
			return;

		if (edge.getLeftChildId() < 0 || nodes.size() < edge.getLeftChildId())
			throw new IndexOutOfBoundsException();

		this.edges.get(edge.getLeftChildId() - 1).add(edge);
	}

	public List<E> findpath(N origin, N destination)
	{
		if (origin.getId() <= 0 || nodes.size() < origin.getId())
			throw new IndexOutOfBoundsException();

		if (destination.getId() <= 0 || nodes.size() < destination.getId())
			throw new IndexOutOfBoundsException();

		// stores the id of nodes
		Queue<Pair<Integer, List<E>>> waitingQueue = new LinkedList<Pair<Integer, List<E>>>();
		List<N> visitedNodes = new ArrayList<N>();

		// add neighbours of origin node to queue
		for (E i : edges.get(origin.getId() - 1))
			waitingQueue.add(new Pair<Integer, List<E>>(i.getRightChildId(), Arrays.asList(i)));

		visitedNodes.add(origin);

		while (!waitingQueue.isEmpty())
		{
			Pair<Integer, List<E>> current = ((LinkedList<Pair<Integer, List<E>>>) waitingQueue).getFirst();
			((LinkedList<Pair<Integer, List<E>>>) waitingQueue).removeFirst();

			// check if we have visited this node
			if (visitedNodes.contains(getNode(current.left)))
				continue;

			// is that our destionation ?
			if (current.left == destination.getId())
				return current.right;
			else // it is not
			{
				// add the neighbours of the current station
				for (E i : edges.get(current.left - 1))
				{
					List<E> tmpList = new ArrayList<>(current.right);
					tmpList.add(i);
					waitingQueue.add(new Pair<Integer, List<E>>(i.getRightChildId(), tmpList));
				}
			}

			visitedNodes.add(getNode(current.left));
		}

		// no path found
		return null;
	}

	// FOR TESTS
	public void getNeighbours(int nodeId)
	{
		List<E> neighbours = edges.get(nodeId - 1);

		for (E n : neighbours)
			System.out.println(getNode(n.getLeftChildId()).getName() + " with " + getNode(n.getRightChildId()).getName());
	}

	// FOR TESTS
	public void printStations()
	{
		for (N node : nodes)
			System.out.println(node.getId() + " " + node.getName());
	}

	// FOR TESTS
	public void printLines()
	{
		for (List<E> lines : edges)
		{
			for (E neighbour : lines)
				System.out.print(getNode(neighbour.getLeftChildId()).getName() + " to " + getNode(neighbour.getRightChildId()).getName() + " | ");

			System.out.println();
		}
	}

	class Pair<L, R>
	{
		private final L left;
		private final R right;

		public Pair(L left, R right)
		{
			this.left = left;
			this.right = right;
		}

		public L getLeft()
		{
			return left;
		}

		public R getRight()
		{
			return right;
		}
	}
}
