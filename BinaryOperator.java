/** Enumeration class for the four basic arithmetic binary operators. */
public enum BinaryOperator {
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
			throw new Error("Illegal operator");
		}
	}
}
