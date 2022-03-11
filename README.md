# Performance Analysis of Bubble, Insertion, and Selection Sort

## Work in progress. Please check again tomorrow for the finished product.

The data consists of the following columns:
- `SortType` - The sorting algorithm: bubble sort, selection sort, insertion sort.
- `ArrayType` - The integer array type used during the test. 
    - `Random` - As the name implies, the values in the array were in a random order.
    - `Increasing` - The values in the array were in ascending order.
    - `IncreasingAndRandom` - 90% of the values were in ascending order, and the remaining in a random order.
    - `Decreasing` - The values in the array were in descending order.
    - `Equal` - The values in the array were all the same.
- `ArraySize` - The size of the array used during the test.
    - `10000` - Integer array containing 10,000 elements.
    - `25000` - Integer array containing 25,000 elements.
    - `50000` - Integer array containing 50,000 elements.
    - `100000` - Integer array containing 100,000 elements.
- `Test`# - The time it took in **milliseconds** to sort the array.
- `AverageTime` - The average time of all the tests.
- `MemoryUsage` - The average memory usage.
