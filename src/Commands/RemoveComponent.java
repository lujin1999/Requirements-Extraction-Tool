package Commands;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Repository;
import BusinessObjects.RequirementComponent;
import BusinessObjects.Step;

public class RemoveComponent {
	
	RequirementComponent child;
	
	public RemoveComponent(RequirementComponent child) {
		this.child=child;
	}
	
	
	public boolean execute()
	{
		
		Object parent = child.getParent();
		
		
		if(child instanceof BusinessProcess)
		{
			Repository.getInstance().getBusinessProcessList().remove(child);
			
			return true;
		}
		else if(child instanceof Step)
		{
					if(parent instanceof BusinessProcess)
					((BusinessProcess)parent).getStepsList().remove(child);
					
			return true;
		}
		else if(child instanceof Action)
		{
			if(parent instanceof Step)
			((Step)parent).getActionsList().remove(child);
			
			return true;
		}
		

		return false;
	}

}