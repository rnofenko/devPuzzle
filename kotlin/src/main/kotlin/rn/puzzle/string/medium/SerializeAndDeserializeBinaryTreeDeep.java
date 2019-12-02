package rn.puzzle.string.medium;

public class SerializeAndDeserializeBinaryTreeDeep {
    private final static String LEFT_IS_NULL = "";
    private final static String TERMINATION = "T";
    private final static String SEPARATOR = ",";

    public String serialize(Object obj) {
        if(obj == null) {
            return null;
        }

        TreeNode root = (TreeNode)obj;
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        serialize(builder, root);
        return builder.toString();
    }

    private int serialize(StringBuilder builder, TreeNode root) {
        if(root == null) {
            return 0;
        }

        int terminationsCount = 0;
        if(root.left == null) {
            if(root.right == null) {
                return 1;
            } else {
                builder.append(SEPARATOR).append(LEFT_IS_NULL);
            }
        } else {
            builder.append(SEPARATOR).append(root.left.val);
            terminationsCount = serialize(builder, root.left);
        }
        if(root.right == null) {
            terminationsCount++;
        } else {
            while (terminationsCount-- > 0) {
                builder.append(SEPARATOR).append(TERMINATION);
            }

            builder.append(SEPARATOR).append(root.right.val);
            return serialize(builder, root.right);
        }
        return terminationsCount;
    }

    public Object deserialize(String data) {
        if(data == null || data.isEmpty()) {
            return null;
        }
        String[] parts = data.split(SEPARATOR);
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(parts[index++]));
        deserialize(root, parts, index);
        return root;
    }

    private int deserialize(TreeNode parent, String[] parts, int index) {
        if(index >= parts.length) {
            return index;
        }
        String str = parts[index++];
        if(str.equals(TERMINATION)) {
            return index;
        }
        if(!str.equals(LEFT_IS_NULL)) {
            parent.left = new TreeNode(Integer.parseInt(str));
            index = deserialize(parent.left, parts, index);
        }

        if(index >= parts.length) {
            return index;
        }
        str = parts[index++];
        if(str.equals(TERMINATION)) {
            return index;
        }
        parent.right = new TreeNode(Integer.parseInt(str));
        return deserialize(parent.right, parts, index);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
