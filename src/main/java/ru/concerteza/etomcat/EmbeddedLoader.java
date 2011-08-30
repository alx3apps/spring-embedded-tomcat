package ru.concerteza.etomcat;

import org.apache.catalina.Container;
import org.apache.catalina.Loader;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.StandardClassLoader;

import java.beans.PropertyChangeListener;

/**
 * User: alexey
 * Date: 8/27/11
 */
class EmbeddedLoader implements Loader {

    private StandardContext container;

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = (StandardContext) container;
    }

    @Override
    public void backgroundProcess() {
        // this method is initially left blank
    }

    @Override
    public boolean getDelegate() {
        return false;
    }

    @Override
    public void setDelegate(boolean delegate) {
        // this method is initially left blank
    }

    @Override
    public String getInfo() {
        return "Embedded Loader/1.0";
    }

    @Override
    public boolean getReloadable() {
        return false;
    }

    @Override
    public void setReloadable(boolean reloadable) {
        // this method is initially left blank
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // this method is initially left blank
    }

    @Override
    public void addRepository(String repository) {
        // this method is initially left blank
    }

    @Override
    public String[] findRepositories() {
        return new String[0];
    }

    @Override
    public boolean modified() {
        return false;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // this method is initially left blank
    }
}
