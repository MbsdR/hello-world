import java.sql.Timestamp;
import java.util.Random;

//////////////////////////
//Informatik II - SoSe 2020
//Task 2.1. a) b)
//////////////////////////

//////////////////////////
// Submission by:
// 
// 
//
//////////////////////////

public class MergeSort {
	
	//////////////////////
	// Main function
	public static void main(String[] args) {
		// task 2.1.a)
		// Example Values:
		int[] list = {2,1,6,2,4,1,6};
		int k = 4;

		// use MergeSort
		int[] newlist = kMergeSort(list, k);

		// print List
		printList(newlist);
		
		// task 2.1.b)
		analyzeTimeComplexity();
	}

	//////////////////////
	// Helper function to print the elements of a list
	private static void printList(int[] list) {
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
	}

	//////////////////////
	// Task 2.1.a)
	// Implement the merge sort algorithm as stated in 
	// the exercise sheet
	public static int[] kMergeSort(int[] input_list, int k) {
		if (input_list.length < 2) {
			return input_list;
		}
		int half = k/2;
		int[] ll = new int[half];
		int[] rl = new int[k-half];
		
		for (int i = 0; i < half; i++) {
			ll[i] = input_list[i];
		}
		for (int i = half; i < k; i++) {
			rl[i - half] = input_list[i];
		}
		
		kMergeSort(ll, half);
		kMergeSort(rl, k - half);
		merge(input_list, ll, rl, half, k - half);
		
		return input_list;
	}
	
	private static void merge(int[] input_list, int[] ll, int[] rl, int firsthalf, int secondhalf) {
		int a = 0;
		int b = 0;
		int c = 0;
		while (a < firsthalf && b < secondhalf) {
			if(ll[a] <= rl[b]) {
				input_list[c++] = ll[a++];
			}
			else {input_list[c++] = ll[b++];}
		}
		while(a < firsthalf) {input_list[c++] = ll[a++];}
		while(b < secondhalf) {input_list[c++] = rl[b++];}
	}

	//////////////////////
	// Task 2.1.b)
	// Create three lists of size n based on exercise 1
	private static int[] generateAverageCase(int n) {
		int [] averagecase = new int[n];
		for(int i = 0; i < n; i++) {
			Random rng = new Random();	
			averagecase[i] = rng.nextInt();;
		}
		return averagecase;
	}
	

	// Hilfsfunktion für worst case Bestimmung
	public static int[] reverseInsertionSort(int[] input_list) {
		for(int j = 1; j < input_list.length; j++) {
			int tmp;
			tmp = input_list[j];
			int i;
			i = j-1;
				while(i >= 0 && input_list[i] < tmp){
					input_list[i+1] = input_list[i];
					i--;
				}
				input_list[i+1] = tmp;		
		}
		return input_list;
	}

	private static int[] generateWorstCase(int n) {
		int [] averagecase = new int[n];
		for(int i = 0; i < n; i++) {
			Random rng = new Random();
			averagecase[i] = rng.nextInt();
		}
		int[] worstcase;
		worstcase = reverseInsertionSort(averagecase);
		return worstcase;
	}

	private static int[] generateBestCase(int n) {
		int [] averagecase = new int[n];
		for(int i = 0; i < n; i++) {
			Random rng = new Random();
			averagecase[i] = rng.nextInt();
		}
		int[] bestcase;
		bestcase = kMergeSort(averagecase, n);
		return bestcase;
	}

	//////////////////////
	//Task 2.1.b)
	// Apply the function from 1.2.b for every n in [1,50000]
	// as well as for k = 2 and k = 7
	// and measure the time
	// Plot the results in an application of your choice
	//
	private static void analyzeTimeComplexity() {
		for(int n = 0; n <= 20000; n += 1000) {
			
			int[] averagecase = new int[n];
			averagecase = generateAverageCase(n);
			long averagestart = System.currentTimeMillis();
			kMergeSort(averagecase, n);
			long averageend = System.currentTimeMillis();
			float averagetime;
			averagetime = averageend - averagestart;
			System.out.print(" n=" + n + ", " + averagetime + " ");
			
			
			int[] worstcase = new int[n];
			worstcase = generateWorstCase(n);
			long worststart = System.currentTimeMillis();
			kMergeSort(worstcase, n);
			long worstend = System.currentTimeMillis();
			float worsttime;
			worsttime = worstend - worststart;
			System.out.print(worsttime + " ");
			
			
			int[] bestcase = new int[n];
			bestcase = generateBestCase(n);
			long beststart = System.currentTimeMillis();
			kMergeSort(bestcase, n);
			long bestend = System.currentTimeMillis();
			float besttime;
			besttime = bestend -beststart;
			System.out.print(besttime + ";  ");
		}

	}
}