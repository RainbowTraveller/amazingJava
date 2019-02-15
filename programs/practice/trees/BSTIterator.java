/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

	//Keeping track of next element
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        add(root);
    }

	//Push all left subtree on the stack
	//the least element become stack top
	//which can be returned as next element
    private void add(TreeNode node) {
        while(node != null) {
            stack.push( node );
            node = node.left;
        }
    }
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        int val = 0;
        if( stack.peek() != null) {
            TreeNode curr = stack.pop();
			//next least element will be leftmost
			//element of right child, so ....
            add(curr.right);
            val = curr.val;
        }
        return val;
    }
}

