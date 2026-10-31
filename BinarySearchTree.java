public class BinarySearchTree<E extends Comparable<E>>
{
    TreeNode<E> root; // the root of the tree

    /**
     * constructor create a empty binary search tree by setting root to be null
     */
    public BinarySearchTree(){
        root = null;
    }


    public TreeNode<E> search(E data){
        TreeNode<E> current = root;
        while(current != null){
            int cmp = data.compareTo(current.getData());
            if(cmp == 0)
                return current;
            else if(cmp < 0)
                current = current.getLeft();
            else
                current = current.getRight();
        }
        return null;
    }


    public void insert(TreeNode<E> newNode){
        if(newNode == null)
            return;
        // the node is inserted as a single new leaf
        newNode.setParent(null);
        newNode.setLeft(null);
        newNode.setRight(null);

        if(root == null){
            root = newNode;
            return;
        }
        TreeNode<E> current = root;
        while(true){
            if(newNode.getData().compareTo(current.getData()) < 0){
                if(current.getLeft() == null){
                    current.setLeft(newNode);
                    newNode.setParent(current);
                    return;
                }
                current = current.getLeft();
            }
            else{
                // equal data goes to the right subtree
                if(current.getRight() == null){
                    current.setRight(newNode);
                    newNode.setParent(current);
                    return;
                }
                current = current.getRight();
            }
        }
    }

    /**
     * insert given data to this binary search tree. If this tree
     * was empty, the given node becomes the root of this tree.
     * @param data the given data to be inserted
     */
    public void insert(E data){
        insert(new TreeNode<E>(data));
    }

    /**
     * remove the given data from this binary search tree and return
     * true. If the data is not in the tree, return false
     */
    
    public boolean remove(E data){
        TreeNode<E> target = search(data);
        if(target == null)
            return false;

        // two children: copy the in-order successor's data here,
        // then remove the successor node instead (it has no left child)
        
        if(target.getLeft() != null && target.getRight() != null){
            TreeNode<E> successor = target.getRight();
            while(successor.getLeft() != null)
                successor = successor.getLeft();
            target.setData(successor.getData());
            target = successor;
        }

        // now target has at most one child
        
        TreeNode<E> child = (target.getLeft() != null) ? target.getLeft() : target.getRight();
        replaceNode(target, child);
        return true;
    }

    /**
     * put child (may be null) in the place of node under node's parent
     */
    private void replaceNode(TreeNode<E> node, TreeNode<E> child){
        TreeNode<E> parent = node.getParent();
        if(parent == null)
            root = child;
        else if(node.isLeftChild())
            parent.setLeft(child);
        else
            parent.setRight(child);
        if(child != null)
            child.setParent(parent);
        node.setParent(null);
        node.setLeft(null);
        node.setRight(null);
    }

    /**
     * return a string representation of the tree
     * @return a String representation of the tree
     */
    
    public String toString(){
        return "(" + nodeTravesal(root) + ")";
    }

    private String nodeTravesal(TreeNode<E> treeNode)
    {
        if(treeNode == null)
            return "-";
        return treeNode.getData().toString()
               + "(" + nodeTravesal(treeNode.getLeft())
               + ", " + nodeTravesal(treeNode.getRight()) + ")";
    }

    /**
     * return true if the tree is empty. False otherwise
     * @return true if the tree is empty; false otherwise
     */
    
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * return the height of the tree. Notice the height is defined as
     * the length of the longest path from nodes to root
     * @return the height of the tree
     */
    
    public int height(){
        return height(root);
    }

    // length (in edges) of the longest path down from the node; empty tree is -1
    
    private int height(TreeNode<E> node){
        if(node == null)
            return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * return the number of nodes in the tree
     * @return the number of nodes in this tree
     */
    
    public int size(){
        return size(root);
    }

    private int size(TreeNode<E> node){
        if(node == null)
            return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
}
