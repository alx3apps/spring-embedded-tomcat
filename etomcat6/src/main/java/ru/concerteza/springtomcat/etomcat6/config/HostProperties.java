package ru.concerteza.springtomcat.etomcat6.config;

/**
 * User: alexey
 * Date: 11/4/11
 */

// http://tomcat.apache.org/tomcat-6.0-doc/config/host.html
public class HostProperties {
    private String name = "localhost";
    private String errorReportValveClass = "org.apache.catalina.valves.ErrorReportValve";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorReportValveClass() {
        return errorReportValveClass;
    }

    public void setErrorReportValveClass(String errorReportValveClass) {
        this.errorReportValveClass = errorReportValveClass;
    }
}
