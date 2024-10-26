package Blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {
    private static String url="jdbc:mysql://localhost:3306/blogmaster";

    private static String  username="root";
    private static String password="";

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection connection=null;
        try{
             connection= DriverManager.getConnection(url,username,password);
        }catch(SQLException e){
            e.printStackTrace();
        }
        Scanner scanner=new Scanner(System.in);

        CreateBlog blog=new CreateBlog(connection,scanner);

        while(true){
            System.out.println("--------------------------------------------------");
            System.out.println("Blog Writing");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Write Blog");
            System.out.println("2. View Blogs");
            System.out.println("3. Exit");
            int choice=scanner.nextInt();

            switch (choice){
                case 1:
                    blog.addblog();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("1. View By the Id");
                    System.out.println("2. View By the Date");
                    int choice2=scanner.nextInt();
                    switch (choice2){
                        case 1:blog.printblog(1);
                                break;

                        case 2:blog.printblog(2);
                                break;

                        default:
                            System.out.println("Enter Valid input");

                    }

                    break;

                case 3:
                    return;
                default:
                    System.out.println("Enter Valid input");
            }


        }

    }
}
