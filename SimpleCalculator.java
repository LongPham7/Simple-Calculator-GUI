/** This class acts as a controller in the MVC architecture. */
public class SimpleCalculator {
	
	// View
	private AppFrame app = new AppFrame();
	
	// Model
	private ArithmeticExpression exp = new ArithmeticExpression(app);

	public static void main(String[] args) {
		SimpleCalculator gui = new SimpleCalculator();
	    gui.activate();
	}
	
	/** Registers the model with the view and activates the view. */
	public void activate() {
		app.register(exp);
		app.activate();
	}
}
