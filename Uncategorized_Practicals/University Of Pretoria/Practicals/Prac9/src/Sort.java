// Name: Teboho Mokoena
// Student number: u14415888
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort
{
	private static int em;
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{
            // Your code here
            if (first < last) 
            { 
                // Find the middle point 
                int m = (first+last)/2; 

                // Sort first and second halves 
                mergesort(data, first, m,debug); 
                mergesort(data , m+1, last,debug); 

                // Merge the sorted halves 
                em = m;
                merge(data, first,last,debug); 
            } 

	}
     
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{

		// Your code here
            // Find sizes of two subarrays to be merged 
            int n1 = em - first + 1; 
            int n2 = last - em; 

            /* Create temp arrays */
            List<T> L = new ArrayList<T>();
            List<T> R = new ArrayList<T>();

            /*Copy data to temp arrays*/
            for (int i=0; i<n1; ++i) {
                L.add(data[first + i]);
            }
            for (int j=0; j<n2; ++j) {
                R.add(data[em + 1+ j]); 
            }


            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays 
            int i = 0, j = 0; 

            // Initial index of merged subarry array 
            int k = first; 
            while (i < n1 && j < n2) { 
                if (L.get(i).compareTo(R.get(j)) <= 0){ 
                    data[k] = L.get(i); 
                    i++; 
                }else{ 
                    data[k] = R.get(j); 
                    j++; 
                } 
                k++; 
            } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1){ 
            data[k] = L.get(i); 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            data[k] = R.get(j); 
            j++; 
            k++; 
        } 
        
		//DO NOT MOVE OR REMOVE!
		if (debug){
                    System.out.println(Arrays.toString(data));
                }
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug)
	{

            int maxValue = findMax(data);
            int[] counts = new int[maxValue + 1];
            updateCounts(data, counts);
            populateCounts(data, counts);
            //DO NOT MOVE OR REMOVE!
            if (debug)
                    System.out.println(Arrays.toString(data));
        }
        private static int findMax(int[] unsorted) {
            int max = Integer.MIN_VALUE;
            for (int i : unsorted) {
                if (i > max)
                    max = i;
            }
            return max;
        }

        private static void updateCounts(int[] unsorted, int[] counts) {
            for (int e : unsorted)
                counts[e]++;
        }

        private static void populateCounts(int[] unsorted, int[] counts) {
            int index = 0;
            for (int i = 0; i < counts.length; i++) {
                int e = counts[i];
                while (e > 0) {
                    unsorted[index++] = i;
                    e--;
                }
            }
        }

}