//Haleigh Jayde Doetschman
//09.11.2019
//CMSC 451 Project 1
package sortmain;

import java.util.Random;

public class YourSort implements SortInterface {

    int[] list;
    int count = 0;
    long startTime = System.nanoTime();
    long endTime = System.nanoTime();
    int j = 1;

    public YourSort() {
        count = 0;
        startTime = 0;
        endTime = 0;
    }

    //warm up JVM
    //based off code found on github.com
    public void dummy() {
        System.out.println("Warming up JVM...");
        int[] list = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 1000; j++) {
                //populate list
                list[j] = random.nextInt();
            }
            // Load classes
            recursiveSort(list);
            iterativeSort(list);
            resetCount();
        }
        // warm up I/O
        System.out.println("Warmup Complete");
    }

    //from geeksforgeeks.com
    public int[] recursiveSort(int[] list) {
        startTime = System.nanoTime();
        int n = list.length - 1;
        count++;

        //build heap(rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        //one by one extract an element from heap
        for (int i = n; i >= 0; i--) {
            //move current root to end
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;

            //call max heapify on the reduced heap
            heapify(list, i, 0);
        }
        //Check if sorted, if not throw exception
        for (int i = 0; i < list.length; i++) {
            if (j < i) {
                try {
                    throw new UnsortedException();
                } catch (UnsortedException ex) {
                }
            }
            j++;
        }
        j = 1;
        endTime = System.nanoTime();
        return list;
    }

    //from geeksforgeeks.com
    public int[] iterativeSort(int[] list) {
        startTime = System.nanoTime();
        int n = list.length;
        buildMaxHeap(list, n);

        for (int i = n - 1; i > 0; i--) {
            // swap value of first indexed with last indexed 
            swap(list, 0, i);

            // maintaining heap property after each swapping 
            int j = 0, index;

            do {
                count++;
                index = (2 * j + 1);

                // if left child is smaller than right child point index variable to right child 
                if (index < (i - 1) && list[index] < list[index + 1]) {
                    index++;
                }

                // if parent is smaller than child then swap parent with child of higher value 
                if (index < i && list[j] < list[index]) {
                    swap(list, j, index);
                }

                j = index;

            } while (index < i);
        }
        //check if sorted, if not throw exception
        for (int i = 0; i < list.length; i++) {
            if (j < i) {
                try {
                    throw new UnsortedException();
                } catch (UnsortedException ex) {
                }
            }
            j++;
        }
        j = 1;
        endTime = System.nanoTime();
        return list;
    }

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }

    public long getTime() {
        return (endTime-startTime);
    }

    //from geeksforgeeks.com
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) {
        count++;
        int largest = i;  // Initialize largest as root 
        int l = 2 * i + 1;  // left = 2*i + 1 
        int r = 2 * i + 2;  // right = 2*i + 2 

        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root 
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest);
        }
    }

    //from geeksforgeeks.com
    private void buildMaxHeap(int arr[], int n) {
        for (int i = 1; i < n; i++) {
            count++;
            // if child is bigger than parent 
            if (arr[i] > arr[(i - 1) / 2]) {
                int j = i;

                // swap child and parent until parent is smaller 
                while (arr[j] > arr[(j - 1) / 2]) {
                    count++;
                    swap(arr, j, (j - 1) / 2);
                    j = (j - 1) / 2;
                }
            }
        }
    }

    //from geeksforgeeks.com
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
