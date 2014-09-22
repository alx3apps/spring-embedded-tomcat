package ru.concerteza.springtomcat.etomcat8.failfast;

import org.apache.catalina.connector.CoyoteWriter;
import org.apache.catalina.connector.OutputBuffer;

import java.io.IOException;

import static ru.concerteza.springtomcat.etomcat8.failfast.SneakThrower.sneakyThrow;

/**
 * User: alexkasko
 * Date: 4/8/14
 */
public class FailFastResponseWriter extends CoyoteWriter {
    public FailFastResponseWriter(OutputBuffer ob) {
        super(ob);
    }

    public void flush() {
        try {
            ob.flush();
        } catch (IOException e) {
            error = true;
            sneakyThrow(e);
        }

    }

    public void write(char buf[], int off, int len) {
        try {
            ob.write(buf, off, len);
        } catch (IOException e) {
            error = true;
            sneakyThrow(e);
        }
    }


    public void write(String s, int off, int len) {
        try {
            ob.write(s, off, len);
        } catch (IOException e) {
            error = true;
            sneakyThrow(e);
        }
    }

    public boolean checkError() {
        try {
            flush();
        } catch (Exception e) {
            if (e instanceof IOException) {
                // NOOP
            } else {
                sneakyThrow(e);
            }
        }
        return error;
    }
}
