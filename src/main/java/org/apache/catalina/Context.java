package org.apache.catalina;

import org.apache.catalina.deploy.NamingResourcesImpl;

/**
 * A <b>Context</b> is a Container that represents a servlet context, and
 * therefore an individual web application, in the Catalina servlet engine.
 * It is therefore useful in almost every deployment of Catalina (even if a
 * Connector attached to a web server (such as Apache) uses the web server's
 * facilities to identify the appropriate Wrapper to handle this request.
 * It also provides a convenient mechanism to use Interceptors that see
 * every request processed by this particular web application.
 * <p>
 * The parent Container attached to a Context is generally a Host, but may
 * be some other implementation, or may be omitted if it is not necessary.
 * <p>
 * The child containers attached to a Context are generally implementations
 * of Wrapper (representing individual servlet definitions).
 * <p>
 *
 * @author Craig R. McClanahan
 */
public interface Context {

    /**
     * @return the naming resources associated with this web application.
     */
    public NamingResourcesImpl getNamingResources();
}
