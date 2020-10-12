import java.util.*;

public class DFS_stack {
	
	private LinkedList<Integer> adj[];
	
	public DFS_stack(int v) {
		adj = new LinkedList[v+1];
		for(int i = 1; i<=v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int source, int destination) {
		adj[source].add(destination);
		adj[destination].add(source);
	}
	
	public boolean dfsStack(int source, int destination) {
		boolean vis[] = new boolean[adj.length];
		vis[source] = true;
		Stack<Integer> stack = new Stack<>();
		
		stack.push(source);
		
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			
			if(cur == destination) return true;
			
			for(int neighbor: adj[cur]) {
				if(!vis[neighbor]) {
					vis[neighbor] = true;
					stack.push(neighbor);
				}
			}
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of vertices and edges");
		
		int v = sc.nextInt();
		int e = sc.nextInt();
		
		DFS_stack graph = new DFS_stack(v);
		System.out.println("Enter "+ e +" edges");
		for(int i = 0; i<e; i++) {
			int source = sc.nextInt();
			int destination = sc.nextInt();
			
			graph.addEdge(source, destination);
		}
		
		System.out.println("Enter source and destination");
		
		int source = sc.nextInt();
		int destination = sc.nextInt();
		
		System.out.println("is possible -> " + graph.dfsStack(source, destination));
		
		sc.close();
	}
}