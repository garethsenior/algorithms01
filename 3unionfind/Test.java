public class Test {
	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedUnionUF uf = new WeightedUnionUF(N);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				StdOut.println(p + " - " + q + " now linked");
			} else {
				StdOut.println(p + " - " + q + " are already connected");
			}
			int[] connections = uf.get_roots();
			String str = "";
			for (int i=0; i<connections.length; i++) {
				str = str + " [" + i + "] " + connections[i];
			}
			StdOut.println(str);
		}
	}
}