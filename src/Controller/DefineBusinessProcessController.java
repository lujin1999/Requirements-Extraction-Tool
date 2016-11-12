package Controller;

import java.util.List;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Phrase;
import BusinessObjects.Repository;
import BusinessObjects.Step;
import Commands.AddComponent;
import Commands.GetComponent;

public class DefineBusinessProcessController {
	
	public List<BusinessProcess> getBusinessProcesses(){
		return Repository.getInstance().getBusinessProcessList();
	}
	
	public List<Step> getSteps(BusinessProcess bp){
		return bp.getStepsList();
	}
	
	public List<Action> getActions(Step step){
		return step.getActionsList();
	}

	public void createBusinessProcess(String verb, String noun, String sentence, int position){
		
		Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		BusinessProcess bp = new BusinessProcess(phrase);
		
		AddComponent add = new AddComponent(Repository.getInstance(), bp, position);
		add.execute();
	}
	
    public void createStep(String verb, String noun, String sentence,BusinessProcess bp ,int position){
		
        Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		Step step = new Step(phrase);
		
		AddComponent add = new AddComponent(bp, step, position);
		add.execute();
	}
    
    public void addAction(String verb, String noun, String sentence, Step step ,int position){
		
    	Phrase phrase = new Phrase(verb, noun);
 		
 		if(sentence!=null && sentence.length()>0)
 			phrase.setSentence(sentence);
 		
 		Action action = new Action(phrase);
 		
 		AddComponent add = new AddComponent(step, action, position);
 		add.execute();
	}
}