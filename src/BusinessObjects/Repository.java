package BusinessObjects;


import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class Repository extends DefaultMutableTreeNode {
	private static Repository instance;
	private ArrayList<BusinessProcess> businessProcessList;
	
	
	public static Repository getInstance(){
		if(instance==null)
			instance = new Repository();
		return instance;
	}
	
	private Repository()
	{
		businessProcessList = new ArrayList<BusinessProcess>();

	}
	
	public ArrayList<BusinessProcess> getBusinessProcessList() {
		return businessProcessList;
	}

	public void setBusinessProcessList(ArrayList<BusinessProcess> businessProcessList) {
		this.businessProcessList = businessProcessList;
	}

	
	
	public String toString()
	{
		return "Requirement";
	}
	
    public TreeNode getChildAt(int index) {
        if (businessProcessList == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return (TreeNode)businessProcessList.get(index);
    }

    /**
     * Returns the number of children of this node.
     *
     * @return  an int giving the number of children of this node
     */
    public int getChildCount() {
        if (businessProcessList == null) {
            return 0;
        } else {
            return businessProcessList.size();
        }
    }

	public BusinessProcess getBusinessProcessAt(int selectedIndex) {
		// TODO Auto-generated method stub
		return this.businessProcessList.get(selectedIndex);
	}
	

	
	
}