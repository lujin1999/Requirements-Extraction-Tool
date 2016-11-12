package Commands;

import java.util.List;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Repository;
import BusinessObjects.RequirementComponent;
import BusinessObjects.Step;

public class GetComponent extends ListCommand{
	Object parent;
	
	public GetComponent(Object parent) {
		this.parent=parent;
	}
	
	
	public boolean execute()
	{
		if(parent instanceof Repository){
			super.setResult(((Repository) parent).getBusinessProcessList());
			return true;
		}
		else if(parent instanceof BusinessProcess){
			super.setResult(((BusinessProcess)parent).getStepsList());
			return true;
		}
		else if(parent instanceof Step){
			super.setResult(((Step)parent).getActionsList());
			return true;
		}
			
		return false;
	}
}
