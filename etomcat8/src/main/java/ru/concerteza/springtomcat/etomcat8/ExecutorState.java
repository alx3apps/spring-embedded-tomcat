package ru.concerteza.springtomcat.etomcat8;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ExecutorState");
        sb.append("{maxIdleTime=").append(maxIdleTime);
        sb.append(", maxThreads=").append(maxThreads);
        sb.append(", minSpareThreads=").append(minSpareThreads);
        sb.append(", activeCount=").append(activeCount);
        sb.append(", completedCount=").append(completedCount);
        sb.append(", corePoolSize=").append(corePoolSize);
        sb.append(", largestPoolSize=").append(largestPoolSize);
        sb.append(", poolSize=").append(poolSize);
        sb.append(", queueSize=").append(queueSize);
        sb.append('}');
        return sb.toString();
    }
}
