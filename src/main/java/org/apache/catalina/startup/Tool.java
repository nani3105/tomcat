package org.apache.catalina.startup;


import org.apache.catalina.Globals;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.ExceptionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Niranjan.kurra - Date: 10/12/18 5:42 PM
 */
public class Tool {

    private static final Log log = LogFactory.getLog(Tool.class);

    // ------------------------------------------------------- Static Variables


    /**
     * Set <code>ant.home</code> system property?
     */
    private static boolean ant = false;


    /**
     * The pathname of our installation base directory.
     */
    private static final String catalinaHome =
            System.getProperty(Globals.CATALINA_HOME_PROP);


    /**
     * Include common classes in the repositories?
     */
    private static boolean common = false;


    /**
     * Include server classes in the repositories?
     */
    private static boolean server = false;


    /**
     * Include shared classes in the repositories?
     */
    private static boolean shared = false;


    public static void main(String args[]) {
        // Verify that "catalina.home" was passed.
        if (catalinaHome == null) {
            log.error("Must set '" + Globals.CATALINA_HOME_PROP + "' system property");
            System.exit(1);
        }

        // Process command line options
        int index = 0;

        while (true) {
            if (index == args.length) {
                usage();
                System.exit(1);
            }

            if ("-ant".equals(args[index]))
                ant = true;
            else if ("-common".equals(args[index]))
                common = true;
            else if ("-server".equals(args[index]))
                server = true;
            else if ("-shared".equals(args[index]))
                shared = true;
            else
                break;
            index++;
        }

        if (index > args.length) {
            usage();
            System.exit(1);
        }


        // Set "ant.home" if requested
        if (ant)
            System.setProperty("ant.home", catalinaHome);

        // Construct the class loader we will be using
        ClassLoader classLoader = null;
        try {
            List<File> packed = new ArrayList<>();
            List<File> unpacked = new ArrayList<>();
            unpacked.add(new File(catalinaHome, "classes"));
            packed.add(new File(catalinaHome, "lib"));
            if (common) {
                unpacked.add(new File(catalinaHome,
                        "common" + File.separator + "classes"));
                packed.add(new File(catalinaHome,
                        "common" + File.separator + "lib"));
            }
            if (server) {
                unpacked.add(new File(catalinaHome,
                        "server" + File.separator + "classes"));
                packed.add(new File(catalinaHome,
                        "server" + File.separator + "lib"));
            }
            if (shared) {
                unpacked.add(new File(catalinaHome,
                        "shared" + File.separator + "classes"));
                packed.add(new File(catalinaHome,
                        "shared" + File.separator + "lib"));
            }
            classLoader =
                    ClassLoaderFactory.createClassLoader
                            (unpacked.toArray(new File[0]),
                                    packed.toArray(new File[0]),
                                    null);
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            log.error("Class loader creation threw exception", t);
            System.exit(1);
        }

        Thread.currentThread().setContextClassLoader(classLoader);

        // Load our application class
        Class<?> clazz = null;
        String className = args[index++];
        try {
            if (log.isDebugEnabled())
                log.debug("Loading application class " + className);
            clazz = classLoader.loadClass(className);
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            log.error("Exception creating instance of " + className, t);
            System.exit(1);
        }

        Method method = null;
        String params[] = new String[args.length - index];
        System.arraycopy(args, index, params, 0, params.length);
        try {
            if (log.isDebugEnabled())
                log.debug("Identifying main() method");
            String methodName = "main";
            Class<?> paramTypes[] = new Class[1];
            paramTypes[0] = params.getClass();
            method = clazz.getMethod(methodName, paramTypes);
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            log.error("Exception locating main() method", t);
            System.exit(1);
        }

        // Invoke the main method of the application class
        try {
            if (log.isDebugEnabled())
                log.debug("Calling main() method");
            Object paramValues[] = new Object[1];
            paramValues[0] = params;
            method.invoke(null, paramValues);
        } catch (Throwable t) {
            t = ExceptionUtils.unwrapInvocationTargetException(t);
            ExceptionUtils.handleThrowable(t);
            log.error("Exception calling main() method", t);
            System.exit(1);
        }

    }

    /**
     * Display usage information about this tool.
     */
    private static void usage() {

        log.info("Usage:  java org.apache.catalina.startup.Tool [<options>] <class> [<arguments>]");

    }
}
