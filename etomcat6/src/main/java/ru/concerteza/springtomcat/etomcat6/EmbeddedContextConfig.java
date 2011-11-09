package ru.concerteza.springtomcat.etomcat6;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.DigesterFactory;
import org.apache.catalina.startup.WebRuleSet;
import org.apache.tomcat.util.digester.Digester;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: alexey
 * Date: 8/28/11
 */
class EmbeddedContextConfig implements LifecycleListener {
    private final String webXmlFile;

    public EmbeddedContextConfig(String webXmlFile) {
        this.webXmlFile = webXmlFile;
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if (event.getType().equals(Lifecycle.START_EVENT)) {
            StandardContext context = (StandardContext) event.getLifecycle();
            Digester webDigester =  DigesterFactory.newDigester(false, false, new WebRuleSet());
            webDigester.push(context);
            InputStream is = null;
            try {
                is = new FileInputStream(webXmlFile);
                webDigester.parse(is);
                context.setConfigured(true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
