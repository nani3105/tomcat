package org.apache.coyote;

import org.apache.catalina.startup.Constants;
import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.buf.UDecoder;
import org.apache.tomcat.util.http.Parameters;
import org.apache.tomcat.util.res.StringManager;

/**
 * This is a low-level, efficient representation of a server request. Most
 * fields are GC-free, expensive operations are delayed until the  user code
 * needs the information.
 *
 * Processing is delegated to modules, using a hook mechanism.
 *
 * This class is not intended for user code - it is used internally by tomcat
 * for processing the request in the most efficient way. Users ( servlets ) can
 * access the information using a facade, which provides the high-level view
 * of the request.
 *
 * Tomcat defines a number of attributes:
 * <ul>
 *   <li>"org.apache.tomcat.request" - allows access to the low-level
 *       request object in trusted applications
 * </ul>
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author Harish Prabandham
 * @author Alex Cruikshank [alex@epitonic.com]
 * @author Hans Bergsten [hans@gefionsoftware.com]
 * @author Costin Manolache
 * @author Remy Maucherat
 */
public final class Request {

    private static final StringManager sm =
            StringManager.getManager(Constants.Package);


    // ----------------------------------------------------------- Constructors

    public Request() {
        parameters.setQuery(queryMB);
        parameters.setURLDecoder(urlDecoder);
    }

    private final MessageBytes queryMB = MessageBytes.newInstance();

    /**
     * URL decoder.
     */
    private final UDecoder urlDecoder = new UDecoder();

    private final Parameters parameters = new Parameters();

}
