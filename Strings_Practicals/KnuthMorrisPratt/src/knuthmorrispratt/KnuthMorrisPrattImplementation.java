package knuthmorrispratt;
import java.util.ArrayList;
import java.util.List;
/**
 * @description implements KMP algorithm for finding length of maximal prefix-suffix for each prefix of the string.
 * @author Vincent
 */
public class KnuthMorrisPrattImplementation {
    public KnuthMorrisPrattImplementation() {}
    public static List<Integer> getPrefSufTable(String text) {

        final List<Integer> prefSufTable = new ArrayList<Integer>();
        final char[] chars = text.toCharArray();

        if (text.length() == 0) 
            return prefSufTable;

        prefSufTable.add(0);

        for (int i = 1; i<chars.length; i++) {
            int sizeOfPrefSuf = prefSufTable.get(i-1);
            while (sizeOfPrefSuf > 0 && (chars[i] != chars[sizeOfPrefSuf]))
                sizeOfPrefSuf = prefSufTable.get(sizeOfPrefSuf-1); // because string is 0-indexed

            // if characters at this positions are different then sizeOfPrefSuf is equal to zero,
            // so there is no proper prefix-suffix
            if (chars[i] == chars[sizeOfPrefSuf]) {
                prefSufTable.add(sizeOfPrefSuf+1);
            } else {
                prefSufTable.add(0);
            }
        }
        return prefSufTable;
    }
    
}
