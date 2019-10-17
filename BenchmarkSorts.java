//Haleigh Jayde Doetschman
//09.11.2019
//CMSC 451 Project 1
package sortmain;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import static java.lang.Math.sqrt;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class BenchmarkSorts {

    private String[][] data;
    private Random randomNumber = new Random();
    private long[] iterativeTimes = new long[50];
    private int[] iterativeCounts = new int[50];
    private long[] recursiveTimes = new long[50];
    private int[] recursiveCounts = new int[50];
    private DecimalFormat df = new DecimalFormat("#.#####");

    public BenchmarkSorts(int[] sizes) {
        data = new String[sizes.length][9];
        for (int i = 0; i < sizes.length; i++) {
            data[i][0] = Integer.toString(sizes[i]);
        }
    }

    public void runSorts() {
        YourSort sort = new YourSort();
        sort.dummy();
        //iterate over data array to get data set sizes
        for (int i = 0; i < data.length; i++) {
            int setSize = Integer.parseInt(data[i][0]);
            //loop that runs 50 times
            for (int j = 0; j < 50; j++) {
                //produce random data set
                int[] dataSet = new int[setSize];
                for (int k = 0; k < dataSet.length; k++) {
                    dataSet[k] = randomNumber.nextInt();
                }
                //YourSort sort = new YourSort();
                //run iterative algorithm
                sort.iterativeSort(dataSet);
                //store all this info in an array for iterative algs
                iterativeTimes[j] = sort.getTime();
                //get count
                iterativeCounts[j] = sort.getCount();
                //reset count
                sort.resetCount();
                //run recursive algorithm
                sort.recursiveSort(dataSet);
                //store all this info in an array for recursive algs 
                recursiveTimes[j] = sort.getTime();
                //get count
                recursiveCounts[j] = sort.getCount();
                //reset count
                sort.resetCount();
            }
            //average together iterative critical operations and put in matrix
            double sum = 0;
            for (int l = 0; l < 50; l++) {
                sum += iterativeCounts[l];
            }
            double avg = sum / 50;
            data[i][1] = String.valueOf(df.format(avg));
            //find iterative coefficient of variance of count and put in matrix
            double standardDev = 0;
            for (int l = 0; l < 50; l++) {
                standardDev += ((iterativeCounts[l] - avg) * (iterativeCounts[l] - avg));
            }
            standardDev = standardDev/50;
            standardDev = sqrt(standardDev);
            double coOfVar = 0;
            if (avg != 0) {
            coOfVar = standardDev / avg * 100;
            } else {
                coOfVar = 0;
            }
            data[i][2] = String.valueOf(df.format(coOfVar) + " %");
            //average together iterative execution time and put in matrix
            sum = 0;
            for (int l = 0; l < 50; l++) {
                sum += iterativeTimes[l];
                System.out.println("Time to run: " + iterativeTimes[l]);
            }
            avg = sum / 50;
            data[i][3] = String.valueOf(df.format(avg));
            //find iterative coefficient of variance of time and put in matrix
            standardDev = 0;
            for (int l = 0; l < 50; l++) {
                standardDev += ((iterativeTimes[l] - avg) * (iterativeTimes[l] - avg));
            }
            standardDev = standardDev/50;
            standardDev = sqrt(standardDev);
            if (avg != 0) {
            coOfVar = standardDev / avg * 100;
            } else {
                coOfVar = 0;
            }
            data[i][4] = String.valueOf(df.format(coOfVar) + " %");
            //average together recursive critical operations and put in matrix
            sum = 0;
            for (int l = 0; l < 50; l++) {
                sum += recursiveCounts[l];
            }
            avg = sum / 50;
            data[i][5] = String.valueOf(df.format(avg));
            //find recursive coefficient of variance of count and put in matrix
            standardDev = 0;
            for (int l = 0; l < 50; l++) {
                standardDev += ((recursiveCounts[l] - avg) * (recursiveCounts[l] - avg));
            }
            standardDev = standardDev/50;
            standardDev = sqrt(standardDev);
            if (avg != 0) {
            coOfVar = standardDev / avg * 100;
            } else {
                coOfVar = 0;
            }
            data[i][6] = String.valueOf(df.format(coOfVar) + " %");
            //average together recursive execution time and put in matrix
            sum = 0;
            for (int l = 0; l < 50; l++) {
                sum += recursiveTimes[l];
            }
            avg = sum / 50;
            data[i][7] = String.valueOf(df.format(avg));
            //find recursive coefficient of variance of time and put in matrix
            standardDev = 0;
            for (int l = 0; l < 50; l++) {
                standardDev += ((recursiveTimes[l] - avg) * (recursiveTimes[l] - avg));
            }
            standardDev = standardDev/50;
            standardDev = sqrt(standardDev);
            if (avg != 0) {
            coOfVar = standardDev / avg * 100;
            } else {
                coOfVar = 0;
            }
            data[i][8] = String.valueOf(df.format(coOfVar) + " %");
        }
    }

    public void displayReport() {
        JPanel guiPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        String[] headers = {"Data Set Size n", "<html><center>Iterative<br> Average Critical Operation Count", "<html><center>Iterative<br> Coefficient of Variance of Count",
            "<html><center>Iterative<br> Average Execution Time", "<html><center>Iterative<br> Coefficient of Variance of Time", "<html><center>Recursive<br> Average Critical Operation Count",
            "<html><center>Recursive<br> Coefficient of Variance of Count", "<html><center>Recursive<br> Average Execution Time", "<html><center>Recursive<br> Coefficient of Variance of Time"};
        JTable table = new JTable(data, headers);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 60));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 400;      //make this component tall
        c.weightx = 0.9;
        c.gridwidth = 3;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 1;
        guiPanel.add(scrollPane, c);

        JFrame panelFrame = new JFrame("Benchmark Report");
        panelFrame.add(guiPanel);
        panelFrame.setSize(3500, 350);
        panelFrame.setVisible(true);
        panelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

         System.out.println("Data Set Size n --- Iterative Average Critical Operation Count --- Iterative Coefficient of Variance of Count --- "
             + "Iterative Average Execution Time --- Iterative Coefficient of Variance of Time --- Recursive Average Critical Operation Count --- Recursive Coefficient of Variance of Count --- "
                 + "Recursive Average Execution Time --- Recursive Coefficient of Variance of Time");
      
        for(int i = 0; i < 10; i++)
   {
      for(int j = 0; j < 9; j++)
      {
         System.out.printf("%-42s", data[i][j]);
      }
      System.out.println();
   }
    }
}
