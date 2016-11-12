package BusinessObjects;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class BusinessProcess extends RequirementComponent 
{
	private ArrayList<Step> stepsList;
	
	public BusinessProcess(Phrase phrase)
	{
		super(phrase);
		
		stepsList = new ArrayList<Step>();
	}
	
	public ArrayList<Step> getStepsList() {
		return stepsList;
	}

	public void setStepsList(ArrayList<Step> stepsList) {
		this.stepsList = stepsList;
	}
	
	public String toString()
	{
		//TODO 
		return this.getPhrase().getVerb() + " " + this.getPhrase().getNoun();
	}

    public TreeNode getChildAt(int index) {
        if (stepsList == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return (TreeNode)stepsList.get(index);
    }

    /**
     * Returns the number of children of this node.
     *
     * @return  an int giving the number of children of this node
     */
    public int getChildCount() {
        if (stepsList == null) {
            return 0;
        } else {
            return stepsList.size();
        }
    }

 
	
	/*//Temporary method - delete this method
	public void add(Step s)
	{
		stepsList.add(s);
	}*/
	
	
	
	/*public Step getStepAt(int index)
	{
		return stepsList.get(index);
	}*/
	
	public int size()
	{
		return stepsList.size();
	}
	
	public int getIndex(Step s)
	{
		return stepsList.indexOf(s);
	}
	

	 

	
	


}
