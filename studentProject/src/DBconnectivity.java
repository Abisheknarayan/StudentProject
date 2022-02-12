import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
public class DBconnectivity
{
	Connection con = null;
	Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Student_attendance_management_System","root","root");
		return con;
	}
	public static void login(Scanner sc , Connection con) throws Exception
	{
		
		String login=sc.next();
		if(login.equalsIgnoreCase("Admin"))
		{
			admin_login(sc , con);
		}
		else
		{
			student_login(sc , con);
		}
	}
	public static void admin_login(Scanner sc, Connection con)throws Exception
	{
		int iend1=4;
		do
		{
			int s1=0;
					Statement stm=con.createStatement();
					System.out.println("Admin Username => " );
					String uname=sc.next();
					System.out.println("Admin Password => " );
					String pass=sc.next();
					String sql="select *from admin_login where username='"+uname+"' and password='"+pass+"' ";
					ResultSet res= stm.executeQuery(sql);
					if(res.next())
					{
						System.out.println();
						System.out.println("	login sucess !!!	");
						System.out.println();
						int iend=0;
						do
						{	
							iend=0;
							System.out.println("Enter the numbers respectively to modify :	");
							System.out.println();
							System.out.println("  (1) Add Time Table ");
							System.out.println("  (2) Add student"  );
							System.out.println("  (3) Delete student  ");
							System.out.println("  (4) Display Student Details  ");
							System.out.println("  (5) Mark Attandance  ");
							System.out.println("  (6) View Attandance  ");
							int itest=sc.nextInt();
							switch(itest)
							{
								case 1:
								{
									to_add_timetable(sc,con);
									break;
								}
								case 2:
								{
									to_add_student(sc,con);
									break;
								}
								case 3:
								{
									to_delete_student(sc,con);
									break;
								}
								case 4:
								{
									to_display_student_detail(sc ,con);
									break;
								}
								case 5:
								{
									to_mark_attendance(sc,con);
									break;
								}
								case 6:
								{
									to_view_attendance(sc,con);
									break;
								}
							}
							if(iend==0)
							{
								System.out.println();
								System.out.println("	Press '0' to return Main Menu  ");
								System.out.println("	Press '1' to Exit   ");
								int k=sc.nextInt();
								if(k==0)
								{
									iend++;
								}	
								else
								{	
									System.out.println("	Thank you for your response!!!");
									s1++;	
								}
							}
						}while(iend>0);
					}
				else
				{
					System.out.println();
					System.out.println("Invalid Username or Password -_- !");
					if(iend1>0)
					{
						System.out.println();
						System.out.println("	"+(iend1-1)+" attemps allowed   ");
						iend1--;
					}
					if(iend1==0)
					{
						System.out.println("Account has been locked !!!!!");
						
					}
				}
					if(s1>0)
					{
						iend1=0;
					}
		}while(iend1>=1);
}
	
	public static void to_add_timetable(Scanner sc , Connection con) throws Exception
	{
		Statement stm=con.createStatement();
		String sql5="select class_id from time_table";
		ResultSet res2=stm.executeQuery(sql5);
		res2.next();
		System.out.println("Kindly Enter the given class_id ");
		while(res2.next())
		{
			String class1=res2.getString("class_id");
			System.out.println(class1);
		}
		System.out.println();
		System.out.println("Enter in the class_id use (_) or (-) for space ( ) : ");
		String Class_id=sc.next();
		System.out.println("	Enter the Number of Days [1-7] to added:");
		int isize=sc.nextInt();
		if(isize>0 && isize<8)
		{
			sc.nextLine();
			for(int i=0;i<isize;i++)
			{
				int k=0;
				do
				{
					    k=0;	
					System.out.println("Enter the day :");
					String day=sc.nextLine();
					String sql="select day from time_table";
					ResultSet res1=stm.executeQuery(sql);
					res1.next();
					String check=res1.getString(1);
					if(day.equalsIgnoreCase(check))
					{
						System.out.println("Day is already Defined kindly select another day ");
							k++;
					}
					else
					{
						System.out.println("Total Number of Hours : 5");
						System.out.println();
						System.out.println("Enter the 1st hour : ");
						String h1=sc.nextLine();
						System.out.println("Enter the 2st hour : ");
						String h2=sc.nextLine();
						System.out.println("Enter the 3rd hour : ");
						String h3=sc.nextLine();
						System.out.println("Enter the 4th hour : ");
						String h4=sc.nextLine();
						System.out.println("Enter the 5th hour : ");
						String h5=sc.nextLine();
						String sql1="insert into time_table values ('"+Class_id+"','"+day+"','"+h1+"','"+h2+"','"+h3+"','"+h4+"','"+h5+"')";
						int res=stm.executeUpdate(sql1);
						if(res==1)
						{
							System.out.println();
							System.out.println("	"+Class_id+"  Time Table has been updated Sucessfully!!!	");
							System.out.println();
						}
					}
				}while(k>0);	
			}
		}	
		else
		{
			System.out.println();
			System.out.println("Invalid Number of Day ");
		}	
	}
	public static void to_add_student(Scanner sc , Connection con) throws Exception
	{
		Statement stm=con.createStatement();
		System.out.println("Enter the Number of Students to be added :");
		int isize=sc.nextInt();
		for(int i=0;i<isize;i++)
		{
			System.out.println("Enter in the class_id use (_) or (-) for space ( ) : ");
			String class_id=sc.next();
			System.out.println("Enter the Name : ");
			String iname=sc.next();
			System.out.println("Enter the Gender [ Male[M] or Female[F] or Transgender[T] ] :");
			String g=sc.next();
			System.out.println("Enter the Date of Birth [YYYY-MM-DD] :");
			String dob=sc.next();
			System.out.println("Enter the Father's Name : ");
			String fname=sc.next();
			System.out.println("Enter the Mother's Name : ");
			String mname=sc.next();
			System.out.println("Enter the Parent's Mobile Number :");
			String inum=sc.next();
			System.out.println("Enter the New Register Number :");
			String reg=sc.next();
			System.out.println("Enter the New Password :");
			String pwd=sc.next();
			String sql2="insert into student values ('"+class_id+"','"+iname+"','"+g+"','"+dob+"','"+fname+"','"+mname+"','"+inum+"','"+reg+"')";
			int res=stm.executeUpdate(sql2);
			String sql3="insert into student_login values('"+reg+"','"+pwd+"')";
			stm.executeUpdate(sql3);
			int ab=0;
			double per=100;
			String sql5="insert into attendance values('"+reg+"','"+ab+"','"+String.format("%.2f", per)+"','"+class_id+"')";
			stm.executeUpdate(sql5);
			if(res==1)
			{
				System.out.println();
				System.out.println("	"+reg+" Student Record has been Updated !!!	");
				System.out.println();
			}
			else
			{
				System.out.println("	"+reg+" Student Record has not been Updated - _- Kindly Check again ");
			}
		}
	}
	public static void to_delete_student(Scanner sc , Connection con) throws Exception
	{
		Statement stm=con.createStatement();
		System.out.println("  Enter the Number of Students to be Removed :  ");
		int isize=sc.nextInt();
		sc.nextLine();
		while(isize!=0)
		{	
			System.out.println("Enter the Register Number : (eg:[004])");
			String regno=sc.next();
			String sql3="delete from student where reg_no = '"+regno+"'";
			String sql2="delete from student_login where reg_no = '"+regno+"'";
			String sql4="delete from attendance where reg_no = '"+regno+"'";
			int res = stm.executeUpdate(sql3);
			stm.executeUpdate(sql2);
			stm.executeUpdate(sql4);
			if(res==1)
			{
				System.out.println();
				System.out.println("	 "+regno+" Student has been Removed Sucessfully!!! 	");
				System.out.println();
			}
			else
			{
				System.out.println("	"+regno+" Student Record has not been Updated - _- Kindly Check again ");
			}
			isize--;
	
		}
	}
	public static void to_display_student_detail(Scanner sc , Connection con) throws Exception
	{
		Statement stm=con.createStatement();
		sc.nextLine();
		String sql5="select class_id from time_table";
		ResultSet res2=stm.executeQuery(sql5);
		res2.next();
		System.out.println("Kindly select the given class_id ");
		while(res2.next())
		{
			String class1=res2.getString("class_id");
			System.out.println(class1);
		}
		System.out.println();
		System.out.println("Enter the Class Id to display Student_Detail :");
		String class_id=sc.nextLine();
		String sql4="select *from student where class_id ='"+class_id+"'";
		ResultSet res=stm.executeQuery(sql4);
		while(res.next())
		{
			String classid = res.getString("class_id");
			String reg = res.getString("reg_no");
			String name = res.getString("name");
			String gender = res.getString("gender");
			String dob = res.getString("D_O_B");
			String fname = res.getString("Father_name");
			String mname = res.getString("Mother_name");
			String pnumber =res.getString("Phone_number");
			System.out.println();
			System.out.println("Class_id : "+classid);
			System.out.println("Register Number : "+reg);
			System.out.println("Name : "+name);
			System.out.println("Gender : "+gender);
			System.out.println("Date of Birth : "+dob);
			System.out.println("Father Name : "+fname);
			System.out.println("Mother Name : "+mname);
			System.out.println("Parent's Number : "+pnumber);
			System.out.println();
		}
		System.out.println();
		System.out.println("Details has been displayed Sucessfully....");
		System.out.println();
	}
	public static void to_mark_attendance(Scanner sc,Connection con)throws Exception
	{
		Statement stm = con.createStatement();
		String sql5="select class_id from time_table";
		ResultSet res2=stm.executeQuery(sql5);
		res2.next();
		System.out.println("Kindly Enter the given class_id ");
		while(res2.next())
		{
			String class1=res2.getString("class_id");
			System.out.println(class1);
		}
		System.out.println("	Enter the Class_Id to Mark Attendance:");
		String class_id=sc.next();
		System.out.println();
		String sql3="select name , reg_no from student";
		ResultSet res4=stm.executeQuery(sql3);
		System.out.println("Kindly Enter the given Register Number :");
		while(res4.next())
		{
			String class1=res4.getString("name");
			String reg=res4.getString("reg_no");
			System.out.println(reg+"  "+class1);
		}
		String sql1="Select count(reg_no) from attendance where class_id='"+class_id+"'";
        ResultSet res= stm.executeQuery(sql1);
        res.next();
		int isize = res.getInt(1);
		System.out.println(" Number of student in "+class_id+" : "+isize);
		System.out.println();
        System.out.println("Total no of days : 120");
        System.out.println();
        for(int i=0;i<isize;i++)
        {
		        System.out.println("Enter Register Number eg[004]:");
		        String reg=sc.next();
		        System.out.println("press 'P' for present and 'A' for Absent : ");
		        char ch=sc.next().charAt(0);
		        if(ch=='P')
		        {
		        	String sql2="Select *from Attendance where reg_no='"+reg+"'";
		        	ResultSet res1= stm.executeQuery(sql2);
			        res1.next();
			        int ab=res1.getInt("absent_days");
			        ab=ab+0;
				    int res3=stm.executeUpdate("Update Attendance set absent_days ='"+ab+"' where reg_no ='"+reg+"'");
				    if(res3==1)
				    {
				    	System.out.println("Attendance has been updated Sucessfully ");
				    	System.out.println();
				    }
				    else
				    	System.out.println("Attendance has been not been updated");
		        }
			    else if(ch=='A')
			    {
			    	String sql4="Select absent_days,Percentage from Attendance where reg_no='"+reg+"'";
			    	ResultSet res3= stm.executeQuery(sql4);
		            res3.next();
		            int ab=res3.getInt("absent_days");
		            double per=res3.getInt("Percentage");
		            ab=ab+1;
		            per=120-ab;
		    		double p1=(per*100);
		    		double p2=p1/120;

		            String sql2="Update Attendance set absent_days ='"+ab+"',Percentage='"+String.format("%.2f", p2)+"' where reg_no ='"+reg+"'";
			        int res1= stm.executeUpdate(sql2);
			        if(res1==1)
			        {
				    	System.out.println("	Attendance has been updated Sucessfully ");
			        	System.out.println();
			        }
			        else
				    	System.out.println("Attendance has been not been updated");
			  }
	        
        }
        System.out.println();
		
	}
	public static void to_view_attendance(Scanner sc,Connection con) throws Exception
	{
		Statement stm=con.createStatement();
		System.out.println("Kindly Enter the given class_id ");
		String sql7="select class_id from attendance  ";
		ResultSet res2=stm.executeQuery(sql7);
		res2.next();
		while(res2.next())
		{
			String class1=res2.getString("class_id");
			System.out.println(class1);
		}
		System.out.println("  Enter the Class_Id to view Attendance :");
		String class_id=sc.next();
		String sql6="select count(reg_no) from attendance where class_id ='"+class_id+"'";
		ResultSet res1=stm.executeQuery(sql6);
		res1.next();
		int isize=res1.getInt(1);
		String sql5="select reg_no,absent_days , percentage from attendance where class_id ='"+class_id+"'";
		ResultSet res=stm.executeQuery(sql5);
		int i=0;
		while(res.next())
		{
		   if(i==0)
		{
			System.out.println();
			System.out.println("   Number of student in "+class_id+" : "+isize);
			System.out.println();
			System.out.println("   Total no of days : 120");
			System.out.println();
			i++;
		}
			String reg=res.getString("reg_no");
			int absent =res.getInt("absent_days");
			int percentage=res.getInt("percentage");
			int p=120-absent;
			System.out.println();
			System.out.println("Register Number : "+reg);
			System.out.println("Out of 120 Days : "+p+" Days Present ");
			System.out.println("Out of 120 Days : "+absent+" Days Absent ");
			System.out.println("Present Days in Percentage : "+percentage+" %");
        }
        System.out.println();
		System.out.println("Details has been displayed Sucessfully....");
	}
	public static void student_login(Scanner sc, Connection con) throws Exception
	{
		int iend1=3;
		int s1=0;
		do
		{
		Statement stm=con.createStatement();
		System.out.println("Username => " );
		String uname=sc.next();
		System.out.println("Password => " );
		String pass=sc.next();
		String sql="select *from student_login where reg_no='"+uname+"' and password='"+pass+"' ";
		ResultSet res= stm.executeQuery(sql);
		if(res.next())
		{
			System.out.println();
			System.out.println("	Login sucess !!!");
			System.out.println();
			int iend=0;
			do
			{
				iend=0;
				System.out.println("Enter the below key values to view Details : ");
				System.out.println();
				System.out.println(" (1) To view Attendance :");
				System.out.println(" (2) To view your details :");
				System.out.println(" (3) To view Time Table :");
				int itest=sc.nextInt();
				switch(itest)
				{
					case 1:
					{
						view_attendance( con, uname,sc);
						break;
					}
					case 2:
					{
						view_student_detail(sc,con,uname);
						break;
					}
					case 3:
					{
						view_timetable(sc,con,uname);
						break;
					}
				}
					if(iend==0)
					{
						System.out.println();
						System.out.println("	Press '0' to return Main Menu  ");
						System.out.println("	Press '1' to Exit   ");
						int k=sc.nextInt();
						if(k==0)
							iend++;
						else
						{
							System.out.println("	Thank you for your Response");
							s1++;
						}	
					}
				}while(iend>0);
		}
		else
		{
			if(iend1>0)
			{
				System.out.println();
				System.out.println("	"+(iend1-1)+" attemps allowed   ");
				iend1--;
			}
			if(iend1==0)
			{
				System.out.println();
				System.out.println("Account has been locked -_-!");
			}
		}
		if(s1>0)
		{
			iend1=0;
		}
		}while(iend1>=1);
	}
	public static void view_attendance(Connection con,String uname,Scanner sc) throws Exception
	{
		Statement stm=con.createStatement();
		String sql6="select * from student where reg_no ='"+uname+"'";
		ResultSet res1=stm.executeQuery(sql6);
		res1.next();
		String name=res1.getString("name");
		System.out.println();
		System.out.println("    "+name+" your Attendance has been Displayed below : ");
		String sql5="select *from attendance where reg_no ='"+uname+"'";
		ResultSet res=stm.executeQuery(sql5);
		res.next();
		System.out.println();
        System.out.println("   Total no of days : 120");
        System.out.println();
		int absent =res.getInt("absent_days");
		int p=120-absent;
		double p1=(p*100);
		double p2=p1/120;
		System.out.println("Out of 120 Days : "+p+" Days Present");
		System.out.println("Out of 120 Days : "+absent+" Days Absent ");
		System.out.println("Attendance Percentage : "+String.format("%.2f", p2)+" %");
		System.out.println();
		System.out.println("Details has been displayed Sucessfully....");
	}
	public static void view_student_detail(Scanner sc , Connection con,String uname) throws Exception
	{
		Statement stm=con.createStatement();
		System.out.println("Your details are displayed below :");
		String sql2="select *from student where reg_no ='"+uname+"'";
		ResultSet res= stm.executeQuery(sql2);
		res.next();
		int k=0;
		if(k==0)
		{
			String reg = res.getString("reg_no");
			String name = res.getString("name");
			String classid = res.getString("class_id");
			String gender = res.getString("gender");
			String dob = res.getString("D_O_B");
			String fname = res.getString("Father_name");
			String mname = res.getString("Mother_name");
			String pnumber =res.getString("Phone_number");
			System.out.println();
			System.out.println("Class_id : "+classid);
			System.out.println("Register Number : "+reg);
			System.out.println("Name : "+name);
			System.out.println("Gender : "+gender);
			System.out.println("Date of Birth : "+dob);
			System.out.println("Father Name : "+fname);
			System.out.println("Mother Name : "+mname);
			System.out.println("Parent's Number : "+pnumber);
			System.out.println();
			k++;
		}
		if(k>0);
		{
			System.out.println();
			System.out.println("Details has been displayed Sucessfully....");
		}
	}
	public static void view_timetable(Scanner sc , Connection con,String uname) throws Exception
	{
		Statement stm=con.createStatement();
		System.out.println("Your class Time table is Displayed below :");
		String sql4="select class_id from student where reg_no='"+uname+"'";
		ResultSet res1=stm.executeQuery(sql4);
		res1.next();
		String class1=res1.getString("class_id");
		System.out.println(class1);
		String sql3="select *from time_table where class_id ='"+class1+"'";
		ResultSet res=stm.executeQuery(sql3);
		res.next();
		while(res.next())
		{
			String classid = res.getString("class_id");
			String day= res.getString("day");
			String h1 = res.getString("h1");
			String h2 = res.getString("h2");
			String h3 = res.getString("h3");
			String h4 = res.getString("h4");
			String h5 = res.getString("h5");
			System.out.println();
			System.out.println("Class_id : "+classid);
			System.out.println("DAY: "+day);
			System.out.println("H1 : "+h1);
			System.out.println("H2 : "+h2);
			System.out.println("H3 : "+h3);
			System.out.println("H4 : "+h4);
			System.out.println("H5 : "+h5);
			System.out.println();
		}
		System.out.println();
		System.out.println("Details has been displayed Sucessfully....");
	}
	public static void main(String[] args) throws Exception
	{
		System.out.println("Enter 'Admin' [OR] 'Student' to Login ");
		DBconnectivity obj =new DBconnectivity();
		Scanner sc=new Scanner(System.in);
		Connection connect=obj.getConnection();
		login(sc,connect);
	}
}

