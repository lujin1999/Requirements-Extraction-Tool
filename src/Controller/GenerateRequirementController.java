package Controller;

import java.util.List;

import BusinessObjects.Action;
import BusinessObjects.BusinessProcess;
import BusinessObjects.Phrase;
import BusinessObjects.Repository;
import BusinessObjects.Step;
import Commands.EditComponent;
import Commands.GetComponent;

public class GenerateRequirementController {

	String requirement;
	String tab = "      ";
	
    public String generateRequirement(){
        requirement = "";
        
        GetComponent get = new GetComponent(Repository.getInstance());
        get.execute();
        
        List<BusinessProcess> bpList = (List<BusinessProcess>)get.getResult();
        
        for(int i = 0; i< bpList.size(); i++){
            String reqID = "R"+(i+1)+".";
        	requirement = requirement 
        			+reqID+ bpList.get(i).getPhrase().getSentence() +"\n";
        	
        	get = new GetComponent(bpList.get(i));
        	get.execute();
        	
        	List<Step> stepList = (List<Step>)get.getResult();
        	
        	for(int j = 0; j<stepList.size(); j++){
        		String stepID = reqID + (j+1) +".";
        		requirement = requirement +
        				tab+stepID+ stepList.get(j).getPhrase().getSentence() +"\n";
        		
        		get = new GetComponent(stepList.get(j));
            	get.execute();
            	
            	List<Action> actionList = (List<Action>)get.getResult();
            	
            	for(int k =0; k<actionList.size(); k++){
            		String actionID = stepID + (k+1) +".";
            		requirement = requirement + tab+
            				tab+actionID+ actionList.get(k).getPhrase().getSentence() +"\n";
            	}
        	}
        }
        
        return requirement;
	}
}
