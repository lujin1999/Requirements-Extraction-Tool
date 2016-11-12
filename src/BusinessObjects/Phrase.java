package BusinessObjects;

public class Phrase {
	private String verb;
	private String noun;
	private String sentence;
	
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}




	
	public Phrase(String v, String n)
	{
		verb = v;
		noun = n;
		sentence = createSentence();
	}
	
	String createSentence()
	{
		return "The system shall allow the user to " + verb + " " + noun;
	}

}
