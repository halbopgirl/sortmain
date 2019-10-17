//Haleigh Jayde Doetschman
//09.11.2019
//CMSC 451 Project 1 

package sortmain;

public class SortMain {
    private static final int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

    public static void main(String[] args) {
        BenchmarkSorts run = new BenchmarkSorts(sizes);
        run.runSorts();
        run.displayReport();
    }
    
}
