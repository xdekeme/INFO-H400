import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;


public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField username_box;
	private JTextField password_box;
	private String username = "";
	private String password = "";
	public int value_logo = 0;
	public int check_position_eye = 1;
	private MainDoctorWindow_v2 doctorWindow;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JPasswordField secretpassword_field;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public LoginWindow() {
		init_component();
	}
	
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 523);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel txt_username = new JLabel("Username: ");
		txt_username.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel txt_password = new JLabel("Password: ");
		txt_password.setFont(new Font("Arial", Font.BOLD, 14));
		
		username_box = new JTextField();
		username_box.setFont(new Font("Algerian", Font.PLAIN, 12));
		username_box.setForeground(new Color(0, 0, 0));
		username_box.setBackground(Color.WHITE);
		username_box.setColumns(10);
		
		secretpassword_field = new JPasswordField();
		secretpassword_field.setSelectionColor(Color.BLACK);
		secretpassword_field.setForeground(new Color(0, 0, 0));
		secretpassword_field.setFont(new Font("Algerian", Font.PLAIN, 12));
		
		JLabel error_txt = new JLabel("");
		error_txt.setHorizontalAlignment(SwingConstants.CENTER);
		error_txt.setForeground(new Color(255, 15, 11));
		
		JButton Connection = new JButton("Sign In");
		Connection.setBorder(null);
		Connection.setFont(new Font("Arial", Font.BOLD, 20));
		Connection.setForeground(Color.WHITE);
		Connection.setBackground(new Color(0, 128, 0));
		Connection.setOpaque(true);
		Connection.setBorderPainted(false);
		Connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = username_box.getText().toString();
				password = secretpassword_field.getText().toString();
				connection_database(username, password, error_txt);
			}
		});
		
		JButton btnCreateProfil = new JButton("New profile");
		btnCreateProfil.setBorder(null);
		btnCreateProfil.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCreateProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create_profil_ActionPerformed();
			}
		});
		btnCreateProfil.setForeground(new Color(255, 255, 255));
		btnCreateProfil.setBackground(new Color(128, 0, 0));
		btnCreateProfil.setOpaque(true);
		btnCreateProfil.setBorderPainted(false);
		
		
		GetRessource getressource_eye = new GetRessource();
		Image img_eye_off = getressource_eye.GetImage("/closed.png");
		Image img_eye_on = getressource_eye.GetImage("/open.png");
		
		
		JLabel eye_password_1 = new JLabel("");
		eye_password_1.setIcon(new ImageIcon(img_eye_off));
		eye_password_1.setVisible(true);
		
		eye_password_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("deprecation")
				String current_password = secretpassword_field.getText().toString();
				change_eye_icon(eye_password_1, img_eye_off, img_eye_on, secretpassword_field);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(104)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(109))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(Connection, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
					.addGap(82))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(136)
					.addComponent(btnCreateProfil, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(139))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txt_username, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(22))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txt_password, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(18)))
							.addComponent(error_txt, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(username_box, Alignment.LEADING)
							.addComponent(secretpassword_field, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eye_password_1, GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(eye_password_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(txt_username, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(username_box, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(error_txt, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_password, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(secretpassword_field, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
					.addGap(68)
					.addComponent(Connection, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(btnCreateProfil, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	
	
	public void create_profil_ActionPerformed() {
		JFrame frame = new CreateProfil();
		frame.setVisible(true);
	}
	
	
	public int change_eye_icon(JLabel label, Image eye_off, Image eye_on, JPasswordField password_text) {
		if (check_position_eye == 1) {
			label.setIcon(new ImageIcon(eye_on));
			check_position_eye = 2;
			password_text.setEchoChar((char)0);
			return check_position_eye; 
		}
		
		if (check_position_eye == 2) {
			label.setIcon(new ImageIcon(eye_off));
			check_position_eye = 1;
			password_text.setEchoChar('●');
			return check_position_eye;
		}
		return check_position_eye;
	}
	
	
	public void connection_database(String user, String pass, JLabel error_txt) {
		GetSQL getsql = new GetSQL();
		String value = getsql.login_to_db(user, pass); //All the SQL code is in this java class. It returns a string that can be used here
		int id_person = 0; 
		
		if (value.equalsIgnoreCase("Connection_patient")) {
			error_txt.setText("Welcome Patient!");
			this.dispose();
			GetSQL getidperson = new GetSQL();
			id_person = getidperson.get_ID_person(user);
			MainWindow_v2 wind = new MainWindow_v2();
			wind.get_id_user(id_person);
			wind.main(null);
		}
		
		if (value.equalsIgnoreCase("Connection_doctor")) {
			error_txt.setText("Welcome Doctor!");
			this.dispose();
			int id_person_doctor = getsql.get_ID_person(user);
			
			//wind.get_id_user(id_person_doctor);-
			if (doctorWindow == null) {
		        doctorWindow = new MainDoctorWindow_v2();
		    }
			
			doctorWindow.main(null, id_person_doctor);
		}
		
		if (value.equalsIgnoreCase("Error_username_password")) {
			error_txt.setText("Wrong username or password");
		}
		
	}
}