package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.dto.UserDto;
import mx.com.ipn.upiicsa.poo.pizarron.pr.RegisterPr;
import mx.com.ipn.upiicsa.poo.pizarron.util.Result;
import mx.com.ipn.upiicsa.poo.pizarron.util.SpringUtilities;

public class RegisterUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel nameLb;
	private JTextField nameTxt;
	private JLabel lastNameLb;
	private JTextField lastNameTxt;
	private JLabel secondLastNameLb;
	private JTextField secondLastNameTxt;
	private JLabel loginLb;
	private JTextField loginTxt;
	private JLabel passwordLb;
	private JPasswordField passwordTxt;
	private JLabel confirmPasswordLb;
	private JPasswordField confirmPasswordTxt;
	private JButton acceptBtn;
	private JButton cancelBtn;
	
	
	public RegisterUI() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Register");
		setResizable(false);
		initializeComponents();
		buildLayout();
		initializeListeners();
		pack();
		setVisible(true);
	}
	
	private void initializeComponents() {
		nameLb = new JLabel("Name: ");
		nameTxt = new JTextField("Aylin", 15);
		lastNameLb = new JLabel("Last Name: ");
		lastNameTxt = new JTextField("Yepez", 15);
		secondLastNameLb = new JLabel("Second Last Name: ");
		secondLastNameTxt  = new JTextField("Granados", 15);
		loginLb = new JLabel("Login: ");
		loginTxt = new JTextField("example@gmail.com", 15);
		passwordLb = new JLabel("Password: ");
		passwordTxt = new JPasswordField("Password", 15);
		confirmPasswordLb = new JLabel("Confirm Password: ");
		confirmPasswordTxt = new JPasswordField("Password", 15);
		acceptBtn = new JButton("Accept");
		cancelBtn = new JButton("Cancel");
	}
	
	private void initializeListeners() {
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTxt.getText();
				String lastName = lastNameTxt.getText();
				String secondLastName = secondLastNameTxt.getText();
				String login = loginTxt.getText();
				String password = new String(passwordTxt.getPassword());
				String confirmPassword = new String(confirmPasswordTxt.getPassword());
				UserDto userDto = new UserDto(name, lastName, secondLastName, login, password, confirmPassword);
				Result<UserDto> result = RegisterPr.register(userDto);
				
			}
		});
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void buildLayout() {
		Container pane = getContentPane();
		SpringLayout layout = new SpringLayout();
		pane.setLayout(layout);
		
		pane.add(nameLb);
		pane.add(nameTxt);
		pane.add(lastNameLb);
		pane.add(lastNameTxt);
		pane.add(secondLastNameLb);
		pane.add(secondLastNameTxt);
		pane.add(loginLb);
		pane.add(loginTxt);
		pane.add(passwordLb);
		pane.add(passwordTxt);
		pane.add(confirmPasswordLb);
		pane.add(confirmPasswordTxt);
		pane.add(acceptBtn);
		pane.add(cancelBtn);
		
		SpringUtilities.makeCompactGrid(pane,
                7  , 2, 		//rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
	}
	
	
}
