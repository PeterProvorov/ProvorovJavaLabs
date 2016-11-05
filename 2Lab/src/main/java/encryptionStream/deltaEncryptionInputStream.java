package encryptionStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ThinkPad on 23.10.2016.
 */
public class deltaEncryptionInputStream extends InputStream {
    private InputStream inputS;
    private int prev = 0;
    public static final int END_OF_STREAM = 256;

    public deltaEncryptionInputStream(InputStream inputStream) {
        inputS = inputStream;
    }

    @Override
    public int read() throws IOException {
        int cur = inputS.read();
        if(-1 == cur)
            return END_OF_STREAM;

        int newCode = cur - prev;
        prev = cur;
        return newCode;
    }

    @Override
    public int read(byte arr[]) throws IOException {
        return this.read(arr, 0, arr.length);
    }

    @Override
    public int read(byte arr[], int off, int length) throws IOException {
        if(null == arr) {
            throw new NullPointerException();
        } else if (off < 0 || off > arr.length || length < 0 || length > arr.length - off) {
            throw  new IndexOutOfBoundsException();
        } else if (0 == length) {
            return 0;
        }
        int cur = read();
        if(END_OF_STREAM == cur) {
            return -1;
        }
        arr[off] = (byte)cur;
        int i = 1;
        for(; i < length; i++) {
            cur = read();
            if(END_OF_STREAM == cur) {
                break;
            }
            arr[off + i] = (byte)cur;
        }

        return i;
    }

    @Override
    public void close() throws IOException {
        inputS.close();
    }
}
