package org.apache.tomcat.util.http;

import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.buf.UDecoder;

/**
 *
 * @author Costin Manolache
 */
public final class Parameters {

    private static final org.apache.juli.logging.Log log =
            org.apache.juli.logging.LogFactory.getLog(Parameters.class );

    private MessageBytes queryMB;

    private UDecoder urlDec;

    public void setQuery( MessageBytes queryMB ) {
        this.queryMB=queryMB;
    }

    public void setURLDecoder( UDecoder u ) {
        urlDec=u;
    }
}
