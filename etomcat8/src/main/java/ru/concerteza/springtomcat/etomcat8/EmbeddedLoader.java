package ru.concerteza.springtomcat.etomcat8;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Loader;
import org.apache.catalina.core.StandardContext;

import java.beans.PropertyChangeListener;

/**
 * User: alexey
 * Date: 8/27/11
 */
class EmbeddedLoader implements Loader {

    private Context context;

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
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
    public boolean modified() {
        return false;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // this method is initially left blank
    }
}
