package ru.concerteza.springtomcat.etomcat6;

/**
 * User: wmel
 * Date: 27.06.12
 */
public class ExecutorState {
    private final int maxIdleTime;
    private final int maxThreads;
    private final int minSpareThreads;

    private final int activeCount;
    private final long completedCount;

    private final int corePoolSize;
    private final int largestPoolSize;

    private final int poolSize;
    private final int queueSize;

    public ExecutorState(int maxIdleTime,
                         int maxThreads,
                         int minSpareThreads,
                         int activeCount,
                         long completedCount,
                         int corePoolSize,
                         int largestPoolSize,
                         int poolSize,
                         int queueSize) {
        this.maxIdleTime = maxIdleTime;
        this.maxThreads = maxThreads;
        this.minSpareThreads = minSpareThreads;
        this.activeCount = activeCount;
        this.completedCount = completedCount;
        this.corePoolSize = corePoolSize;
        this.largestPoolSize = largestPoolSize;
        this.poolSize = poolSize;
        this.queueSize = queueSize;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public long getCompletedCount() {
        return completedCount;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public int getLargestPoolSize() {
        return largestPoolSize;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public int getQueueSize() {
        return queueSize;
    }
}
