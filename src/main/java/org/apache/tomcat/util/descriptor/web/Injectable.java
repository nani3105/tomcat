package org.apache.tomcat.util.descriptor.web;

import java.util.List;

/**
 * User: Niranjan.kurra - Date: 10/13/18 3:34 AM
 */
public interface Injectable {
    public String getName();
    public void addInjectionTarget(String injectionTargetName, String jndiName);
    public List<InjectionTarget> getInjectionTargets();
}
