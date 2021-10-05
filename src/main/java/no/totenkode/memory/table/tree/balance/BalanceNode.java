package no.totenkode.memory.table.tree.balance;

public class BalanceNode {

    int key;
    int height;
    BalanceNode left;
    BalanceNode right;

    public BalanceNode(int key) {
        this.key = key;
    }
}
