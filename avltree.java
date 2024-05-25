//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.company;

public class AVLTree {
    TreeNode root;

    public AVLTree() {
    }

    private int height(TreeNode root) {
        return root == null ? -1 : Math.max(this.height(root.left), this.height(root.right)) + 1;
    }

    private int heightRL(TreeNode root) {
        return root == null ? -1 : this.height(root.left) - this.height(root.right);
    }

    public void insert(int item) {
        this.root = this.insert(this.root, item);
    }

    private TreeNode insert(TreeNode root, int item) {
        if (root == null) {
            root = this.createNewNode(item);
            return root;
        } else {
            if (item < root.item) {
                root.left = this.insert(root.left, item);
            } else if (item > root.item) {
                root.right = this.insert(root.right, item);
            }

            int h = this.heightRL(root);
            int hl = this.heightRL(root.left);
            int hr = this.heightRL(root.right);
            if (h == -2 && hr == -1) {
                return this.rightRot(root);
            } else if (h == 2 && hl == 1) {
                return this.leftRot(root);
            } else if (h == -2 && hr == 1) {
                return this.rightLeftRot(root);
            } else {
                return h == 2 && hl == -1 ? this.leftRightRot(root) : root;
            }
        }
    }

    public void delete(int item) {
        this.root = this.delete(this.root, item);
    }

    private TreeNode delete(TreeNode root, int item) {
        if (root == null) {
            return null;
        } else {
            if (item < root.item) {
                root.left = this.delete(root.left, item);
            } else if (item > root.item) {
                root.right = this.delete(root.right, item);
            } else {
                if (root.left == null && root.right == null) {
                    return null;
                }

                if (root.left == null) {
                    return root.right;
                }

                if (root.right == null) {
                    return root.left;
                }

                TreeNode min = this.findMinNode(root.right);
                root.item = min.item;
                root.right = this.delete(root.right, min.item);
            }

            int h = this.height(root.left) - this.height(root.right);
            int hl = this.heightRL(root.left);
            int hr = this.heightRL(root.right);
            if (h == -2 && hr == -1) {
                return this.rightRot(root);
            } else if (h == 2 && hl == 1) {
                return this.leftRot(root);
            } else if (h == -2 && hr == 1) {
                return this.rightLeftRot(root);
            } else {
                return h == 2 && hl == -1 ? this.leftRightRot(root) : root;
            }
        }
    }

    private TreeNode findMinNode(TreeNode root) {
        TreeNode temp;
        for(temp = root; temp.left != null; temp = temp.left) {
        }

        return temp;
    }

    private TreeNode leftRightRot(TreeNode root) {
        TreeNode middleNode = root.left;
        TreeNode newRoot = root.left.right;
        root.left = newRoot.right;
        middleNode.right = newRoot.left;
        newRoot.right = root;
        newRoot.left = middleNode;
        return newRoot;
    }

    private TreeNode rightLeftRot(TreeNode root) {
        TreeNode middleNode = root.right;
        TreeNode newRoot = root.right.left;
        root.right = newRoot.left;
        middleNode.left = newRoot.right;
        newRoot.left = root;
        newRoot.right = middleNode;
        return newRoot;
    }

    private TreeNode rightRot(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    private TreeNode leftRot(TreeNode root) {
        TreeNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        return newRoot;
    }

    private TreeNode createNewNode(int item) {
        TreeNode treeNode = new TreeNode();
        treeNode.item = item;
        treeNode.left = treeNode.right = null;
        return treeNode;
    }

    public boolean search(TreeNode root, int item) {
        if (root == null) {
            return false;
        } else if (root.item == item) {
            return true;
        } else {
            return item < root.item ? this.search(root.left, item) : this.search(root.right, item);
        }
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            this.inOrder(root.left);
            System.out.print(root.item + "\t");
            this.inOrder(root.right);
        }
    }

    public void printLeafNodes(TreeNode root) {
        if (root != null) {
            if (root != null && root.left == null && root.right == null) {
                System.out.println(root.item);
            }

            this.printLeafNodes(root.left);
            this.printLeafNodes(root.right);
        }
    }
}
