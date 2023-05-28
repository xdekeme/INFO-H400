import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class AddVaccinWindow extends JFrame {

	private JPanel contentPane;
	private List<String> info_patient_list;
	private JTextField vaccname_txt;
	private JTextField untildate_txt;
	private JTextField inaminum_txt;
	private int id_patient;
	private int id_doctor;

	
	public AddVaccinWindow(int idpatient, List<String> all_info_patient) {
		info_patient_list = all_info_patient;
		id_patient = idpatient;
		init_component();
		
	}
	
	
	
	public void init_component() {
		GetSQL windsql = new GetSQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closebtn_ActionPerformed();
			}
		});
		closebtn.setBounds(442, 329, 117, 29);
		contentPane.add(closebtn);
		
		List<String> drug_list = windsql.get_all_drug_name();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(drug_list.toArray(new String[drug_list.size()]));
		
		JLabel title_label = new JLabel("ADD VACCCINE");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setForeground(new Color(255, 71, 29));
		title_label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title_label.setBounds(6, 6, 553, 41);
		contentPane.add(title_label);
		
		JLabel vaccin_label = new JLabel("Vaccine's name:\n");
		vaccin_label.setBounds(6, 89, 105, 16);
		contentPane.add(vaccin_label);
		
		vaccname_txt = new JTextField();
		vaccname_txt.setBounds(123, 84, 227, 26);
		contentPane.add(vaccname_txt);
		vaccname_txt.setColumns(10);
		
		JLabel until_label = new JLabel("Deadline:");
		until_label.setBounds(6, 135, 105, 16);
		contentPane.add(until_label);
		
		untildate_txt = new JTextField();
		untildate_txt.setColumns(10);
		untildate_txt.setBounds(123, 130, 227, 26);
		contentPane.add(untildate_txt);
		
		JLabel dateformat_label = new JLabel("(YYYY-MM-DD)");
		dateformat_label.setBounds(386, 135, 105, 16);
		contentPane.add(dateformat_label);
		
		JLabel inaminum_label = new JLabel("INAMI:");
		inaminum_label.setBounds(6, 183, 105, 16);
		contentPane.add(inaminum_label);
		
		inaminum_txt = new JTextField();
		inaminum_txt.setColumns(10);
		inaminum_txt.setBounds(123, 178, 227, 26);
		contentPane.add(inaminum_txt);
		
		JLabel result_label = new JLabel("");
		result_label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		result_label.setForeground(Color.RED);
		result_label.setHorizontalAlignment(SwingConstants.CENTER);
		result_label.setBounds(6, 310, 117, 16);
		contentPane.add(result_label);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					result_label.setText("");
					String vaccname = vaccname_txt.getText();
					String date_txt = untildate_txt.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date =  sdf.parse(date_txt);
					String inami = inaminum_txt.getText();
					int inaminum = Integer.parseInt(inami);
					
					id_doctor = windsql.get_ID_doctor(inaminum);
					
					if (id_doctor == 0) {
						result_label.setText("Error INAMI");
					}
					
					if (id_doctor > 0) {
						addvaccin_ActionPerfomed(vaccname, date, inaminum, id_patient, id_doctor);
						result_label.setText("Added!");
					}
					
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(6, 329, 117, 29);
		contentPane.add(btnAdd);
		
		
	}
	
	
	public void addvaccin_ActionPerfomed(String vaccname, Date date, int inami, int idpatient, int iddoctor) {
		GetSQL windsql = new GetSQL();
		windsql.create_new_vaccin(vaccname, date, inami, id_patient, iddoctor);
	}
	
	public void closebtn_ActionPerformed() {
		InfoPatientDoctorWindow wind = new InfoPatientDoctorWindow(info_patient_list, id_patient);
		wind.setVisible(true);
		this.dispose();
	}
}
