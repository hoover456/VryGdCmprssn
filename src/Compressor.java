import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hoove on 4/14/2017.
 */
public class Compressor {
    private HashMap<Character, Character> substitutionMap;

    Compressor() {
        this.substitutionMap = new HashMap<>(126);
        substitutionMap.put('[', '(');
        substitutionMap.put(']', ')');
        substitutionMap.put('{', '(');
        substitutionMap.put('}', ')');
        substitutionMap.put('$', 's');
        substitutionMap.put('!', '.');
        substitutionMap.put('"', '\'');
        substitutionMap.put(';', ':');
        substitutionMap.put('a', '\0');
        substitutionMap.put('e', '\0');
        substitutionMap.put('i', '\0');
        substitutionMap.put('o', '\0');
        substitutionMap.put('u', '\0');
    }

    public byte[] compress(String input) {
        HashMap<Character, Byte> compressionMap = new HashMap<>(36);
        ArrayList<Byte> outList = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (this.substitutionMap.get('c')!=null)
                c = this.substitutionMap.get('c');
            if(compressionMap.size()==0 || (compressionMap.get('c')==null && compressionMap.size()<255)){
                compressionMap.put(c, (byte)compressionMap.size());
            }
            outList.add(new Byte(compressionMap.get(c)));
        }
        byte[] out = new byte[outList.size()];
        for (int i = 0; i < outList.size(); i++) {
            out[i]=outList.get(i);
        }
        return(out);
    }

    public String deCompress(byte[] data){

    }
}
