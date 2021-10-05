package no.totenkode.memory.table.tree;

import java.util.Random;

public class TreeTest {

    private static final int million = 1000000;

    private static void test() {
        final int[] data = new int[million * 10];
        long time;
        time = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < million * 10; i++) {
            data[i] = random.nextInt();
        }
        System.out.println("insert: " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for (int i = 0; i < million * 10; i++) {
            if(data[i] == 10000) {
                System.out.println(i);
            }
        }
        System.out.println("search: " + (System.currentTimeMillis() - time));
    }

    public static void main(String[] args) {

        test();
        test();
        test();
        test();

//        final BinarySearchTree tree = new BinarySearchTree();
//
//        tree.insert(1, 11);
//        tree.insert(1, 12);
//        tree.insert(1, 13);
//        tree.insert(2, 22);
//        tree.insert(2, 23);
//        tree.insert(2, 24);
//
//        tree.print();
//        System.out.println("search: " + tree.search(2));
//
//        System.out.println("remove");
//        tree.delete(1, 11);
//
//        tree.print();
//
//        System.out.println("insert");
//        tree.insert(1, 11);
//        tree.print();
//        tree.printReverse();
    }
}
