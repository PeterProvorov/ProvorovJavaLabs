package encryptionStream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ThinkPad on 24.10.2016.
 */
public class deltaEncryptionOutputStream extends OutputStream{
    private OutputStream OutputS;
    private int prev = 0;

    public deltaEncryptionOutputStream(OutputStream outputStream) {
        OutputS = outputStream;
    }

    @Override
    public void write(int c) throws IOException {
        OutputS.write(c + prev);
        prev += c;
    }

    @Override
    public void write(byte arr[]) throws IOException {
        OutputS.write(arr, 0, arr.length);
    }

    @Override
    public void write(byte arr[], int off, int length) throws IOException {
        if(null == arr) {
            throw new NullPointerException();
        } else if (off < 0 || off > arr.length || length < 0 || length > arr.length - off) {
            throw  new IndexOutOfBoundsException();
        } else if (0 == length) {
            return;
        }

        for (int i = 0; i < length; i++) {
            OutputS.write(arr[off + i]);
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