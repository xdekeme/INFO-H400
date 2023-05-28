import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GetSQL {
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	
	public String login_to_db(String user, String pass) {
		Connect();
		String value_return = "";
		String password_db = "";
		int id_db = 0;
		List<Integer> id_db_patient_list = new ArrayList<>();
		List<Integer> id_db_doctor_list = new ArrayList<>();
		
		String sql = "SELECT * FROM Person WHERE Username LIKE ?";
		String sql_id_patient = "SELECT Person_ID FROM Patient";
		String sql_id_doctor = "SELECT Person_ID FROM Doctor";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, user);
			rs = pst.executeQuery();
			
			while (rs.next()) {
			    password_db = rs.getString("Password").toString();
			    id_db = rs.getInt("ID");
			}
			
			
			if (password_db.equals(pass)) {
				pst = con.prepareStatement(sql_id_patient);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					int value = rs.getInt("Person_ID");
					id_db_patient_list.add(value);
				}
				
				pst = con.prepareStatement(sql_id_doctor);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					int value = rs.getInt("Person_ID");
					id_db_doctor_list.add(value);
				}

				if (id_db_patient_list.contains(id_db)) {
					value_return = "Connection_patient";
				}
				
				if (id_db_doctor_list.contains(id_db)) {
					value_return = "Connection_doctor";
				}
			}
			
			else {
				value_return = "Error_username_password";
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in the connection here");
			e.printStackTrace();
		}
		
		return value_return; 
	}
	
	public String check_username(String username) {
		Connect(); 
		List<String> liste_allusername = new ArrayList<>();
		String check_value_username = "";
		
		try {	            
	            String sql = "SELECT Username FROM Person";
	            PreparedStatement pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	                String valeurColonne = rs.getString("Username");
	                liste_allusername.add(valeurColonne);
	            }
	            
	            rs.close();
	            pst.close();
	            con.close();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in getting the list of user names from the database");
	            e.printStackTrace();
	        }
		
		for (int i = 0; i < liste_allusername.size(); i++) {
		    String element = liste_allusername.get(i);
		    if (element.equalsIgnoreCase(username)) {
		    	check_value_username = "Username_already_used";
		    	break;
		    }
		}
		
		return check_value_username;
	}
	
	
	public void add_person_db(String username, String password, String name, String firstname, int phonenumber, String mail) {
		Connect(); 
		if (username.equalsIgnoreCase(" ") || password.equalsIgnoreCase(" ")) {
		}
		
		else {
			try {
	            String sql = "INSERT INTO Person (Username, Password, Name, Firstname, Phonenumber, Email) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement pst = con.prepareStatement(sql);
	            
	            pst.setString(1, username);
	            pst.setString(2, password);
	            pst.setString(3, name);
	            pst.setString(4, firstname);
	            pst.setInt(5, phonenumber);
	            pst.setString(6, mail);
	            
	            pst.executeUpdate();
	            
	            System.out.println("Added to the database - table person");
	            pst.close();
	            con.close();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding into the databse - table person");
	            e.printStackTrace();
	        }			
		}
	}
	
	public int get_ID_person(String username) {
		Connect(); 
		int id_person = 0;
		try {
			String sql ="SELECT ID FROM Person WHERE username = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, username);            
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int value = rs.getInt("ID");
                id_person = value;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the ID person in the database");
	            e.printStackTrace();
	        }	
		return id_person;
	}
	
	public int get_ID_patient_fromregister(int register) {
		Connect(); 
		int id_person = 0;
		
		try {
			String sql ="SELECT ID FROM Patient WHERE National_Registration_Number = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, register);            
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int value = rs.getInt("ID");
                id_person = value;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the ID in patient from register number in the database");
	            e.printStackTrace();
	        }	
		return id_person;
	}
	
	public int get_ID_person_fromregister(int register) {
		Connect(); 
		int id_person = 0;
		
		try {
			String sql ="SELECT Person_ID FROM Patient WHERE National_Registration_Number = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, register);            
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int value = rs.getInt("Person_ID");
                id_person = value;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the Person_ID in patient from register number in the database");
	            e.printStackTrace();
	        }	
		return id_person;
	}
	
	
	public void add_adress_db(String country, String city, String region, int postcode, String adress, int numberhouse) {
		Connect(); 
		try {
	        String sql = "INSERT INTO Address_ID (Country, City, Region, Post_Code, Address, Number) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	         pst.setString(1, country);
	         pst.setString(2, city);
	         pst.setString(3, region);
	         pst.setInt(4, postcode);
	         pst.setString(5, adress);
	         pst.setInt(6, numberhouse);
	            
	         pst.executeUpdate();
	            
	         System.out.println("Added to the database - table adress");
	         pst.close();
	         con.close();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding into the databse - table adress");
	            e.printStackTrace();
	        }			
	}
	
	
	public int get_ID_adress(String city, String address, int numberhouse) {
		Connect(); 
		int id_adress = 0;
		
		try {
			String sql ="SELECT ID FROM Address_ID WHERE City = ? AND Address = ? AND Number = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, city);  
	        pst.setString(2, address); 
	        pst.setInt(3, numberhouse); 
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int value = rs.getInt("ID");
                id_adress = value;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the ID address in the database");
	            e.printStackTrace();
	        }	
		return id_adress;
	}
	
	public void add_patient_db(String date, String sex, int size, int weight, int registernumber, int id_person, int id_address) {
		Connect(); 
		date = date + " 12:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date new_date = null;
		try {
			new_date = format.parse(date);
		} catch (ParseException e1) {
			System.out.println("Error in converting datetime");
		}
		
		try {
	        String sql = "INSERT INTO Patient (DoB, Sex, Size, Weight, National_Registration_Number, Person_ID, Address_ID_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	         pst.setDate(1, new java.sql.Date(new_date.getTime()));
	         pst.setString(2, sex);
	         pst.setInt(3, size);
	         pst.setInt(4, weight);
	         pst.setInt(5, registernumber);
	         pst.setInt(6, id_person);
	         pst.setInt(7, id_address);
	            
	         pst.executeUpdate();
	            
	         System.out.println("Added to the database - table patient");
	         pst.close();
	         con.close();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding into the databse - table patient");
	            e.printStackTrace();
	        }			
	}
	
	public int get_ID_patient(int id_person) {
		Connect(); 
		int id_patient = 0;
		
		try {
			String sql ="SELECT ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int value = rs.getInt("ID");
                id_patient = value;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the ID patient in the database");
	            e.printStackTrace();
	        }	
		return id_patient;
	}
	
	public List<String> get_info_patient(int id_person) {
		List<String> info_patient = new ArrayList<String>();
		Connect();
		
		try {
			String sql ="SELECT Password, Name, Firstname, Phonenumber, Email FROM Person WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	String password = rs.getString("Password");
            	String name = rs.getString("Name");
                String firstname = rs.getString("Firstname");
                
                int phonenumber = rs.getInt("Phonenumber");
                String new_phonenumber = String.valueOf(phonenumber);
                String email = rs.getString("Email");
                info_patient.add(name);
                info_patient.add(firstname);
                info_patient.add(password);
                info_patient.add(new_phonenumber);
                info_patient.add(email);                
            }
            
            String sql2 = "SELECT Sex, Size, Weight, National_Registration_Number FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, id_person);  
	        ResultSet rs2 = pst2.executeQuery();
	        
	        while (rs2.next()) {
            	String sex = rs2.getString("Sex");
            	int size = rs2.getInt("Size");
            	int weight = rs2.getInt("Weight");
            	int registernumber = rs2.getInt("National_Registration_Number");
                String new_size = String.valueOf(size);
                String new_weight = String.valueOf(weight);
                String new_registernumber = String.valueOf(registernumber);
                info_patient.add(sex);
                info_patient.add(new_size);
                info_patient.add(new_weight);
                info_patient.add(new_registernumber);
  
                         
            }
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the patient infos in the database");
	            e.printStackTrace();
	        }
		
		return info_patient;
		
	}
	
	public List<String> get_adress_patient(int id_person) {
		List<String> adress_patient = new ArrayList<String>();
		int id_address = 0;
		Connect();
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
                id_address = rs.getInt("Address_ID_ID");     
            }
            
            String sql2 = "SELECT Country, City, Region, Post_Code, Address, Number FROM Address_ID WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, id_address);  
	        ResultSet rs2 = pst2.executeQuery();
	        
	        while (rs2.next()) {
            	String country = rs2.getString("Country");
            	String city = rs2.getString("City");
            	String region = rs2.getString("Region");
            	int postcode = rs2.getInt("Post_Code");
            	String street = rs2.getString("Address");
            	int number = rs2.getInt("Number");
      
                String new_postcode = String.valueOf(postcode);
                String new_number = String.valueOf(number);
                adress_patient.add(country);
                adress_patient.add(city);
                adress_patient.add(region);
                adress_patient.add(new_postcode);
                adress_patient.add(street);
                adress_patient.add(new_number);
  
                         
            }
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the address of the patient in the database");
	            e.printStackTrace();
	        }
		System.out.println(adress_patient);
		return adress_patient;
		
	}
	
	public void update_patient_name(int id_person, String new_name) {
		Connect(); 
		
		try {
			String sql ="UPDATE Person SET Firstname = ? WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, new_name);  
	        pst.setInt(2, id_person);  
	        pst.executeUpdate();
	        
            System.out.println("The name has changed");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in updating the name");
	            e.printStackTrace();
	        }	
	}
	
	public List<String> get_all_patient_name(String name) {
		Connect();
		ArrayList<String> listModel = new ArrayList<String>();
		ArrayList<Integer> correct_patient = new ArrayList<Integer>();
		
		try {
			String sql ="SELECT Person_ID FROM Patient";
	        PreparedStatement pst = con.prepareStatement(sql); 
	        ResultSet rs = pst.executeQuery(); 
	        
	        while (rs.next()) {
	        	int number = rs.getInt("Person_ID");
	        	correct_patient.add(number) ;
	        }
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in creating the list of all Patient-ID");
	            e.printStackTrace();
	        }
		
		try {  
	        String query = "SELECT * FROM Person WHERE Firstname = ?"; 
	        PreparedStatement pst = con.prepareStatement(query); 

	        pst.setString(1, name); 

	        ResultSet rs = pst.executeQuery(); 
	        
	        while (rs.next()) {
	        	int id_person_check = rs.getInt("ID");
	        	for (int i=0; i<correct_patient.size(); i++) {
	        		if(id_person_check == correct_patient.get(i)) {
	        			String value = "";
	    	        	int number = get_resiter_number(rs.getInt("ID"));
	    	        	value = rs.getString("Name").toUpperCase() + " " + rs.getString("Firstname") + " - " + number;
	    	            listModel.add(value) ;
	        		}
	        	}
	        	
	        }
	        
	        Collections.sort(listModel);
	        
	        String query2 = "SELECT * FROM Person WHERE Name = ?"; 
	        PreparedStatement pst2 = con.prepareStatement(query2); 

	        pst2.setString(1, name); 

	        ResultSet rs2 = pst2.executeQuery(); 
	        
	        while (rs2.next()) {
	 	        int id_person_check = rs2.getInt("ID");
	 	        for (int i=0; i<correct_patient.size(); i++) {
	 	        	if(id_person_check == correct_patient.get(i)) {
	 	        		String value = "";
	 	        		int number = get_resiter_number(rs2.getInt("ID"));
	 	        		value = rs2.getString("Name").toUpperCase() + " " + rs2.getString("Firstname") + " - " + number ;
	 	        		listModel.add(value) ;
	 	        	}
	 	        }
	        }
	        
	        Collections.sort(listModel);
	          
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
		
	    return listModel;
	}
	
	public int get_resiter_number(int id_person) {
		int registernumber = 0;
		
		try {  
	        String query = "SELECT National_Registration_Number FROM Patient WHERE Person_ID = ?"; 
	        PreparedStatement pst = con.prepareStatement(query); 

	        pst.setInt(1, id_person); 

	        ResultSet rs = pst.executeQuery(); 
	        
	        while (rs.next()) {
	        	registernumber = rs.getInt("National_Registration_Number");
	        }
	          
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
		return registernumber;
	}
	
	
	public List<String> get_all_patient_name_by_number(int registernumber) {
		Connect();
		int id_person = 0;
		ArrayList<String> listModel = new ArrayList<String>();
		
		try {  
	        String query = "SELECT Person_ID FROM Patient WHERE National_Registration_Number = ?"; 
	        PreparedStatement pst = con.prepareStatement(query); 

	        pst.setInt(1, registernumber); 

	        ResultSet rs = pst.executeQuery(); 
	        
	        while (rs.next()) {
	        	id_person = rs.getInt("Person_ID");
	        }
	        
	        String query2 = "SELECT Firstname, Name FROM Person WHERE ID = ?"; 
	        PreparedStatement pst2 = con.prepareStatement(query2); 

	        pst2.setInt(1, id_person); 

	        ResultSet rs2 = pst2.executeQuery(); 
	        
	        while (rs2.next()) {
	        	String value = registernumber + " - " + rs2.getString("Name").toUpperCase() + " " + rs2.getString("Firstname");
	        	listModel.add(value) ;
	        }
	        
	        Collections.sort(listModel);
	          
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return listModel;
	}
	
	
	
	public void update_patient_lastname(int id_person, String new_lastname) {
		Connect(); 
		
		try {
			String sql ="UPDATE Person SET Name = ? WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, new_lastname);  
	        pst.setInt(2, id_person);  
	        pst.executeUpdate();
            System.out.println("The lastname has changed");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in updating the lastname");
	            e.printStackTrace();
	        }	
	}
	
	public void update_patient_password(int id_person, String new_password) {
		Connect(); 
		
		try {
			String sql ="UPDATE Person SET Password = ? WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, new_password);  
	        pst.setInt(2, id_person);  
	        pst.executeUpdate();
            System.out.println("The password has changed");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in updating the password");
	            e.printStackTrace();
	        }	
	}
	
	public void update_patient_mail(int id_person, String new_mail) {
		Connect(); 
		
		try {
			String sql ="UPDATE Person SET Email = ? WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setString(1, new_mail);  
	        pst.setInt(2, id_person);  
	        pst.executeUpdate();
            System.out.println("The mail has changed");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in updating the mail");
	            e.printStackTrace();
	        }	
	}
	
	public void update_patient_phone(int id_person, String new_phone) {
		Connect(); 
		int num = Integer.parseInt(new_phone);
		
		try {
			String sql ="UPDATE Person SET Phonenumber = ? WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, num);  
	        pst.setInt(2, id_person);  
	        pst.executeUpdate();
            System.out.println("The phone number has changed");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in updating the phone number");
	            e.printStackTrace();
	        }	
	}
	
	public void update_patient_size(int id_person, String new_size) {
		Connect(); 
		int num = Integer.parseInt(new_size);
		int id_patient = 0;
		
		try {
			String sql ="SELECT ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_patient = rs.getInt("ID");     
            }
		
			String sql2 ="UPDATE Patient SET Size = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, num);  
	        pst2.setInt(2, id_patient);  
	        pst2.executeUpdate();
            System.out.println("The size has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the size");
	            e.printStackTrace();
	        }	
	}
	
	public void update_patient_weight(int id_person, String new_weight) {
		Connect(); 
		int num = Integer.parseInt(new_weight);
		int id_patient = 0;
		
		try {
			String sql ="SELECT ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_patient = rs.getInt("ID");     
            }
		
			String sql2 ="UPDATE Patient SET Weight = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, num);  
	        pst2.setInt(2, id_patient);  
	        pst2.executeUpdate();
            System.out.println("The weight has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the weight");
	            e.printStackTrace();
	        }	
	}
	
	public void update_adress_country(int id_person, String new_country) {
		Connect(); 
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET Country = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setString(1, new_country);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The country address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the country address");
	            e.printStackTrace();
	        }	
	}
	
	public void update_adress_city(int id_person, String new_city) {
		Connect(); 
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET City = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setString(1, new_city);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The city address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the city address");
	            e.printStackTrace();
	        }	
	}
	
	public void update_adress_region(int id_person, String new_region) {
		Connect(); 
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET Region = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setString(1, new_region);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The region address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the region address");
	            e.printStackTrace();
	        }	
	}
	
	
	public void update_adress_postcode(int id_person, String new_postcode) {
		Connect(); 
		int num = Integer.parseInt(new_postcode);
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET Post_Code = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, num);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The postcode address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the postcode address");
	            e.printStackTrace();
	        }	
	}
	
	public void update_adress_street(int id_person, String new_street) {
		Connect(); 
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET Address = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setString(1, new_street);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The street address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the street address");
	            e.printStackTrace();
	        }	
	}
	
	public void update_adress_number(int id_person, String new_number) {
		Connect(); 
		int num = Integer.parseInt(new_number);
		int id_adress = 0;
		
		try {
			String sql ="SELECT Address_ID_ID FROM Patient WHERE Person_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {                
            	id_adress = rs.getInt("Address_ID_ID");     
            }
		
			String sql2 ="UPDATE Address_ID SET Number = ? WHERE ID = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	            
	        pst2.setInt(1, num);  
	        pst2.setInt(2, id_adress);  
	        pst2.executeUpdate();
            System.out.println("The number address has changed");
		}
	            
	         catch (SQLException e) {
	        	System.out.println("Error in updating the number address");
	            e.printStackTrace();
	        }	
	}
	
	
	
	
	public void add_doctor_db(int inami, int id_person, int id_address) {
		Connect(); 
		
		try {
	        String sql = "INSERT INTO Doctor (INAMI, Person_ID, Address_ID_ID) VALUES (?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql);
	      
	         pst.setInt(1, inami);
	         pst.setInt(2, id_person);
	         pst.setInt(3, id_address);
	            
	         pst.executeUpdate();
	            
	         System.out.println("Added to the database - table doctor");
	         pst.close();
	         con.close();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding into the databse - table doctor");
	            e.printStackTrace();
	        }			
	}
	
	public String get_doctor_name(int id_doctor) {
		//Connect();
		int person_id = 0;
		String doctor_name = "";
		
		try {
			String sql ="SELECT Person_ID FROM Doctor WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_doctor);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	person_id= rs.getInt("Person_ID");
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the docotr name (1)in the database");
	            e.printStackTrace();
	        }	
		
		try {
			String sql ="SELECT Firstname, Name FROM Person WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, person_id);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("Firstname");
                String lastname = rs.getString("Name");
                doctor_name = "Dr. " + lastname.toUpperCase() + " " + name;
                //doctor_name = lastname.toUpperCase();
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the docotr name (2) in the database");
	            e.printStackTrace();
	        }
		
		
		return doctor_name;
	}
	
	public String get_doctor_name_fromidperson(int id_person) {
		Connect();
		String doctor_name = "";
		
		try {
			String sql ="SELECT Firstname, Name FROM Person WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_person);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("Firstname");
                String lastname = rs.getString("Name");
                doctor_name = "Dr. " + lastname.toUpperCase() + " " + name;
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the docotr name from idperson in the database");
	            e.printStackTrace();
	        }
		
		
		return doctor_name;
	}
	
	public int get_ID_doctor(int inami) {
		Connect();
		int id_doctor = 0;
		
		try {
			String sql ="SELECT ID FROM Doctor WHERE INAMI = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, inami);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	int id = rs.getInt("ID");
            	id_doctor = id;
                
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the ID Doctor in the database");
	            e.printStackTrace();
	        }	
		
		return id_doctor;
	}
	
	
	
	public List<String> get_drug_name(int id_drug) {
		//Connect();
		List<String> drug_name = new ArrayList<String>();
		
		try {
			String sql ="SELECT Name, Until, xperday FROM Drug WHERE ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_drug);   
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	String name_drug = rs.getString("Name");
            	int xday = rs.getInt("xperday");
            	String new_xday = String.valueOf(xday);
            	String end_date = rs.getString("Until").substring(0, 10);
            	
            	drug_name.add(name_drug);
            	drug_name.add(end_date);
            	drug_name.add(new_xday);
                
            }
            
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the drug name in the database");
	            e.printStackTrace();
	        }	
		return drug_name;
	}
	
	
	
	public List<String> get_all_drug_name() {
		Connect();
		List<String> all_drug_name = new ArrayList<String>();
		
		try {
			String sql ="SELECT Name FROM Drug ";
	        PreparedStatement pst = con.prepareStatement(sql);
	  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	String name_drug = rs.getString("Name");
            	all_drug_name.add(name_drug);
                
            }
            
            Collections.sort(all_drug_name);
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding all the drugs name in the database");
	            e.printStackTrace();
	        }	
		return all_drug_name;
	}
	
	
	public void create_new_prescri(String drugname, int numday, Date date, int inami, int idpatient, int iddoctor) {
		Connect();
		int id_drug = 0;
		
		try {
			String sql = "INSERT INTO Drug (Name, xperday, Until) VALUES (?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, drugname);
	        pst.setInt(2, numday);
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        pst.setDate(3, sqlDate);
	        
	        int rowsInserted = pst.executeUpdate();
            
	        
	        String sql2 ="SELECT ID FROM Drug WHERE Name = ? AND xperday = ? AND Until = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	        pst2.setString(1, drugname);
	        pst2.setInt(2, numday);
	        pst2.setDate(3, sqlDate);
	        ResultSet rs2 = pst2.executeQuery();
            
            while (rs2.next()) {
            	int id = rs2.getInt("ID");
            	id_drug = id;
            }
            
            String sql3 = "INSERT INTO Drug_Prescription (Drug_ID, Patient_ID, Doctor_ID, Date) VALUES (?, ?, ?, ?)";
	        PreparedStatement pst3 = con.prepareStatement(sql3);
	        pst3.setInt(1, id_drug);
	        pst3.setInt(2, idpatient);
	        pst3.setInt(3, iddoctor);
	        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	        pst3.setDate(4, currentDate);
	    
	        
	        int rowsInserted3 = pst3.executeUpdate();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding the new drug into the DRUG table");
	            e.printStackTrace();
	        }	
	}
	
	public void create_new_vaccin(String vaccname, Date date, int inami, int idpatient, int iddoctor) {
		Connect();
		int id_vacc = 0;
		
		try {
			String sql = "INSERT INTO Vaccine (Name, Deadline) VALUES (?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, vaccname);
	        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        pst.setDate(2, sqlDate);
	        
	        int rowsInserted = pst.executeUpdate();
            
	        
	        String sql2 ="SELECT ID FROM Vaccine WHERE Name = ? AND Deadline = ?";
	        PreparedStatement pst2 = con.prepareStatement(sql2);
	        pst2.setString(1, vaccname);
	        pst2.setDate(2, sqlDate);
	        ResultSet rs2 = pst2.executeQuery();
            
            while (rs2.next()) {
            	int id = rs2.getInt("ID");
            	id_vacc = id;
            }
            
            String sql3 = "INSERT INTO Vaccine_Appointment (Vaccine_ID, Patient_ID, Doctor_ID, Date) VALUES (?, ?, ?, ?)";
	        PreparedStatement pst3 = con.prepareStatement(sql3);
	        pst3.setInt(1, id_vacc);
	        pst3.setInt(2, idpatient);
	        pst3.setInt(3, iddoctor);
	        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	        pst3.setDate(4, currentDate);
	    
	        
	        int rowsInserted3 = pst3.executeUpdate();
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in adding the new vaccine into the DRUG table");
	            e.printStackTrace();
	        }	
	}
	
	//DRUG/PRESCRIPTION/VACCINE
	public List<List<String>> get_prescri_patient(int id_patient) {
		Connect();
		List<List<String>> prescri_patient_tot = new ArrayList<>();
		int drug_id;
		int doctor_id;
		
		try {
			String sql ="SELECT Date, Drug_ID, Doctor_ID FROM Drug_Prescription WHERE Patient_ID = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	            
	        pst.setInt(1, id_patient);  
	        ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
            	List<String> current_prescri = new ArrayList<String>();
            	String date = rs.getString("Date").substring(0, 10);
            	
            	drug_id = rs.getInt("Drug_ID");
            	doctor_id = rs.getInt("Doctor_ID"); 
            	
            	String doctor_name = get_doctor_name(doctor_id);
            	List<String> drug_name_list = get_drug_name(drug_id);
            	
            	current_prescri.add(date);
            	for (int i=0; i<drug_name_list.size(); i++) {
            		current_prescri.add(drug_name_list.get(i));
            	}
            	current_prescri.add(doctor_name);
            	prescri_patient_tot.add(current_prescri);
            }
	            
	        } catch (SQLException e) {
	        	System.out.println("Error in finding the prescri ingo in the database");
	            e.printStackTrace();
	        }
		
		return prescri_patient_tot;
		
	}
	
	
	//DRUG/PRESCRIPTION/VACCINE
		public Object[][] get_prescri_patient_object(int id_patient) {
			Connect();
			List<List<String>> prescri_patient_tot = new ArrayList<>();
			int drug_id;
			int doctor_id;
			
			try {
				String sql ="SELECT Date, Drug_ID, Doctor_ID FROM Drug_Prescription WHERE Patient_ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_patient);  
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	List<String> current_prescri = new ArrayList<String>();
	            	String date = rs.getString("Date").substring(0, 10);
	            	
	            	drug_id = rs.getInt("Drug_ID");
	            	doctor_id = rs.getInt("Doctor_ID"); 
	            	
	            	String doctor_name = get_doctor_name(doctor_id);
	            	List<String> drug_name_list = get_drug_name(drug_id);
	            	
	            	current_prescri.add(date);
	            	for (int i=0; i<drug_name_list.size(); i++) {
	            		current_prescri.add(drug_name_list.get(i));
	            	}
	            	current_prescri.add(doctor_name);
	            	prescri_patient_tot.add(current_prescri);
	            }
	            
	            
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the prescri ingo in the database");
		            e.printStackTrace();
		        }
			
			int number_line = prescri_patient_tot.size();
            int number_colone= 5; //Change the size when we add the x/day variable in the database (ligne puis colone)
            Object[][] tableauObject = new Object[number_line][number_colone]; 
            for (int i=0; i<number_line; i++) {
            	for (int j=0; j<number_colone; j++) {
            		tableauObject[i][j] = prescri_patient_tot.get(i).get(j);
            	}
            	
            }
			return tableauObject;
		}
	
		public Object[][] get_prescri_patient_object_specific_drugname(int id_patient, String drug_name) {
			Connect();
			List<List<String>> prescri_patient_tot = new ArrayList<>();
			int drug_id;
			int doctor_id;
			
			try {
				String sql ="SELECT Date, Drug_ID, Doctor_ID FROM Drug_Prescription WHERE Patient_ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_patient);  
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	List<String> current_prescri = new ArrayList<String>();
	            	String date = rs.getString("Date").substring(0, 10);
	            	
	            	drug_id = rs.getInt("Drug_ID");
	            	doctor_id = rs.getInt("Doctor_ID"); 
	            	
	            	String doctor_name = get_doctor_name(doctor_id);
	            	List<String> drug_name_list = get_drug_name(drug_id);
	            	
	            	if (drug_name_list.get(0).equalsIgnoreCase(drug_name)) {
	            		current_prescri.add(date);
		            	for (int i=0; i<drug_name_list.size(); i++) {
		            		current_prescri.add(drug_name_list.get(i));
		            	}
		            	current_prescri.add(doctor_name);
		            	prescri_patient_tot.add(current_prescri);
	            	}
	            	
	            }
	            
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the prescri ingo in the database");
		            e.printStackTrace();
		        }
			
			int number_line = prescri_patient_tot.size();
            int number_colone= 5; //Change the size when we add the x/day variable in the database (ligne puis colone)
            Object[][] tableauObject = new Object[number_line][number_colone]; 
            for (int i=0; i<number_line; i++) {
            	for (int j=0; j<number_colone; j++) {
            		tableauObject[i][j] = prescri_patient_tot.get(i).get(j);
            	}
            	
            }
			return tableauObject;
		}
	
		
		
		public List<String> get_vaccin_name(int id_vaccin) {
			//Connect();
			List<String> vaccin_name = new ArrayList<String>();
			
			try {
				String sql ="SELECT Name, Deadline FROM Vaccine WHERE ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_vaccin);   
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	String name_vacc = rs.getString("Name");
	            	String deaddate = rs.getString("Deadline").substring(0, 10);
	            	
	            	vaccin_name.add(name_vacc);
	            	vaccin_name.add(deaddate);
	                
	            }
	            
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the vaccine name in the database");
		            e.printStackTrace();
		        }	
			return vaccin_name;
		}
		
		
		public List<List<String>> get_vaccin_patient(int id_patient) {
			Connect();
			List<List<String>> vaccin_patient_tot = new ArrayList<>();
			int vacc_id;
			int doctor_id;
			
			try {
				String sql ="SELECT Date, Vaccine_ID, Doctor_ID FROM Vaccine_Appointment WHERE Patient_ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_patient);  
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	List<String> current_vacc = new ArrayList<String>();
	            	String date = rs.getString("Date").substring(0, 10);
	            	
	            	vacc_id = rs.getInt("Vaccine_ID");
	            	doctor_id = rs.getInt("Doctor_ID"); 
	            	
	            	String doctor_name = get_doctor_name(doctor_id);
	            	List<String> drug_name_list = get_vaccin_name(vacc_id);
	            	
	            	current_vacc.add(date);
	            	for (int i=0; i<drug_name_list.size(); i++) {
	            		current_vacc.add(drug_name_list.get(i));
	            	}
	            	current_vacc.add(doctor_name);
	            	vaccin_patient_tot.add(current_vacc);
	            }
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the vaccins list in the database");
		            e.printStackTrace();
		        }
			
			return vaccin_patient_tot;
			
		}
		
		
		public Object[][] get_vaccin_patient_object(int id_patient) {
			Connect();
			List<List<String>> vaccin_patient_tot = new ArrayList<>();
			int vaccin_id;
			int doctor_id;
			
			try {
				String sql ="SELECT Date, Vaccine_ID, Doctor_ID FROM Vaccine_Appointment WHERE Patient_ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_patient);  
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	List<String> current_vaccin = new ArrayList<String>();
	            	String date = rs.getString("Date").substring(0, 10);
	            	
	            	vaccin_id = rs.getInt("Vaccine_ID");
	            	doctor_id = rs.getInt("Doctor_ID"); 
	            	
	            	String doctor_name = get_doctor_name(doctor_id);
	            	List<String> vacc_name_list = get_vaccin_name(vaccin_id);
	            	
	            	current_vaccin.add(date);
	            	for (int i=0; i<vacc_name_list.size(); i++) {
	            		current_vaccin.add(vacc_name_list.get(i));
	            	}
	            	current_vaccin.add(doctor_name);
	            	vaccin_patient_tot.add(current_vaccin);
	            }
	            
	            
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the vaccins into in the database");
		            e.printStackTrace();
		        }
			
			int number_line = vaccin_patient_tot.size();
            int number_colone= 4; 
            Object[][] tableauObject = new Object[number_line][number_colone]; 
            for (int i=0; i<number_line; i++) {
            	for (int j=0; j<number_colone; j++) {
            		tableauObject[i][j] = vaccin_patient_tot.get(i).get(j);
            	}
            	
            }
			return tableauObject;
		}

		public Object[][] get_vaccin_patient_object_specific_drugname(int id_patient, String vacc_name) {
			Connect();
			List<List<String>> vaccin_patient_tot = new ArrayList<>();
			int vacc_id;
			int doctor_id;
			
			try {
				String sql ="SELECT Date, Vaccine_ID, Doctor_ID FROM Vaccine_Appointment WHERE Patient_ID = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		            
		        pst.setInt(1, id_patient);  
		        ResultSet rs = pst.executeQuery();
	            
	            while (rs.next()) {
	            	List<String> current_vaccin = new ArrayList<String>();
	            	String date = rs.getString("Date").substring(0, 10);
	            	
	            	vacc_id = rs.getInt("Vaccine_ID");
	            	doctor_id = rs.getInt("Doctor_ID"); 
	            	
	            	String doctor_name = get_doctor_name(doctor_id);
	            	List<String> vacc_name_list = get_vaccin_name(vacc_id);
	            	
	            	if (vacc_name_list.get(0).equalsIgnoreCase(vacc_name)) {
	            		current_vaccin.add(date);
		            	for (int i=0; i<vacc_name_list.size(); i++) {
		            		current_vaccin.add(vacc_name_list.get(i));
		            	}
		            	current_vaccin.add(doctor_name);
		            	vaccin_patient_tot.add(current_vaccin);
	            	}
	            	
	            }
	            
		            
		        } catch (SQLException e) {
		        	System.out.println("Error in finding the specific vaccin info in the database");
		            e.printStackTrace();
		        }
			
			int number_line = vaccin_patient_tot.size();
            int number_colone= 4; //Change the size when we add the x/day variable in the database (ligne puis colone)
            Object[][] tableauObject = new Object[number_line][number_colone]; 
            for (int i=0; i<number_line; i++) {
            	for (int j=0; j<number_colone; j++) {
            		tableauObject[i][j] = vaccin_patient_tot.get(i).get(j);
            	}
            	
            }
			return tableauObject;
		}
	
		
	
	//Connection Data Base
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "");
			System.out.println("Connection OK");
		} catch (Exception e) {
			System.out.println("Error in the connection");
			e.printStackTrace();
		}	
	}

}
