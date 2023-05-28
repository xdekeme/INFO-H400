import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
 
public class MainWindow_v2 {
	
	public MainWindow_v2() {
	}
	GetSQL windsql = new GetSQL();
    final static String Home = "Home";
    final static String My_Infos = "My Infos";
    final static String MY_PRESCRI = "My Prescriptions";
    final static String MY_Vaccine = "My Vaccines";
    private JPanel home;
    private JPanel info;
    private JPanel prescri;
    private JPanel vaccine;
    private static int id_person;
    private static int id_patient;
    public JLabel firstname_txt = new JLabel("");
    private JLabel lastname_txt;
    private String firstname_home;
    private JTable table;
    private JTable table_test;
    private JTable table_vaccine;
    private JTabbedPane tabbedPane = new JTabbedPane();
    public static JFrame frame = new JFrame("Menu - PATIENT");
    private JTextField drugname_txt;
    private List<String> info_patient;
    private JTextField textField_vaccine;
    public Object[][] get_prescri_patient_object; 
    public Object[][] get_vaccin_patient_object; 
 
    
    public void addComponentToPane(Container pane) {
    	GetSQL windsql = new GetSQL();
        id_patient = windsql.get_ID_patient(id_person);
        get_prescri_patient_object = windsql.get_prescri_patient_object(id_patient);
        get_vaccin_patient_object = windsql.get_vaccin_patient_object(id_patient);
        
        
        //Create the "cards".
        home = new JPanel() {};
        home.setBackground(Color.WHITE);
        home_components();
        
        info = new JPanel() {};
        info.setBackground(Color.WHITE);
        info_components();
        
        prescri = new JPanel() {};
        prescri_components();
        
        vaccine = new JPanel() {};
        vaccine_components();
 
        tabbedPane.addTab(Home, home);
        
        tabbedPane.addTab(My_Infos, info);
        
        tabbedPane.addTab(MY_PRESCRI, prescri);
        
        
        
        tabbedPane.addTab(MY_Vaccine, vaccine);
        
        
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
    
    public void home_components() {
        home.setLayout(null);
        
        
        
        GetSQL getinfopatient = new GetSQL();
    	info_patient = getinfopatient.get_info_patient(id_person);
    	firstname_home = info_patient.get(1);
    	
    	JLabel reminder_drug = new JLabel("Reminder - Drug:");
        reminder_drug.setFont(new Font("Arial", Font.BOLD, 16));
        reminder_drug.setBounds(21, 83, 214, 24);
        home.add(reminder_drug);
        
    	JScrollPane scrollPane_reminderdrug = new JScrollPane();
    	scrollPane_reminderdrug.setBounds(21, 119, 787, 132);
		home.add(scrollPane_reminderdrug);
    	
    	JList table_reminderdrug = new JList();
    	table_reminderdrug.setBounds(21, 149, 256, 128);
    	scrollPane_reminderdrug.setViewportView(table_reminderdrug);
    	
    	add_reminderdrug(table_reminderdrug);
        
        JLabel title = new JLabel("");
        title.setText("Welcome, " + firstname_home + " !");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial Black", Font.BOLD, 25));
        title.setBounds(0, 6, 823, 55);
        home.add(title);
        
        JLabel reminder_vaccine = new JLabel("Reminder - Vaccine:");
        reminder_vaccine.setFont(new Font("Arial", Font.BOLD, 16));
        reminder_vaccine.setBounds(21, 276, 214, 24);
        home.add(reminder_vaccine);
        
        JScrollPane scrollPane_remindervaccine = new JScrollPane();
        scrollPane_remindervaccine.setBounds(21, 312, 787, 132);
        home.add(scrollPane_remindervaccine);
        
        JButton logout_btn = new JButton("Log out");
        logout_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		logout_btnActionPerformed();
        	}
        });
        
        JList table_remindervaccine = new JList();
        table_remindervaccine.setBounds(21, 312, 783, 128);
        scrollPane_remindervaccine.setViewportView(table_remindervaccine);
        
        add_remindervaccin(table_remindervaccine);
        logout_btn.setBounds(6, 481, 123, 39);
        home.add(logout_btn);
    }
    
    
    public void info_components() {
    	info.setLayout(null);
    	GetSQL getinfopatient = new GetSQL();
    	List<String> adress_patient = getinfopatient.get_adress_patient(id_person);
    	
    	JLabel number_label = new JLabel("Number:");
    	number_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	number_label.setBounds(519, 300, 77, 16);
    	info.add(number_label);
    	
    	JLabel country_txt = new JLabel("");
    	country_txt.setText(adress_patient.get(0).toUpperCase());
    	country_txt.setForeground(new Color(0, 0, 205));
    	country_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	country_txt.setBounds(628, 109, 168, 16);
    	info.add(country_txt);
    	
    	JLabel city_label = new JLabel("City:");
    	city_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	city_label.setBounds(519, 146, 99, 16);
    	info.add(city_label);
    	
    	JLabel title2 = new JLabel("ADDRESS");
    	title2.setHorizontalAlignment(SwingConstants.CENTER);
    	title2.setForeground(Color.BLUE);
    	title2.setFont(new Font("Arial", Font.BOLD, 20));
    	title2.setBounds(494, 23, 318, 49);
    	info.add(title2);
    	
    	JLabel number_txt = new JLabel("");
    	number_txt.setText(adress_patient.get(5));
    	number_txt.setForeground(new Color(0, 0, 205));
    	number_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	number_txt.setBounds(628, 300, 184, 16);
    	info.add(number_txt);
    	
    	JLabel street_txt = new JLabel();
    	street_txt.setText(adress_patient.get(4));
    	street_txt.setForeground(new Color(0, 0, 205));
    	street_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	street_txt.setBounds(628, 261, 184, 16);
    	info.add(street_txt);
    	
    	JLabel region_label = new JLabel("Region:");
    	region_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	region_label.setBounds(519, 186, 86, 16);
    	info.add(region_label);
    	
    	JLabel country_label = new JLabel("Country:");
    	country_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	country_label.setBounds(519, 109, 86, 16);
    	info.add(country_label);
    	
    	JLabel region_txt = new JLabel();
    	region_txt.setText(adress_patient.get(2));
    	region_txt.setForeground(new Color(0, 0, 205));
    	region_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	region_txt.setBounds(628, 186, 184, 16);
    	info.add(region_txt);
    	
    	JLabel street_label = new JLabel("Street:");
    	street_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	street_label.setBounds(519, 261, 64, 16);
    	info.add(street_label);
    	
    	JLabel postcode_label = new JLabel("Postcode:");
    	postcode_label.setFont(new Font("Arial", Font.PLAIN, 15));
    	postcode_label.setBounds(519, 222, 77, 16);
    	info.add(postcode_label);
    	
    	JLabel city_txt = new JLabel("");
    	city_txt.setText(adress_patient.get(1));
    	city_txt.setForeground(new Color(0, 0, 205));
    	city_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	city_txt.setBounds(628, 146, 184, 16);
    	info.add(city_txt);
    	
    	JLabel postcode_txt = new JLabel();
    	postcode_txt.setText(adress_patient.get(3));
    	postcode_txt.setForeground(new Color(0, 0, 205));
    	postcode_txt.setFont(new Font("Arial", Font.PLAIN, 15));
    	postcode_txt.setBounds(628, 222, 184, 16);
    	info.add(postcode_txt);
    	
    	JLabel firstname_label = new JLabel("Firstname: ");
    	firstname_label.setFont(new Font("Arial", Font.PLAIN, 15));
        firstname_label.setBounds(42, 109, 86, 16);
        info.add(firstname_label);
        
        firstname_txt = new JLabel(info_patient.get(1));
        firstname_txt.setForeground(new Color(0, 0, 205));
        firstname_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        firstname_txt.setBounds(181, 109, 210, 16);
        info.add(firstname_txt);
        
        JLabel lastname_label = new JLabel("Last name:");
        lastname_label.setFont(new Font("Arial", Font.PLAIN, 15));
        lastname_label.setBounds(42, 146, 86, 16);
        info.add(lastname_label);
        
        lastname_txt = new JLabel(info_patient.get(0).toUpperCase());
        lastname_txt.setForeground(new Color(0, 0, 205));
        lastname_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        lastname_txt.setBounds(181, 146, 210, 16);
        info.add(lastname_txt);
        
        JLabel password_label = new JLabel("Password:");
        password_label.setFont(new Font("Arial", Font.PLAIN, 15));
        password_label.setBounds(42, 186, 86, 16);
        info.add(password_label);
        
        JLabel password_txt = new JLabel("");
        password_txt.setForeground(new Color(0, 0, 205));
        password_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        password_txt.setText(info_patient.get(2));
        password_txt.setBounds(181, 186, 210, 16);
        info.add(password_txt);
        
        JLabel phonenumber_label = new JLabel("Phone number:");
        phonenumber_label.setFont(new Font("Arial", Font.PLAIN, 15));
        phonenumber_label.setBounds(42, 261, 127, 16);
        info.add(phonenumber_label);
        
        JLabel phonenumber_txt = new JLabel("");
        phonenumber_txt.setForeground(new Color(0, 0, 205));
        phonenumber_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        phonenumber_txt.setText(info_patient.get(3));
        phonenumber_txt.setBounds(181, 261, 210, 16);
        info.add(phonenumber_txt);
        
        JLabel size_label = new JLabel("Size (in cm):");
        size_label.setFont(new Font("Arial", Font.PLAIN, 15));
        size_label.setBounds(42, 300, 86, 16);
        info.add(size_label);
        
        JLabel size_txt = new JLabel();
        size_txt.setForeground(new Color(0, 0, 205));
        size_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        size_txt.setText(info_patient.get(6));
        size_txt.setBounds(181, 300, 210, 16);
        info.add(size_txt);
        
        JLabel weight_label = new JLabel("Weight (in kg):");
        weight_label.setFont(new Font("Arial", Font.PLAIN, 15));
        weight_label.setBounds(42, 337, 108, 16);
        info.add(weight_label);
        
        JLabel wieght_txt = new JLabel("");
        wieght_txt.setForeground(new Color(0, 0, 205));
        wieght_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        wieght_txt.setText(info_patient.get(7));
        wieght_txt.setBounds(181, 337, 210, 16);
        info.add(wieght_txt);
        
        JLabel registernumber_label = new JLabel("Register number:");
        registernumber_label.setFont(new Font("Arial", Font.PLAIN, 15));
        registernumber_label.setBounds(42, 377, 127, 16);
        info.add(registernumber_label);
        
        JLabel registernumber_txt = new JLabel("");
        registernumber_txt.setForeground(new Color(0, 0, 205));
        registernumber_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        registernumber_txt.setText(info_patient.get(8));
        registernumber_txt.setBounds(181, 377, 210, 16);
        info.add(registernumber_txt);
        
        JLabel sex_label = new JLabel("Sex:");
        sex_label.setFont(new Font("Arial", Font.PLAIN, 15));
        sex_label.setBounds(42, 414, 127, 16);
        info.add(sex_label);
        
        JLabel sex_txt = new JLabel("");
        sex_txt.setForeground(new Color(0, 0, 205));
        sex_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        sex_txt.setText(info_patient.get(5));
        sex_txt.setBounds(181, 414, 210, 16);
        info.add(sex_txt);
        
        JLabel mail_label = new JLabel("Mail:");
        mail_label.setFont(new Font("Arial", Font.PLAIN, 15));
        mail_label.setBounds(42, 222, 86, 16);
        info.add(mail_label);
        
        JLabel mail_txt = new JLabel("");
        mail_txt.setForeground(new Color(0, 0, 205));
        mail_txt.setFont(new Font("Arial", Font.PLAIN, 15));
        mail_txt.setText(info_patient.get(4));
        mail_txt.setBounds(181, 222, 210, 16);
        info.add(mail_txt);
        
        JButton modify_btn = new JButton("MODIFY");
        modify_btn.setOpaque(true);
        modify_btn.setBorderPainted(false);
        modify_btn.setBackground(new Color(128, 128, 128));
        modify_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modify_btn_ActionPerformed();
        	}
        });
        
        modify_btn.setFont(new Font("Arial", Font.BOLD, 18));
        modify_btn.setBounds(655, 454, 168, 66);
        GetRessource modify_logo = new GetRessource();
		Image mod_logo = modify_logo.GetImage("/gear.png");
		modify_btn.setIcon(new ImageIcon(mod_logo));
        info.add(modify_btn);
        
        JLabel lblNewLabel = new JLabel("PERSONAL INFOS");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(17, 23, 374, 49);
        info.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panel.setBounds(17, 24, 415, 429);
        info.add(panel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(494, 23, 318, 304);
        info.add(panel_1);
    	
    }
    
    public void prescri_components() {
        
    	GetRessource getressource_img = new GetRessource();
		Image drug_img= getressource_img.GetImage("/drug.png");
		
    	
        prescri.setLayout(null);
        
      //TEST NEW TABLE
        String[] columnNames = {"Received date", "Drug's Name", "Experation date", "Number per day", "Doctor's name" };
        table_test = new JTable(get_prescri_patient_object, columnNames);
    
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 124, 817, 334);
        scrollPane.setViewportView(table_test); //-----------------------------------LINE HERE TO COMMENT IF WE WANT TO CHANGE THE DESIGN!!!!
        prescri.add(scrollPane);
        
        JLabel find_label = new JLabel("Find Drug's name:");
        find_label.setHorizontalAlignment(SwingConstants.CENTER);
        find_label.setBounds(6, 73, 129, 16);
        prescri.add(find_label);
        
        drugname_txt = new JTextField();
        drugname_txt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        drugname_txt.setBounds(147, 60, 439, 34);
        prescri.add(drugname_txt);
        drugname_txt.setColumns(10);
        
        
        JLabel noresult_label = new JLabel("");
        noresult_label.setForeground(Color.RED);
        noresult_label.setFont(new Font("Arial", Font.PLAIN, 15));
        noresult_label.setHorizontalAlignment(SwingConstants.CENTER);
        noresult_label.setBounds(669, 96, 154, 16);
        prescri.add(noresult_label);
        
        JButton finddrug_btn = new JButton("FIND");
        finddrug_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String txt_user_drug = drugname_txt.getText();
        		find_drugbtnActionPerformed(scrollPane, txt_user_drug, get_prescri_patient_object, noresult_label); 
        	}
        });
        
        finddrug_btn.setFont(new Font("Arial", Font.BOLD, 20));
        finddrug_btn.setBounds(659, 19, 164, 75);
        finddrug_btn.setIcon(new ImageIcon(drug_img));
        prescri.add(finddrug_btn);
        
        
        JButton show_prescri_btn = new JButton("SHOW PRESCRIPTION");
        show_prescri_btn.setFont(new Font("Arial", Font.BOLD, 20));
        show_prescri_btn.setBounds(6, 470, 817, 50);
        show_prescri_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int num_row = table_test.getSelectedRow();
        		find_show_prescri_btnActionPerformed(num_row, get_prescri_patient_object);
        	}
        });
        prescri.add(show_prescri_btn);
    }
    
    public void vaccine_components() {
    	vaccine.setLayout(null);
    	GetRessource getressource_img = new GetRessource();
		Image vacc_img= getressource_img.GetImage("/drug.png");
		
    	GetSQL windsql = new GetSQL();
        
    	String[] columnNames = {"Received date", "Vaccine's Name", "Deadline", "Doctor's name" };
    	table_vaccine = new JTable(get_vaccin_patient_object, columnNames);
    
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 124, 817, 334);
        scrollPane.setViewportView(table_vaccine); 
        vaccine.add(scrollPane);
    	
    	JLabel find_label = new JLabel("Find Vaccine's name:");
        find_label.setHorizontalAlignment(SwingConstants.CENTER);
        find_label.setBounds(0, 75, 169, 16);
        vaccine.add(find_label);
        
        JLabel noresult_label = new JLabel("");
        noresult_label.setHorizontalAlignment(SwingConstants.CENTER);
        noresult_label.setForeground(Color.RED);
        noresult_label.setFont(new Font("Arial", Font.PLAIN, 15));
        noresult_label.setBounds(653, 96, 154, 16);
        vaccine.add(noresult_label);
        
        textField_vaccine = new JTextField();
        textField_vaccine.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        textField_vaccine.setColumns(10);
        textField_vaccine.setBounds(181, 66, 439, 34);
        vaccine.add(textField_vaccine);
        
        JButton findvaccine_btn = new JButton("FIND");
        findvaccine_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name_vacc = textField_vaccine.getText();
        		findvaccin_ActionPerformed(scrollPane, name_vacc, get_vaccin_patient_object, noresult_label);
        	}
        });
        findvaccine_btn.setFont(new Font("Arial", Font.BOLD, 20));
        findvaccine_btn.setBounds(653, 21, 164, 75);
        vaccine.add(findvaccine_btn);
        
        JScrollPane scrollPane_vacc = new JScrollPane();
        scrollPane_vacc.setBounds(6, 121, 817, 334);
        vaccine.add(scrollPane_vacc);
    }
    
    
    
    
    
    public void add_reminderdrug(JList table) {
    	GetSQL windsql = new GetSQL();
    	List<List<String>> all_prescri = windsql.get_prescri_patient(id_patient);
    	List<String> final_list = new ArrayList<String>();
    	String value = null;
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	LocalDate date_today = LocalDate.now();
    	
    	for (int i=0; i<all_prescri.size(); i++) {
    		
			try {
				String date_string = all_prescri.get(i).get(2);
				String dateOnlyStr = date_string; 
				Date date = dateFormat.parse(dateOnlyStr); 

				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if ( date_today.compareTo(localDate) <= 0) { //>0 date1 posterieur ==0 meme jour <0 antérieur
    				
	    			value = "Expiration date: " +  all_prescri.get(i).get(2) + " - Drug name: " + all_prescri.get(i).get(1) + " - Number per day: " +  all_prescri.get(i).get(3);
	    			final_list.add(value);
	    		}
	    		
	    		value = "";
	    	
		    	
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Unable to find the date");
			}

    	}	
    	
    	if (final_list.size() > 0) {
    		Collections.sort(final_list);
    		table.setListData(final_list.toArray(new String[final_list.size()]));
    		
    	}
    	
    	if (final_list.size() == 0) {
    		value = "No drug";
    		final_list.add(value);
    		table.setListData(final_list.toArray(new String[final_list.size()]));
    	}
    }
    
    
    public void add_remindervaccin(JList table) {
    	GetSQL windsql = new GetSQL();
    	List<List<String>> all_vaccin = windsql.get_vaccin_patient(id_patient);
    	List<String> final_list = new ArrayList<String>();
    	String value = null;
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	LocalDate date_today = LocalDate.now();
    	
    	for (int i=0; i<all_vaccin.size(); i++) {
    		
			try {
				String date_string = all_vaccin.get(i).get(2);
				String dateOnlyStr = date_string; 
				Date date = dateFormat.parse(dateOnlyStr); 

				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if ( date_today.compareTo(localDate) <= 0) { //>0 date1 posterieur ==0 meme jour <0 antérieur
    				
	    			value = "Deadline: " +  all_vaccin.get(i).get(2) + " - Vaccine name: " + all_vaccin.get(i).get(1);
	    			final_list.add(value);
	    		}
	    		
	    		value = "";
	    	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Unable to find the date");
			}

    	}	
    	
    	
    	if (final_list.size() > 0) {
    		Collections.sort(final_list);
    		table.setListData(final_list.toArray(new String[final_list.size()]));
    		
    	}
    	
    	if (final_list.size() == 0) {
    		value = "No vaccine";
    		final_list.add(value);
    		table.setListData(final_list.toArray(new String[final_list.size()]));
    	}
    }
    
    
    
    
    public void get_id_user(int id_person_fct) {
    	id_person = id_person_fct;
    }
    
    
	public void findvaccin_ActionPerformed(JScrollPane scrollPane, String vacc_name, Object[][] data, JLabel noresult_txt) {
		noresult_txt.setText("");
		GetSQL windsql = new GetSQL();
		Component[] components = scrollPane.getViewport().getComponents();

		
		scrollPane.repaint();
		
		String[] columnNames = {"Received date", "Vaccine's Name", "Deadline", "Doctor's name" };

		if (vacc_name.equalsIgnoreCase("")) {
			data =windsql.get_vaccin_patient_object(id_patient);
			table_vaccine = new JTable(data, columnNames);
			scrollPane.setViewportView(table_vaccine); 
			scrollPane.repaint();
			
		} else {
			data = windsql.get_vaccin_patient_object_specific_drugname(id_patient, vacc_name);
			table_vaccine= new JTable(data, columnNames);
			scrollPane.setViewportView(table_vaccine); 
			if(data.length == 0) {
				noresult_txt.setText("No result");
			}
		}
		
	}
	
	public void find_drugbtnActionPerformed(JScrollPane scrollPane, String drug_name, Object[][] data, JLabel noresult_txt) {
		noresult_txt.setText("");
		GetSQL windsql = new GetSQL();
		Component[] components = scrollPane.getViewport().getComponents();
		
		scrollPane.repaint();
		
		String[] columnNames = {"Received date", "Drug's Name", "Experation date", "Number per day", "Doctor's name" };

		if (drug_name.equalsIgnoreCase("")) {
			data = windsql.get_prescri_patient_object(id_patient);
			table_test = new JTable(data, columnNames);
			scrollPane.setViewportView(table_test); 
			scrollPane.repaint();
			
		} else {
			data = windsql.get_prescri_patient_object_specific_drugname(id_patient, drug_name);
			table_test = new JTable(data, columnNames);
			scrollPane.setViewportView(table_test); 
			if(data.length == 0) {
				noresult_txt.setText("No result");
			}
		}
		
	}
	
	
	
	public void find_show_prescri_btnActionPerformed(int num_row, Object[][] data ) {
		if (num_row == -1) {
			
		}
		else {
			String drug_name = data[num_row][1].toString();
			String prescridate = data[num_row][0].toString();
			String enddate = data[num_row][2].toString();
			String doctor = data[num_row][4].toString();
			String num_day = data[num_row][3].toString();
			
			
			PrescriptionPatientWindow wind = new PrescriptionPatientWindow(drug_name, prescridate, enddate, doctor, num_day);
			wind.setVisible(true);
		}
	}

    
    public void modify_btn_ActionPerformed() {
    	ModifyInfoPatientWindow wind = new ModifyInfoPatientWindow(id_person);
    	wind.setVisible(true);
    	frame.remove(tabbedPane);
    	frame.dispose();
    }
    
    public static void createAndShowGUI() {
    	//JFrame frame = new JFrame("Menu - Patient");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 850, 600);
 
        MainWindow_v2 demo = new MainWindow_v2();
        demo.addComponentToPane(frame.getContentPane());

        //frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    
    public JFrame main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
		return null;
    }

	public void logout_btnActionPerformed( ) {
		LoginWindow wind = new LoginWindow();
		wind.setVisible(true);
		frame.dispose();
		//frame.setVisible(false);
	}
}
