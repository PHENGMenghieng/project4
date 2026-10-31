
public class TreeNode<E extends Comparable<E>>
{
    E data;
    TreeNode<E> parent;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode()
    {
        data = null;
        parent = null;
        left = null;
        right = null;
    }

    public TreeNode(E data)
    {
        this.data = data;
        parent = null;
        left = null;
        right = null;
    }


    public void setData(E data)
    {
        this.data = data;
    }


    public E getData()
    {
        return data;
    }


    public void setParent(TreeNode<E> parent)
    {
        this.parent = parent;
    }

   
    public TreeNode<E> getParent()
    {
        return parent;
    }

    /**
     * set this node's left child node as given node
     * @param left The given node
     */
    public void setLeft(TreeNode<E> left)
    {
        this.left = left;
    }

    public TreeNode<E> getLeft()
    {
        return left;
    }


    public void setRight(TreeNode<E> right)
    {
        this.right = right;
    }


    public TreeNode<E> getRight()
    {
        return right;
    }

    public boolean isLeftChild(){
        return parent != null && parent.left == this;
    }


    public boolean isRightChild(){
        return parent != null && parent.right == this;
    }

  
    public boolean isLeaf(){
        return left == null && right == null;
    }


    public boolean isRoot(){
        return parent == null;
    }
}
