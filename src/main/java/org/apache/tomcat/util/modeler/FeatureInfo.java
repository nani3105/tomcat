package org.apache.tomcat.util.modeler;

import javax.management.MBeanFeatureInfo;
import java.io.Serializable;

/**
 * <p>Convenience base class for <code>AttributeInfo</code> and
 * <code>OperationInfo</code> classes that will be used to collect configuration
 * information for the <code>ModelMBean</code> beans exposed for management.</p>
 *
 * @author Craig R. McClanahan
 * @version $Id$
 */

public class FeatureInfo implements Serializable {
    static final long serialVersionUID = -911529176124712296L;

    protected String description = null;
    protected String name = null;
    protected MBeanFeatureInfo info = null;

    // all have type except Constructor
    protected String type = null;


    // ------------------------------------------------------------- Properties

    /**
     * The human-readable description of this feature.
     */
    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * The name of this feature, which must be unique among features in the
     * same collection.
     */
    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The fully qualified Java class name of this element.
     */
    public String getType() {
        return (this.type);
    }

    public void setType(String type) {
        this.type = type;
    }


}