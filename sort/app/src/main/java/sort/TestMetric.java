package sort;

public class TestMetric {

  public static enum TimeUnit {
    NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS
  }

  public static enum MemoryUnit {
    BYTES, KILOBYTES, MEGABYTES
  }

  private final int MAX_NUM_TESTS = 10;

  private long[] testTimes;
  private long[] memoryUsages;
  private TimeUnit timeUnit;
  private MemoryUnit memoryUnit;
  private int count;

  public TestMetric() {
    this.testTimes = new long[MAX_NUM_TESTS];
    this.memoryUsages = new long[MAX_NUM_TESTS];
    this.timeUnit = TimeUnit.NANOSECONDS;
    this.memoryUnit = MemoryUnit.BYTES;
    this.count = 0;
  }

  public TestMetric(TimeUnit timeUnit, MemoryUnit memoryUnit) {
    this();
    this.timeUnit = timeUnit;
    this.memoryUnit = memoryUnit;
  }

  public void addTest(long runTime) {
    if (this.count < MAX_NUM_TESTS) {
      this.testTimes[count] = runTime;
      this.memoryUsages[count] = getMemoryInTheJVM();
      this.count++;
    } else {
      for (int i = 0; i < (this.testTimes.length - 1); i++) {
        /* Shift the array one position to the left */
        this.testTimes[i] = this.testTimes[i + 1];
        this.memoryUsages[i] = this.memoryUsages[i + 1];
      }
      this.testTimes[MAX_NUM_TESTS - 1] = runTime;
      this.memoryUsages[MAX_NUM_TESTS - 1] = getMemoryInTheJVM();
    }
  }

  public double getAverageTime() {
    if (this.count == 0) {
      return 0;
    }

    long totalTime = 0;
    for (int i = 0; i < this.count; i++) {
      totalTime += this.testTimes[i];
    }
    long averageTime = totalTime / this.count;

    return getConvertedTime(this.timeUnit, averageTime);
  }

  public double getAverageMemory() {
    if (this.count == 0) {
      return 0;
    }

    long totalUsage = 0;
    for (int i = 0; i < this.count; i++) {
      totalUsage += this.memoryUsages[i];
    }
    long averageUsage = totalUsage / this.count;

    return getConvertedMemory(this.memoryUnit, averageUsage);
  }

  public long[] getTestTimes() {
    long[] testTimesCopy = new long[this.testTimes.length];
    for (int i = 0; i < this.testTimes.length; i++) {
      testTimesCopy[i] = this.testTimes[i];
    }
    return testTimesCopy;
  }

  public long[] getMemoryUsages() {
    long[] memoryUsagesCopy = new long[this.memoryUsages.length];
    for (int i = 0; i < this.memoryUsages.length; i++) {
      memoryUsagesCopy[i] = this.memoryUsages[i];
    }
    return memoryUsagesCopy;
  }

  /******************
   * HELPER METHODS *
   ******************/

  /**
   * Use this method to get memory being used by the Java Virtual Machine.
   *
   * @return memory usage in bytes.
   */
  private long getMemoryInTheJVM() {
    return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
  }

  /**
   * Use this method to convert time from nanoseconds to target {@link TimeUnit}.
   *
   * @param unit The desired unit.
   * @param time The time in nanoseconds to convert to <code>unit</code>.
   * @return The given <code>time</code> in <code>unit</code>.
   */
  private double getConvertedTime(TimeUnit unit, long time) {
    /*
     * 1 microsecond = 1000¹ nanoseconds
     * 1 milliscond = 1000² nanoseconds
     * 1 second = 1000³ nanoseconds
     */
    final double microsecond = 1000;
    final double millisecond = microsecond * microsecond;
    final double second = millisecond * microsecond;

    switch (unit) {
      case MICROSECONDS:
        return time / microsecond;
      case MILLISECONDS:
        return time / millisecond;
      case SECONDS:
        return time / second;
      default: /* NANOSECONDS */
        return time;
    }
  }

  /**
   * Use this method to convert memory from bytes to target {@link MemoryUnit}.
   *
   * @param unit  The desired unit.
   * @param usage The memory in bytes to convert to <code>unit</code>.
   * @return The given memory <code>usage</code> in <code>unit</code>.
   */
  private double getConvertedMemory(MemoryUnit unit, long usage) {
    /*
     * In base 2 (binary):
     * 1 kilobyte = 1024¹ bytes
     * 1 megabyte = 1024² bytes
     */
    final double kilobyte = 1024;
    final double megabyte = kilobyte * kilobyte;

    switch (unit) {
      case KILOBYTES:
        return usage / kilobyte;
      case MEGABYTES:
        return usage / megabyte;
      default: /* BYTES */
        return usage;
    }
  }

  @Override
  public String toString() {
    String timeString = "[ ";
    String memoryString = "[ ";

    for (int i = 0; i < this.count; i++) {
      timeString += this.testTimes[i] + " ";
      memoryString += this.memoryUsages[i] + " ";
    }

    timeString += String.format("| %d ]", getAverageTime());
    memoryString += String.format("| %d ]", getAverageMemory());

    return String.format("%12s = %s\nMemory usage = %s", "Test times", timeString, memoryString);
  }
}
