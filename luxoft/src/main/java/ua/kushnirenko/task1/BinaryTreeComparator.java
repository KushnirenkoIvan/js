package ua.kushnirenko.task1;


public class BinaryTreeComparator {

    /**
     * Compares two trees with root elements root1 and root2 respectively
     *
     * @param root1 - the root node of the first tree
     * @param root2 - the root node of the second tree
     * @return true - if two trees with root elements root1 and root2 are fully equal
     */
    public static boolean compareTrees(BTN root1, BTN root2) {
        if (root1 == null && root2 == null) return true;

        if ((root1 != null) != (root2 != null) || (root1.getVal() != root2.getVal())) return false;

        return compareTrees(root1.getLeft(), root2.getLeft()) && compareTrees(root1.getRight(), root2.getRight());
    }
}
