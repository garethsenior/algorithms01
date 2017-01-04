/*
when running this faile like so:

	java test

you need to type the width of the grid
and hit enter
and then the height of the grid
and hit enter
*/

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		int W = StdIn.readInt();
		int H = StdIn.readInt();

		int testruns = 20;
		int[] results = new int[testruns];

		for (int i=0; i<=testruns-1; i++) {
			int counter = 0;
			int limit = W*H;
			Percolator grid = new Percolator(W, H);
			while ((!grid.percolationcheck()) && (counter < limit)) {
				grid.open_random_closed_node();
				counter++;
			}
			if (counter >= limit) {
				StdOut.println("We have a bug...");
			}
			else {
				results[i] = counter;
			}
		}
		StdOut.println("Grid of width:" + W + " height:" + H + " has " + (W*H) + " tiles.");
		StdOut.println("Ran monte carlo simulation " + testruns + " times.");
		StdOut.println("Results " + Arrays.toString(results));
		double total = 0;
		for (int i=0; i<testruns; i++) {
			total = total + results[i];
		}
		double avg = (total/testruns);
		StdOut.println("Average: " + avg);
	}
}