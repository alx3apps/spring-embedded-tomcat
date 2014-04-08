package ru.concerteza.springtomcat.etomcat6.failfast;

import org.apache.catalina.Globals;
import org.apache.catalina.connector.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: alexkasko
 * Date: 4/8/14
 */
public class FailFastResponse extends Response {

    @Override
    public void setConnector(Connector connector) {
        this.connector = connector;
        if ("AJP/1.3".equals(connector.getProtocol())) {
            // default size to size of one ajp-packet
            outputBuffer = new OutputBuffer(8184);
        } else {
            outputBuffer = new OutputBuffer();
        }
        outputStream = new FailFastProxyOutputStream(outputBuffer);
        writer = new FailFastResponseWriter(outputBuffer);
    }

    @Override
    public PrintWriter getReporter() throws IOException {
        if (outputBuffer.isNew()) {
            outputBuffer.checkConverter();
            if (writer == null) {
                writer = new CoyoteWriter(outputBuffer);
            }
            return writer;
        } else {
            return null;
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        if (usingOutputStream)
            throw new IllegalStateException
                    (sm.getString("coyoteResponse.getWriter.ise"));

        if (Globals.STRICT_SERVLET_COMPLIANCE) {
                   /*
                    * If the response's character encoding has not been specified as
                    * described in <code>getCharacterEncoding</code> (i.e., the method
                    * just returns the default value <code>ISO-8859-1</code>),
                    * <code>getWriter</code> updates it to <code>ISO-8859-1</code>
                    * (with the effect that a subsequent call to getContentType() will
                    * include a charset=ISO-8859-1 component which will also be
                    * reflected in the Content-Type response header, thereby satisfying
                    * the Servlet spec requirement that containers must communicate the
                    * character encoding used for the servlet response's writer to the
                    * client).
                    */
            setCharacterEncoding(getCharacterEncoding());
        }

        usingWriter = true;
        outputBuffer.checkConverter();
        if (writer == null) {
            writer = new CoyoteWriter(outputBuffer);
        }
        return writer;
    }
}
