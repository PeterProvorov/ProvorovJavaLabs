package encryptionStream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ThinkPad on 24.10.2016.
 */
public class DeltaEncryptionOutputStream extends OutputStream {
    private OutputStream OutputS;
    private int prevCode = 0;

    public DeltaEncryptionOutputStream(OutputStream outputStream) {
        OutputS = outputStream;
    }

    @Override
    public void write(int code) throws IOException {
        OutputS.write(code + prevCode);
        prevCode += code;
    }

    @Override
    public void write(byte arr[]) throws IOException {
        write(arr, 0, arr.length);
    }

    @Override
    public void write(byte arr[], int off, int length) throws IOException {
        if (null == arr) {
            throw new NullPointerException();
        } else if (off < 0 || off > arr.length || length < 0 || length > arr.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (0 == length) {
            return;
        }

        for (int i = 0; i < length; i++) {
            write(arr[off + i]);
        }
    }

    @Override
    public void close() throws IOException {
        OutputS.close();
    }

    @Override
    public void flush() throws IOException {
        OutputS.flush();
    }
}