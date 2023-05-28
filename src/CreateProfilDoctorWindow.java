import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class CreateProfilDoctorWindow extends JFrame {

	private JPanel contentPane;
	private JTextField name_field;
	private JTextField lastname_field;
	private JTextField username_field;
	private JTextField password_field;
	private JTextField password_confirm_field;
	private String name;
	private String lastname;
	private String username;
	private String password;
	private String password_confirm;
	private String mail;
	private int inami;
	private String year_dob;
	private String month_dob;
	private String day_dob;
	private String sex;
	private int size;
	private int weight;
	private String country;
	private String city;
	private String region;
	private int postcode;
	private String adress;
	private int number_house;
	private int phonenumber;
	private int value_check_password; 
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField phone_number_field;
	private JTextField country_field;
	private JTextField city_field;
	private JTextField region_field;
	private JTextField postcode_field;
	private JTextField adress_field;
	private JTextField numberhouse_field;
	private int value_check_username;
	private JTextField mail_field;
	private JTextField inami_field;



	public CreateProfilDoctorWindow() {
		init_component();
		
	}
	
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(28, 71, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblProfilCreation = new JLabel("PROFILE CREATION - DOCTOR");
		lblProfilCreation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProfilCreation.setForeground(Color.RED);
		lblProfilCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfilCreation.setBounds(0, 0, 663, 29);
		contentPane.add(lblProfilCreation);
		

		JLabel lblPersonalInfo = new JLabel("PERSONAL INFO");
		lblPersonalInfo.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPersonalInfo.setForeground(Color.BLUE);
		lblPersonalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalInfo.setBounds(6, 41, 304, 16);
		contentPane.add(lblPersonalInfo);
		
		JLabel lblAdress = new JLabel("ADDRESS");
		lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdress.setForeground(Color.BLUE);
		lblAdress.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAdress.setBounds(353, 41, 310, 16);
		contentPane.add(lblAdress);
		
		JLabel lblLastname = new JLabel("Last name:");
		lblLastname.setBounds(28, 99, 99, 16);
		contentPane.add(lblLastname);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(28, 156, 99, 16);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password: ");
		lblConfirmPassword.setBounds(28, 184, 159, 16);
		contentPane.add(lblConfirmPassword);
		
		JLabel txt_user = new JLabel("Username: ");
		txt_user.setBounds(28, 128, 99, 16);
		contentPane.add(txt_user);
		
		name_field = new JTextField();
		name_field.setBounds(165, 66, 130, 26);
		contentPane.add(name_field);
		name_field.setColumns(10);
		
		lastname_field = new JTextField();
		lastname_field.setColumns(10);
		lastname_field.setBounds(165, 94, 130, 26);
		contentPane.add(lastname_field);
		
		username_field = new JTextField();
		username_field.setColumns(10);
		username_field.setBounds(165, 123, 130, 26);
		contentPane.add(username_field);
		
		password_field = new JTextField();
		password_field.setColumns(10);
		password_field.setBounds(165, 151, 130, 26);
		contentPane.add(password_field);
		
		password_confirm_field = new JTextField();
		password_confirm_field.setColumns(10);
		password_confirm_field.setBounds(165, 179, 130, 26);
		contentPane.add(password_confirm_field);
		
		JLabel error_label = new JLabel("");
		error_label.setForeground(new Color(255, 33, 11));
		error_label.setHorizontalAlignment(SwingConstants.CENTER);
		error_label.setBounds(459, 296, 204, 16);
		contentPane.add(error_label);
		
		
		JButton close_btn = new JButton("CLOSE");
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close_btn_ActionPerformed();
			}
		});
		close_btn.setBounds(6, 319, 117, 29);
		contentPane.add(close_btn);
		
		JLabel PhoneNumber_label = new JLabel("Phone number:");
		PhoneNumber_label.setBounds(28, 213, 159, 16);
		contentPane.add(PhoneNumber_label);
		
		JLabel mail_label = new JLabel("Email:");
		mail_label.setBounds(28, 240, 61, 16);
		contentPane.add(mail_label);
		
		mail_field = new JTextField();
		mail_field.setColumns(10);
		mail_field.setBounds(165, 235, 130, 26);
		contentPane.add(mail_field);
		
		JLabel inaminumber_label = new JLabel("INAMI:");
		inaminumber_label.setBounds(28, 268, 123, 16);
		contentPane.add(inaminumber_label);
		
		inami_field = new JTextField();
		inami_field.setColumns(10);
		inami_field.setBounds(165, 263, 130, 26);
		contentPane.add(inami_field);
		
		phone_number_field = new JTextField();
		phone_number_field.setColumns(10);
		phone_number_field.setBounds(165, 208, 130, 26);
		contentPane.add(phone_number_field);
		
		JLabel country_label = new JLabel("Country:");
		country_label.setBounds(380, 71, 61, 16);
		contentPane.add(country_label);
		
		country_field = new JTextField();
		country_field.setColumns(10);
		country_field.setBounds(517, 66, 130, 26);
		contentPane.add(country_field);
		
		JLabel city_label = new JLabel("City:");
		city_label.setBounds(380, 99, 99, 16);
		contentPane.add(city_label);
		
		city_field = new JTextField();
		city_field.setColumns(10);
		city_field.setBounds(517, 94, 130, 26);
		contentPane.add(city_field);
		
		JLabel region_label = new JLabel("Region:");
		region_label.setBounds(380, 128, 99, 16);
		contentPane.add(region_label);
		
		region_field = new JTextField();
		region_field.setColumns(10);
		region_field.setBounds(517, 123, 130, 26);
		contentPane.add(region_field);
		
		JLabel postcode_label = new JLabel("Postcode:");
		postcode_label.setBounds(380, 156, 99, 16);
		contentPane.add(postcode_label);
		
		postcode_field = new JTextField();
		postcode_field.setColumns(10);
		postcode_field.setBounds(517, 151, 130, 26);
		contentPane.add(postcode_field);
		
		JLabel adress_label = new JLabel("Address (street):");
		adress_label.setBounds(380, 184, 159, 16);
		contentPane.add(adress_label);
		
		adress_field = new JTextField();
		adress_field.setColumns(10);
		adress_field.setBounds(517, 179, 130, 26);
		contentPane.add(adress_field);
		
		JLabel numberhouse_label = new JLabel("Number:");
		numberhouse_label.setBounds(380, 213, 159, 16);
		contentPane.add(numberhouse_label);
		
		numberhouse_field = new JTextField();
		numberhouse_field.setColumns(10);
		numberhouse_field.setBounds(517, 208, 130, 26);
		contentPane.add(numberhouse_field);
		
		JButton create_profil_btn = new JButton("CREATE");
		create_profil_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value_check_password = 0;
				value_check_username = 2;
				error_label.setText("");
				password = password_field.getText();
				password_confirm = password_confirm_field.getText();
				username = username_field.getText();
				name = name_field.getText();
				lastname = lastname_field.getText();
				phonenumber = Integer.parseInt(phone_number_field.getText());
				mail = mail_field.getText()
