package encryptionStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ThinkPad on 24.10.2016.
 */
public interface bufferedReader {
    int BUFFER_SIZE = 100;

    static void rewrite(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
        int bufSize;
        while ((bufSize = inputStream.read(buf, 0, buf.length)) != -1) {
            outputStream.write(buf, 0, bufSize);
        }
    }
}
