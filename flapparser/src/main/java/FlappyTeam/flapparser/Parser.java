package FlappyTeam.flapparser;

public class Parser {
	private	Question	question;
	private String 		questionTypeStr;
	private String		responseStr;
	private	String		strToParse;

	public	Parser(String strToParse) {
		this.question = new Question();
		this.questionTypeStr = null;
		this.responseStr = null;
		this.strToParse = strToParse;
	}
	
	public void setStrToParse(String strToParse) {
		this.strToParse = strToParse;
	}

	public Question getQuestion() {
		return question;
	}

	public String getStrToParse() {
		return strToParse;
	}

	/**
	 *@author Houpert
	 *
	 *@param parseStr, la chaine qui sera parser
	 *
	 *@return la chaine parser
	 **/
	public void	doParser() {
		int value = 0, temp = 0;

		if (this.strToParse.indexOf("|") != -1 && this.strToParse.indexOf("{") != -1 && this.strToParse.indexOf("{") == 0 ) {
			this.question.setQuestion(this.strToParse.substring(1, this.strToParse.indexOf("|")));
			value = this.strToParse.indexOf("|")+1;
		} else return;
		if (this.strToParse.indexOf("}") != -1) {
			temp = this.strToParse.indexOf("}");
			/*Si de type '}' on prend le 2nd '}'*/
			if (this.strToParse.charAt(temp) == this.strToParse.charAt(temp+2)) {	
				this.questionTypeStr = this.strToParse.substring(value, this.strToParse.indexOf("}")+2);
				value = this.strToParse.indexOf("}")+3;
			} else if (this.strToParse.lastIndexOf("}") >= value) {
					this.questionTypeStr = this.strToParse.substring(value, this.strToParse.indexOf("}"));
					value = this.strToParse.indexOf("}")+1;
			} else return;
		} else return;
		if (value < this.strToParse.length()) {
			this.responseStr = this.strToParse.substring(value, this.strToParse.length());
		} else return;
		if (!this.question.getQuestion().equals(null) && !this.questionTypeStr.equals(null) && !this.responseStr.equals(null)) {
			getResponseByType();
		}
	}


	/**
	 *@author Houpert
	 *
	 *@param q
	 *@param typeStr, permet d'identifier le sous parseur à appeler
	 *@rep rep,	la liste de reponse données au sous parseur
	 *
	 *@return la question mis à jour
	 **/
	private void getResponseByType() {
		if (this.questionTypeStr.equals("type=\"()\"")) {
			/*Expression pour differencier le type de question*/
			if (this.responseStr.matches("(\\s*[+-]\\s*(true|false|TRUE|FALSE).\\s*)*")) {
				this.question.setType(TypeQuestion.bool);
				//this.question.setListeRep(BooleanQuestion(rep));
				System.out.println("Parser Guillaume");
			} else {
				this.question.setType(TypeQuestion.simple);
				//this.question.setListeRep(parserDamien(rep));
				System.out.println("Parser Damien");
			}	
		} else if (this.questionTypeStr.equals("type=\"[]\"")) {
			this.question.setType(TypeQuestion.multiple);
			//this.question.setListeRep(parserCedric(rep));
			System.out.println("Parser Cedric");

		} else if (this.questionTypeStr.equals("type=\"{}\"")) {
			this.question.setType(TypeQuestion.gapfill);
			//this.question.setListeRep(parserDax(rep));
			System.out.println("Parser Dax");
		}
	}
}