;				country = country_field.getText();
				city = city_field.getText();
				region = region_field.getText();
				postcode = Integer.parseInt(postcode_field.getText());
				adress = adress_field.getText();
				number_house = Integer.parseInt(numberhouse_field.getText());
				inami = Integer.parseInt(inami_field.getText());
				check_password(error_label);
				
				if (value_check_password == 1) {
					check_username(username, error_label);
				}
				
				if (value_check_username == 0) {
					GetSQL adddoctor = new GetSQL();
					adddoctor.add_person_db(username, password, lastname, name, phonenumber, mail); //Add person first
					int id_person = adddoctor.get_ID_person(username);
					adddoctor.add_adress_db(country, city, region, postcode, adress, number_house); //Add adress after
					int id_address = adddoctor.get_ID_adress(city, adress, number_house);
					adddoctor.add_doctor_db(inami, id_person, id_address);
					
				}
			}
		});
		
		create_profil_btn.setBounds(546, 319, 117, 29);
		contentPane.add(create_profil_btn);	
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(SystemColor.window);
		panel.setBounds(16, 30, 310, 275);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(353, 30, 310, 275);
		contentPane.add(panel_1);
	}
	
	public void check_password(JLabel error_txt) {
		if (password.equalsIgnoreCase("")) {
			error_txt.setText("Please, complete the password");
		}
		else {
			if (password.equals(password_confirm)) {
				value_check_password = 1;
			}
			else {
				error_txt.setText("Passwords doesn't match");
			}
		}
			
	}
	
	
	public void check_username(String username, JLabel error_txt) {
		if (username.equalsIgnoreCase("")) {
			error_txt.setText("Please, complete the username");
		}
		
		else {
			GetSQL getsql = new GetSQL();
			String value = getsql.check_username(username);
			value_check_username = 0;
			if (value.equalsIgnoreCase("Username_already_used")) {
				error_txt.setText("Username is already used");
				value_check_username = 1;
			}
		}
		
	}
	
	public void close_btn_ActionPerformed() {
		this.dispose();
	}

}
