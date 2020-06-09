public class SerDeBinaryTree {

    public static void main(String[] args) {
        SerDeBinaryTree codec = new SerDeBinaryTree();
        TreeNode root = codec.deserialize("1,2,null,null,3,4,null,null,5,null,null");
        printTree(root);
        System.out.println(codec.serialize(root));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.toString();
    }

    // "1,2,null,null,3,4,null,null,5,null,null"
    public void serializeHelper(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(root.val);
            sb.append(",");
            serializeHelper(sb, root.left);
            serializeHelper(sb, root.right);
        }
    }

    // Decodes your encoded data to tree.
    int index = 0;

    public TreeNode deserialize(String data) {
        String[] splitStrings = data.split(",");
        TreeNode root = null;
        root = deserializeHelper(splitStrings);

        return root;
    }

    public TreeNode deserializeHelper(String[] splitStrings) {
        while (index < splitStrings.length) {
            String nextString = splitStrings[index++];
            if (nextString.equals("null")) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(nextString));
                node.left = deserializeHelper(splitStrings);
                node.right = deserializeHelper(splitStrings);

                return node;
            }
        }

        return null;

    }

    public static void printTree(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            printTree(root.left);
            printTree(root.right);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public String toString() {
        return String.valueOf(val);
    }
}
