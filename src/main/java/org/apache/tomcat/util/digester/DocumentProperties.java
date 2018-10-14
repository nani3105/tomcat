package org.apache.tomcat.util.digester;

/**
 *
 * A collection of interfaces, one per property, that enables the object being
 * populated by the digester to signal to the digester that it supports the
 * given property and that the digester should populate that property if
 * available.
 */
public interface DocumentProperties {

    /**
     * The character encoding used by the source XML document.
     */
    public interface Charset {
        public void setCharset(java.nio.charset.Charset charset);
    }
}