package unionFind;

// a weighted quick-union is similar to a quick-union
// except that we keep an additional array telling us how many child elements any point has.
// then, when we make a union, we always link the root of the smaller tree to the root of the larger tree
// the depth of any node is log(base2) of (n)
public class WeightedQuickUnion {
	
	
	int[] id;
	int[] rootCount;
	
	public WeightedQuickUnion(int n) {
		//initialize the ID array
		id = new int[n];
		//initialize the rootcount array
		rootCount =  new int[n];
		for(int i = 0 ; i < n; i++) {
			id[i] = i;
			rootCount[i] = 0; // everything is independent
		}
		this.printArrays();
	}
	
	private int getRoot(int p) {
		//find the root of p
		while(id[p]!=p) {
			//if we wanted to , we can use "path compression here"
			// which we can do by making each node point to its 'grandparent' while we search.
			id[p]=id[id[p]]; // this makes the path compression work.
			p = id[p];
		}
		return p;
	}
	
	private int getRootCount(int p) {
		//return the number of children that have this root
		return rootCount[p];
	}
	
	public void union(int p, int q) {
		int pDepth = getRootCount(p);
		int qDepth = getRootCount(q);
		if(qDepth<pDepth) {
			id[getRoot(p)]=getRoot(q);
			rootCount[getRoot(q)]++;
		}
		else {
			id[getRoot(q)]=getRoot(p);
			rootCount[getRoot(p)]++;
		}
		
		
		this.printArrays();
	}
	
	public boolean connected(int p, int q) {
		return getRoot(p)==getRoot(q);
	}
	
    private void printArrays() {
    	for(int i=0; i<id.length; i++){
    		System.out.print( i+ "     ");
    		
    	}
    	System.out.println();
    	for(int i=0; i<id.length; i++){
    		System.out.print(id[i] + "     ");
    		
    	}
    	
    	System.out.println();
    	for(int i=0; i<id.length; i++){
    		System.out.print(rootCount[i] + "     ");
    		
    	}
    	System.out.println();System.out.println();
    }
	

}
