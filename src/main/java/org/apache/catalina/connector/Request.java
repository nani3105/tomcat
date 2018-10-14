package org.apache.catalina.connector;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Niranjan.kurra - Date: 10/13/18 4:31 PM
 */
public class Request
        implements HttpServletRequest {

    private static final Log log = LogFactory.getLog(Request.class);

    // ----------------------------------------------------------- Constructors


    public Request() {

        formats[0].setTimeZone(GMT_ZONE);
        formats[1].setTimeZone(GMT_ZONE);
        formats[2].setTimeZone(GMT_ZONE);

    }

    // ------------------------------------------------------------- Properties


    /**
     * Coyote request.
     */
    protected org.apache.coyote.Request coyoteRequest;
}
