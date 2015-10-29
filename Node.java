import java.util.LinkedList;
import java.util.Stack;
 
public class Node {
 
    private String name;
    public LinkedList<Node> neighbors;
 
    public Node(String name) {
        this.name = name;
        neighbors = new LinkedList<Node>();
    }
 
    @Override
    public String toString() {
        return "Node: " + name;
    }
 
private void depthFirst(Node start, Node goal) {
	Stack<Node> nextStack = new Stack<Node>();
	Stack<Node> traversed = new Stack<Node>();
 
	boolean found = false;
 
	//Enqueue root
	nextStack.add(start);
 
	while (!nextStack.isEmpty()) {
		//Dequeue next node for comparison
		//And add it 2 list of traversed nodes
		Node node = nextStack.pop();
		traversed.push(node);
 
		if (node.equals(goal)) {
			found = true;
			break;
		} else {
 
			//Enqueue new neighbors
			for (Node neighbor : node.neighbors) {
				if (!traversed.contains(neighbor) 
				&& !nextStack.contains(neighbor)) {
					nextStack.push(neighbor);
				}
			}
		}
	}
}

}

