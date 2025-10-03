/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "#";
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        StringBuilder s = new StringBuilder();
        s.append(Integer.toString(root.val));
        s.append(",");
        while(!q.isEmpty())
        {
            int n = q.size();
            for(int i=0;i<n;i++)
            {
                TreeNode node = q.poll();
                if(node.left == null)
                {
                    s.append("#,");
                }
                else if(node.left != null)
                {
                    s.append(Integer.toString(node.left.val));
                    s.append(',');
                    q.add(node.left);
                }
                if(node.right == null)
                {
                    s.append("#,");
                }
                else if(node.right != null)
                {
                    s.append(Integer.toString(node.right.val));
                    s.append(',');
                    q.add(node.right);
                }
            }
        }
        System.out.println(s);
        return s.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int n = data.length();
        if(data.charAt(0) == '#') return null;
        String[] tokens = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;

        while (!q.isEmpty() && i < tokens.length) {
            TreeNode curr = q.poll();

            // left child
            if (!tokens[i].equals("#")) {
                curr.left = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(curr.left);
            }
            i++;

            // right child
            if (i < tokens.length && !tokens[i].equals("#")) {
                curr.right = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
