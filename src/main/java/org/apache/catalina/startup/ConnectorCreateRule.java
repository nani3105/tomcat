package org.apache.catalina.startup;

import org.apache.catalina.Service;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.digester.Rule;
import org.apache.tomcat.util.res.StringManager;
import org.xml.sax.Attributes;

/**
 * Rule implementation that creates a connector.
 */

public class ConnectorCreateRule extends Rule {

    private static final Log log = LogFactory.getLog(ConnectorCreateRule.class);
    protected static final StringManager sm = StringManager.getManager(ConnectorCreateRule.class);
    // --------------------------------------------------------- Public Methods


    /**
     * Process the beginning of this element.
     *
     * @param namespace the namespace URI of the matching element, or an
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just
     *   the element name otherwise
     * @param attributes The attribute list for this element
     */
    @Override
    public void begin(String namespace, String name, Attributes attributes)
            throws Exception {
        Service svc = (Service)digester.peek();
    }
}
