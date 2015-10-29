import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

//Имплементација на n-арно дрво
public class Tree {
    
    //Имплементација на јазол за n-арно дрво
    protected class TreeNode {

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
    }

    //Единствено поле во класата е коренот на дрвото
    private TreeNode root;

    //Конструктор без параметри, го остава коренот на дрвото на null вредност
    Tree() {
    }

    //Конструктор со параметар - коренот на дрвото
    Tree(TreeNode root) {
        this.root = root;
    }

    //Метода за поставување на корен на дрвото
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    //Метода која го враќа коренот на дрвото
    public TreeNode getRoot() {
        return root;
    }

    //Рекурзивна метода која ја враќа максималната длабочина на дрвото
    public int maxDepth() {
        TreeNode temp = root;
        if (!temp.hasChildren()) {
            return 0;
        } else {
            ListIterator<TreeNode> iter = temp.getChildren().listIterator();
            int max = new Tree(iter.next()).maxDepth(), maxIndex = 0;
            while (iter.hasNext()) {
                Tree tempTree = new Tree(iter.next());
                if (max < tempTree.maxDepth()) {
                    max = tempTree.maxDepth();
                    maxIndex = iter.nextIndex() - 1;
                }
            }
            return new Tree(temp.getChildren().get(maxIndex)).maxDepth() + 1;
        }
    }

    // Итеративна имплементација на Iterative Deepening Depth First Search.
    public boolean iterativeIDDFS(Comparable value) {
        Stack<TreeNode> stack = new Stack<TreeNode>();      //Иницијализација на празен стек
        int maxDepth = this.maxDepth(), depth = 0;          //Иницијализација на додатни помошни вредности: максимална длабочина на дрвото, и почетната длабочина до која бараме
        while (depth <= maxDepth) {                         //Надворешен циклус, кој ја зголемува длабочината до која се пребарува
            TreeNode state = root;                          //Состојбата на секоја итерација на DFS се иницијализира почетно на коренот на дрвото
            while (true) {                                  //Внатрешен циклус, обичен DFS со ограничена длабочина
                if (state.getValue().compareTo(value) == 0) 
                    return true;                            //true ако моменталната состојба е бараната состојба
                else if (state.hasChildren() && state.depth() < depth) {    //Во случај да не е бараната состојба, се проверува дали моменталната има деца и дали нејзината длабочина е дозволена во моменталната итерација на DFS
                    ListIterator<TreeNode> iter = state.getChildren().listIterator(state.getChildren().size()); //Иницијализација на итератор кој тргнува од последното дете на листата од деца на моменталната состојба
                    while (iter.hasPrevious())
                        stack.push(iter.previous());        //Ставање на децата на моменталната состојба на стек
                }
                if (stack.empty())                      
                    break;                                  //Ако стекот е празен, доаѓа до завршување на моменталната итерација на DFS
                state = stack.pop();                        //Ако има елементи во стекот, се вади последно ставениот елемент
            }
            depth++;                                        //На крајот од секоја итерација на DFS, се зголемува длабочината до која се пребарува
        }
        return false;                                       //Ако методата излегла од надворешниот циклус без да врати решение, значи дека решение не постои и се враќа false
    }
    
    //Итеративна имплементација на DFS со ограничена длабочина на пребарување - вториот параметар depth
    public boolean iterativeLDDFS(Comparable value, int depth) {
        Stack<TreeNode> stack = new Stack<TreeNode>();      //Иницијализација на празен стек за јазлите на дрвото
        TreeNode state = root;                              //Првично, моменталната состојба е коренот на дрвото
        while(true) {                                       //Циклус кој не завршува освен ако не е прекинат одвнатре
            if (state.getValue().compareTo(value) == 0)    
                return true;                                //true ако моменталната состојба е бараната
            else if (state.hasChildren() && state.depth() < depth) {    //Ако моменталната состојба не е бараната и ако нејзината длабочина е помала од максималната дозволена...
                ListIterator<TreeNode> iter = state.getChildren().listIterator(state.getChildren().size()); //се иницијализира итератор кој тргнува од последното дете на моменталната состојба
                while (iter.hasPrevious())
                    stack.push(iter.previous());            //Стекот се полни со децата на моменталната состојба
            }
            if (stack.empty())                              
                return false;                                      //Ако стекот останал празен, се враќа false, односно бараната вредност не е пронајдена
            state = stack.pop();                            //Ако во стекот останале јазли, се вади последно ставениот како моментална состојба и се продолжува со него
        }
    }
    
