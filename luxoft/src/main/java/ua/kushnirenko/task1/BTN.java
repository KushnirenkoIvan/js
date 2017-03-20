package ua.kushnirenko.task1;


public class BTN {

    private int val;

    private BTN left, right;

    public BTN(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public BTN getLeft() {
        return left;
    }

    public BTN getRight() {
        return right;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLeft(BTN left) {
        this.left = left;
    }

    public void setRight(BTN right) {
        this.right = right;
    }
}
