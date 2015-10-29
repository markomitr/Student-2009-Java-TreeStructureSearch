import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class Drvo {
    
    private TreeNode root;//коренот на дрвото
    Drvo() {
    }


    Drvo(TreeNode root) {
        this.root = root;     //Конструктор - сетирање на коренот на дрвото
    }

    
    public void setRoot(TreeNode root) { 
        this.root = root;				//Метода за поставување на корен на дрвото
    }

    
    public TreeNode getRoot() {
        return root; //Метода - враќа коренот на дрвото
    }

   
    public int maxDepth() {  //Максималната длабочина на дрвото
        TreeNode temp = root;
        if (!temp.hasChildren()) {
            return 0;
        } else {
            ListIterator<TreeNode> iter = temp.getChildren().listIterator();
            int max = new Drvo(iter.next()).maxDepth(), maxIndex = 0;
            while (iter.hasNext()) {
                Drvo tempTree = new Drvo(iter.next());
                if (max < tempTree.maxDepth()) {
                    max = tempTree.maxDepth();
                    maxIndex = iter.nextIndex() - 1;
                }
            }
            return new Drvo(temp.getChildren().get(maxIndex)).maxDepth() + 1;
        }
    }
    // Итеративна имплементација на  DBFS.
    public boolean iterativeIDDFS(Comparable value) {
        Stack<TreeNode> stack = new Stack<TreeNode>();     
        int maxDepth = this.maxDepth(), depth = 0;          
        while (depth <= maxDepth) {                         //Циклусја зголемува длабочината до која се пребарува
            TreeNode state = root;                          // DFS се иницијализира почетно на коренот на дрвото
            while (true) {                                  // обичен DFS со ограничена длабочина
                if (state.getValue().compareTo(value) == 0) 
                    return true;                            //true - бараната состојба
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
                if (new Drvo(iter.next()).recursiveLDDFS(value, depth))         //и методата рекурзивно се повикува за сите нив
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
   
    
}