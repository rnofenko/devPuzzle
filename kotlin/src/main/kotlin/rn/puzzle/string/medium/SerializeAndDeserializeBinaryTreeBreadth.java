package rn.puzzle.string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeAndDeserializeBinaryTreeBreadth {
    public String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        return builder.toString();
    }

    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) {
            return null;
        }
        String[] parts = data.split(",");
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        deserialize(Arrays.asList(root), parts, index + 1);
        return root;
    }

    private void deserialize(List<TreeNode> parents, String[] parts, int index) {
        if(index >= parts.length) {
            return;
        }
        ArrayList<TreeNode> children = new ArrayList<>();
        for (TreeNode parent : parents) {
            if(parent == null) {
                index += 2;
                continue;
            }
            String str = parts[index++];
            if(!str.isEmpty()) {
                parent.left = new TreeNode(Integer.parseInt(str));
            }
            str = parts[index++];
            if(!str.isEmpty()) {
                parent.right = new TreeNode(Integer.parseInt(str));
            }
            children.add(parent.left);
            children.add(parent.right);
        }
        deserialize(children, parts, index);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
