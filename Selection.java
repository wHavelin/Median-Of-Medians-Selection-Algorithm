import java.util.*;

public class Selection
{
    public static int comparisons = 0;
    public static int Icomparisons = 0;
    public static int select(int[] input_array, int k){
        if(input_array.length < 11){
            InsertionSort.comparisons = 0;
            input_array = InsertionSort.run_insertionsort(input_array);
            Icomparisons += InsertionSort.comparisons;
            return input_array[k-1];
        }
        if(input_array.length%5!=0){
            int[] temp_array = new int[(input_array.length+5-(input_array.length%5))];
            int i = input_array.length;
            for(int a = 0; a < input_array.length; ++a){
                temp_array[a] = input_array[a];
            }
            for(int x = 0; x < 5-(input_array.length%5); ++x){
                temp_array[i] = Integer.MAX_VALUE;
                ++i;
            }
            input_array = temp_array;
        }
        int num_arrays = input_array.length/5; 
        int[][] arrays_of_five = new int[num_arrays][5];
        int[] median_array = new int[num_arrays];
        int[] greater_than_median = new int[input_array.length];
        int gtm_index = 0; //greater than median index
        int[] less_than_median = new int[input_array.length]; 
        int ltm_index = 0; //less than median index
        int[] equal_to_median = new int[input_array.length];
        int etm_index = 0;
        int[] return_array;
        for(int i = 0; i < num_arrays; ++i){
            for(int j = 0; j < 5; ++j){
                arrays_of_five[i][j] = input_array[j+(5 * i)];
            }
        }
        for(int i = 0; i < num_arrays; ++i){
            median_array[i] = select(arrays_of_five[i], 2);
        }
        int median = Selection.select(median_array, median_array.length/2);
        for(int i = 0; i < input_array.length; ++i){
            comparisons+=2;
            if(input_array[i] > median){
                greater_than_median[gtm_index] = input_array[i];
                ++gtm_index;
                comparisons--;
            }
            else if(input_array[i] == median){
                equal_to_median[etm_index] = median;
                ++etm_index;
            }
            else{
                less_than_median[ltm_index] = input_array[i];
                ++ltm_index;
            }
        }
        if(k <= ltm_index){
            return_array = new int[ltm_index];
            for(int i = 0; i < ltm_index; ++i){
                return_array[i] = less_than_median[i];
            }
            return select(return_array, k);
        }
        if(k > (ltm_index + etm_index)){
            return_array = new int[gtm_index];
            for(int i = 0; i < gtm_index; ++i){
                return_array[i] = greater_than_median[i];
            }
            return select(return_array, (k - (ltm_index + etm_index)));
        }
        return median;
    }
}