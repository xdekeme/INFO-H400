import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ModifyInfoPatientWindow extends JFrame {

	private JPanel contentPane;
	private JTextField firstname_field;
	private JTextField lastname_field;
	private int id_person;
	private JTextField password_field;
	private JTextField mail_field;
	private JTextField phone_field;
	private JTextField size_field;
	private JTextField weight_field;
	private JTextField country_field;
	private JTextField city_field;
	private JTextField region_field;
	private JTextField postcode_field;
	private JTextField street_field;
	private JTextField number_field;
	
	
	 
	public ModifyInfoPatientWindow(int id_person_fct) {
		id_person = id_person_fct;
		init_component();
	}
	
	
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GetSQL getinfopatient = new GetSQL();
    	List<String> info_patient = getinfopatient.get_info_patient(id_person);
    	List<String> adress_patient = getinfopatient.get_adress_patient(id_person);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> sexcomboBox = new JComboBox<String>();
		sexcomboBox.setBounds(164, 364, 219, 27);
		contentPane.add(sexcomboBox);
		sexcomboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Female", "Male" }));
		sexcomboBox.setSelectedItem(info_patient.get(5));
		
		JLabel firstname_label = new JLabel("Firstname: ");
		firstname_label.setFont(new Font("Arial", Font.PLAIN, 15));
		firstname_label.setBounds(37, 158, 96, 16);
		contentPane.add(firstname_label);
		
		firstname_field = new JTextField(info_patient.get(1));
		firstname_field.setBackground(new Color(192, 192, 192));
		firstname_field.setForeground(new Color(0, 0, 255));
		firstname_field.setFont(new Font("Arial", Font.PLAIN, 15));
		firstname_field.setBounds(164, 152, 219, 26);
		contentPane.add(firstname_field);
		firstname_field.setColumns(10);
		
		JLabel lastname_label = new JLabel("Lastname: ");
		lastname_label.setFont(new Font("Arial", Font.PLAIN, 15));
		lastname_label.setBounds(37, 188, 96, 16);
		contentPane.add(lastname_label);
		
		lastname_field = new JTextField(info_patient.get(0));
		lastname_field.setBackground(new Color(192, 192, 192));
		lastname_field.setForeground(new Color(0, 0, 255));
		lastname_field.setFont(new Font("Arial", Font.PLAIN, 15));
		lastname_field.setColumns(10);
		lastname_field.setBounds(164, 185, 219, 26);
		contentPane.add(lastname_field);
		
		JButton update_btn = new JButton("Update");
		update_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_btn_ActionPerformed();		
			}
		});
		
		
		update_btn.setBounds(416, 454, 389, 67);
		contentPane.add(update_btn);
		
		JButton close_btn = new JButton("Close");
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close_btnActionPerformed();
			}
		});
		close_btn.setBounds(6, 492, 117, 29);
		contentPane.add(close_btn);
		
		JLabel password_label = new JLabel("Password:");
		password_label.setFont(new Font("Arial", Font.PLAIN, 15));
		password_label.setBounds(37, 218, 86, 16);
		contentPane.add(password_label);
		
		JLabel mail_label = new JLabel("Mail:");
		mail_label.setFont(new Font("Arial", Font.PLAIN, 15));
		mail_label.setBounds(37, 248, 86, 16);
		contentPane.add(mail_label);
		
		JLabel phonenumber_label = new JLabel("Phone number:");
		phonenumber_label.setFont(new Font("Arial", Font.PLAIN, 15));
		phonenumber_label.setBounds(37, 278, 127, 16);
		contentPane.add(phonenumber_label);
		
		JLabel size_label = new JLabel("Size (in cm):");
		size_label.setFont(new Font("Arial", Font.PLAIN, 15));
		size_label.setBounds(37, 308, 86, 16);
		contentPane.add(size_label);
		
		JLabel weight_label = new JLabel("Weight (in kg):");
		weight_label.setFont(new Font("Arial", Font.PLAIN, 15));
		weight_label.setBounds(37, 338, 108, 16);
		contentPane.add(weight_label);
		
		JLabel sex_label = new JLabel("Sex:");
		sex_label.setFont(new Font("Arial", Font.PLAIN, 15));
		sex_label.setBounds(37, 368, 127, 16);
		contentPane.add(sex_label);
		
		password_field = new JTextField(info_patient.get(2));
		password_field.setBackground(new Color(192, 192, 192));
		password_field.setForeground(new Color(0, 0, 255));
		password_field.setFont(new Font("Arial", Font.PLAIN, 15));
		password_field.setColumns(10);
		password_field.setBounds(164, 213, 219, 26);
		contentPane.add(password_field);
		
		mail_field = new JTextField(info_patient.get(4));
		mail_field.setBackground(new Color(192, 192, 192));
		mail_field.setForeground(new Color(0, 0, 255));
		mail_field.setFont(new Font("Arial", Font.PLAIN, 15));
		mail_field.setColumns(10);
		mail_field.setBounds(164, 243, 219, 26);
		contentPane.add(mail_field);
		
		phone_field = new JTextField(info_patient.get(3));
		phone_field.setBackground(new Color(192, 192, 192));
		phone_field.setForeground(new Color(0, 0, 255));
		phone_field.setFont(new Font("Arial", Font.PLAIN, 15));
		phone_field.setColumns(10);
		phone_field.setBounds(164, 273, 219, 26);
		contentPane.add(phone_field);
		
		size_field = new JTextField(info_patient.get(6));
		size_field.setBackground(new Color(192, 192, 192));
		size_field.setForeground(new Color(0, 0, 255));
		size_field.setFont(new Font("Arial", Font.PLAIN, 15));
		size_field.setColumns(10);
		size_field.setBounds(164, 303, 219, 26);
		contentPane.add(size_field);
		
		weight_field = new JTextField(info_patient.get(7));
		weight_field.setBackground(new Color(192, 192, 192));
		weight_field.setForeground(new Color(0, 0, 255));
		weight_field.setFont(new Font("Arial", Font.PLAIN, 15));
		weight_field.setColumns(10);
		weight_field.setBounds(164, 333, 219, 26);
		contentPane.add(weight_field);
		
		JLabel lblNewLabel = new JLabel("PERSONAL INFOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(16, 71, 389, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblModificationsInformation = new JLabel("MODIFICATIONS INFORMATION");
		lblModificationsInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificationsInformation.setForeground(new Color(0, 0, 0));
		lblModificationsInformation.setFont(new Font("Arial", Font.BOLD, 20));
		lblModificationsInformation.setBounds(6, 0, 764, 49);
		contentPane.add(lblModificationsInformation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(Color.WHITE);
		panel.setBounds(16, 71, 389, 345);
		contentPane.add(panel);
		
		JLabel country_label = new JLabel("Country:");
		country_label.setFont(new Font("Arial", Font.PLAIN, 15));
		country_label.setBounds(484, 158, 86, 16);
		contentPane.add(country_label);
		
		JLabel city_label = new JLabel("City:");
		city_label.setFont(new Font("Arial", Font.PLAIN, 15));
		city_label.setBounds(484, 188, 99, 16);
		contentPane.add(city_label);
		
		JLabel region_label = new JLabel("Region:");
		region_label.setFont(new Font("Arial", Font.PLAIN, 15));
		region_label.setBounds(484, 223, 86, 16);
		contentPane.add(region_label);
		
		JLabel postcode_label = new JLabel("Postcode:");
		postcode_label.setFont(new Font("Arial", Font.PLAIN, 15));
		postcode_label.setBounds(484, 251, 77, 16);
		contentPane.add(postcode_label);
		
		JLabel street_label = new JLabel("Street:");
		street_label.setFont(new Font("Arial", Font.PLAIN, 15));
		street_label.setBounds(484, 283, 64, 16);
		contentPane.add(street_label);
		
		JLabel number_label = new JLabel("Number:");
		number_label.setFont(new Font("Arial", Font.PLAIN, 15));
		number_label.setBounds(484, 313, 77, 16);
		contentPane.add(number_label);
		
		country_field = new JTextField(adress_patient.get(0).toUpperCase());
		country_field.setForeground(Color.BLUE);
		country_field.setFont(new Font("Arial", Font.PLAIN, 15));
		country_field.setColumns(10);
		country_field.setBackground(Color.LIGHT_GRAY);
		country_field.setBounds(564, 153, 206, 26);
		contentPane.add(country_field);
		
		city_field = new JTextField(adress_patient.get(1));
		city_field.setForeground(Color.BLUE);
		city_field.setFont(new Font("Arial", Font.PLAIN, 15));
		city_field.setColumns(10);
		city_field.setBackground(Color.LIGHT_GRAY);
		city_field.setBounds(564, 183, 206, 26);
		contentPane.add(city_field);
		
		region_field = new JTextField(adress_patient.get(2));
		region_field.setForeground(Color.BLUE);
		region_field.setFont(new Font("Arial", Font.PLAIN, 15));
		region_field.setColumns(10);
		region_field.setBackground(Color.LIGHT_GRAY);
		region_field.setBounds(564, 218, 206, 26);
		contentPane.add(region_field);
		
		postcode_field = new JTextField(adress_patient.get(3));
		postcode_field.setForeground(Color.BLUE);
		postcode_field.setFont(new Font("Arial", Font.PLAIN, 15));
		postcode_field.setColumns(10);
		postcode_field.setBackground(Color.LIGHT_GRAY);
		postcode_field.setBounds(564, 248, 206, 26);
		contentPane.add(postcode_field);
		
		street_field = new JTextField(adress_patient.get(4));
		street_field.setForeground(Color.BLUE);
		street_field.setFont(new Font("Arial", Font.PLAIN, 15));
		street_field.setColumns(10);
		street_field.setBackground(Color.LIGHT_GRAY);
		street_field.setBounds(564, 278, 206, 26);
		contentPane.add(street_field);
		
		number_field = new JTextField(adress_patient.get(5));
		number_field.setForeground(Color.BLUE);
		number_field.setFont(new Font("Arial", Font.PLAIN, 15));
		number_field.setColumns(10);
		number_field.setBackground(Color.LIGHT_GRAY);
		number_field.setBounds(564, 308, 206, 26);
		contentPane.add(number_field);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(Color.BLUE);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddress.setBounds(459, 71, 326, 60);
		contentPane.add(lblAddress);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(459, 71, 331, 294);
		contentPane.add(panel_1);
		
	}
	
	public void update_btn_ActionPerformed() {
		GetSQL wind = new GetSQL();
		GetSQL getinfopatient = new GetSQL();
    	List<String> info_patient = getinfopatient.get_info_patient(id_person);
    	List<String> adress_patient = getinfopatient.get_adress_patient(id_person);
    	//Personal info
		String new_lastname = lastname_field.getText().toString();
		String new_name = firstname_field.getText().toString();
		String new_password = password_field.getText().toString();
		String new_mail =  mail_field.getText().toString();
		String new_phone =  phone_field.getText().toString();
		String new_size = size_field.getText().toString();
		String new_weight =  weight_field.getText().toString();;
		//Adress info
		String new_country =  country_field.getText().toString();
		String new_city =  city_field.getText().toString();
		String new_region = region_field.getText().toString();
		String new_postcode =  postcode_field.getText().toString();
		String new_street =  street_field.getText().toString();
		String new_number =  number_field.getText().toString();

		//Personal info
		if (info_patient.get(1).equalsIgnoreCase(new_name)) {
		}
		else {
			wind.update_patient_name(id_person, new_name);
		}
		
		if (info_patient.get(0).equalsIgnoreCase(new_lastname)) {
		}
		else {
			wind.update_patient_lastname(id_person, new_lastname);
		}
		
		if (info_patient.get(2).equalsIgnoreCase(new_password)) {
		}
		else {
			wind.update_patient_password(id_person, new_password);
		}
		
		if (info_patient.get(4).equalsIgnoreCase(new_mail)) {
		}
		else {
			wind.update_patient_mail(id_person, new_mail);
		}
		
		if (info_patient.get(3).equalsIgnoreCase(new_phone)) {
		}
		else {
			wind.update_patient_phone(id_person, new_phone);
		}
		
		if (info_patient.get(6).equalsIgnoreCase(new_size)) {
		}
		else {
			wind.update_patient_size(id_person, new_size);
		}
		
		if (info_patient.get(7).equalsIgnoreCase(new_weight)) {
		}
		else {
			wind.update_patient_weight(id_person, new_weight);
		}
		
		
		//Adress info
		if (adress_patient.get(0).equalsIgnoreCase(new_country)) {
		}
		else {
			wind.update_adress_country(id_person, new_country);
		}
		
		if (adress_patient.get(1).equalsIgnoreCase(new_city)) {
		}
		else {
			wind.update_adress_city(id_person, new_city);
		}
		
		if (adress_patient.get(2).equalsIgnoreCase(new_region)) {
		}
		else {
			wind.update_adress_region(id_person, new_region);
		}
		
		if (adress_patient.get(3).equalsIgnoreCase(new_postcode)) {
		}
		else {
			wind.update_adress_postcode(id_person, new_postcode);
		}
		
		if (adress_patient.get(4).equalsIgnoreCase(new_street)) {
		}
		else {
			wind.update_adress_street(id_person, new_street);
		}
		
		if (adress_patient.get(5).equalsIgnoreCase(new_number)) {
		}
		else {
			wind.update_adress_number(id_person, new_number);
		}
		
		
	}
	
	
	
	public void close_btnActionPerformed() {
		this.dispose();
		MainWindow_v2 wind = new MainWindow_v2();
		wind.main(null);
	}
	
}
