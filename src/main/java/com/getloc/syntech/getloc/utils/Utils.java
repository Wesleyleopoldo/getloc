package com.getloc.syntech.getloc.utils;

import java.util.UUID;

public class Utils {

    private UUID uuid;

    private Utils() {
    }

    public Utils setUUID(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public static Utils create() {
        return new Utils();
    }

    public Utils generateRandomUUID() {
        this.uuid = UUID.randomUUID();
        return this;
    }

    public byte[] generateRandomBinaryByUUID() {

        byte[] bytes = new byte[16];
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();

        for(int i = 0; i < 8; i++) {
            bytes[i] = (byte) (mostSigBits >>> (8 * (7 -i)) & 0xff);
            bytes[8 + i] = (byte) (leastSigBits >>> (8 * (7 -i)) & 0xff);
        }
        return bytes;
    }

    public UUID convertBytesForUUID(byte[] bytes) {
        long mostSigBits = 0;
        long leastSigBits = 0;

        for(int i = 0; i < 8; i++) {
            mostSigBits |= ((long) bytes[i] & 0xff) << (8 * (7 -i));
            leastSigBits |= ((long) bytes[8 + i] & 0xff) << (8 * (7 -i));
        }

        return new UUID(mostSigBits, leastSigBits);
    }
}
