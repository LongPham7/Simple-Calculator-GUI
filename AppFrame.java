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
	
	/** Whether the expression on the display is in the normal form. */
	boolean cle = false;
	
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
		buttonMul.addActionListener(new OperatorListener("*"));
		buttonDiv.addActionListener(new OperatorListener("/"));
		buttonAdd.addActionListener(new OperatorListener("+"));
		buttonSub.addActionListener(new OperatorListener("-"));
		buttonEq.addActionListener(new EqListener());
		buttonClear.addActionListener(new ClearListener());
		buttonPoint.addActionListener(new PointListener());
		buttonSquare.addActionListener(new SquareListener());
		buttonDel.addActionListener(new DelListener());
		buttonSqrt.addActionListener(new SqrtListener());

		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	private void changeFont(JButton b, int n) {
		Font font = new Font(Font.DIALOG, n, n);
		b.setFont(font);
	}
	
	/** Listener class for buttons of digits. */
	class DigitListener implements ActionListener {
		int value;

		public DigitListener(int v) {
			value = v;
		}

		public void actionPerformed(ActionEvent event) {
			if (cle == false) {
				addWord(Integer.toString(value));
			} 
			else {
				field.setText(Integer.toString(value));
				cle = false;
			}
		}
	}

	/** Listener class for buttons of operators. */
	class OperatorListener implements ActionListener {
		String n;

		public OperatorListener(String op) {
			n = op;
		}

		public void actionPerformed(ActionEvent event) {
			addWord(n);
			try {
				if (secondOp()) {
					field.setText(Double.toString(calculate(field.getText().substring(0,
									field.getText().length() - 1))));
					addWord(n);
				}
				cle = false;
			} 
			catch (Exception e) {
				field.setText("Error");
				cle = true;
			}

		}
	}

	/** Listener class for the "clear" button. */
	class ClearListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			field.setText("");
		}
	}

	/** Listener class for the = button. */
	class EqListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			field.setText(Double.toString(calculate(field.getText())));
			cle = true;
		}
	}

	/** Listener class for the decimal point button. */
	class PointListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			addWord(".");
		}
	}

	/** Listener class for the ^2 button. */
	class SquareListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String equ = field.getText();
			double a = Double.parseDouble(equ);
			double b = a * a;
			field.setText(Double.toString(b));
		}
	}

	/** Listener class for the delete button. */
	class DelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String n = field.getText();
			field.setText(n.substring(0, n.length() - 1));
		}
	}

	/** Listener class for the square root button. */
	class SqrtListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String n = field.getText();
			double result = Math.sqrt(Double.parseDouble(n));
			field.setText(Double.toString(result));
		}
	}
	
	/** Adds an input string to the text field in the calculator.*/
	private void addWord(String n) {
		String result = field.getText() + n;
		field.setText(result);
	}

	/** Evaluates an input string that contains at most one operator. */
	private double calculate(String n) {
		String equ = n;
		double result = 0.0;
		char op = '+';
		int stop = equ.length();
		for (int i = 0; i < equ.length(); i++) {
			char A = equ.charAt(i);
			if (A == '+' || A == '-' || A == '*' || A == '/') {
				stop = i;
				op = A;
				break;
			}
		}
		double first = Double.parseDouble(equ.substring(0, stop));
		double second = Double.parseDouble(equ.substring(stop + 1));
		if (op == '*') {
			result = first * second;
		} 
		else if (op == '/') {
			result = first / second;
		} 
		else if (op == '-') {
			result = first - second;
		} 
		else if (op == '+') {
			result = first + second;
		} 
		else {
			result = first;
		}

		return result;

	}

	/** Returns true if the expression contains two operators. */
	private boolean secondOp() {
		boolean find = false;
		boolean find2 = false;
		String n = field.getText();
		for (int i = 0; i < n.length(); i++) {
			char a = n.charAt(i);
			if (find == false && (a == '+' || a == '-' || a == '*' || a == '/')) {
				find = true;
			} else if (find == true
					&& (a == '+' || a == '-' || a == '*' || a == '/')) {
				find2 = true;
			}
		}
		return find2;
	}


}
