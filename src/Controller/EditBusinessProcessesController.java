package Controller;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Phrase;
import BusinessObjects.Repository;
import BusinessObjects.RequirementComponent;
import BusinessObjects.Step;
import Commands.EditComponent;
import Commands.RemoveComponent;

public class EditBusinessProcessesController {
	
	public void removeRequirementComponent(RequirementComponent requirementComponent)
	{
		RemoveComponent remove=new RemoveComponent(requirementComponent);
		remove.execute();
		
	}
	
	public void editBusinessProcess(BusinessProcess oldBp,String verb, String noun, String sentence, int position){
        
        Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		BusinessProcess newBp = new BusinessProcess(phrase);
		newBp.setStepsList(oldBp.getStepsList());

		EditComponent edit = new EditComponent(oldBp,newBp,position);
		edit.execute();
		
	}
	
	public void editStep(Step oldStep, BusinessProcess parent, String verb, String noun, String sentence, int position){
		
		 Phrase phrase = new Phrase(verb, noun);
			
			if(sentence!=null && sentence.length()>0)
				phrase.setSentence(sentence);
			
			Step newStep = new Step(phrase);
			
			newStep.setActionsList(oldStep.getActionsList());
			newStep.setParent(parent);
			
			EditComponent edit = new EditComponent(oldStep,newStep,position);
			edit.execute();
			
	}
	
	public void editAction(Action oldAction, Step parent, String verb, String noun, String sentence, int position){
		 Phrase phrase = new Phrase(verb, noun);
			
			if(sentence!=null && sentence.length()>0)
				phrase.setSentence(sentence);
			
			Action newAction = new Action(phrase);
			
			newAction.setParent(parent);
			
			EditComponent edit = new EditComponent(oldAction,newAction,position);
			edit.execute();
	}
}