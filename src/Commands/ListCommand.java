package Commands;

public abstract class ListCommand {
	private String message;
	private Object result;
	
	public abstract boolean execute();
	
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
