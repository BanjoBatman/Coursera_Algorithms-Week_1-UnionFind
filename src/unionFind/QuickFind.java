package unionFind;


/**
 * @author Tomas
 *  this is an n^2 algorithm - becuase it has to touch all of the elements in the array twice. 
 *  once to create it, and once every time it creates a union. 
 *  this is called quadradic, and quadradic algorithms don't scale.
 *  it's called "quick find" because it's a union search algorith, with a constant (1) time see if any 2 elements are connected 
 *  Initializes in N time
 *  Unions in N time.
 *  finds in 1 time.
 */
public class QuickFind {
	
	private int[] id;
	
	
	public QuickFind(int n) {
		// initialize the id array with the number of elements passed in by n
		id = new int[n];
		// set and id for int with each element in the array equal to itself.
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
		// so now the id array is like [0,1,2,3,4,........,n-1]
		
	}
	
	public boolean connected(int p, int q) {
		// return true if p and q are connected.
		// p and q are connected if id[p] == id[q]
		return id[p] == id[q];
	}
	
	public void union (int p, int q) {
		// define a connection between p and q.
		// in a connection, we take all of the values id[q] and reassign them id[p]
		// for instance, if the array were to look
		for(int i = 0; i < id.length;  i++) {
			if(id[i] == id[q]) {
				id[i] = id[p];
			}
		}
		
	}

}
