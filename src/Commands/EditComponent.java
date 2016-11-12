package Commands;
import org.omg.CORBA.RepositoryIdHelper;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Repository;
import BusinessObjects.RequirementComponent;
import BusinessObjects.Step;

public class EditComponent extends ListCommand {
	RequirementComponent oldCom;
	RequirementComponent newCom;
    int position;
	
	public EditComponent(RequirementComponent oldCom,RequirementComponent newCom,int position) {
				this.newCom = newCom;
                this.oldCom = oldCom;
                this.position=position;
                
	}
	
	public boolean execute()
	{
		
		
		RemoveComponent remove = new RemoveComponent(oldCom);
		remove.execute();
		
		AddComponent add =null;
		System.out.println("adding new comp next");
	
		if(newCom.getParent() == null)
			{
			if(newCom instanceof BusinessProcess)
			add=new AddComponent(Repository.getInstance(),newCom, position);
			}
		else
		{
			add= new AddComponent(newCom.getParent(),newCom, position);
		}
			
		
		add.execute();
		System.out.println("finish edit execute");

		
		return true;
	}

}