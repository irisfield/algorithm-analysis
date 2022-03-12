# Performance Analysis of Bubble, Insertion, and Selection Sort

### Testing the Performance of Bubble, Insertion Sort, and Selection.
The data consists of the following columns:
- `SortType` - The sorting algorithm: bubble sort, selection sort, insertion sort.
- `ArrayType` - The integer array type used during the test. 
    - `Random` - As the name implies, the integers in the array were in random order.
    - `Increasing` - The integers in the array were in ascending order.
    - `IncreasingAndRandom` - 90% of the integers were in ascending order, and the remaining in random order.
    - `Decreasing` - The integers in the array were in descending order.
    - `Equal` - The integers in the array were all the same.
- `ArraySize` - The size of the array used during the test.
    - `10000` - The array contained 10,000 elements.
    - `25000` - The array contained 25,000 elements.
    - `50000` - The array contained 50,000 elements.
    - `100000` - The array contained 100,000 elements.
- `Test`# - The time it took in **milliseconds** to sort the array.
- `AverageTime` - The average time of all the tests.
- `MemoryUsage` - The average memory usage.

### Tests
The code I wrote to perform the tests are inside the `sort` directory. The algorithms are located in the `sort/algorithm` directory. The `SortDriver` class performs the tests and writes a nicely formatted CSV file of the test times. The `TestTime` class is used by the `SortDriver` to store the test times.
