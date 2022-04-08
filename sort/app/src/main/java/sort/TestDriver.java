package sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import sort.TestMetric.MemoryUnit;
import sort.TestMetric.TimeUnit;

public class TestDriver {

  /**
   * Equal - The elements in the array are all equal.
   * Random - The elements in the array are randomly generated.
   * Increasing - The elements of the array are arranged in increasing order.
   * Decreasing - The elements of the array are arranged in decreasing order.
   * IncreasingAndRandom - The first 90% of the elements are arranged in
   * increasing order and the last 10% of the elements are randomly generated.
   * DecreasingAndRandom - The first 90% of the elements are arranged in
   * decreasing order and the last 10% of the elements are randomly generated.
   */
  public static enum ArrayType {
    Equal, Random, Increasing, Decreasing, IncreasingAndRandom
  }

  public static enum SortType {
    BubbleSort, InsertionSort, SelectionSort
  }

  /**
   * Create an array based on <b>ArrayType</b> and <b>arraySize<b>.
   *
   * @return arr A populated integer array.
   */
  public Integer[] createArray(ArrayType arrayType, int arraySize) {
    Integer[] arr = new Integer[arraySize];
    Random rand = new Random();
    int startNumber;
    int upperLimit = Integer.MAX_VALUE;

    switch (arrayType) {
      case Equal:
        int fixedValue = rand.nextInt(upperLimit);

        for (int i = 0; i < arraySize; i++) {
          arr[i] = fixedValue;
        }
        break;
      case Random:
        for (int i = 0; i < arraySize; i++) {
          arr[i] = rand.nextInt(upperLimit);
        }
        break;
      case Increasing:
        startNumber = rand.nextInt(upperLimit / 2);

        for (int i = 0; i < arraySize; i++) {
          arr[i] = startNumber++;
        }
        break;
      case Decreasing:
        int min = upperLimit / 2;
        startNumber = rand.nextInt(upperLimit - min) + min;

        for (int i = 0; i < arraySize; i++) {
          arr[i] = startNumber--;
        }
        break;
      case IncreasingAndRandom:
        int ninetyPercent = (int) (arraySize * 0.9);
        startNumber = rand.nextInt(upperLimit / 2);

        for (int i = 0; i < ninetyPercent; i++) {
          arr[i] = startNumber++;
        }

        for (int i = ninetyPercent; i < arraySize; i++) {
          arr[i] = rand.nextInt(upperLimit);
        }
        break;
    }

    return arr;
  }

  /**
   * Run tests based on arguments.
   *
   * @return testMetric Array containing all the test times and memory usages.
   */
  public TestMetric runSort(SortType sortType, ArrayType arrayType, int arraySize, int numberOfTimes) {
    SortingAlgorithm sort = new SortingAlgorithm();
    TestMetric testMetric = new TestMetric(TimeUnit.MILLISECONDS, MemoryUnit.MEGABYTES);

    for (int i = 0; i < numberOfTimes; i++) {
      switch (sortType) {
        case BubbleSort:
          Integer[] arr = createArray(arrayType, arraySize);

          long timeStart = System.nanoTime();
          sort.bubble(arr);
          long timeEnd = System.nanoTime();

          testMetric.addTest(timeEnd - timeStart);
          break;
        case InsertionSort:
          arr = createArray(arrayType, arraySize);

          timeStart = System.nanoTime();
          sort.insertion(arr);
          timeEnd = System.nanoTime();

          testMetric.addTest(timeEnd - timeStart);
          break;
        case SelectionSort:
          arr = createArray(arrayType, arraySize);

          timeStart = System.nanoTime();
          sort.selection(arr);
          timeEnd = System.nanoTime();

          testMetric.addTest(timeEnd - timeStart);
          break;
      }
    }

    return testMetric;
  }

  public static void main(String[] args) throws IOException {
    TestDriver driver = new TestDriver();
    int[] arraySizes = { 10000, 25000 };
    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("sort_tests.csv")));

    int runs = 9;
    String[] categories = {
        "SortType,",
        "ArrayType,",
        "ArraySize,",
        "Test1,",
        "Test2,",
        "Test3,",
        "Test4,",
        "Test5,",
        "Test6,",
        "Test7,",
        "Test8,",
        "Test9,",
        "AverageTime,",
        "MemoryUsage\n",
    };

    for (String category : categories) {
      writer.write(category);
    }

    for (SortType sortType : SortType.values()) {
      for (ArrayType arrayType : ArrayType.values()) {
        for (int arraySize : arraySizes) {

          TestMetric tests = driver.runSort(sortType, arrayType, arraySize, runs);
          long[] times = tests.getTestTimes();

          writer.write(String.format("%s,%s,%d,", sortType, arrayType, arraySize));

          for (int i = 0; i < runs; i++) {
            System.out.println(times[i]);
            writer.write(String.format("%.6s,", (times[i])));
          }

          writer.write(String.format("%.6s,", (tests.getAverageTime())));
          writer.write(String.format("%.5s\n", (tests.getAverageMemory())));

        }
      }
    }

    writer.close();
  }
}
