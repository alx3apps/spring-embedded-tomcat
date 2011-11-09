package ru.concerteza.springtomcat.etomcat6;

import org.apache.catalina.Container;
import org.apache.catalina.Loader;
import org.apache.catalina.core.StandardContext;

import java.beans.PropertyChangeListener;

/**
 * User: alexey
 * Date: 8/27/11
 */
class EmbeddedLoader implements Loader {

    private static final String[] REPOS = new String[0];
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
    public String getInfo() {
        return getClass().getName();
    }

    // unimplemented methods futher

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
        return REPOS;
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
