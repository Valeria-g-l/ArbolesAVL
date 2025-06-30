public class AVLTree {
    private Node root;

    public AVLTree() {
        this.root = null;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    public int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    
    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

       
        y.setLeft(x);
        x.setRight(T2);

        
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    public void insert(int value) {
        System.out.println("Insertar nodo: " + value);
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        
        if (node == null) {
            Node newNode = new Node(value);
            System.out.println("Nodo insertado: " + newNode.getValue() + " (balance = " + getBalance(newNode) + ")");
            return newNode;
        }

        if (value < node.getValue()) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRec(node.getRight(), value));
        } else {
           
            return node;
        }

       
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        
        int balance = getBalance(node);
        System.out.println("Nodo actual: " + node.getValue());
        System.out.println("Altura = " + node.getHeight());
        System.out.println("Balance de nodo " + node.getValue() + " = " + balance);

       

       
        if (balance > 1 && value < node.getLeft().getValue()) {
            System.out.println("Rotación Derecha (Izquierda-Izquierda)");
            return rightRotate(node);
        }

        
        if (balance < -1 && value > node.getRight().getValue()) {
            System.out.println("Rotación Izquierda (Derecha-Derecha)");
            return leftRotate(node);
        }

        
        if (balance > 1 && value > node.getLeft().getValue()) {
            System.out.println("Rotación Izquierda-Derecha");
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

       
        if (balance < -1 && value < node.getRight().getValue()) {
            System.out.println("Rotación Derecha-Izquierda");
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }
}