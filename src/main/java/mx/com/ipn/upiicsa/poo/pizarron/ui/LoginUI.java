package mx.com.ipn.upiicsa.poo.pizarron.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import mx.com.ipn.upiicsa.poo.pizarron.dto.LoginDto;
import mx.com.ipn.upiicsa.poo.pizarron.dto.UserDto;
import mx.com.ipn.upiicsa.poo.pizarron.pr.LoginPr;
import mx.com.ipn.upiicsa.poo.pizarron.util.Result;
import mx.com.ipn.upiicsa.poo.pizarron.util.SpringUtilities;
import mx.com.ipn.upiicsa.poo.pizarron.util.StatusCodes;
import mx.com.ipn.upiicsa.poo.pizarron.util.Error;

public class LoginUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel titleLb;
	private JLabel loginLb;
	private JTextField loginTxt;
	private JLabel passwordLb;
	private JPasswordField passwordTxt;
	private JButton loginBtn;
	private JButton registerBtn;
	
	
	public LoginUI() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Login");
		setResizable(false);
		initializeComponents();
		buildLayout();
		initializeListeners();
		pack();
		setVisible(true);
	}
	
	private void initializeComponents() {
		titleLb = new JLabel("LOGIN");
		passwordLb = new JLabel("Password: ");
		passwordTxt = new JPasswordField("Password", 15);
		loginLb = new JLabel("Login: ");
		loginTxt = new JTextField("example@gmail.com", 15);
		loginBtn = new JButton("Login");
		registerBtn  = new JButton("Register");
	}
	
	private void initializeListeners() {
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginDto loginDto = new LoginDto(loginTxt.getText(), passwordTxt.getText());
				Result<UserDto> usuarioResult =  LoginPr.login(loginDto);
				if(usuarioResult.getStatus() == StatusCodes.ERROR_FORM) {
					List<Error> errors = usuarioResult.getErrors();
					if(errors.contains(new Error("password"))) {
						passwordTxt.setBackground(Color.RED);
						//System.out.println("Hay error en el password");
					}
					if(errors.contains(new Error("login"))) {
						loginTxt.setBackground(Color.RED);
						//System.out.println("Hay error en el login");
					}
				}else if(usuarioResult.getStatus() == StatusCodes.ERROR_LOGIN){
					System.out.println("Error en el login "+usuarioResult.getErrors().get(0).getMessage());
				}else {
					JOptionPane.showMessageDialog(null, "Hi...!");
					dispose();
					PizarronUI ventanaPizarronUI = new PizarronUI();
				}
			}
		});
		
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				RegisterUI registerUI = new RegisterUI();
				
			}
		});
	}
	
	private void buildLayout() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		JPanel inputsPanel = new JPanel();
		SpringLayout layout = new SpringLayout();
		inputsPanel.setLayout(layout);
		inputsPanel.add(loginLb);
		inputsPanel.add(loginTxt);
		inputsPanel.add(passwordLb);
		inputsPanel.add(passwordTxt);
		SpringUtilities.makeCompactGrid(inputsPanel,
                2, 2, 		//rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(loginBtn);
		buttonsPanel.add(registerBtn);
		
		pane.add(inputsPanel, BorderLayout.CENTER);
		pane.add(buttonsPanel, BorderLayout.PAGE_END);
		pane.add(titleLb, BorderLayout.PAGE_START);
				
	}
	
}
