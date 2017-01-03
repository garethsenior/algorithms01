import java.util.Random;

public class Percolator {
	
	private int[] id;
	private int[] sz;
	private int width;
	private int height;
	private int nodes;
	private Random rand = new Random();

	public Percolator(int W, int H) {
		/*
		create the initial list of nodes
		intially, they are their own root.
		Nodes which are their own root are 'closed'.
		Create the initial size array, to begin
		with all nodes are size 1
		*/
		nodes = W*H+2;
		id = new int[nodes];
		sz = new int[nodes];
		width = W;
		height = H;
		for (int i=0; i<nodes; i++) {
			id[i] = i;
			sz[i] = 1;
			StdOut.println(i + " vs " + nodes + " length: " + id.length);
		}
	}

	private int root(int i) {
		/*
		traverses from a node to root, 
		whilst doing so, sets the root of each
		node to the new root
		returns the new root value
		*/
		while (i != id[i]) {
			id[i] = id[id[i]];
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

	public boolean percolationcheck() {
		/*
		checks to see if the first node and last node are 
		connected.
		*/
		boolean percolate = connected(0, sz.length - 1);
		if (percolate) {
			return true;
		}
		return false;
	}

	public int union(int p, int q) {
		/*
		get the root node of both trees.
		set the root node of the smallest tree
		to be the root of the largest.
		Adjust the stored size.
		Returns 1 for a successful new union
		Returns 0 if no new union is created.
		*/
		int i = root(p);
		int j = root(q);
		if (i == j) return 0;
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] = sz[j] + sz[i];
		}
		else {
			id[j] = i;
			sz[i] = sz[i] + sz[j];
		}
		return 1;
	}

	public int unionise_node(int x) {
		/*
		where x is the node number....
		we'll attempt to union with the node above, left, right and below...
		if that's possoble.
		return the number of new unions
		*/
		int links = 0;
		int lft = x-1;
		int rght = x+1;
		int above = x-width;
		int below = x+width;
		if ((lft >= 0) && (lft%width != 0)) {
			links =+ union(x, lft);
		}
		if ((rght < id.length) && (rght%width != 1)) {
			links =+ union(x, rght);
		}
		if (above > 0) {
			links =+ union(x, above);
		}
		if (below < nodes) {
			links =+ union(x, below);
		}
		return links;
	}

	public int get_random_closed_node() {
		int[] closed_nodes = get_closed_nodes();
		return closed_nodes[rand.nextInt(closed_nodes.length)];
	}

	public int[] get_closed_nodes() {
		int[] nodes = new int[0];
		for (int i=0; i<id.length; i++) {
			if (i == id[i]) {
				nodes = new int[nodes.length+1];
				nodes[nodes.length-1] = i;
			}
		}
		return nodes;
	}
}


