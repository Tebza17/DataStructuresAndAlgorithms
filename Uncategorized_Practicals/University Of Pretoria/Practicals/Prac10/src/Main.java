
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

public class Main {

    public static void main(String[] args) {
        DynamicHashMap map = new DynamicHashMap(3, 3.0);
        System.out.println("h(ABC) = " + map.hash("ABC"));

        System.out.println(map.toString());

        System.out.println("h(I) = " + map.hash("I"));
        map.put("I", 4);

        System.out.println("h(COS) = " + map.hash("COS"));
        map.put("COS", 216);
        System.out.println(map.toString());
        map.put("COS", 212);
        System.out.println(map.toString());

        System.out.println("h(KEY) = " + map.hash("KEY"));
        map.put("KEY", 50);
        System.out.println(map.toString());

        System.out.println("h(TREE) = " + map.hash("TREE"));
        map.put("TREE", 63);
        System.out.println(map.toString());

        map.put("THE", 1);
        map.put("FINAL", 2);
        map.put("COS212", 3);
        map.put("PRACTICAL", 4);
        map.put("THISSEMESTER", 5);
        // Before tSize doubled
        System.out.println(map.toString());

        map.put("YAY", 6);
        // After tSize doubled
        System.out.println(map.toString());

        System.out.println("h(COS) = " + map.hash("COS"));

        System.out.println(map.remove("COS"));

        System.out.println(map.toString());


        // Expected output:
        /**
        1000001 = A
                1000010 = B
        100000101000010 = AB
                1000011 = C
        100000100000001 = AB XOR C
        ------
        h(ABC) = 0
        [][][]
        h(I) = 1
        h(COS) = 2
        [][4][216]
        [][4][212]
        h(KEY) = 1
        [][4,50][212]
        h(TREE) = 1
        [][4,50,63][212]
        [][4,50,63,1,2,3,4][212,5]
        [][4,63,1,3,4][212,6][][50,2][5]
        h(COS) = 2
        212
        [][4,63,1,3,4][6][][50,2][5]
         */
//         A = 'A'; B = 'B';int C = 'C', D = 'D', E = 'E';
//        AB = A; int CD = C;
//        AB <<= 8; CD <<=8;
//        AB += B; CD += D;
//        System.out.println(Integer.toBinaryString(AB));
//        System.out.println(Integer.toBinaryString(CD));
//        System.out.println(Integer.toBinaryString(E));
//        System.out.println((AB^CD^E) % 19);
        
    }
    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
    
    public static int getP(String key, int i) {
        int sum = 0;
        for (int x = 0; x < key.length()/4; x++) {
                sum += blockToInt(key.substring(x*4, x*4+4),x);
        }
        return sum*i;
    }

    protected static long blockToInt(String block, int k) {
        int t = 0;
        for (int x = 0; x < block.length(); x++) {
                t += block.charAt(x) * Math.pow(8, x);
        }
        return t;
    }
}