import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class CreateProfil extends JFrame {

	private JPanel contentPane;

	public CreateProfil() {
		setBackground(new Color(238, 238, 238));
		init_component();
	}
	
	 
	 
	  
	    
	    
	
	public void init_component() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 407);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton close_btn = new JButton("Close");
		close_btn.setBackground(new Color(125, 125, 125));
		close_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close_btn_ActionPerformed();
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("CHOOSE YOUR PROFILE");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 384, 71);
		contentPane.add(lblNewLabel);
		close_btn.setBounds(0, 344, 117, 29);
		getContentPane().add(close_btn);
		
		JButton create_patient_btn = new JButton("PATIENT");
		create_patient_btn.setOpaque(true);
		create_patient_btn.setBorderPainted(false);
		create_patient_btn.setBackground(new Color(110, 133, 213));
		create_patient_btn.setForeground(Color.BLACK);
		create_patient_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patient_create_profil();
			}
		});
		create_patient_btn.setFont(new Font("Arial", Font.BOLD, 20));
		GetRessource getressource_patient_btn = new GetRessource();
		Image img_patient = getressource_patient_btn.GetImage("/patient_icon.png");
		create_patient_btn.setIcon(new ImageIcon(img_patient));
		create_patient_btn.setBounds(6, 104, 372, 91);
		getContentPane().add(create_patient_btn);
		
		JButton create_doctor_btn = new JButton("DOCTOR");
		create_doctor_btn.setOpaque(true);
		create_doctor_btn.setBorderPainted(false);
		create_doctor_btn.setBackground(new Color(207, 76, 61));
		create_doctor_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctor_create_profil();
			}
		});
		create_doctor_btn.setFont(new Font("Arial", Font.BOLD, 20));
		GetRessource getressource_doctor_btn = new GetRessource();
		Image img_doctor = getressource_doctor_btn.GetImage("/doctor.png");
		create_doctor_btn.setIcon(new ImageIcon(img_doctor));
		create_doctor_btn.setBounds(6, 225, 372, 91);
		getContentPane().add(create_doctor_btn);
		GetRessource getressource_logo = new GetRessource();
		Image img_logo = getressource_logo.GetImage("/logo.png");

	}
	
	
	public void patient_create_profil() {
		this.dispose();
		CreateProfilPatientWindow patient_window = new CreateProfilPatientWindow();
		patient_window.setVisible(true);
	}
	
	public void doctor_create_profil() {
		this.dispose();
		CreateProfilDoctorWindow doctor_window = new CreateProfilDoctorWindow();
		doctor_window.setVisible(true);
	}
	
	
	public void close_btn_ActionPerformed() {
		this.dispose();
	}
	
	
}
