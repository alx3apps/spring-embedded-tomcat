package ru.concerteza.springtomcat.etomcat8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@link ExecutorService} implementation uses caller thread to execute tasks. May be replaced by
 * properly configured {@link ThreadPoolExecutor} with ThreadPoolExecutor.CallerRunsPolicy.
 * Added for customizable future implementation. Suitable for tests.
 *
 * @author alexey
 * Date: 5/20/12
 */
class SameThreadExecutor extends ThreadPoolExecutor {
    private AtomicBoolean shuttedDown = new AtomicBoolean(false);

    public SameThreadExecutor() {
        super(1, 1, 1, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(1));
    }

    /**
     * Switches shutdown flag
     */
    @Override
    public void shutdown() {
        shuttedDown.set(true);
    }

    /**
     * Switches shutdown flag
     *
     * @return empty list
     */
    @Override
    public List<Runnable> shutdownNow() {
        shuttedDown.set(true);
        return Collections.emptyList();
    }

    /**
     * @return whether <code>shutdown()</code> or <code>shutdownNow()</code> was called
     */
    @Override
    public boolean isShutdown() {
        return shuttedDown.get();
    }

    /**
     * @return false
     */
    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes task immediately in callers thread and returns result value or exception wrapped into {@link Future}
     *
     * @param task callable to run
     * @param <T> results generic param
     * @return result value or exception wrapped into {@link Future}
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        try {
            T res = task.call();
            return new SameThreadFuture<T>(res);
        } catch (Exception e) {
            return new SameThreadFuture<T>(e);
        }
    }

    /**
     * Executes task immediately in callers thread
     *
     * @param task runnable to run
     * @param result result to return
     * @param <T> result generic param
     * @return provided result value or exception wrapped into {@link Future}
     */
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        try {
            task.run();
            return new SameThreadFuture<T>(result);
        } catch (Exception e) {
            return new SameThreadFuture<T>(e);
        }
    }

    /**
     * Executes task immediately in callers thread
     *
     * @param task runnable to run
     * @return null value or exception wrapped into {@link Future}
     */
    @Override
    public Future<?> submit(Runnable task) {
        return submit(task, null);
    }

    /**
     * Executes task list immediately in callers thread one by one
     *
     * @param tasks task list
     * @param <T> task results generic param
     * @return list of result values or exceptions wrapped into {@link Future}
     * @throws InterruptedException
     */
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        List<Future<T>> list = new ArrayList<Future<T>>();
        for(Callable<T> ca : tasks) {
            Future<T> res = submit(ca);
            list.add(res);
        }
        return list;
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes tasks in callers thread one by one until first successful execution
     *
     * @param tasks task list
     * @param <T> task results generic param
     * @return first successful execution result
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        StringBuilder errors = new StringBuilder();
        for (Callable<T> ta : tasks) {
            try {
                return ta.call();
            } catch (Exception e) {
                errors.append(e.getMessage()).append("\n");
            }
        }
        throw new ExecutionException(errors.toString(), new RuntimeException());
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }

    /**
     * Execute runnable immediately in callers thread
     *
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    private class SameThreadFuture<T> implements Future<T> {
        private final T value;
        private final Exception exception;
        private boolean cancelled;

        private SameThreadFuture(Exception exception) {
            this.value = null;
            this.exception = exception;
        }

        private SameThreadFuture(T value) {
            this.value = value;
            this.exception = null;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            cancelled = true;
            return false;
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public T get() throws InterruptedException, ExecutionException {
            if(null != exception) throw new ExecutionException(exception);
            return value;
        }

        @Override
        public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            if(null != exception) throw new ExecutionException(exception);
            return value;
        }
    }
}
