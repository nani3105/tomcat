package org.apache.tomcat.util.descriptor.web;

/**
 * User: Niranjan.kurra - Date: 10/13/18 3:35 AM
 */
public class InjectionTarget {

    private static final long serialVersionUID = 1L;

    private String targetClass;
    private String targetName;


    public InjectionTarget() {
        // NOOP
    }

    public InjectionTarget(String targetClass, String targetName) {
        this.targetClass = targetClass;
        this.targetName = targetName;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
