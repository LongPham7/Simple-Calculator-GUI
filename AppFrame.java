import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** This class acts as a view in the MVC architecture. It instantiate all components of
 * a GUI for the calculator and registers the buttons with appropriate event listeners.*/
public class AppFrame {
	
	JFrame frame = new JFrame("Calculator");
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton buttonMul = new JButton("*");
	JButton buttonDiv = new JButton("/");
	JButton buttonAdd = new JButton("+");
	JButton buttonSub = new JButton("-");
	JButton buttonEq = new JButton("=");
	JButton buttonClear = new JButton("Clear");
	JButton buttonPoint = new JButton(".");
	JButton buttonSquare = new JButton("^2");
	JButton buttonSqrt = new JButton("Square Root");
	JButton buttonDel = new JButton("Delete");
	JTextField field = new JTextField(32);
	
	// Model
	private ArithmeticExpression exp = null;
	
	// Instantiates GUI components and registers them with event listeners.
	public void activate() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		changeFont(button0, 30);
		changeFont(button1, 30);
		changeFont(button2, 30);
		changeFont(button3, 30);
		changeFont(button4, 30);
		changeFont(button5, 30);
		changeFont(button6, 30);
		changeFont(button7, 30);
		changeFont(button8, 30);
		changeFont(button9, 30);
		changeFont(buttonMul, 30);
		changeFont(buttonDiv, 30);
		changeFont(buttonAdd, 30);
		changeFont(buttonSub, 30);
		changeFont(buttonEq, 30);
		changeFont(buttonClear, 20);
		changeFont(buttonPoint, 30);
		changeFont(buttonSquare, 30);
		changeFont(buttonDel, 20);
		changeFont(buttonSqrt, 13);

		panel1.setBackground(Color.CYAN);
		panel2.setBackground(Color.GRAY);

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);

		panel1.add(field);
		panel2.setLayout(new GridLayout(5, 4, 8, 8));
		panel2.add(button7);
		panel2.add(button8);
		panel2.add(button9);
		panel2.add(buttonMul);
		panel2.add(button4);
		panel2.add(button5);
		panel2.add(button6);
		panel2.add(buttonDiv);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(buttonAdd);
		panel2.add(buttonClear);
		panel2.add(button0);
		panel2.add(buttonEq);
		panel2.add(buttonSub);
		panel2.add(buttonDel);
		panel2.add(buttonPoint);
		panel2.add(buttonSquare);
		panel2.add(buttonSqrt);

		button0.addActionListener(new DigitListener(0));
		button1.addActionListener(new DigitListener(1));
		button2.addActionListener(new DigitListener(2));
		button3.addActionListener(new DigitListener(3));
		button4.addActionListener(new DigitListener(4));
		button5.addActionListener(new DigitListener(5));
		button6.addActionListener(new DigitListener(6));
		button7.addActionListener(new DigitListener(7));
		button8.addActionListener(new DigitListener(8));
		button9.addActionListener(new DigitListener(9));
		buttonMul.addActionListener(new BinaryOperatoreratorListener(BinaryOperator.MUL));
		buttonDiv.addActionListener(new BinaryOperatoreratorListener(BinaryOperator.DIV));
		buttonAdd.addActionListener(new BinaryOperatoreratorListener(BinaryOperator.ADD));
		buttonSub.addActionListener(new BinaryOperatoreratorListener(BinaryOperator.SUB));
		buttonEq.addActionListener(new EqListener());
		buttonClear.addActionListener(new ClearListener());
		buttonPoint.addActionListener(new PointListener());
		buttonSquare.addActionListener(new SquareListener());
		buttonDel.addActionListener(new DelListener());
		buttonSqrt.addActionListener(new SqrtListener());

		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	// Registers a given model with this view. 
	public void register(ArithmeticExpression exp) {
		this.exp = exp;
	}
	
	// Updates the text field to display the input string. 
	public void update(String s) {
		field.setText(s);
	}
	
	/** Listener class for digit buttons. */
	class DigitListener implements ActionListener {
		private int value;

		public DigitListener(int value) {
			this.value = value;
		}

		// Inserts the corresponding digit to the text field when
		// this button is clicked. 
		public void actionPerformed(ActionEvent event) {
			exp.insert(value);
		}
	}

	/** Listener class for operator buttons. */
	class BinaryOperatoreratorListener implements ActionListener {
		private BinaryOperator bop;

		public BinaryOperatoreratorListener(BinaryOperator op) {
			bop = op;
		}

		// Inserts the corresponding operator to the text field when
		// this button is clicked. 
		public void actionPerformed(ActionEvent event) {
			exp.insert(bop);
		}
	}

	/** Listener class for a button to clear the text field. */
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.clear();
		}
	}

	/** Listener class for a = button. */
	class EqListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.reduce();
		}
	}

	/** Listener class for a decimal point button. */
	class PointListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.insertDecimalPoint();
		}
	}

	/** Listener class for a ^2 button to square a number. */
	class SquareListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.square();
		}
	}

	/** Listener class for a button to delete the last letter in the text field. */
	class DelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.delete();
		}
	}

	/** Listener class for a square-root button. */
	class SqrtListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			exp.sqrt();
		}
	}
	
	// Changes the font style of the label of an input button to
	// Font.DIALOG and the font size to a specified size. 
	private void changeFont(JButton b, int n) {
		Font font = new Font(Font.DIALOG, n, n);
		b.setFont(font);
	}

}
