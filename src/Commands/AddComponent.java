package Commands;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Repository;
import BusinessObjects.RequirementComponent;
import BusinessObjects.Step;

public class AddComponent extends ListCommand {
	Object parent;
	RequirementComponent child;
	int index;
	
	public AddComponent(Object parent,RequirementComponent child,int index) {
		this.parent=parent;
		this.child=child;
		this.index = index;
	}
	
	public boolean execute()
	{
		
		
		if(parent instanceof Repository )
		{
			child.setParent(null);
			((Repository) parent).getBusinessProcessList().add(index,(BusinessProcess) child);
			return true;
		}
		else if(parent instanceof BusinessProcess)
		{
			child.setParent(((BusinessProcess)parent));
			((BusinessProcess)parent).getStepsList().add(index ,(Step) child);
			return true;
		}
		else if(parent instanceof Step)
		{
			child.setParent(((Step)parent));
			((Step)parent).getActionsList().add(index ,(Action) child);
			return true;
		}
			
		return false;
	}

}