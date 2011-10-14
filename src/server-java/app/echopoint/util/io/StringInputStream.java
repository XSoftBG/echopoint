package echopoint.util.io;

import java.io.InputStream;

/**
 * <code>StringInputStream</code> can read a String and return it as an
 * <code>InputStream.</code>
 */

public class StringInputStream extends InputStream {
	private String buffer;

	private int pos;

	private int count;

	/**
	 * Constructs a <code>StringInputStream</code> from the specified
	 * <code>String</code>
	 *
	 * @param s - the string to read
	 */
	public StringInputStream(String s) {
		this.buffer = s;
		count = s.length();
	}

	/**
	 * @see java.io.InputStream#read()
	 */
	public synchronized int read() {
		return (pos < count) ? (buffer.charAt(pos++) & 0xFF) : -1;
	}

	/**
	 * @see java.io.InputStream#read(byte[], int, int)
	 */
	public synchronized int read(byte b[], int off, int len) {
		if (b == null) {
			throw new NullPointerException();
		} else if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
			throw new IndexOutOfBoundsException();
		}
		if (pos >= count) {
			return -1;
		}
		if (pos + len > count) {
			len = count - pos;
		}
		if (len <= 0) {
			return 0;
		}
		String s = buffer;
		int cnt = len;
		while (--cnt >= 0) {
			b[off++] = (byte) s.charAt(pos++);
		}

		return len;
	}

	/**
	 * @see java.io.InputStream#skip(long)
	 */
	public synchronized long skip(long n) {
		if (n < 0) {
			return 0;
		}
		if (n > count - pos) {
			n = count - pos;
		}
		pos += n;
		return n;
	}

	/**
	 * @see java.io.InputStream#available()
	 */
	public synchronized int available() {
		return count - pos;
	}

	/**
	 * @see java.io.InputStream#reset()
	 */
	public synchronized void reset() {
		pos = 0;
	}
}

