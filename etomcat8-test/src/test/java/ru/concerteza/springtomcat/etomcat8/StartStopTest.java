package ru.concerteza.springtomcat.etomcat8;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import ru.concerteza.springtomcat.etomcat8.context.EmbeddedAnnotationSpringContext;

import java.io.File;

/**
 * @author alexkasko
 *         Date: 9/22/14
 */
public class StartStopTest {

    @Test
    public void test() throws InterruptedException {
        File baseDir = new File("src/main/app-dirs/annotationdir");
        AbstractApplicationContext ctx;
        // 1
        ctx = new EmbeddedAnnotationSpringContext(Config.class);
        ctx.getBean(EmbeddedTomcat.class).start(baseDir);
        ctx.close();
        // 2
        ctx = new EmbeddedAnnotationSpringContext(Config.class);
        ctx.getBean(EmbeddedTomcat.class).start(baseDir);
        ctx.close();
        // 3
        ctx = new EmbeddedAnnotationSpringContext(Config.class);
        ctx.getBean(EmbeddedTomcat.class).start(baseDir);
        ctx.close();
    }

    @Configuration
    public static class Config {
        @Bean(destroyMethod = "stop")
        public EmbeddedTomcat etomcat() {
            return new EmbeddedTomcat();
        }
    }
}
