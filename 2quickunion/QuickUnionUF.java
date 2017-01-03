public class QuickUnionUF {
	
	private int[] id;

	public QuickUnionUF(int N) {
		/*
		create the initial list of nodes
		intially, they are their own root
		*/
		id = new int[N];
		for (int i=0; i<N; i++) {
			id[i] = i;
		}
	}

	private int root(int i) {
		/*
		returns the root value of a node
		*/
		while (i != id[i]) {
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		/*
		compare the root values of each node
		*/
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		/*
		get both roots, set the root of the first root
		to the root of the second.
		*/
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}

	public int[] connections() {
		return id;
	}	

}