    //Рекурзивна имплементација на DFS со ограничена длабочина на пребарување - вториот параметар depth
    public boolean recursiveLDDFS(Comparable value, int depth) {
        if (root.getValue().compareTo(value) == 0)
            return true;                                    //Како кај сите рекурзии, се дефинира терминирачки услов, тоа е вредноста на коренот на дрвото-повикувач на методата да биде еднаква со бараната вредност
        else if (root.hasChildren() && root.depth() < depth) {  //Инаку се проверува дали коренот има деца и дали неговата длабочина во неговото дрво (бидејќи тој претставува корен на нечие поддрво) е помала од дозволената длабочина
            ListIterator<TreeNode> iter = root.getChildren().listIterator();    //Ако условите од претходната линија се исполнети, се дефинира итератор од децата на коренот...
            while (iter.hasNext())
                if (new Tree(iter.next()).recursiveLDDFS(value, depth))         //и методата рекурзивно се повикува за сите нив
                    return true;                                                //Ако барем една од нив врати true, веднаш се враќа true за целата метода
        }
        return false;                                       //Ако ниедна од рекурзивно повиканите методи не вратила true, се враќа false, бидејќи во дрвото не е пронајдена бараната вредност до дозволената длабочина
    }
    
    //Рекурзивна имплементација на Iterative Deepening Depth First Search, која како помошна методата ја користи DFS со ограничена длабочина
    public boolean recursiveIDDFS(Comparable value) {
        int maxDepth = maxDepth();                          //Се иницијализира променлива за максималната длабочина на дрвото
        for (int i = 0; i <= maxDepth; i++)                 //За секоја вредност од 0 до максималната длабочина на дрвото, се повикува DFS со таа ограничена длабочина
            if (recursiveLDDFS(value, i))                   //Првата од DFS-ите со ограничена длабочина која ќе го најде јазолот, ја терминира методата...
                return true;                                //со вредност true
        return false;                                       //Ако ниедна од DFS-ите со ограничена длабочина не вратила true, тогаш вредноста не е пронајдена во целото дрво и се враќа false
    }
    
