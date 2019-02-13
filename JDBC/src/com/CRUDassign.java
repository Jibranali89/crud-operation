package com;
import java.io.*;
import java.sql.*;
import java.util.*;
public class CRUDassign {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		File fd= new File("E:\\New folder\\CRUD_Assignment");
		fd.mkdirs();
	    String tname="copy";
		File f=new File(fd,tname+".txt");
		f.createNewFile();
		 FileWriter fw = new FileWriter(f,true);
   	     BufferedWriter bw = new BufferedWriter(fw);
   	     PrintWriter pw = new PrintWriter(bw);
		
		Class.forName("com.mysql.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jibrandb","root", "root");
				Statement st=con.createStatement();
				{
			    System.out.println("Connected");
			    System.out.println("Enter table name :");
			     String table = sc.next();

		 st.executeUpdate("create table "+table+"(eid int(10),ename varchar(20),esal int(10))");

			while (true) {
				
		System.out.println("*****Menu Operations*****");
				System.out.println("1\t Insert Data");
				System.out.println("2\t Update Data");
				System.out.println("3\t Delete Data");
				System.out.println("4\t Retrieve Data");
				System.out.println("****Enter the Numberic value for Menu Operations****");
				int choice = sc.nextInt();
                switch (choice)
                {
case 1:
					System.out.println("Enter Employee Details.");
					
					 {
						System.out.println();
						System.out.println("Enter Employee id : ");
						int id = sc.nextInt();
						System.out.println("Enter Employee name : ");
						String name = sc.next();
						System.out.println("Enter Employee salary : ");
						int sal = sc.nextInt();
						st.executeUpdate("insert into "+table+" values(" + id + ",'" + name + "'," + sal + ")");
						System.out.println(" Inserted");
						ResultSet rs = st.executeQuery("select * from employee");
						pw.println("**********EMPLOYEE TABLE*******"); ;
						String header = "Eid\tEname\tEsalary\n";
						pw.println(header);
						
						String add = "";
						while (rs.next()) {
							add += rs.getInt(1) + "\t";
							add += rs.getString(2) + "\t";
							add += rs.getInt(3) + "\t";
							pw.println(add);
							
						}
					pw.println("***************************************");
						
						pw.flush();
						System.out.println("  Press  Y to Insert More and  N for Menu ");
						String opt = sc.next();
						if (opt.equals("Y") || opt.equals("y")) {
							continue;
						} else {
							break;
						}}					              					

case 2:// UPDATE OPERATION
					
					boolean b1 = true;
					while (true) {
						System.out.println("Update by id");
							System.out.println("  Press  Y to Backup and  N for opertation ");
							String bc = sc.next();

							if (bc.equals("Y") || bc.equals("y")) {

								pw.println("*******BACKUP Before Update*******");
								
								ResultSet rs = st.executeQuery("select * from "+table+"");
								String add = "";
								while (rs.next()) {
									add += rs.getInt(1) + "\t";
									add += rs.getString(2) + "\t";
									add += rs.getInt(3) + "\t";
									pw.println(add);
									
								}
                             pw.println("****************************"); 

							 pw.flush();	
								System.out.println("Backup success");
							}
							System.out.println("Enter Employee id for Updating Salary");
							int id1 = sc.nextInt();
							System.out.println("Enter new Salary Increment");
							int sal1 = sc.nextInt();
							st.executeUpdate("update "+table+" set esal = esal +" + sal1 + " where eid = " + id1 + "");
							System.out.println("Updated");
						System.out.println(" Press  Y to update more and  N for menu ");
						String opt = sc.next();
						if (opt.equals("Y") || opt.equals("y")) {
							continue;
						} else {
							b1 = false;
							break;
						}
					}
					break;
					
					
case 3://DELETE OPERATION
					
						while (true) {
						System.out.println("Delete by ID");
							System.out.println("Press Y for Backup before Deleting Data and N  fo operation");
							String dd = sc.next();

							if (dd.equals("Y") || dd.equals("y")) {

								pw.println("*************BACKUP*************");
							
								ResultSet rs = st.executeQuery("select * from "+table+"");
								String add = "";
								while (rs.next()) {
									add += rs.getInt(1) + "\t";
									add += rs.getString(2) + "\t";
									add += rs.getInt(3) + "\t";
								pw.println(add);	
								}

							pw.println("*************************");	
								
								pw.flush();
								System.out.println("Backup Success");
							}

							System.out.println("\nEnter Employee id to Delete data.");
							int id1 = sc.nextInt();
							st.executeUpdate("delete from "+table+" where eid = " + id1 + "");
							System.out.println("Deleted ");
						
						System.out.println(" Press Y for more Delete and  N for Menu ");
						String opt = sc.next();
						if (opt.equals("Y") || opt.equals("y")) {
							continue;
						} else {
							    break;
						}
					}
					break;

case 4:
					ResultSet rs = st.executeQuery("select * from "+table+"");
					System.out.println("Reveiw Table ...");
					System.out.println("\nEid\t\tEname\t\tESal\n");
					while (rs.next()) {
						System.out.print(rs.getInt(1) + "\t\t");
						System.out.print(rs.getString(2) + "\t\t");
						System.out.println(rs.getInt(3));
					}
					break;
				default:
					System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
					break;
				}
			}
		}} catch (Exception e) {
			e.printStackTrace();
		}
	}
}