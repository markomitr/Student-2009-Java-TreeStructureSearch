public class Test {
	public static void main(String[] args)
	{
	Drvo drvo = new Drvo();
    TreeNode temp = new TreeNode("Makedonija");
    drvo.setRoot(temp);
    temp.addChild("Grcija").addChild("Bugarija").addChild("Albanija");
    temp.addChild("Slovenija").addChild("Srbija");
    temp.addChild("Romanija").addChild("Ungarija").addChild("Ceska").addChild("Polska").addChild("Germanija");
    temp = temp.getChildren().getFirst();
    temp.addChild("Avstrija").addChild("Svajcarija").addChild("Italija");
    temp = drvo.getRoot().getChildren().get(2);
    temp.addChild("Kosovo");
    temp = temp.getChildren().getFirst();
    temp.addChild("Makedonija").addChild("Srbija");
    if(drvo.iterativeIDDFS("Makedonija"))
    {
    	System.out.println("Go najdov!!!");
    }
    System.out.println();
    if(drvo.recursiveIDDFS("Srbija"))
    {
    	System.out.println("Go najdov!!!");
    }
    System.out.println();
    if(drvo.iterativeLDDFS("Bugarija", 3))
    {
    	System.out.println("Go najdov!!!");
    }
    System.out.println();
    if(drvo.breadthFirstSearch("Bugarija"))
    {
    	System.out.println("Go najdov!!!");
    }
    
    System.out.println();
	}
}
