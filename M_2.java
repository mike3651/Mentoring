/**
 * This file doesn't compile, it was coded with codepad and explained 
 * via phone/skype
 *
 * @author Michael Wilson
 * @version 1.0 */
public TNode<T> LCA(TNode<T> root, final TNode<T> a, T<Node<T> b)  {
    // tree is empty
    if(root == null) {
       return null;
   }
    // deal with case where one root is the lowest common ancestor
    if(root == a || root == b) {
        return root;
    }
    
    // get the proper LCA's for the left and the right subtrees
    TNode<T> left = LCA(root.left, a, b);
    TNode<T> right = LCA(root.right, a, b);
    
    if(left == null && right == null) {
        return null;
    }
    if(left != null && right != null) {
        return root;
    } 
       
    return left == null ? right: left;
}

// replace each node in the tree with a summation of it's children
public void summation(TNode<T> root) {
    if(root == null) {
        return 0;        
    }
    
    oldData = root.data;
    root.data = summation(root.left) + summation(root.right);
    return oldData + root.data;
}

// given a sorted array make a binary tree from it
public TNode<T> createTree(int[] a) {
    return createTree(root, a, 0, a.length - 1);
}

private TNode<T> createTree(TNode<T> root, int[] a, int start, int end) {
    if(start <= end) {
        int midPoint = (start + end)/2;
        TNode<T> temp = new TNode(a[mid]);
        temp.left = createTree(root.left, a, start, mid - 1);
        temp.right = createTree(root.right, a, mid + 1, end);
        return temp;
    } return null;
}

// prints out the contents of a tree level by level
public void printLevel(){
    int height = height(root);
    for(int i = 1; i <= height; i++){
        printLevel(root, i);
    }
}

// prints out a node on the given level
private void printLevel(TNode<T> root, int level) {
    if(root == null) {
        return;
    }
    if(level == 1) {
        System.out.print(root.data + " ");
    } else{
        printLevel(root.left, level -1);
        printLevel(root.right, level - 1);
    }
}

public int height(TNode<T> root) {
    if(root == null) {
        return 0;
    } else {
        return 1 + Math.max(height(root.left), height(root.right));
    }
}

// prints the contents of a tree preorder
public void preorder(TNode<T> root) {
    if(root != null) {
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
}

// prints the contents of a tree inorder
public void inOrder(TNode<T> root) {
    if(root != null) {
        inOrder(root.left);
        System.out.println(root.data + " ");
        inOrder(root.right);
    }
}

// prints the contents of a tree postorder
public void postOrder(TNode<T> root) {
    if(root != null) {
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data + " ");
    }
} 

// checks to see if a given string is valid 
public boolean parenValidate(final String str) {
    char[] opening = {'(', '{', '['};
    char[] closing = {')', '}', "]"};
    Stack<Character> validate = new Stack<Character>();
    for(int i = 0; i < str.length; i++) {
        char check = str.charAt(i);
        if(closing.contains(check)) {
            char x = validate.pop();
            switch(x) {
                case "[":
                    if(check != "]") {
                        return false;
                    }
                    break;
                case "(":
                    if(check != ")") {
                        return false;
                    }
                    break;
                case "{":
                    if(check != "}") {
                        return false;
                    }
                    break;
            }
        } else if(opening.contains(check)) {
            validate.push(check);
        }
    }
    // check to see if the stack is empty for validation
    return validate.isEmpty();
}

// adds the value of two linkedlists together
public LinkedNode add(Linkedlist a, Linkedlist b) {
    LinkedNode p1 = a.head;
    LinkedNode p2 = b.head;
    int carryOver = 0; int sum = 0;
    LinkedNode result, temp, prev;
    while(p1 != null || p2 != null) {
        sum = p1.data + p2.data + carryOver;
        carryOver = sum/10;
        temp = new LinkedNode(sum);
        if(result == null) {
            result = temp;
            prev = result;
        } else {
            prev.next = temp;
            prev = prev.next;
        }
        
        if(p1 != null) {
            p1 = p1.next;
        }
        if(p2 != null) {
            p2 = p2.next;
        }
    }
    if(carryOver >= 1) {
        temp = new Node(carryOver);
    }
    prev.next = temp;
    return result;
}