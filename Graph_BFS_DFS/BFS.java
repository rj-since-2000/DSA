import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {		// breadth first search for finding minimum distance between source and destination
	
	private LinkedList<Integer> adj[];
	
	public BFS(int v) {
		adj = new LinkedList[v+1]; 		// it can be [v] if vertices counting start from 0
		for(int i = 1; i<=v; i++) { 	// it can be i < v if vertices counting start from 0
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int source, int destination) {
		adj[source].add(destination);
		adj[destination].add(source);
	}
	
	public int bfs(int source, int destination) {
		
		boolean vis[] = new boolean[adj.length];
		int parent[] = new int[adj.length];
		Queue<Integer> q = new LinkedList<>();
		
		q.add(source);
		parent[source] = -1;
		vis[source] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == destination) break;
			
			for(int neighbor: adj[cur]) {
				if(!vis[neighbor]) {
					vis[neighbor] = true;
					q.add(neighbor);
					parent[neighbor] = cur;
				}
			}
		}
		
		int cur = destination;
		int distance = 0;
		
		while(parent[cur] != -1) {
			if (parent[cur] == 0) {
				System.out.println("no possible route");
				return -1;
			}
			System.out.print(cur+" -> ");
			cur = parent[cur];
			distance++;
		}
		System.out.println(cur);
		return distance;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of vertices and edges");
		
		int v = sc.nextInt();
		int e = sc.nextInt();
		
		BFS graph = new BFS(v);
		System.out.println("Enter "+ e +" edges");
		for(int i = 0; i<e; i++) {
			int source = sc.nextInt();
			int destination = sc.nextInt();
			
			graph.addEdge(source, destination);
		}
		
		System.out.println("Enter source and destination");
		
		int source = sc.nextInt();
		int destination = sc.nextInt();
		
		int distance = graph.bfs(source, destination);
		System.out.println("min distance is " + distance);
		
		sc.close();
	}
}