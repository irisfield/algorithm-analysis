public class InsertionSort {

  public void sort(Integer[] arr) {
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
}
