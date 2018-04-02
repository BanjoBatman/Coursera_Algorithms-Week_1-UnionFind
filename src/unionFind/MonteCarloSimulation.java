package unionFind;

import java.util.Calendar;
import java.util.Random;

public class MonteCarloSimulation {

	int[] id; //these are the relationships of the elements in the map
	int[] map; //this map holds a 1 for cells that are "open" and a 0 for cells that are "closed"
	int n; //the number passed into the constructor, it's the number of rows and columns that was used to create the map
	
	// if virtualBeginning and virtuaEnd are connected, then there is a path between the bottom and the top of the graph.
	int virtualBeginning;//this is 0
	int virtualEnd;// the is the last element of the id array. 
	
	
	
	
	/*
	 * In a MonteCarlo Simulation, there is a matrix of n x n rows
	 * randomly, the cells in the matrix are randomly "empty"
	 * empty cells next to each other can be connected. 
	 * the question then, is "is there a path beween the top and the bottom of the graph?
	 */
	public MonteCarloSimulation(int n) {
		this.n = n;
		id = new int[n*n+2];	// add a virtual beginning, and a virtual ending
							// the first and last rows of the graph will be rooted to those end points.
		map = new int[n*n];// this is all of the available cells in the graph
		virtualBeginning = 0;
		virtualEnd=id.length-1;
		//fill in the array 
		for(int i = 0; i <n*n+2; i++) {
			id[i] = i;
		}
		
		// set the root of the first n elements to the virtual root we've added, which is (0)
		// and set the root of the last n elements to the end root which is (n*n+1)
		for(int i = 0; i<n; i++) {
			union(i+1,0);
			union(virtualEnd-1-n+i, virtualEnd);
		}
		
		
	}
	
	//get the root of this element
	public int root(int i) {
		while(id[i] != i) {
			i=id[i];
		}
		return i;
	}
	
	public void union(int p, int q) {
		//set the root of p to q.
		id[root(p)]=root(q);
	}
	
	public boolean connected(int p, int q) {
		return root(p)==root(q);
	}
	
	// generate a random set of numbers that will be used to determine whether a site is open, or closed
	public void generateRandomGraph() {
       
		Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
		for(int i=0;i<id.length-2;i++) {
			map[i] = rnd.nextInt(2);
//			if(map[i]==0) {
//				map[i]=rnd.nextInt(2);
//			}
		}
		
	}
	
	public void mapGraph() {
		//now run through the graph to make the connections that are necessary.
		for(int i=0; i<n*n;i++) {
			//first, check to see if this cell is open. If it's not, then do nothing
			if(map[i]==1) { 
				// the cell is open, now check the adjacent cells to see if they're open
				if((i-1)%n!=0 && i>0) {
					//there is an element to the left of this one
					if(map[i-1]==1) {
						//this cell is open. join them
						this.mapUnion(i, i-1);
					}
				}
				if((i+1)%n !=0) {
					//there is an element to the right of this one
					if(map[i+1]==1) {
					   //this cell is open. Join them.
						this.mapUnion(i, i+1);
					}
				}
				if(i-n>0) {
					//there is an element above this one
					if(map[i-n]==1) {
						//this cell is open, join them
						this.mapUnion(i, i-n);
					}
				}
				if(i+n<n*n) {
					//there is an element below this one
					if(map[i+n]==1){
						//this cell is open, join them
						this.mapUnion(i, i+n);
					}
				}
			}
			
		}
		
	}
	
	private void mapUnion(int p, int q) {
		// since there are 'virtual elements in the ID array, but not the map array
		// we need to translate the map elements into id elements, and make the connections.
		// System.out.println("Connecting " + p+1 + " to " + q+1);
		this.union(p+1, q+1);
	}
	
	public boolean canPerculate() {
		return this.connected(this.virtualBeginning, this.virtualEnd);
	}
	
	
	public void printGraph() {
		for(int i=0; i<n*n; i++) {
			if(i%n==0) {
				System.out.println();
			}
			String charToPrint = map[i]==1?"1":"0";
			System.out.print(charToPrint);
		}
	}
	
	

}
