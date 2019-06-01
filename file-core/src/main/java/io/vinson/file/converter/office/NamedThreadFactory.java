package io.vinson.file.converter.office;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger threadIndex = new AtomicInteger(0);

    private final String baseName;
    private final boolean daemon;

    public NamedThreadFactory(String baseName) {
        this(baseName, true);
    }

    public NamedThreadFactory(String baseName, boolean daemon) {
        this.baseName = baseName;
        this.daemon = daemon;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, baseName + "-" + threadIndex.getAndIncrement());
        thread.setDaemon(daemon);
        return thread;
    }

}
