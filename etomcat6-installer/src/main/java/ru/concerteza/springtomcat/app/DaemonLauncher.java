package ru.concerteza.springtomcat.app;

import org.apache.commons.lang.UnhandledException;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.err;
import static java.lang.System.out;
import static java.lang.System.setProperty;

/**
 * User: alexey
 * Date: 4/20/12
 */
public class DaemonLauncher extends BaseLauncher{
    private static final CountDownLatch LATCH = new CountDownLatch(1);

    public static void main(String args[]) throws InterruptedException {
        try {
            String cmd = args.length > 0 ? args[0] : "start";
            if ("start".equals(cmd)) {
                out.println("Starting daemon...");
//          http://issues.apache.org/jira/browse/DAEMON-100
                Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader());
                String configPath = new File(APP_ROOT, "conf/app.properties").getPath();
                setProperty("app.config.path", configPath);
                start();
                out.println("Daemon started");
                LATCH.await();
            } else if ("stop".equals(cmd)) {
                out.println("Stopping daemon...");
                LATCH.countDown();
            } else {
                err.println("Unsupported arguments, must be 'start' or 'stop': " + Arrays.toString(args));
            }
        } catch (InterruptedException e) {
            out.println("Daemon interrupted, exiting...");
        } catch (Exception e) {
            // logging app startup exception and skipping latch
            err.println("Cannot start daemon, error: " + e.toString());
            throw new UnhandledException(e);
        }
    }
}
