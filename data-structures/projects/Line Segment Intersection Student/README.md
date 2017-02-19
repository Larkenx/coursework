Steven Myers
Username: stelmyer

This is a late submission - I uploaded it after the due date since my original submission was incomplete.
**BSTNode**
My BSTNode uses the following fields:

    BSTNode<Key> right;
    BSTNode<Key> left;
    BSTNode<Key> parent;
    Key key;

I used the parent pointers to grab the after and before. The insert and search functions are just like the ones
we implemented in our last lab where we were doing BST's. The delete function grabs the inorder successor and replaces
the deleted node with that successor.

**AVLNode**
My AVLNode is a little different. It uses these fields:

    AVLNode<Key> right, left, parent;
    Key key;
    int height;

It holds the height of the current node, and is populated during the insert function.

**Rotation Functions**
I have defined four rotation functions:
RR, LL, LR, RL

RL and LR (double rotations) simply reuse LL and RR.

**AVL Insert**
For AVL insert, as we traverse the tree, we check the Balance Factor by comparing
the difference between the height of the left of the current node and the right of the current node.

If the current node has a bf == 2, then we perform a rotation based on the insertion on the left or right child.
At the end of our insert function, we fix up our heights by grabbing the max of the heights of the children of the
current node.

**AVL Delete**
My AVL delete function performs a normal BST Delete by grabbing the in-order successor, and setting the deleted node to
be the in-order sucessor, then recurses on the right of the deleted node, deleting the min.

But, after we do the regular AVL deletion, we go through the current node and check if its BF is 2, then we check
the bf of its children and perform the proper rotations based on its bf and its childrens' bf's.

**Graph Result**
I have included a picture of the program running. I am not certain that it works as intended, but I was able to get two of lines
that were intersecting lit up.


