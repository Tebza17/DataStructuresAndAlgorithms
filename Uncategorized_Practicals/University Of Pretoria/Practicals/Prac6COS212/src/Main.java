/**
 * Name: Teboho Mokoena
 * Student Number: u14415888
 */

public class Main {

    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie);

        trie.insert("apples");
        System.out.println(trie);

        trie.insert("api");
        System.out.println(trie);

        System.out.println(trie.contains("app"));
        System.out.println(trie.contains("api"));

        System.out.println(trie.closestMatch("app", 1));
        System.out.println(trie.closestMatch("a", 1));
        System.out.println(trie.closestMatch("aploe", 2));

        /** 
            Expected output:
            
            [false, NO_KEY, [a]][true, apple, []]
            [false, NO_KEY, [a]][false, NO_KEY, [p]][false, NO_KEY, [p]][false, NO_KEY, [l]][false, NO_KEY, [e]][true, apple, [s]][true, apples, []]
            [false, NO_KEY, [a]][false, NO_KEY, [p]][false, NO_KEY, [i, p]][true, api, []][false, NO_KEY, [l]][false, NO_KEY, [e]][true, apple, [s]][true, apples, []]
            false
            true
            api
            null
            apple
         */

    }
}