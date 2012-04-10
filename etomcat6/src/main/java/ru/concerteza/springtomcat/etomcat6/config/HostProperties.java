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

    public HostProperties setName(String name) {
        this.name = name;
        return this;
    }

    public String getErrorReportValveClass() {
        return errorReportValveClass;
    }

    public HostProperties setErrorReportValveClass(String errorReportValveClass) {
        this.errorReportValveClass = errorReportValveClass;
        return this;
    }
}
