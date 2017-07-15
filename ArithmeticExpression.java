/** This class acts as a model in the MVC architecture, maintaining and
 * manipulating an arithmetic expression. 
 * 
 * Its representation comprises three components: first operand, binary
 * operator, and second operand. If only the first operand has been given,
 * the binary operator is null. 
 * 
 * A state of the model is said to be normal if and only if the first
 * operand is non-empty and the binary operator and second operand are
 * empty; that is, the state represents a single number, rather than a
 * composite arithmetic expression.
 * 
 * As in lambda calculus, reducing an arithmetic expression is to evaluate
 * its normal value. An arithmetic expression is reducible if and only if
 * it can be reduced. 
 * 
 * Invariant: 
 * We never run into a state where the binary operator is not null, while
 * the first operand is empty. Likewise, we cannot have the first operand or
 * binary operator empty while the second operand being non-empty. 
 * 
 * */
public class ArithmeticExpression {
	
	private String firstOperand = "";
	private String secondOperand = "";
	private BinaryOperator bop = null;
	
	// View
	private AppFrame frame = null;
	
	public ArithmeticExpression(AppFrame frame) {
		this.frame = frame;
	}
	
	// Inserts a digit. 
	public void insert(int n) {
		insert(Integer.toString(n));
		refresh();
	}
	
	// Inserts a decimal point. 
	public void insertDecimalPoint() {
		insert(".");
		refresh();
	}
	
	// Inserts an arithmetic operator. 
	public void insert(BinaryOperator op) {
		reduce();
		if (isNormal()) {
			bop = op;
			refresh();
		}
		else {
			throw new Error("You cannot input another operator.");
		}
	}
	
	// Clears the arithmetic expression. 
	public void clear() {
		bop = null;
		firstOperand = "";
		secondOperand = "";
		refresh();
	}
	
	// Reduces the arithmetic expression if reducible. 
	public void reduce() {
		if (isReducible()) {
			evaluate();
			refresh();
		}
	}
	
	// Squares the number, given that the arithmetic expression is normal.
	// Otherwise, this method throws an error. 
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
	
	// Deletes the last letter in the arithmetic expression. 
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
	
	// Evaluates the square root, given that the arithmetic expression is normal.
	// Otherwise, this method throws an error. 
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
	
	// Appends a given string to the arithmetic expression.  
	private void insert(String s) {
		if (bop == null) {
			firstOperand = firstOperand + s;
		}
		else {
			secondOperand = secondOperand + s;
		}
	}
	
	// Returns true if the arithmetic expression is reducible. Otherwise,
	// false is returned. 
	private boolean isReducible() {
		return (bop != null && !firstOperand.equals("") && !secondOperand.equals(""));
	}
	
	// Reduces the arithmetic expression, given that it is reducible. 
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
	
	// Returns true if the arithmetic expression is normal. Otherwise,
	// false is returned. 
	private boolean isNormal() {
		return (bop == null && !firstOperand.equals(""));
	}
	
	// Refreshes the text field in the view. 
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
