package com.test.graph;

import java.util.*;

/**
 * The Class Solution 
 * There is an undirected graph G = (V, E), with a set of
 * vertices V and a set of edges E. Vertices are numbered by {1, 2,...n} where
 * n=|V|. Given two vertices p and q determine whether they are connected i.e.
 * whether there exists a path from p to q .
 *
 * @author memerida
 */
public class Solution0 {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Solution0 solution = new Solution0();
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		graph.put(1, new HashSet<Integer>(Arrays.asList(3,5)));
		graph.put(2, new HashSet<Integer>(Arrays.asList(3, 4)));
		graph.put(3, new HashSet<Integer>(Arrays.asList(1, 2, 4)));
		graph.put(4, new HashSet<Integer>(Arrays.asList(2, 3)));
		graph.put(5, new HashSet<Integer>(Arrays.asList(6)));
		graph.put(6, new HashSet<Integer>(Arrays.asList(5,3,1)));
		graph.put(7, new HashSet<Integer>(Arrays.asList(4)));
		System.out.println("Solution:" + solution.solution(graph, 7, 6));
	}


	/**
	 * Solution.
	 * A BFS based function to check whether p is reachable from q.
	 *
	 * @param graph the graph
	 * @param p the p
	 * @param q the q
	 * @return true, if successful
	 */
	public boolean solution(Map<Integer, Set<Integer>> graph, int p, int q) {
		if (p == q) {
			return true;
		}
		if (isValidEntry(graph, p, q)) {
			Boolean[] visitedVertex = new Boolean[graph.size()];
			Queue<Integer> queue = new LinkedList<Integer>();
		
			for (int i = 0; i < graph.size(); i++) {
				visitedVertex[i] = false;
			}
			
			visitedVertex[p-1] = true;
			queue.add(p);
			
			while (!queue.isEmpty()) {				
				p = queue.poll();
				
				Iterator<Integer> graphIterator = graph.get(p).iterator();
				while (graphIterator.hasNext()) {
					int adjNode = graphIterator.next();
					if (adjNode == q) {
						return true;
					}					
					if (!visitedVertex[adjNode-1]) {
						visitedVertex[adjNode-1] = true;
						queue.add(adjNode);
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if is valid entry.
	 *
	 * @param graph the graph
	 * @param p the p
	 * @param q the q
	 * @return true, if is valid entry
	 */
	private boolean isValidEntry(Map<Integer, Set<Integer>> graph, int p, int q) {
		if (graph == null || graph.isEmpty()) {
			System.out.println("Please insert some values into the graph structure");
			return false;
		} else {
			if (p > graph.size()) {
				System.out.println("Please verify than p value is <= than the graph size");
				return false;
			}
			if(q > graph.size()){
				System.out.println("Please verify than q value is <= than the graph size");
				return false;
			}
		}
		return true;
	}	
}
