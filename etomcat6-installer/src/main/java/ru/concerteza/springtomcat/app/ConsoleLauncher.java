package ru.concerteza.springtomcat.app;

import org.apache.commons.cli.*;
import org.apache.commons.lang.UnhandledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Date: 4/19/12
 */
public class ConsoleLauncher extends BaseLauncher {
    private static final String HELP_OPTION = "help";
    private static final String VERSION_OPTION = "version";
    private static final String CONFIG_OPTION = "config";

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        try {
            options.addOption("h", HELP_OPTION, false, "show this page");
            options.addOption("v", VERSION_OPTION, false, "show version");
            options.addOption("c", CONFIG_OPTION, true, "config file");
            CommandLine cline = new GnuParser().parse(options, args);
            if(cline.hasOption(HELP_OPTION)) {
                throw new ParseException("Printing help page");
            } else if(cline.hasOption(VERSION_OPTION)) {
                out.println("ETomcat6 Based Application Installer v. 1.0");
            } else {
                resolveConfig(cline);
                start();
                while (true) {
                    Thread.sleep(5 * 60 * 1000);
                    logHeartbeat();
                }
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            out.println(e.getMessage());
            formatter.printHelp("startup.{sh|bat} [-c app.properties]", options);
        }
    }

    private static void resolveConfig(CommandLine cline) {
        final String configPath;
        if (cline.hasOption(CONFIG_OPTION)) {
            configPath = cline.getOptionValue(CONFIG_OPTION);
        } else {
            configPath = new File(APP_ROOT, "conf/app.properties").getPath();
        }
        setProperty("app.config.path", configPath);
    }

    // http://en.wikipedia.org/wiki/Heart_sounds
    private static void logHeartbeat() {
        Logger logger = LoggerFactory.getLogger(ConsoleLauncher.class);
        logger.info("lub-dub");
    }
}
