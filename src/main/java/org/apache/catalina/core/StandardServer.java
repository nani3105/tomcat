package org.apache.catalina.core;

import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.deploy.NamingResourcesImpl;
import org.apache.catalina.startup.Catalina;
import org.apache.catalina.startup.Constants;
import org.apache.catalina.util.LifecycleMBeanBase;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.res.StringManager;

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.net.ServerSocket;
import java.util.Random;

/**
 * Standard implementation of the <b>Server</b> interface, available for use
 * (but not required) when deploying and starting Catalina.
 *
 * @author Craig R. McClanahan
 * @version $Id$
 */
public final class StandardServer extends LifecycleMBeanBase implements Server {

    private static final Log log = LogFactory.getLog(StandardServer.class);


    // ------------------------------------------------------------ Constructor


    /**
     * Construct a default instance of this class.
     */
    public StandardServer() {

        super();

        globalNamingResources = new NamingResourcesImpl();
        globalNamingResources.setContainer(this);

        if (isUseNaming()) {
            namingContextListener = new NamingContextListener();
            addLifecycleListener(namingContextListener);
        } else {
            namingContextListener = null;
        }
    }

    // ----------------------------------------------------- Instance Variables


    /**
     * Global naming resources context.
     */
    private javax.naming.Context globalNamingContext = null;


    /**
     * Global naming resources.
     */
    private NamingResourcesImpl globalNamingResources = null;


    /**
     * The naming context listener for this web application.
     */
    private final NamingContextListener namingContextListener;


    /**
     * The port number on which we wait for shutdown commands.
     */
    private int port = 8005;

    /**
     * The address on which we wait for shutdown commands.
     */
    private String address = "localhost";


    /**
     * A random number generator that is <strong>only</strong> used if
     * the shutdown command string is longer than 1024 characters.
     */
    private Random random = null;


    /**
     * The set of Services associated with this Server.
     */
    private Service services[] = new Service[0];
    private final Object servicesLock = new Object();

    /**
     * The shutdown command string we are looking for.
     */
    private String shutdown = "SHUTDOWN";


    /**
     * The string manager for this package.
     */
    private static final StringManager sm =
            StringManager.getManager(Constants.Package);


    /**
     * The property change support for this component.
     */
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private volatile boolean stopAwait = false;

    private Catalina catalina = null;

    private ClassLoader parentClassLoader = null;

    /**
     * Thread that currently is inside our await() method.
     */
    private volatile Thread awaitThread = null;

    /**
     * Server socket that is used to wait for the shutdown command.
     */
    private volatile ServerSocket awaitSocket = null;

    private File catalinaHome = null;

    private File catalinaBase = null;

    // ------------------------------------------------------------- Properties

    /**
     * Return the global naming resources context.
     */
    @Override
    public javax.naming.Context getGlobalNamingContext() {

        return (this.globalNamingContext);

    }


    /**
     * Set the global naming resources context.
     *
     * @param globalNamingContext The new global naming resource context
     */
    public void setGlobalNamingContext
    (javax.naming.Context globalNamingContext) {

        this.globalNamingContext = globalNamingContext;

    }


    /**
     * Return the global naming resources.
     */
    @Override
    public NamingResourcesImpl getGlobalNamingResources() {

        return (this.globalNamingResources);

    }


    /**
     * Set the global naming resources.
     *
     * @param globalNamingResources The new global naming resources
     */
    @Override
    public void setGlobalNamingResources
    (NamingResourcesImpl globalNamingResources) {

        NamingResourcesImpl oldGlobalNamingResources =
                this.globalNamingResources;
        this.globalNamingResources = globalNamingResources;
        this.globalNamingResources.setContainer(this);
        support.firePropertyChange("globalNamingResources",
                oldGlobalNamingResources,
                this.globalNamingResources);

    }


    /**
     * Report the current Tomcat Server Release number
     * @return Tomcat release identifier
     */
    public String getServerInfo() {

        return ServerInfo.getServerInfo();
    }

    /**
     * Return the port number we listen to for shutdown commands.
     */
    @Override
    public int getPort() {

        return (this.port);

    }


    /**
     * Set the port number we listen to for shutdown commands.
     *
     * @param port The new port number
     */
    @Override
    public void setPort(int port) {

        this.port = port;

    }


    /**
     * Return the address on which we listen to for shutdown commands.
     */
    @Override
    public String getAddress() {

        return (this.address);

    }


    /**
     * Set the address on which we listen to for shutdown commands.
     *
     * @param address The new address
     */
    @Override
    public void setAddress(String address) {

        this.address = address;

    }

    /**
     * Return the shutdown command string we are waiting for.
     */
    @Override
    public String getShutdown() {

        return (this.shutdown);

    }


    /**
     * Set the shutdown command we are waiting for.
     *
     * @param shutdown The new shutdown command
     */
    @Override
    public void setShutdown(String shutdown) {

        this.shutdown = shutdown;

    }


    /**
     * Return the outer Catalina startup/shutdown component if present.
     */
    @Override
    public Catalina getCatalina() {
        return catalina;
    }


    /**
     * Set the outer Catalina startup/shutdown component if present.
     */
    @Override
    public void setCatalina(Catalina catalina) {
        this.catalina = catalina;
    }



}
