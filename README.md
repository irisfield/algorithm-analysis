# Description
The goal of this project is to examine general trends in the time performance of bubble, insertion and selection sort when sorting data different ordered data.

# About the Data
The data consists of the following columns:
- `SortType` - The algorithm used to sort the data (bubble sort, selection sort, insertion sort).
- `ArrayType` - The order the integers in the array prior to sorting.
    - `Random` - The integers in the array were in random order.
    - `Increasing` - The integers in the array were in ascending order.
    - `IncreasingAndRandom` - 90% of the integers were in ascending order while 10% were in random order.
    - `Decreasing` - The integers in the array were in descending order.
    - `Equal` - The integers in the array were all the same.
- `ArraySize` - The size of the array.
    - `10000` - The array contained 10,000 integers.
    - `25000` - The array contained 25,000 integers.
    - `50000` - The array contained 50,000 integers.
    - `100000` - The array contained 100,000 integers.
- `Test`# - The time it took in milliseconds to sort the array.
- `AverageTime` - The average of all the test times in milliseconds.
