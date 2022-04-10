package sort;

public class TestMetric {

  public static enum TimeUnit {
    NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS
  }

  private final int MAX_NUM_TESTS = 10;

  private long[] testTimes;
  private TimeUnit timeUnit;
  private int count;

  public TestMetric() {
    this.testTimes = new long[MAX_NUM_TESTS];
    this.timeUnit = TimeUnit.NANOSECONDS;
    this.count = 0;
  }

  public TestMetric(TimeUnit timeUnit) {
    this();
    this.timeUnit = timeUnit;
  }

  public void addTest(long runTime) {
    if (this.count < MAX_NUM_TESTS) {
      this.testTimes[count] = runTime;
      this.count++;
    } else {
      for (int i = 0; i < (this.testTimes.length - 1); i++) {
        /* Shift the array one position to the left */
        this.testTimes[i] = this.testTimes[i + 1];
      }
      this.testTimes[MAX_NUM_TESTS - 1] = runTime;
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

  public long[] getTestTimes() {
    long[] testTimesCopy = new long[this.testTimes.length];
    for (int i = 0; i < this.testTimes.length; i++) {
      testTimesCopy[i] = this.testTimes[i];
    }
    return testTimesCopy;
  }


  /******************
   * HELPER METHODS *
   ******************/

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

  @Override
  public String toString() {
    String timeString = "[ ";

    for (int i = 0; i < this.count; i++) {
      timeString += this.testTimes[i] + " ";
    }

    timeString += String.format("| %d ]", getAverageTime());

    return String.format("Test times = %s", timeString);
  }

}
