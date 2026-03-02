package util;

import java.util.concurrent.atomic.AtomicLong;

public final class AccountNumberGenerator {
    private static final AtomicLong SEQ = new AtomicLong(1000000000L);

    private AccountNumberGenerator() {}

    public static long next() {
        return SEQ.getAndIncrement();
    }
}