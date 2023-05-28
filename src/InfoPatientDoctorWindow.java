import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class InfoPatientDoctorWindow extends JFrame {

	private JPanel contentPane;
	private List<String> info_patient_list;
	private JTable table;
	private int id_patient;

	public InfoPatientDoctorWindow(List<String> all_info_patient, int idpatient) {
		info_patient_list = all_info_patient;
		id_patient = idpatient;

		init_component();
	}
	
	
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title_label = new JLabel("");
		title_label.setText("PATIENT: " + info_patient_list.get(0).toUpperCase() + " " + info_patient_list.get(1));
		title_label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title_label.setForeground(new Color(255, 56, 21));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(6, 6, 875, 40);
		contentPane.add(title_label);
		
		JLabel phone_label = new JLabel("Phone number:");
		phone_label.setBounds(6, 86, 137, 16);
		contentPane.add(phone_label);
		
		JLabel phone_txt = new JLabel("");
		phone_txt.setText(info_patient_list.get(3));
		phone_txt.setBounds(157, 86, 144, 16);
		contentPane.add(phone_txt);
		
		JLabel mail_label = new JLabel("Mail:");
		mail_label.setBounds(6, 119, 137, 16);
		contentPane.add(mail_label);
		
		JLabel mail_txt = new JLabel("");
		mail_txt.setText(info_patient_list.get(4));
		mail_txt.setBounds(157, 119, 221, 16);
		contentPane.add(mail_txt);
		
		JLabel registration_label = new JLabel("Registration number:");
		registration_label.setBounds(6, 157, 137, 16);
		contentPane.add(registration_label);
		
		JLabel registration_txt = new JLabel("");
		registration_txt.setText(info_patient_list.get(8));
		registration_txt.setBounds(157, 157, 144, 16);
		contentPane.add(registration_txt);
		
		JLabel lastprescri_label = new JLabel("Last prescriptions: ");
		lastprescri_label.setBounds(6, 202, 137, 16);
		contentPane.add(lastprescri_label);
		
		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closebtn_ActionPerformed();
			}
		});
		
		GetSQL windsql = new GetSQL();
		int id_person = windsql.get_ID_person_fromregister(Integer.parseInt(info_patient_list.get(8)));
        int id_patient = windsql.get_ID_patient(id_person);       
        
    
        String[] columnNames = {"Received date", "Drug's Name", "Experation date", "Number per day", "Doctor's name" };
		Object[][] data = windsql.get_prescri_patient_object(id_patient);
        JTable table = new JTable(data, columnNames);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 226, 875, 160);
        scrollPane.setViewportView(table);
        contentPane.add(scrollPane);
        
		closebtn.setBounds(764, 599, 117, 29);
		contentPane.add(closebtn);
		
		JButton addprecri_btn = new JButton("Add prescription");
		addprecri_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addprescri_ActionPerformed();
			
			}
		});
		addprecri_btn.setBounds(0, 599, 144, 29);
		contentPane.add(addprecri_btn);
		
		
        
		
		JButton addvacc_btn = new JButton("Add vaccine");
		addvacc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addvaccin_ActionPerformed();
				
			}
		});
		addvacc_btn.setBounds(194, 599, 144, 29);
		contentPane.add(addvacc_btn);
		
		JLabel lastvacc_label = new JLabel("Last vaccines:");
		lastvacc_label.setBounds(6, 398, 137, 16);
		contentPane.add(lastvacc_label);
		
		JScrollPane vaccine_scrollpanel = new JScrollPane();
		vaccine_scrollpanel.setBounds(6, 427, 875, 160);
		contentPane.add(vaccine_scrollpanel);
		
		String[] columnNames_vacc = {"Received date", "Vaccine's Name", "Deadline", "Doctor's name"};
		Object[][] data_vacc = windsql.get_vaccin_patient_object(id_patient);
		JTable table_vacc = new JTable(data_vacc, columnNames_vacc);
		vaccine_scrollpanel.setViewportView(table_vacc);
		
	}
	
	public void addprescri_ActionPerformed() {
		AddPrescriWindow wind = new AddPrescriWindow(id_patient, info_patient_list);
		wind.setVisible(true);
		this.dispose();
	}
	
	public void addvaccin_ActionPerformed() {
		AddVaccinWindow wind = new AddVaccinWindow(id_patient, info_patient_list);
		wind.setVisible(true);
		this.dispose();
	}
	
	public void closebtn_ActionPerformed() {
		this.dispose();
	}
}
