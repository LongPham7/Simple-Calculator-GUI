
public class SimpleCalculator {
	
	private AppFrame app = new AppFrame();
	private ArithmeticExpression exp = new ArithmeticExpression(app);

	public static void main(String[] args) {
		SimpleCalculator gui = new SimpleCalculator();
	    gui.activate();
	}
	
	public void activate() {
		app.register(exp);
		app.activate();
	}
}
