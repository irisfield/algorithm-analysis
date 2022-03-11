public class BubbleSort {

  public void sort(Integer[] arr) {

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
}
