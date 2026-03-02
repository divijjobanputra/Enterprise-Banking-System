package util;

import java.util.concurrent.atomic.AtomicLong;

public final class TransactionIdGenerator {
    private static final AtomicLong SEQ = new AtomicLong(1L);

    private TransactionIdGenerator() {}

    public static long next() {
        return SEQ.getAndIncrement();
    }
}