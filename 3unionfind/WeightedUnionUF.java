public class WeightedUnionUF {
	
	private int[] id;
	private int[] sz;

	public WeightedUnionUF(int N) {
		/*
		create the initial list of nodes
		intially, they are their own root
		create the initial isze array, to begin
		with all nodes are size 1
		*/
		id = new int[N];
		sz = new int[N];
		for (int i=0; i<N; i++) {
			id[i] = i;
			sz[i] = 1;
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
		get the root node of both trees.
		set the root node of the smallest tree
		to be the root of the largest.
		Adjust the stored size
		*/
		int i = root(p);
		int j = root(q);
		if (i == j) return;
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] = sz[j] + sz[i];
		}
		else {
			id[j] = i;
			sz[i] = sz[i] + sz[j];
		}
	}

	public int[] get_roots() {
		return id;
	}

	public int[] get_sizes() {
		return sz;
	}

}


