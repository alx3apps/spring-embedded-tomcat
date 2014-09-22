package ru.concerteza.springtomcat.etomcat8.valves;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * User: alexey
 * Date: 9/26/12
 */
public class PostCharacterEncodingValve extends ValveBase {
    private final Charset postEncoding;

    public PostCharacterEncodingValve(String postEncoding) {
        this.postEncoding = Charset.forName(postEncoding);
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        request.setCharacterEncoding(postEncoding.name());
        getNext().invoke(request, response);
    }
}
