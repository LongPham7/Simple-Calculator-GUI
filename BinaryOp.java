
public enum BinaryOp {
	ADD, SUB, MUL, DIV;
	
	@Override
	public String toString() {
		switch (this) {
		case ADD:
			return "+";
		case SUB:
			return "-";
		case MUL: 
			return "*";
		case DIV:
			return "/";
		default:
			throw new Error("This binary operator cannot be recognized.");
		}
	}
}
