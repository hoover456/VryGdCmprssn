import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

/**
 * Created by Andrew on 4/13/2017.
 */
public class FileIO {

    public static void compressedWrite(BitSet data, String filePath) throws IOException{
        FileOutputStream fos = new FileOutputStream(filePath+".vgc");
        byte[] outData = data.toByteArray();
        fos.write(outData);
        fos.close();
    }

    public static BitSet compressedRead(String filePath) throws IOException{
        FileInputStream fis = new FileInputStream(filePath);
        byte[] inData = null;
        fis.read(inData);
        if(inData == null)
            throw new IOException("No Data found in file");
        return null;
    }
}
