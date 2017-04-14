import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

/**
 * Created by Andrew on 4/13/2017.
 */
public class FileIO {

    public static void compressedWrite(byte[] data, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath + ".vgc");
        byte[] outData = new byte[data.length + 2];
        outData[0] = 0x7E;
        int i = 1;
        for (byte b : data) {
            outData[i++] = b;
        }
        outData[outData.length-1] = 0x7F;
        fos.write(outData);
        fos.close();
    }

    public static void compressedRead(String filePath) throws IOException{
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] inData = new byte[(int)file.length()];
        fis.read(inData);
        fis.close();
        if(inData == null)
            throw new IOException("No Data found in file");
        for (byte b : inData) {
            System.out.println(Integer.toBinaryString(b));
        }
    }

    public static void  main(String[] args) throws IOException{
        byte[] test = new byte[]{0x1,0x2,0x3,0x4,0x5,0x6,0x7,0x8};
        for (byte b : test) {
            System.out.println(Integer.toBinaryString(b));
        }
        System.out.print("=====\n=====\n");
        compressedWrite(test, "test");
        compressedRead("test.vgc");
    }
}
