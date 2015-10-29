import java.util.LinkedList;

public  class TreeNode {

        private Comparable value;
        private LinkedList<TreeNode> children;
        private TreeNode parent;

        TreeNode(Comparable value) {
            this.value = value;
            children = new LinkedList<TreeNode>();
        }

        TreeNode(Comparable value, TreeNode parent) {
            this.value = value;
            this.parent = parent;
            children = new LinkedList<TreeNode>();
        }

        public Comparable getValue() {
            return value;
        }

        public boolean hasChildren() {
            if (children.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }

        public LinkedList<TreeNode> getChildren() {
            return children;
        }

        public TreeNode getParent() {
            return parent;
        }

        public TreeNode addChild(Comparable childValue) {
            TreeNode newChild = new TreeNode(childValue, this);
            children.add(newChild);
            return newChild;
        }

        public int depth() {
            TreeNode temp = this;
            int counter = 0;
            while ((temp = temp.parent) != null) {
                counter++;
            }
            return counter;
        }
        public String pecati()
        {
        	return this.value.toString();
        }
    }
