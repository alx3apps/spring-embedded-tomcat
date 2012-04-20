package ru.concerteza.springtomcat.app;

import org.apache.commons.lang.UnhandledException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.Log4jConfigurer;
import ru.concerteza.springtomcat.etomcat6.EmbeddedTomcat;
import ru.concerteza.springtomcat.etomcat6.context.EmbeddedAnnotationSpringContext;
import ru.concerteza.util.io.CtzIOUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.System.err;
import static java.lang.System.out;
import static java.lang.System.setProperty;

/**
 * User: alexey
 * Date: 4/20/12
 */
public abstract class BaseLauncher {
    protected static final File APP_ROOT = CtzIOUtils.codeSourceDir(ConsoleLauncher.class).getParentFile();
    private static final File LOG4J_CONFIG = new File(APP_ROOT, "conf/log4j.properties");

    protected static void start() {
        out.println("Initializing application...");
        configLog4j();
        ApplicationContext ctx = loadContext();
        ctx.getBean(EmbeddedTomcat.class).start(APP_ROOT);
    }

    private static AbstractApplicationContext loadContext() {
        setProperty("app.app_root", APP_ROOT.getPath());
        AbstractApplicationContext ctx = new EmbeddedAnnotationSpringContext(Config.class);
        ctx.registerShutdownHook();
        return ctx;
    }

    private static void configLog4j() {
        try {
            Log4jConfigurer.initLogging(LOG4J_CONFIG.getPath());
        } catch (FileNotFoundException e) {
            err.println("Cannot open file: " + LOG4J_CONFIG.getAbsolutePath());
            throw new UnhandledException(e);
        }
    }

}
