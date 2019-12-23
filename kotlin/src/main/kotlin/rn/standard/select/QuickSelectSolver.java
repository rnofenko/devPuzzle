package rn.standard.select;

public class QuickSelectSolver {
    public int kthSmallest(int[] arr, int low, int high, int k) {
        int partition = partition(arr,low,high);

        if(partition == k) {
            return arr[partition];
        } else if(partition < k ) {
            return kthSmallest(arr, partition + 1, high, k);
        } else {
            return kthSmallest(arr, low, partition - 1, k);
        }
    }

    public int partition (int[] arr, int low, int high) {
        int pivot = arr[high];
        int pivotLocation = low;
        for (int i = low; i <= high; i++)
        {
            if(arr[i] < pivot)
            {
                int temp = arr[i];
                arr[i] = arr[pivotLocation];
                arr[pivotLocation] = temp;
                pivotLocation++;
            }
        }

        int temp = arr[high];
        arr[high] = arr[pivotLocation];
        arr[pivotLocation] = temp;

        return pivotLocation;
    }
}
