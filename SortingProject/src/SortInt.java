/**
 * This is a class that contains difrent datastructuers that can
 * be given an array of intigers and returns the same array in
 * acinding sorted order
 * 
 * @author aaron ratcliffe
 * @version 2
 */
public class SortInt {
	
	/**
	 * This method sorts an array on intigers by using insertion sort.
	 * the run time is O(n^2)
	 * @param int[] list  an array of intigers to be sorted
	 */
	public static void useInsertionSort(int[] list){
		//iterating thru the unsorted part of the array
		for(int i = 1; i < list.length; i++){			
			//cheking the newest intiger to the sorted part of the array
			for(int j = i-1; j>=0 && list[j] > list[j+1]; j--){
				//moving the sorted array positions back untill
				//the space has been found for the new intiger
				swap(list, j+1, j);
			}
		}
		return;
	}
	
	/**
	 * This method sorts an array on intigers by using quick sort.
	 * The avrege run time is O(nlog(n))
	 * 
	 * @param int[] list  an array of intigers to be sorted
	 */
	public static void useQuickSort(int[] list, int start, int end){
		//checking to see if recertion has hit the bace case yet
		if(start >= end){
			return;
		}
		else{
			//setting pivot to a random index of the array section
			int pivotPosition = getPivot(list, start, end);
			//swaping the pivot with the start of the array section
			swap(list, start, pivotPosition);
			//index of the curently solected numbers
			int i = start;
			int j = end;
			//finding the numbers in the array to change places and changing them
			while(i < j){
				//incrominting thru the array to find a number that is greater then the pivot
				while(list[i] <= list[start] && i < end){
					i++;
				}
				//dencrominting thru the array to find a number that is less then the pivot
				while(list[j] > list[start] && j > start){
					j--;
				}
				//switching the two numbers found
				swap(list, j, i);
			}
			//re switching the last change to make the array correct
			swap(list, i, j);
			//swoping the last lower term with the pivot to find its final index
			swap(list, start, j);
			pivotPosition = j;
			//recertion on the left side indexes start to pivotPosition-1			
			useQuickSort(list, start, pivotPosition-1);
			//recertion on the right side indexes pivotPosition+1 to end
			useQuickSort(list, pivotPosition+1, end);
		}
		return;
	}


	/**
	 * This method takes in an array and two indexes of that array and then switches the 
	 * values stord at those index locations with each other
	 * 
	 * @param int[] list, int first, int second  the array where the two numbers will be switched in, an index value, an index value
	 */
	public static void swap(int[] list, int first, int second){
		int temp = list[first];
		list[first] = list[second];
		list[second] = temp;
		return;
	}
	
	/**
	 * This method takes in an array and the bound you are serching in and what you are serching for
	 * 
	 * @param int[] list, int start, int end, int query  the array that will be sorted, the lower bound, the upper bound,
	 * 			 what you are looking for
	 * @return int  the index of the first eloment in the aray that is grater then the query.
	 */
	public static int useBinarySerch(int[] list, int start, int end, int query){
		if(end < start){
			return start;
		}
		int midPont = (int)((end+start)/2);
		if(list[midPont] > query ){
			return useBinarySerch(list, start, midPont-1, query);
		}
		else{
			return useBinarySerch(list, midPont+1, end, query);
		}
	}


	/**
	 * This method takes in an array and the bound you are sorting in
	 * and solectes a random index to be used as the pivot
	 * 
	 * @param int[] list, int start, int end  the array that will be sorted, the lower bound, the upper bound
	 * @return int  the pivot that will be used in quick sort
	 */
	public static int getPivot(int[] list, int start, int end){
		int pivot = (int)(Math.random() % (end-start));
		pivot += start;
		return pivot;
	}
}