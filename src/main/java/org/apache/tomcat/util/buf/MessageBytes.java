package org.apache.tomcat.util.buf;

import java.io.Serializable;

/**
 * This class is used to represent a subarray of bytes in an HTTP message.
 * It represents all request/response elements. The byte/char conversions are
 * delayed and cached. Everything is recyclable.
 *
 * The object can represent a byte[], a char[], or a (sub) String. All
 * operations can be made in case sensitive mode or not.
 *
 * @author dac@eng.sun.com
 * @author James Todd [gonzo@eng.sun.com]
 * @author Costin Manolache
 */
public final class MessageBytes implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    // primary type ( whatever is set as original value )
    private int type = T_NULL;

    public static final int T_NULL = 0;
    /** getType() is T_STR if the the object used to create the MessageBytes
     was a String */
    public static final int T_STR  = 1;
    /** getType() is T_STR if the the object used to create the MessageBytes
     was a byte[] */
    public static final int T_BYTES = 2;
    /** getType() is T_STR if the the object used to create the MessageBytes
     was a char[] */
    public static final int T_CHARS = 3;

    private int hashCode=0;
    // did we compute the hashcode ?
    private boolean hasHashCode=false;

    // Internal objects to represent array + offset, and specific methods
//    private final ByteChunk byteC=new ByteChunk();
//    private final CharChunk charC=new CharChunk();

    // String
    private String strValue;
    // true if a String value was computed. Probably not needed,
    // strValue!=null is the same
    private boolean hasStrValue=false;

    /**
     * Creates a new, uninitialized MessageBytes object.
     * Use static newInstance() in order to allow
     *   future hooks.
     */
    private MessageBytes() {
    }

    /** Construct a new MessageBytes instance
     */
    public static MessageBytes newInstance() {
        return factory.newInstance();
    }

    // -------------------- Future may be different --------------------

    private static final MessageBytesFactory factory=new MessageBytesFactory();

    private static class MessageBytesFactory {
        protected MessageBytesFactory() {
        }
        public MessageBytes newInstance() {
            return new MessageBytes();
        }
    }

}
