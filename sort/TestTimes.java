public class TestTimes {

  /*
   * 1 microsecond = 1000¹ nanoseconds
   * 1 millisecond = 1000² nanoseconds
   * 1 second = 1000³ nanoseconds
   *
   * Use this value as the denominator to convert to target time unit.
   */
  public final double microsecond = 1000;
  public final double millisecond = microsecond * microsecond;
  public final double second = millisecond * microsecond;

  /*
   * Note that in binary (base 2):
   * 1 kilobyte = 1024¹ bytes
   * 1 megabyte = 1024² bytes
   * 1 gigabyte = 1024³ bytes
   *
   * Use this value as the denominator to convert to target time unit.
   */
  public final double kilobyte = 1024;
  public final double megabyte = kilobyte * kilobyte;
  public final double gigabyte = megabyte * kilobyte;

  private final int MAX_TESTS = 10;

  private long[] testTimes;
  private long[] memUsage;
  private int numTests;

  public TestTimes(int arrSize) {
    if (arrSize > this.MAX_TESTS) {
      arrSize = this.MAX_TESTS;
    }
    this.testTimes = new long[arrSize];
    this.memUsage = new long[arrSize];
    this.numTests = 0;
  }

  public void addTest(long testTime, long memUsed) {
    if (this.numTests < this.testTimes.length) {
      this.testTimes[this.numTests] = testTime;
      this.memUsage[this.numTests] = memUsed;
      this.numTests++;
    } else {
      for (int i = 0; i < (this.testTimes.length - 1); i++) {
        this.testTimes[i] = this.testTimes[i + 1];
        this.memUsage[i] = this.memUsage[i + 1];
      }
      this.testTimes[testTimes.length - 1] = testTime;
      this.memUsage[memUsage.length - 1] = memUsed;
    }
  }

  public long getAverageTime() {
    if (this.numTests == 0) {
      return 0;
    }

    long sum = 0;
    for (int i = 0; i < this.numTests; i++) {
      sum += this.testTimes[i];
    }

    return sum / this.numTests;
  }

  public long getAverageMem() {
    if (this.numTests == 0) {
      return 0;
    }

    long sum = 0;
    for (int i = 0; i < this.numTests; i++) {
      sum += this.memUsage[i];
    }

    return sum / this.numTests;
  }

  public long[] getTimes() {
    long[] testTimesCopy = new long[this.testTimes.length];
    for (int i = 0; i < this.testTimes.length; i++) {
      testTimesCopy[i] = this.testTimes[i];
    }
    return testTimesCopy;
  }

  public long[] getMemUsage() {
    long[] memUsageCopy = new long[this.memUsage.length];
    for (int i = 0; i < this.memUsage.length; i++) {
      memUsageCopy[i] = this.memUsage[i];
    }
    return memUsageCopy;
  }

  @Override
  public String toString() {
    String times = "[ ";
    String memory = "[ ";

    for (int i = 0; i < this.numTests; i++) {
      times += this.testTimes[i] + " ";
      memory += this.memUsage[i] + " ";
    }

    times += String.format("| %d ]", getAverageTime());
    memory += String.format("| %d ]", getAverageMem());

    return String.format("%12s = %s\nMemory usage = %s", "Test times", times, memory);
  }
}
