package DataStructures.Tree;

class BTree {
    TreeNode root;

    public TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public void insertData(int data) {
        root = insert(root, data);
    }


    public void invertTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.right);
        invertTree(root.left);

    }

    public void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(STR."\{root.data} ");
            inorderTraversal(root.right);
        }
    }

    public int heightOfBtree(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = heightOfBtree(root.left);
        int rightHeight = heightOfBtree(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int checkBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = checkBalance(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = checkBalance(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean BalanceTree(TreeNode root) {

        return checkBalance(root) != -1;
    }


    public void preorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(STR."\{root.data} ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }

    public void postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(STR."\{root.data} ");
        }
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.data < root.data && q.data < root.data) {
            return LCA(root.left, p, q);
        } else if (p.data > root.data && q.data > root.data) {
            return LCA(root.right, p, q);
        } else {
            return root;
        }
    }

    public static void main() {
        BTree tree = new BTree();
        tree.insertData(60);
        tree.insertData(80);
        tree.insertData(10);
        tree.insertData(40);
        tree.insertData(70);
        tree.insertData(90);
        tree.insertData(30);
        tree.insertData(50);
        System.out.println("Inorder Traversal:");
        tree.inorderTraversal(tree.root);
        System.out.println();
        System.out.println("PreOrder Traversal:");
        tree.preorderTraversal(tree.root);
        System.out.println();
        System.out.println("Postorder Traversal:");
        tree.postorderTraversal(tree.root);
        System.out.println();
        System.out.println(STR."Height of Tree is \{tree.heightOfBtree(tree.root)}");
        tree.invertTree(tree.root);
        System.out.println();
        tree.inorderTraversal(tree.root);
        System.out.println();
        System.out.println(tree.LCA(tree.root, tree.root.left, tree.root.right).data);
        System.out.println();
        System.out.println(tree.BalanceTree(tree.root));
    }

}