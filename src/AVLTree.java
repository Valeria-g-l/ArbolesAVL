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

  public void insert(int value) {
    System.out.println("Insertar nodo: " + value);
    root = insertRec(root, value);
  }

  private Node insertRec(Node node, int value) {
    // Paso 1: inserción normal BST
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
      // valores duplicados no se insertan
      return node;
    }

    // Paso 2: actualizar altura
    int altura = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    node.setHeight(altura);

    // Paso 3: obtener balance
    int balance = getBalance(node);
    System.out.println("Nodo actual: " + node.getValue());
    System.out.println("Altura = " + node.getHeight());
    System.out.println("Balance de nodo " + node.getValue() + " = " + balance);

    // Paso 4: verificar y manejar desbalance (solo muestra el tipo)
    if (balance > 1 && value < node.getLeft().getValue()) {
      System.out.println("Rotación Derecha (Izquierda-Izquierda)");
    }

    if (balance < -1 && value > node.getRight().getValue()) {
      System.out.println("Rotación Izquierda (Derecha-Derecha)");
    }

    if (balance > 1 && value > node.getLeft().getValue()) {
      System.out.println("Rotación Izquierda-Derecha");
    }

    if (balance < -1 && value < node.getRight().getValue()) {
      System.out.println("Rotación Derecha-Izquierda");
    }

    return node;
  }
}
