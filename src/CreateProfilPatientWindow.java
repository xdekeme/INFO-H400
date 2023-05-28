import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class CreateProfilPatientWindow extends JFrame {
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
	private int registernumber;
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
	private JTextField size_field;
	private JTextField weight_field;
	private int value_check_username;
	private JTextField mail_field;
	private JTextField registernumber_field;
	private JLabel error_label = new JLabel("");
	private int check_error_label = 0;



	public CreateProfilPatientWindow() {
		init_component();
		
	}
	
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 527);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(41, 72, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblProfilCreation = new JLabel("PROFILE CREATION - PATIENT");
		lblProfilCreation.setBackground(Color.WHITE);
		lblProfilCreation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProfilCreation.setForeground(Color.RED);
		lblProfilCreation.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfilCreation.setBounds(0, 0, 756, 29);
		contentPane.add(lblProfilCreation);
		

		JLabel lblPersonalInfo = new JLabel("PERSONAL INFO");
		lblPersonalInfo.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPersonalInfo.setForeground(Color.BLUE);
		lblPersonalInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalInfo.setBounds(10, 42, 401, 16);
		contentPane.add(lblPersonalInfo);
		
		JLabel lblAdress = new JLabel("ADDRESS");
		lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdress.setForeground(Color.BLUE);
		lblAdress.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAdress.setBounds(462, 41, 269, 16);
		contentPane.add(lblAdress);
		
		JLabel lblLastname = new JLabel("Last name:");
		lblLastname.setBounds(41, 100, 99, 16);
		contentPane.add(lblLastname);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 157, 99, 16);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password: ");
		lblConfirmPassword.setBounds(41, 185, 159, 16);
		contentPane.add(lblConfirmPassword);
		
		JLabel txt_user = new JLabel("Username: ");
		txt_user.setBounds(41, 129, 99, 16);
		contentPane.add(txt_user);
		
		JLabel sex_label = new JLabel("Sex:");
		sex_label.setBounds(41, 357, 61, 16);
		contentPane.add(sex_label);
		
		name_field = new JTextField();
		name_field.setBounds(178, 67, 130, 26);
		contentPane.add(name_field);
		name_field.setColumns(10);
		
		lastname_field = new JTextField();
		lastname_field.setColumns(10);
		lastname_field.setBounds(178, 95, 130, 26);
		contentPane.add(lastname_field);
		
		username_field = new JTextField();
		username_field.setColumns(10);
		username_field.setBounds(178, 124, 130, 26);
		contentPane.add(username_field);
		
		password_field = new JTextField();
		password_field.setColumns(10);
		password_field.setBounds(178, 152, 130, 26);
		contentPane.add(password_field);
		
		password_confirm_field = new JTextField();
		password_confirm_field.setColumns(10);
		password_confirm_field.setBounds(178, 180, 130, 26);
		contentPane.add(password_confirm_field);
		
		JComboBox<String> sexcombobox = new JComboBox<String>();
		sexcombobox.setBounds(178, 353, 140, 27);
		contentPane.add(sexcombobox);
		sexcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Female", "Male" }));
		
		
		error_label.setForeground(new Color(255, 33, 11));
		error_label.setHorizontalAlignment(SwingConstants.CENTER);
		error_label.setBounds(558, 438, 204, 16);
		contentPane.add(error_label);
		
		
		JButton close_btn = new JButton("CLOSE");
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close_btn_ActionPerformed();
			}
		});
		close_btn.setBounds(6, 462, 117, 29);
		contentPane.add(close_btn);
		
		JLabel PhoneNumber_label = new JLabel("Phone number:");
		PhoneNumber_label.setBounds(41, 214, 159, 16);
		contentPane.add(PhoneNumber_label);
		
		JLabel mail_label = new JLabel("Email:");
		mail_label.setBounds(41, 385, 61, 16);
		contentPane.add(mail_label);
		
		mail_field = new JTextField();
		mail_field.setColumns(10);
		mail_field.setBounds(178, 380, 130, 26);
		contentPane.add(mail_field);
		
		JLabel registernumber_label = new JLabel("Register number:");
		registernumber_label.setBounds(41, 413, 123, 16);
		contentPane.add(registernumber_label);
		
		registernumber_field = new JTextField();
		registernumber_field.setColumns(10);
		registernumber_field.setBounds(178, 408, 130, 26);
		contentPane.add(registernumber_field);
		
		phone_number_field = new JTextField();
		phone_number_field.setColumns(10);
		phone_number_field.setBounds(178, 209, 130, 26);
		contentPane.add(phone_number_field);
		
		JLabel country_label = new JLabel("Country:");
		country_label.setBounds(464, 71, 61, 16);
		contentPane.add(country_label);
		
		country_field = new JTextField();
		country_field.setColumns(10);
		country_field.setBounds(601, 66, 130, 26);
		contentPane.add(country_field);
		
		JLabel city_label = new JLabel("City:");
		city_label.setBounds(464, 99, 99, 16);
		contentPane.add(city_label);
		
		city_field = new JTextField();
		city_field.setColumns(10);
		city_field.setBounds(601, 94, 130, 26);
		contentPane.add(city_field);
		
		JLabel region_label = new JLabel("Region:");
		region_label.setBounds(464, 128, 99, 16);
		contentPane.add(region_label);
		
		region_field = new JTextField();
		region_field.setColumns(10);
		region_field.setBounds(601, 123, 130, 26);
		contentPane.add(region_field);
		
		JLabel postcode_label = new JLabel("Postcode:");
		postcode_label.setBounds(464, 156, 99, 16);
		contentPane.add(postcode_label);
		
		postcode_field = new JTextField();
		postcode_field.setColumns(10);
		postcode_field.setBounds(601, 151, 130, 26);
		contentPane.add(postcode_field);
		
		JLabel adress_label = new JLabel("Address (street):");
		adress_label.setBounds(464, 184, 159, 16);
		contentPane.add(adress_label);
		
		adress_field = new JTextField();
		adress_field.setColumns(10);
		adress_field.setBounds(601, 179, 130, 26);
		contentPane.add(adress_field);
		
		JLabel numberhouse_label = new JLabel("Number:");
		numberhouse_label.setBounds(464, 213, 159, 16);
		contentPane.add(numberhouse_label);
		
		numberhouse_field = new JTextField();
		numberhouse_field.setColumns(10);
		numberhouse_field.setBounds(601, 208, 130, 26);
		contentPane.add(numberhouse_field);
		
		JLabel size_label = new JLabel("Size (in cm):");
		size_label.setBounds(41, 296, 159, 16);
		contentPane.add(size_label);
		
		size_field = new JTextField();
		size_field.setColumns(10);
		size_field.setBounds(178, 291, 130, 26);
		contentPane.add(size_field);
		
		JLabel weigth_label = new JLabel("Weigth (in kg):");
		weigth_label.setBounds(41, 326, 159, 16);
		contentPane.add(weigth_label);
		
		weight_field = new JTextField();
		weight_field.setColumns(10);
		weight_field.setBounds(178, 321, 130, 26);
		contentPane.add(weight_field);
		
		JLabel dob_label = new JLabel("Date of birth:");
		dob_label.setBounds(41, 242, 123, 16);
		contentPane.add(dob_label);
		
		JLabel year_label = new JLabel("Year");
		year_label.setBounds(173, 268, 42, 16);
		contentPane.add(year_label);
		
		JComboBox<Integer> yearcombobox = new JComboBox<Integer>();
		Integer[] list_year = new Integer[123];
		for (int i=0; i<123; i++) {
			list_year[i] = i + 1900;
		}
		yearcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(list_year));
		yearcombobox.setBounds(143, 243, 86, 27);
		contentPane.add(yearcombobox);
		
		JLabel separator_1 = new JLabel("/");
		separator_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		separator_1.setBounds(229, 246, 21, 16);
		contentPane.add(separator_1);
		
		JComboBox<Integer> monthcombobox = new JComboBox<Integer>();
		Integer[] list_month = new Integer[12];
		for (int i=0; i<12; i++) {
			list_month[i] = i + 1;
		}
		monthcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(list_month));
		monthcombobox.setBounds(246, 243, 67, 27);
		contentPane.add(monthcombobox);
		
		JLabel separator_2 = new JLabel("/");
		separator_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		separator_2.setBounds(318, 246, 21, 16);
		contentPane.add(separator_2);
		
		JComboBox<Integer> daycombobox = new JComboBox<Integer>();
		Integer[] list_day = new Integer[31];
		for (int i=0; i<31; i++) {
			list_day[i] = i + 1;
		}
		daycombobox.setModel(new javax.swing.DefaultComboBoxModel<>(list_day));
		daycombobox.setBounds(329, 243, 67, 27);
		contentPane.add(daycombobox);
		
		JLabel month_label = new JLabel("Month");
		month_label.setBounds(260, 268, 42, 16);
		contentPane.add(month_label);
		
		JLabel day_label = new JLabel("Day");
		day_label.setBounds(350, 268, 42, 16);
		contentPane.add(day_label);
		
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
				year_dob = yearcombobox.getSelectedItem().toString();
				month_dob = monthcombobox.getSelectedItem().toString();
				day_dob = daycombobox.getSelectedItem().toString();
				sex = sexcombobox.getSelectedItem().toString();
				size = Integer.parseInt(size_field.getText());
				weight = Integer.parseInt(weight_field.getText());
				registernumber = Integer.parseInt(registernumber_field.getText());
				String date_dob = year_dob + "-" + month_dob + "-" + day_dob;
				check_password(error_label);
				
				if (value_check_password == 1) {
					check_username(username, error_label);
				}
				
				if (value_check_username == 0) {
					check_error_label = 0;
					GetSQL addpatient = new GetSQL();
					addpatient.add_person_db(username, password, lastname, name, phonenumber, mail); //Add person first
					int id_person = addpatient.get_ID_person(username);
					addpatient.add_adress_db(country, city, region, postcode, adress, number_house); //Add adress after
					int id_address = addpatient.get_ID_adress(city, adress, number_house);
					addpatient.add_patient_db(date_dob, sex, size, weight, registernumber, id_person, id_address);
					if (check_error_label == 0) {
						error_label.setText("Profile created!");
						close_btn_ActionPerformed();
					}
					
				}
			}
		});
		
		create_profil_btn.setBounds(612, 462, 117, 29);
		contentPane.add(create_profil_btn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(SystemColor.window);
		panel.setBounds(10, 29, 401, 418);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(434, 29, 322, 229);
		contentPane.add(panel_1);
		
	
		
		
		
	}
	
	public void check_password(JLabel error_txt) {
		if (password.equalsIgnoreCase("")) {
			 check_error_label = 1;
			error_txt.setText("Please, complete the password");
		}
		else {
			if (password.equals(password_confirm)) {
				value_check_password = 1;
			}
			else {
				check_error_label = 1;
				error_txt.setText("Passwords don't match");
			}
		}
			
	}
	
	
	public void check_username(String username, JLabel error_txt) {
		if (username.equalsIgnoreCase("")) {
			check_error_label = 1;
			error_txt.setText("Please, complete the username");
		}
		
		else {
			GetSQL getsql = new GetSQL();
			String value = getsql.check_username(username);
			value_check_username = 0;
			if (value.equalsIgnoreCase("Username_already_used")) {
				check_error_label = 1;
				error_txt.setText("Username is already used");
				value_check_username = 1;
			}
		}
		
	}
		
	
	
	public void close_btn_ActionPerformed() {
		this.dispose();
	}
}
