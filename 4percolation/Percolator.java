import java.util.Random;
import java.util.ArrayList;

public class Percolator {
	
	private int[] id;					// parent ids
	private int[] sz;					// size of the tree
	private boolean[] node_state; 		// true = open, false = closed
	private int width;
	private int height;
	private int nodes; 					// number of nodes
	private Random rand = new Random();
	private int debug_level = 0;		// the hgiher the number the more verbose the logs

	public Percolator(int W, int H) {
		/*
		create the initial list of nodes
		intially, they are their own root.
		Nodes which are their own root are 'closed'.
		Create the initial size array, to begin
		with all nodes are size 1
		*/
		nodes = (W*H)+2;
		id = new int[nodes];
		sz = new int[nodes];
		node_state = new boolean[nodes];

		width = W;
		height = H;
		for (int i=0; i<nodes; i++) {
			id[i] = i;
			sz[i] = 1;
			node_state[i] = false;
			log(10, i + " vs " + nodes + " length: " + id.length);
		}
		// the 'virtual' nodes are the only open ones
		node_state[0] = true;
		node_state[nodes-1] = true;
	}

	private void log(int level, String str) {
		if (level <= debug_level) {
			StdOut.println(str);
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
		log(5, "Union(" + p + "," + q+ ")");
		if (!(node_state[p] && node_state[q])) return 0;
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

	private int unionise_node(int x) {
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
		// we only do lft and right nodes if they're on the same row.
		if ((lft > 0) && (lft%width != 0)) {
			links =+ union(x, lft);
		}
		if ((rght < nodes-1) && (rght%width != 1)) {
			links =+ union(x, rght);
		}
		// if above and below are outside of the grid, we link to the 'virtual' nodes
		if (above < 0) above = 0;
		links =+ union(x, above);
		if (below > nodes-1) below = nodes-1;
		links =+ union(x, below);
		return links;
	}

	public void open_random_closed_node() {
		/*
		mutates the state of the node_state array
		calls unionise_node to union ot open neighbours
		returns a string report
		*/
		int[] closed_nodes = get_closed_nodes();
		int new_node = closed_nodes[rand.nextInt(closed_nodes.length)];
		node_state[new_node] = true;
		int links = unionise_node(new_node);
		log(5, "Opened Node " + new_node + " created " + links + " new links");
	}

	private int[] get_closed_nodes() {
		/*
		returns a list of indices that refer to 'closed' nodes in the id list
		*/
		ArrayList<Integer> closed_nodes = new ArrayList<Integer>();
		for (int i=0; i<node_state.length; i++) {
			if (node_state[i] == false) {
				closed_nodes.add(i);
			}
		}
		int[] closed_nodes_list = closed_nodes.stream().mapToInt(i -> i).toArray();
		return closed_nodes_list;
	}
}


