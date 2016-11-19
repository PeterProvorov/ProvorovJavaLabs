package encryptionStream;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by ThinkPad on 18.11.2016.
 */
public class deltaEncryptionTest {

    private void codeAndDecodeByteArray(byte[] inputByteArray) throws Exception {
        ByteArrayOutputStream outputByteStream;
        try (InputStream inputStream = new deltaEncryptionInputStream(new ByteArrayInputStream(inputByteArray));
             OutputStream outputStream = new deltaEncryptionOutputStream(outputByteStream = new ByteArrayOutputStream())) {
            int code;
            while ((code = inputStream.read()) != deltaEncryptionInputStream.END_OF_STREAM) {
                outputStream.write(code);
            }

            outputStream.flush();

            assertTrue(Arrays.equals(inputByteArray, outputByteStream.toByteArray()));
        }
    }

    private void bufferedCodeAndDecodeByteArray(byte[] inputByteArray, int bufLength) throws Exception {
        ByteArrayOutputStream outputByteStream;
        try (InputStream inputStream = new deltaEncryptionInputStream(new ByteArrayInputStream(inputByteArray));
             OutputStream outputStream = new deltaEncryptionOutputStream(outputByteStream = new ByteArrayOutputStream())) {
            int bufSize = (bufLength < inputByteArray.length) ? bufLength : inputByteArray.length;
            byte[] codeBuf = new byte[bufSize];
            while ((bufSize = inputStream.read(codeBuf, 0, codeBuf.length)) != -1) {
                outputStream.write(codeBuf, 0, bufSize);
            }

            outputStream.flush();

            assertTrue(Arrays.equals(inputByteArray, outputByteStream.toByteArray()));
        }
    }

    @Test
    public void emptyByteArray() throws Exception {
        codeAndDecodeByteArray(new byte[]{});
    }

    @Test
    public void orderedByteArray() throws Exception {
        codeAndDecodeByteArray(new byte[]{1, 2, 3, 4, 5});
    }

    @Test
    public void evenByteArray() throws Exception {
        codeAndDecodeByteArray(new byte[]{29, 12, 3, 41, 15, 6});
    }

    @Test
    public void unevenByteArray() throws Exception {
        codeAndDecodeByteArray(new byte[]{29, -12, -3, 41, -15, 6, 0});
    }

    @Test
    public void negativeByteArray() throws Exception {
        codeAndDecodeByteArray(new byte[]{-29, -12, -3, -41, -15, -6});
    }

    @Test
    public void regularByteArray() throws Exception {
        byte[] array = new byte[1024];
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        codeAndDecodeByteArray(array);
    }

    @Test
    public void repeatedByteArrays() throws Exception {
        byte[] array = new byte[]{-29, -12, -3, -41, -15, -6};
        codeAndDecodeByteArray(array);
        codeAndDecodeByteArray(array);
        codeAndDecodeByteArray(array);
    }

    @Test
    public void severalRandomByteArrays() throws Exception {
        Random rand = new Random();
        byte[] array = new byte[1024];

        for (int loop = 0; loop < 2048; loop++) {
            for (int i = 0; i < array.length; i++) {
                array[i] = (byte) rand.nextInt();
            }
            codeAndDecodeByteArray(array);
        }
    }

    @Test
    public void bufferedEncryptionByteArrays() throws Exception {
        bufferedCodeAndDecodeByteArray(new byte[]{1, 2, 3, 4, 5}, 3);
    }

    @Test
    public void bufferedRandomByteArrays() throws Exception {
        Random rand = new Random();
        byte[] array = new byte[1024];

        for (int loop = 0; loop < 2048; loop++) {
            for (int i = 0; i < array.length; i++) {
                array[i] = (byte) rand.nextInt();
            }

            int bufLength = (loop % array.length) + 1;
            bufferedCodeAndDecodeByteArray(array, bufLength);
        }
    }
}