package org.apache.tomcat.util.buf;

import org.apache.catalina.startup.Constants;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

/**
 *  All URL decoding happens here. This way we can reuse, review, optimize
 *  without adding complexity to the buffers.
 *
 *  The conversion will modify the original buffer.
 *
 *  @author Costin Manolache
 */
public final class UDecoder {

    private static final StringManager sm =
            StringManager.getManager(Constants.Package);

    private static final Log log = LogFactory.getLog(UDecoder.class);

}
