

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PrescriptionPatientWindow extends JFrame {

	private JPanel contentPane;
	public String drugname_tot;
	public String prescridate_tot;
	public String unitldate_tot;
	public String doctorname_tot;
	public String numday_tot;

	
	public PrescriptionPatientWindow(String drugname, String prescridate, String untildate, String doctorname, String num_xperday) {
		drugname_tot = drugname;
		prescridate_tot = prescridate;
		unitldate_tot = untildate;
		doctorname_tot = doctorname;
		numday_tot = num_xperday;
		init_component();

	}
	
	public void init_component() {
		
		GetRessource getressource_img = new GetRessource();
		Image qrcode_img = getressource_img.GetImage("/qrcode.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title_label = new JLabel("DRUG PRESCRIPTION");
		title_label.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(6, 6, 613, 39);
		contentPane.add(title_label);
		
		JLabel qrcode_label = new JLabel("");
		qrcode_label.setIcon(new ImageIcon(qrcode_img));
		qrcode_label.setBounds(217, 40, 263, 211);
		contentPane.add(qrcode_label);
		
		JLabel drugname_label = new JLabel("Drug's name: ");
		drugname_label.setBounds(6, 283, 118, 16);
		contentPane.add(drugname_label);
		
		JLabel drugname_txt = new JLabel("");
		drugname_txt.setForeground(new Color(0, 0, 255));
		drugname_txt.setText(drugname_tot);
		drugname_txt.setBounds(161, 283, 288, 16);
		contentPane.add(drugname_txt);
		
		JLabel prescri_label = new JLabel("Prescription day: ");
		prescri_label.setBounds(6, 322, 118, 16);
		contentPane.add(prescri_label);
		
		JLabel prescri_txt = new JLabel("");
		prescri_txt.setForeground(new Color(0, 0, 255));
		prescri_txt.setText(prescridate_tot);
		prescri_txt.setBounds(161, 322, 288, 16);
		contentPane.add(prescri_txt);
		
		JLabel until_label = new JLabel("Until date:");
		until_label.setBounds(6, 359, 118, 16);
		contentPane.add(until_label);
		
		JLabel unitl_txt = new JLabel("");
		unitl_txt.setForeground(new Color(0, 0, 255));
		unitl_txt.setText(unitldate_tot);
		unitl_txt.setBounds(161, 359, 242, 16);
		contentPane.add(unitl_txt);
		
		JLabel doctor_name = new JLabel("Doctor's name:");
		doctor_name.setBounds(6, 398, 118, 16);
		contentPane.add(doctor_name);
		
		JLabel doctorname_txt = new JLabel("");
		doctorname_txt.setForeground(new Color(0, 0, 255));
		doctorname_txt.setText(doctorname_tot);
		doctorname_txt.setBounds(161, 398, 242, 16);
		contentPane.add(doctorname_txt);
		
		JButton closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closebtn_ActionPerformed();
			}
		});
		closebtn.setBounds(502, 444, 117, 29);
		contentPane.add(closebtn);
		
		JLabel num_day = new JLabel("Number per day:");
		num_day.setBounds(6, 437, 118, 16);
		contentPane.add(num_day);
		
		JLabel numday_txt = new JLabel("");
		numday_txt.setForeground(new Color(0, 0, 255));
		numday_txt.setText(numday_tot);
		numday_txt.setBounds(161, 437, 242, 16);
		contentPane.add(numday_txt);
		
	}
	
	public void closebtn_ActionPerformed() {
		this.dispose();
	}
}
