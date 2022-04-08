package sort;

public class SortingAlgorithm {

  public void bubble(Integer[] arr) {
    for (int k = 0; k < arr.length; k++) {
      boolean swapped = false;

      for (int i = 1; i < arr.length; i++) {
        if (arr[i - 1] > arr[i]) {
          int temp = arr[i - 1];
          arr[i - 1] = arr[i];
          arr[i] = temp;
          swapped = true;
        }
      }

      if (!swapped) {
        break;
      }
    }
  }

  public void insertion(Integer[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int x = arr[i];
      int k = i;

      while (k > 0 && arr[k - 1] > x) {
        arr[k] = arr[k - 1];
        k--;
      }

      arr[k] = x;
    }
  }

  public void selection(Integer[] arr) {
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
