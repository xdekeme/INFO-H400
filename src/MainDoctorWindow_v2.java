import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
public class MainDoctorWindow_v2 {
	
    final static String Home = "Home";
    final static String My_Infos = "Search - Name";
    final static String MY_PRESCRI = "Search - Register Number";
    private JPanel home;
    private JPanel searchname;
    private JPanel searchnumber;
    private String patient_name;
    private String patient_number;
    private List<String> all_patient;
    private List<String> all_patient_number;
    public static JFrame frame = new JFrame("Menu - DOCTOR");
    private static int id_person;
    private static int id_patient;
    private JTextField searchname_field;
    private JTextField searchnumber_field;

 
    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        //Create the "cards".
        home = new JPanel() {};
        home_components();
        
        searchname = new JPanel() {};
        searchname_components();
        
        searchnumber= new JPanel() {};
        searchnumber_components();
 
        tabbedPane.addTab(Home, home);
        
        
        tabbedPane.addTab(My_Infos, searchname);
        tabbedPane.addTab(MY_PRESCRI, searchnumber);  
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
 
    
    public void home_components() {
        home.setLayout(null);
        GetSQL windsql = new GetSQL();
        
        String doctor_name = windsql.get_doctor_name_fromidperson(id_person);
        System.out.println(doctor_name);
        JButton logout_btn = new JButton("Log out");
        logout_btn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		logout_btnActionPerformed();
        	}
        });
        logout_btn.setBounds(0, 315, 97, 29);
        
        JLabel lblWelcome = new JLabel("");
        lblWelcome.setText("Welcome, " + doctor_name + " !");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblWelcome.setBounds(0, 0, 630, 55);
        home.add(lblWelcome);
        
        
        home.add(logout_btn);
    }
    
    
    public void searchname_components() {
    	searchname.setLayout(null);
    	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 149, 618, 195);
		searchname.add(scrollPane);
		
		 JList table = new JList();
	     table.setBounds(21, 149, 256, 128);
	     scrollPane.setViewportView(table);
	     table.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if (e.getClickCount()==2) {
	        			int index = table.getSelectedIndex();
	        			String[] parts = table.getModel().getElementAt(index).toString().split(" ");
	        			String registernumber_new = parts[parts.length - 1];
	        			int registernumber_new_int = Integer.parseInt(registernumber_new);
	        			GetSQL wind = new GetSQL();
	        			int id_pat = wind.get_ID_patient_fromregister(registernumber_new_int);
	        			int id_pers = wind.get_ID_person_fromregister(registernumber_new_int);
	        			List<String> info_patient_list = wind.get_info_patient(id_pers);
	        			InfoPatientDoctorWindow wind2 = new InfoPatientDoctorWindow(info_patient_list, id_pat );
	        			wind2.setVisible(true);
	       		 }
	        	}
	        });
	     
	     JLabel searchname_label = new JLabel("Search Patient:");
	     searchname_label.setBounds(6, 113, 110, 16);
	     searchname.add(searchname_label);
	        
	     searchname_field = new JTextField();
	     searchname_field.setBounds(128, 108, 263, 26);
	     searchname.add(searchname_field);
	     searchname_field.setColumns(10);

	     JLabel error_txt = new JLabel("");
	     error_txt.setForeground(new Color(255, 49, 40));
	     error_txt.setHorizontalAlignment(SwingConstants.CENTER);
	     error_txt.setBounds(481, 91, 143, 16);
	     searchname.add(error_txt);
	        
	        
	     JButton findpatientname_btn = new JButton("FIND");
	     findpatientname_btn.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	    	 error_txt.setText("");
	    	 patient_name = searchname_field.getText();
	    	 findpatientnamebtn_ActionPerformed(patient_name);
	    	 if (all_patient.size() == 0) {
	    		 error_txt.setText("No results");
	    	 }
	    	 table.setListData(all_patient.toArray(new String[all_patient.size()]));
	       }
	     });
	     findpatientname_btn.setBounds(507, 108, 117, 29);
	     searchname.add(findpatientname_btn);
    	
    }
    
    public void searchnumber_components() {    
    	searchnumber.setLayout(null);
    	JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 149, 618, 195);
		searchnumber.add(scrollPane);
		
		 JList table = new JList();
	     table.setBounds(21, 149, 256, 128);
	     scrollPane.setViewportView(table);
	     table.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if (e.getClickCount()==2) {
	        			int index = table.getSelectedIndex();
	        			String[] parts = table.getModel().getElementAt(index).toString().split(" ");
	        			String registernumber_new = parts[0];
	        			int registernumber_new_int = Integer.parseInt(registernumber_new);
	        			GetSQL wind = new GetSQL();
	        			int id_pat = wind.get_ID_patient_fromregister(registernumber_new_int);
	        			int id_pers = wind.get_ID_person_fromregister(registernumber_new_int);
	        			List<String> info_patient_list = wind.get_info_patient(id_pers);
	        			InfoPatientDoctorWindow wind2 = new InfoPatientDoctorWindow(info_patient_list, id_pat );
	        			wind2.setVisible(true);
	       		 }
	        	}
	        });
	     
	     JLabel searchnumber_label = new JLabel("Search Patient:");
	     searchnumber_label.setBounds(6, 113, 110, 16);
	     searchnumber.add(searchnumber_label);
	        
	     searchnumber_field = new JTextField();
	     searchnumber_field.setBounds(128, 108, 263, 26);
	     searchnumber.add(searchnumber_field);
	     searchnumber_field.setColumns(10);
	     
	     JLabel error_txt = new JLabel("");
	     error_txt.setForeground(new Color(255, 49, 40));
	     error_txt.setHorizontalAlignment(SwingConstants.CENTER);
	     error_txt.setBounds(481, 91, 143, 16);
	     searchnumber.add(error_txt);
	     
	     JButton findpatientnumber_btn = new JButton("FIND");
	     findpatientnumber_btn.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	    	 error_txt.setText("");
	    	 patient_number = searchnumber_field.getText();
	    	 findpatientnumberbtn_ActionPerformed(patient_number);
	    	 if (all_patient_number.size() == 0) {
	    		 error_txt.setText("No results");
	    	 }
	    	 
	    	 table.setListData(all_patient_number.toArray(new String[all_patient_number.size()]));
	    	
	       }
	     });
	     findpatientnumber_btn.setBounds(507, 108, 117, 29);
	     searchnumber.add(findpatientnumber_btn);

    }
    
	
    
    public void get_id_user(int id_person_fct) {
    	//id_person = id_person_fct;
    }
    
   public void findpatientnamebtn_ActionPerformed(String name) {
	   GetSQL getsql = new GetSQL();
	   all_patient = getsql.get_all_patient_name(patient_name);
   }
   
   public void findpatientnumberbtn_ActionPerformed(String number) {
	   int num = Integer.parseInt(number);
	   GetSQL getsql = new GetSQL();
	   all_patient_number = getsql.get_all_patient_name_by_number(num);
	   
   }
       
    private static void createAndShowGUI() {
        //JFrame frame = new JFrame("Menu - DOCTOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 651, 424);
 
        MainDoctorWindow_v2 demo = new MainDoctorWindow_v2();
        demo.addComponentToPane(frame.getContentPane());

        //frame.pack();
        frame.setVisible(true);
    }
 
    
    
    public static JFrame main(String[] args, int id_person_fct) {
        /* Use an appropriate Look and Feel */
    	id_person = id_person_fct;
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
  

	public void logout_btnActionPerformed() {
		LoginWindow wind = new LoginWindow();
		wind.setVisible(true);
		//frame.setVisible(false);
		frame.dispose();
	}
}
