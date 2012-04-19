package ru.concerteza.springtomcat.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.concerteza.springtomcat.etomcat6.EmbeddedTomcat;
import ru.concerteza.springtomcat.etomcat6.config.GeneralProperties;
import ru.concerteza.springtomcat.etomcat6.config.SslProperties;

/**
 * User: alexey
 * Date: 4/19/12
 */

@Configuration
public class Config {
    @Value("${etomcat.installer.port}")  private int port;
    @Value("${etomcat.installer.docBaseDir}")  private String docBaseDir;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        String propPath = System.getProperty("app.config.path");
        Resource[] resources = new FileSystemResource[]{new FileSystemResource(propPath)};
        pspc.setLocations(resources);
        return pspc;
    }

    @Bean(destroyMethod = "stop")
    public EmbeddedTomcat etomcat() {
        return new EmbeddedTomcat()
                .setGeneralProps(new GeneralProperties()
                        .setPort(port)
                        .setDocBaseDir(docBaseDir));
    }
}
