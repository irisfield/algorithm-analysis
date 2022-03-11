public class SelectionSort {

  public void sort(Integer[] arr) {

    for (int k = 0; k < arr.length; k++) {
      int iMin = k;

      for (int i = k + 1; i < arr.length; i++) {
        if (arr[i] < arr[iMin]) {
          iMin = i;
        }
      }

      if (iMin != k) {
        int temp = arr[k];
        arr[k] = arr[iMin];
        arr[iMin] = temp;
      }
    }
  }
}
