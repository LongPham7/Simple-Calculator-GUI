
public class ArithmeticExpression {
	
	private String firstOperand = "";
	private String secondOperand = "";
	private BinaryOp bop = null;
	private AppFrame frame = null;
	
	public ArithmeticExpression(AppFrame frame) {
		this.frame = frame;
	}
	
	public void insert(int n) {
		insert(Integer.toString(n));
		refresh();
	}
	
	public void insertDecimalPoint() {
		insert(".");
		refresh();
	}
	
	public void insert(BinaryOp op) {
		reduce();
		if (isNormal()) {
			bop = op;
			refresh();
		}
		else {
			throw new Error("You cannot input another operator.");
		}
	}
	
	public void clear() {
		bop = null;
		firstOperand = "";
		secondOperand = "";
		refresh();
	}
	
	public void reduce() {
		if (isReducible()) {
			evaluate();
			refresh();
		}
	}
	
	public void square() {
		reduce();
		if (isNormal()) {
			double input = Double.parseDouble(firstOperand);
			firstOperand = Double.toString(input * input);
			refresh();
		}
		else {
			throw new Error("You cannot square the value in the text field.");
		}
	}
	
	public void delete() {
		if (!secondOperand.equals("")) {
			secondOperand = secondOperand.substring(0, secondOperand.length() - 1);
		}
		else if (bop != null) {
			bop = null;
		}
		else if (!firstOperand.equals("")) {
			firstOperand = firstOperand.substring(0, firstOperand.length() - 1);
		}
		refresh();
	}
	
	public void sqrt() {
		reduce();
		if (isNormal()) {
			double input = Double.parseDouble(firstOperand);
			firstOperand = Double.toString(Math.sqrt(input));
			refresh();
		}
		else {
			throw new Error("A sqaure root cannot be derived.");
		}
	}
	
	private void insert(String s) {
		if (bop == null) {
			firstOperand = firstOperand + s;
		}
		else {
			secondOperand = secondOperand + s;
		}
	}
	
	private boolean isReducible() {
		return (bop != null && !firstOperand.equals("") && !secondOperand.equals(""));
	}
	
	private void evaluate() {
		double result = 0;
		double first = Double.parseDouble(firstOperand);
		double second = Double.parseDouble(secondOperand);
		switch (bop) {
		case ADD: 
			result = first + second;
			break;
		case SUB: 
			result = first - second;
			break;
		case MUL: 
			result = first * second;
			break;
		case DIV: 
			result = first / second;
			break;
		default: 
			throw new Error("An operator is unspecified.");
		}
		
		firstOperand = Double.toString(result);
		secondOperand = "";
		bop = null;
	}
	
	private boolean isNormal() {
		return (bop == null && !firstOperand.equals(""));
	}
	
	private void refresh() {
		String result = "";
		if (bop == null) {
			result  = firstOperand;
		}
		else {
			result  = firstOperand + bop.toString() + secondOperand;
		}
		frame.update(result);
	}
	
}