    //Имплементација на BFS
    public boolean breadthFirstSearch(Comparable value) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();    //Редицата ќе биде имплементирана со готовата класа од Java SE LinkedList
        TreeNode state = root;                                      //Состојбата почетно се иницијализира на null
        while (true) {                                              //Бесконечен циклус кој може да биде терминиран само од наредба вгнезедена во него
            if (state.getValue().compareTo(value) == 0)
                return true;                                        //Ако моменталната состојба е еднаква со бараната, се враќа true
            else if (state.hasChildren()) {                         //Инаку, се проверува дали моменталната состојба - јазол има деца
                ListIterator<TreeNode> iter = state.getChildren().listIterator();   //Ако има деца, се иницијализира итератор за децата на јазолот...
                while (iter.hasNext())
                    queue.add(iter.next());                                         //и секое дете од јазолот се додава на редицата
            }
            if (queue.isEmpty())                                    
                return false;                                       //Ако редицата останала празна, нема понатамошни елементи за проверка и бараната вредност не е пронајдена, се враќа false
            state = queue.poll();                                   //Ако во редицата има елементи, се вади елемент кој е ставен во редицата најодамна и повторно се извршува циклусот со тој елемент како нова состојба
        }
    }
   
    public static void main(String[] args) {
        //Создавање на пример дрво (предавање број 3, слајд број 8)
        Tree drvo = new Tree();
        TreeNode temp = drvo.new TreeNode("Skopje");
        drvo.setRoot(temp);
        temp.addChild("Veles").addChild("Negotino").addChild("Gevgelija");
        temp.addChild("Kumanovo").addChild("Kriva Palanka");
        temp.addChild("Tetovo").addChild("Gostivar").addChild("Debar").addChild("Struga").addChild("Ohrid");
        temp = temp.getChildren().getFirst();
        temp.addChild("Prilep").addChild("Bitola").addChild("Resen").addChild("Ohrid");
        temp = drvo.getRoot().getChildren().get(2);
        temp.addChild("Jegunovce");
        temp = temp.getChildren().getFirst();
        temp.addChild("Kicevo").addChild("Ohrid");
        //Проверка на итеративната имплементација на IDDFS
        System.out.println(drvo.iterativeIDDFS("Skopje"));
        System.out.println(drvo.iterativeIDDFS("Veles"));
        System.out.println(drvo.iterativeIDDFS("Negotino"));
        System.out.println(drvo.iterativeIDDFS("Gevgelija"));
        System.out.println(drvo.iterativeIDDFS("Kumanovo"));
        System.out.println(drvo.iterativeIDDFS("Kriva Palanka"));
        System.out.println(drvo.iterativeIDDFS("Tetovo"));
        System.out.println(drvo.iterativeIDDFS("Gostivar"));
        System.out.println(drvo.iterativeIDDFS("Debar"));
        System.out.println(drvo.iterativeIDDFS("Struga"));
        System.out.println(drvo.iterativeIDDFS("Ohrid"));
        System.out.println(drvo.iterativeIDDFS("Prilep"));
        System.out.println(drvo.iterativeIDDFS("Los Angeles"));
        System.out.println(drvo.iterativeIDDFS("Resen"));
        System.out.println(drvo.iterativeIDDFS("Jegunovce"));
        System.out.println();
        //Проверка на рекурзивната имплементација на IDDFS, а со тоа се проверува и имплементацијата на рекурзивниот LDDFS
        System.out.println(drvo.recursiveIDDFS("Skopje"));
        System.out.println(drvo.recursiveIDDFS("Veles"));
        System.out.println(drvo.recursiveIDDFS("Negotino"));
        System.out.println(drvo.recursiveIDDFS("Gevgelija"));
        System.out.println(drvo.recursiveIDDFS("Kumanovo"));
        System.out.println(drvo.recursiveIDDFS("Kriva Palanka"));
        System.out.println(drvo.recursiveIDDFS("Tetovo"));
        System.out.println(drvo.recursiveIDDFS("Gostivar"));
        System.out.println(drvo.recursiveIDDFS("Debar"));
        System.out.println(drvo.recursiveIDDFS("Struga"));
        System.out.println(drvo.recursiveIDDFS("Ohrid"));
        System.out.println(drvo.recursiveIDDFS("Prilep"));
        System.out.println(drvo.recursiveIDDFS("New York"));
        System.out.println(drvo.recursiveIDDFS("Resen"));
        System.out.println(drvo.recursiveIDDFS("Jegunovce"));
        System.out.println();
        //Проверка на имплементацијата на итеративен DFS со ограничена длабочина
        System.out.println(drvo.iterativeLDDFS("Ohrid", 3));
        System.out.println();
        //Проверка на имплементацијата на BFS
        System.out.println(drvo.breadthFirstSearch("Skopje"));
        System.out.println(drvo.breadthFirstSearch("Veles"));
        System.out.println(drvo.breadthFirstSearch("Negotino"));
        System.out.println(drvo.breadthFirstSearch("Gevgelija"));
        System.out.println(drvo.breadthFirstSearch("Kumanovo"));
        System.out.println(drvo.breadthFirstSearch("Kriva Palanka"));
        System.out.println(drvo.breadthFirstSearch("Tetovo"));
        System.out.println(drvo.breadthFirstSearch("Gostivar"));
        System.out.println(drvo.breadthFirstSearch("Debar"));
        System.out.println(drvo.breadthFirstSearch("Struga"));
        System.out.println(drvo.breadthFirstSearch("Ohrid"));
        System.out.println(drvo.breadthFirstSearch("Prilep"));
        System.out.println(drvo.breadthFirstSearch("Vevcani"));
        System.out.println(drvo.breadthFirstSearch("Resen"));
        System.out.println(drvo.breadthFirstSearch("Jegunovce"));
        System.out.println();
    }
}