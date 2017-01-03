public class Test {
	public static void main(String[] args) {
		int W = StdIn.readInt();
		int H = StdIn.readInt();
		int counter = 0;
		int limit = W*H;
		Percolator grid = new Percolator(W, H);
		StdOut.println("grid initted. limit = " + limit);
		while ((!grid.percolationcheck()) && (counter < limit)) {
			int new_node = grid.get_random_closed_node();
//			StdOut.println("Opening " + new_node);
			int links = grid.unionise_node(new_node);
//			StdOut.println("Node " + new_node + " linked to" + links + " links");
			counter++;
		}
		if (counter >= limit) {
			StdOut.println("We have a bug...");
		}
		else {
			StdOut.println("Grid of width:" + W + " height:" + H + " percolated with " + counter + " open nodes.");			
		}

	}
}