package ru.concerteza.springtomcat.etomcat8.failfast;

/**
 * User: alexkasko
 * Date: 4/8/14
 */

// http://mail.openjdk.java.net/pipermail/lambda-dev/2010-June/001552.html
class SneakThrower {

    static RuntimeException sneakyThrow(Exception e) {
        SneakThrower.<RuntimeException>sneakyThrow0(e);
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void sneakyThrow0(Throwable t) throws T {
        throw (T) t;
    }
}
