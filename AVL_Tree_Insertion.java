/*


Given an AVL tree and N values to be inserted in the tree. Write a function to insert elements into the given AVL tree.

Note:
The tree will be checked after each insertion. 
If it violates the properties of balanced BST, an error message will be printed followed by the inorder traversal of the tree at that moment.
If instead all insertions are successful, inorder traversal of the tree will be printed.

Example 1:

Input:
N = 3
Values to be inserted = {5,1,4} 
Output:
1 4 5
Explanation:
Value to be inserted = 5
    5
Value to be inserted = 1
    5
   /
  1
Value to be inserted = 4
  5                     4
 /    LR rotation        /  \
1    ----------->       1   5
 \
 4
Therefore the inorder of the final tree will be 1, 4, 5.
Example 2:

Input:
N = 7
Values to be inserted = {21,26,30,9,4,14,28} 
Output:
4 9 14 21 26 28 30
Explanation:
Value to be inserted = 21
    21
Value to be inserted = 26
    21
     \
     26
Value to be inserted = 30
  21                        26
   \      LL rotation         /  \
   26    ----------->       21  30
    \
     30
Value to be inserted = 9
    26
   /  \
  21  30
 /
9
Value to be inserted = 4
      26                          26
     /  \                          /  \
    21  30                      9   30
   /          RR rotation        /  \
  9          ----------->       4  21
 /
4
Value to be inserted = 14
      26                          21
     /  \                          /  \
    9   30                      9   26
   / \          LR rotation      /  \    \
  4  21        ----------->    4  14  30
     /
    14
Value to be inserted = 28
      21                          21
     /  \                          /  \
    9   26                      9   28
   / \    \     RL rotation       / \    / \
  4  14   30   ----------->   4  14 26 30
          /
         28
Therefore the inorder of the final tree will be 4, 9, 14, 21, 26, 28, 30.
Your Task:  
You don't need to read input or print anything. Complete the function insertToAVL() which takes the root of the tree and the value of the node to be inserted as input parameters and returns the root of the modified tree.

Expected Time Complexity: O(log N)
Expected Auxiliary Space: O(height of tree)

Constraints:
1 ≤ N ≤ 2000
*/




class Solution {
    public Node insertToAVL(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (node.data > data) {
            node.left = insertToAVL(node.left, data);
        } else if (node.data < data) {
            node.right = insertToAVL(node.right, data);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int diff = height(node.left) - height(node.right);
        if (diff > 1) { // Left subtree is higher
            if (data < node.left.data) {
                return rightRotate(node);
            } else if (data > node.left.data) { // LR rotation
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        } else if (diff < -1) { // Right subtree is higher
            if (data < node.right.data) { // RL rotation
                node.right = rightRotate(node.right);
                return leftRotate(node);
            } else if (data > node.right.data) {
                return leftRotate(node);
            }
        }
        return node;
    }

    public Node leftRotate(Node a) {
        Node b = a.right;
        Node t2 = b.left;
        b.left = a;
        a.right = t2;
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;
        return b;
    }

    public Node rightRotate(Node a) {
        Node b = a.left;
        Node t2 = b.right;
        b.right = a;
        a.left = t2;
        a.height = Math.max(height(a.left), height(a.right)) + 1;
        b.height = Math.max(height(b.left), height(b.right)) + 1;
        return b;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }
}
