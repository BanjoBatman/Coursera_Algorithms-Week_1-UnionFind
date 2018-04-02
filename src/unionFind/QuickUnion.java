package unionFind;
/*
 * this is a quick union algorithm
 * it's called a quick union algorithm because it's very quick to make a union, 
 * except for now we have to iterate between items in order to find the root of each item.
 * Initializes in N time.
 * Unions in N* time, where * depends on how far the root is away from the element.
 * finds in N* time, where * depends on how far the root is away from the element.
 * So both union and find are N time at max.
 */
public class QuickUnion {
	
	int[] id;
	public QuickUnion(int n) {
		id = new int[n];
		for(int i=0;i<n;i++) {
			id[i] = i;
		}
		
	}
	
	// follow each item at id[i] until id[i] = i
	private int root(int i) {
		while(id[i]!=i) {
			i = id[i];
		}
		return i;
	}
	
	//create a connection between p and q by setting the root of p to the root of q
	public void union(int p, int q) {
		id[root(p)]=root(q);
	}
	
	public boolean connected(int p, int q) {
		return root(p)==root(q);
	}
	

}
