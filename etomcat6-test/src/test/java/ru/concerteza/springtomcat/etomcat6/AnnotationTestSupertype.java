package ru.concerteza.springtomcat.etomcat6;

import org.apache.catalina.LifecycleException;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.AbstractApplicationContext;
import ru.concerteza.springtomcat.etomcat6.context.EmbeddedAnnotationSpringContext;

import java.io.File;

/**
 * User: alexey
 * Date: 4/9/12
 */
public abstract class AnnotationTestSupertype {
    private AbstractApplicationContext ctx;

    @Before
       public void setUp() throws Exception {
           File baseDir = new File("src/main/app-dirs/" + dirname());
           ctx = new EmbeddedAnnotationSpringContext(configClass());
           ctx.getBean(EmbeddedTomcat.class).start(baseDir);
       }

       @After
       public void tearDown() throws LifecycleException {
           if (null != ctx) ctx.close();
       }

    protected abstract String dirname();

    protected abstract Class<?> configClass();
}
