package org.apache.tomcat.util.modeler.modules;

import org.apache.tomcat.util.modeler.Registry;

import javax.management.ObjectName;
import java.util.List;

/**
 * Source for descriptor data. More sources can be added.
 */
public abstract class ModelerSource {
    protected Object source;

    /**
     * Load data, returns a list of items.
     *
     * @param registry
     * @param type
     * @param source Introspected object or some other source
     * @throws Exception
     */
    public abstract List<ObjectName> loadDescriptors(Registry registry,
                                                     String type, Object source) throws Exception;
}