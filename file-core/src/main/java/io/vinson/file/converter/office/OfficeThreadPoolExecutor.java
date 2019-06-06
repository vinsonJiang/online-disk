package io.vinson.file.converter.office;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OfficeThreadPoolExecutor extends ThreadPoolExecutor {

    private boolean available = false;
    private ReentrantLock suspendLock = new ReentrantLock();
    private Condition availableCondition = suspendLock.newCondition();

    public OfficeThreadPoolExecutor(ThreadFactory threadFactory) {
        super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable task) {
        super.beforeExecute(thread, task);
        suspendLock.lock();
        try {
            while (!available) {
                availableCondition.await();
            }
        } catch (InterruptedException interruptedException) {
            thread.interrupt();
        } finally {
            suspendLock.unlock();
        }
    }

    public void setAvailable(boolean available) {
        suspendLock.lock();
        try {
            this.available = available;
            if (available) {
                availableCondition.signalAll();
            }
        } finally {
            suspendLock.unlock();
        }
    }

}
