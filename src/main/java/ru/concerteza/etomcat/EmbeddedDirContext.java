package ru.concerteza.etomcat;

import org.apache.naming.NamingContextBindingsEnumeration;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: alexey
 * Date: 8/27/11
 */
class EmbeddedDirContext implements DirContext{
    
    // implementation type is not import
    private final NamingEnumeration EMPTY_NE = new NamingContextBindingsEnumeration(new ArrayList(0).iterator(), this);
    private static final Hashtable EMPTY_TABLE = new Hashtable(0);
    
    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
        return EMPTY_NE;   
    }   
    
    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes, String[] attributesToReturn) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes, String[] attributesToReturn) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(Name name, Attributes matchingAttributes) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(String name, Attributes matchingAttributes) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(Name name, String filter, SearchControls cons) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(String name, String filter, SearchControls cons) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(Name name, String filterExpr, Object[] filterArgs, SearchControls cons) throws NamingException {
        return EMPTY_NE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NamingEnumeration<SearchResult> search(String name, String filterExpr, Object[] filterArgs, SearchControls cons) throws NamingException {
        return EMPTY_NE;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Hashtable<?, ?> getEnvironment() throws NamingException {
        return EMPTY_TABLE;
    }
    
    @Override
    public Attributes getAttributes(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Attributes getAttributes(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Attributes getAttributes(Name name, String[] attrIds) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Attributes getAttributes(String name, String[] attrIds) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public void modifyAttributes(Name name, int mod_op, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void modifyAttributes(String name, int mod_op, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void modifyAttributes(Name name, ModificationItem[] mods) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void modifyAttributes(String name, ModificationItem[] mods) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void bind(Name name, Object obj, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void bind(String name, Object obj, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rebind(Name name, Object obj, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rebind(String name, Object obj, Attributes attrs) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public DirContext createSubcontext(Name name, Attributes attrs) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public DirContext createSubcontext(String name, Attributes attrs) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public DirContext getSchema(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public DirContext getSchema(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public DirContext getSchemaClassDefinition(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public DirContext getSchemaClassDefinition(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }   

    @Override
    public Object lookup(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Object lookup(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public void bind(Name name, Object obj) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void bind(String name, Object obj) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rebind(Name name, Object obj) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rebind(String name, Object obj) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void unbind(Name name) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void unbind(String name) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rename(Name oldName, Name newName) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void rename(String oldName, String newName) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void destroySubcontext(Name name) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public void destroySubcontext(String name) throws NamingException {
        // this method is initially left blank  
    }

    @Override
    public Context createSubcontext(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Context createSubcontext(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Object lookupLink(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Object lookupLink(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public NameParser getNameParser(Name name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public NameParser getNameParser(String name) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Name composeName(Name name, Name prefix) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public String composeName(String name, String prefix) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Object addToEnvironment(String propName, Object propVal) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public Object removeFromEnvironment(String propName) throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }

    @Override
    public void close() throws NamingException {
        // this method is initially left blank    
    }

    @Override
    public String getNameInNamespace() throws NamingException {
        throw new NamingException("Not supported by EmbeddedDirContext");
    }
}
