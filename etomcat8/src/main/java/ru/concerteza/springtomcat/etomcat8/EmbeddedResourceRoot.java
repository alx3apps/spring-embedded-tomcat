package ru.concerteza.springtomcat.etomcat8;

import org.apache.catalina.*;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.EmptyResource;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: alexkasko
 * Date: 9/14/14
 */
public class EmbeddedResourceRoot implements WebResourceRoot {

    private final StandardContext context;

    public EmbeddedResourceRoot(StandardContext context) {
        this.context = context;
    }

    @Override
    public WebResource getResource(String path) {
        return new EmptyResource(this, path);
    }

    @Override
    public WebResource[] getResources(String path) {
        return new WebResource[0];
    }

    @Override
    public WebResource getClassLoaderResource(String path) {
        return new EmptyResource(this, path);
    }

    @Override
    public WebResource[] getClassLoaderResources(String path) {
        return new WebResource[0];
    }

    @Override
    public String[] list(String path) {
        return new String[0];
    }

    @Override
    public Set<String> listWebAppPaths(String path) {
        return new HashSet<String>();
    }

    @Override
    public WebResource[] listResources(String path) {
        return new WebResource[0];
    }

    @Override
    public boolean mkdir(String path) {
        return false;
    }

    @Override
    public boolean write(String path, InputStream is, boolean overwrite) {
        return false;
    }

    @Override
    public void createWebResourceSet(ResourceSetType type, String webAppMount, URL url, String internalPath) {
    }

    @Override
    public void createWebResourceSet(ResourceSetType type, String webAppMount, String base, String archivePath, String internalPath) {
    }

    @Override
    public void addPreResources(WebResourceSet webResourceSet) {
    }

    @Override
    public WebResourceSet[] getPreResources() {
        return new WebResourceSet[0];
    }

    @Override
    public void addJarResources(WebResourceSet webResourceSet) {
    }

    @Override
    public WebResourceSet[] getJarResources() {
        return new WebResourceSet[0];
    }

    @Override
    public void addPostResources(WebResourceSet webResourceSet) {
    }

    @Override
    public WebResourceSet[] getPostResources() {
        return new WebResourceSet[0];
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
    }

    @Override
    public void setAllowLinking(boolean allowLinking) {
    }

    @Override
    public boolean getAllowLinking() {
        return false;
    }

    @Override
    public void setCachingAllowed(boolean cachingAllowed) {
    }

    @Override
    public boolean isCachingAllowed() {
        return false;
    }

    @Override
    public void setCacheTtl(long ttl) {
    }

    @Override
    public long getCacheTtl() {
        return 0;
    }

    @Override
    public void setCacheMaxSize(long cacheMaxSize) {
    }

    @Override
    public long getCacheMaxSize() {
        return 0;
    }

    @Override
    public void setCacheObjectMaxSize(int cacheObjectMaxSize) {
    }

    @Override
    public int getCacheObjectMaxSize() {
        return 0;
    }

    @Override
    public void setTrackLockedFiles(boolean trackLockedFiles) {
    }

    @Override
    public boolean getTrackLockedFiles() {
        return false;
    }

    @Override
    public void backgroundProcess() {
    }

    @Override
    public void registerTrackedResource(TrackedWebResource trackedResource) {
    }

    @Override
    public void deregisterTrackedResource(TrackedWebResource trackedResource) {
    }

    @Override
    public List<URL> getBaseUrls() {
        return new ArrayList<URL>();
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
    }

    @Override
    public void init() throws LifecycleException {
    }

    @Override
    public void start() throws LifecycleException {
    }

    @Override
    public void stop() throws LifecycleException {
    }

    @Override
    public void destroy() throws LifecycleException {
    }

    @Override
    public LifecycleState getState() {
        return LifecycleState.STARTED;
    }

    @Override
    public String getStateName() {
        return getClass().getName();
    }
}
