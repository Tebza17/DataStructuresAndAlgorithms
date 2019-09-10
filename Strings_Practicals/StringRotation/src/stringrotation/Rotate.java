package stringrotation;

/**
 * a string s = uv is said to be a rotation of t if t = vu.
 * @author Vincent
 */
public class Rotate {
    private static char charAt(String text, int pos) {
        pos = pos % text.length();
        return text.charAt(pos);
    }

    private static int compare(char a, char b, boolean greater) {
        if (a == b) 
            return 0;
        return (a < b) ^ greater ? -1 : 1;
    }

    private static String bestRotation(String text, boolean greatest) {
        if (text.length() < 2)
            return text;

        final int n = text.length() * 2;
        int k = 0;
        int i = 0, j = 1;
        while (i + k < n && j + k < n) {
            final char a = charAt(text, i+k);
            final char b = charAt(text, j+k);
            final int comp = compare(a, b, greatest);
            if (comp == 0) {
                k++;
            } else if (comp > 0) {
                i += k+1;
                if (i <= j )
                    i = j + 1;
                k = 0;
            } else {
                j += k+1;
                if (j <= i)
                    j = i + 1;
                k = 0;
            }
        }
        final int pos = i < j ? i : j;
        return text.substring(pos) + text.substring(0, pos);
    }

    /**
     * Finds lexicographically minimal string rotation.
     * Lexicographically minimal string rotation is a rotation of a string possessing the
     * lowest lexicographical order of all such rotations.
     * Finding the lexicographically minimal rotation is useful as a way of normalizing strings.
     * @return lexicographicall minimal rotation of text
     */
    public static String getLexicographicallyMinimalRotation(String text) {
        return bestRotation(text, false);
    }

    /**
     * Finds lexicographically maximal string rotation.
     * Lexicographically maximal string rotation is a rotation of a string possessing the
     * highest lexicographical order of all such rotations.
     * Finding the lexicographically maximal rotation is useful as a way of normalizing strings.
     * @return lexicographicall minimal rotation of text
     */
    public static String getLexicographicallyMaximalRotation(String text) {
        return bestRotation(text, true);
    }
}
