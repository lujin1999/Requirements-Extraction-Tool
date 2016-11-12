package BusinessObjects;

import java.util.ArrayList;

import javax.swing.tree.TreeNode;

public class Step extends RequirementComponent
{

	
	private ArrayList<Action> actionsList;
	
	public ArrayList<Action> getActionsList() {
		return actionsList;
	}

	public void setActionsList(ArrayList<Action> actionsList) {
		this.actionsList = actionsList;
	}

	public Step(Phrase ph)
	{
		super(ph);
		
		actionsList = new ArrayList<Action>();
	}
	
	public String toString()
	{
		
		 return this.getPhrase().getVerb() + " " + this.getPhrase().getNoun();
	}
	
    public TreeNode getChildAt(int index) {
        if (actionsList == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return (TreeNode)actionsList.get(index);
    }

    /**
     * Returns the number of children of this node.
     *
     * @return  an int giving the number of children of this node
     */
    public int getChildCount() {
        if (actionsList == null) {
            return 0;
        } else {
            return actionsList.size();
        }
    }
	 

}
