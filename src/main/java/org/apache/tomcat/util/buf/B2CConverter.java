package org.apache.tomcat.util.buf;

import org.apache.tomcat.util.res.StringManager;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * User: Niranjan.kurra - Date: 10/13/18 2:10 AM
 */
public class B2CConverter {

    private static final StringManager sm = StringManager.getManager(B2CConverter.class);

    private static final Map<String, Charset> encodingToCharsetCache =
            new HashMap<>();

    static {
        for (Charset charset: Charset.availableCharsets().values()) {
            encodingToCharsetCache.put(
                    charset.name().toLowerCase(Locale.ENGLISH), charset);
            for (String alias : charset.aliases()) {
                encodingToCharsetCache.put(
                        alias.toLowerCase(Locale.ENGLISH), charset);
            }
        }
    }

    /**
     * Obtain the Charset for the given encoding
     *
     * @param enc The name of the encoding for the required charset
     *
     * @return The Charset corresponding to the requested encoding
     *
     * @throws UnsupportedEncodingException If the requested Charset is not
     *                                      available
     */
    public static Charset getCharset(String enc) throws UnsupportedEncodingException {

        // Encoding names should all be ASCII
        String lowerCaseEnc = enc.toLowerCase(Locale.ENGLISH);

        Charset charset = encodingToCharsetCache.get(lowerCaseEnc);

        if (charset == null) {
            // Pre-population of the cache means this must be invalid
            throw new UnsupportedEncodingException(
                    sm.getString("b2cConverter.unknownEncoding", lowerCaseEnc));
        }
        return charset;
    }
}
