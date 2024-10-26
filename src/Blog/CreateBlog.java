package Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateBlog {
    Connection connection;
    Scanner scanner;

    public CreateBlog(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }

    public void addblog(){
        scanner.nextLine();
        System.out.println("Enter the title of the blog");
        String title=scanner.nextLine();
        System.out.println();

        System.out.println("Write the Content");
        String content=scanner.nextLine();

        System.out.println("Enter the date in the format YYYY-MM-DD");
        String date=scanner.next();

//        System.out.println(date);
//        System.out.println(title);
//        System.out.println(content);
        try{
        PreparedStatement prepare= connection.
                prepareStatement("Insert into blog(title,content,date) values(?,?,?)");
        prepare.setString(1,title);
        prepare.setString(2,content);
        prepare.setString(3,date);

        int affected=prepare.executeUpdate();
        if(affected>0){
            System.out.println("Blog added Successfully");
        }else{
            System.out.println("Query not executed");
        }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void printblog(int x){
        String orderby="";

        if(x==1){
            orderby=" order by id";
        }else if(x==2){
            orderby=" order by date";
        }
        try{
            PreparedStatement prepare =connection.prepareStatement("Select * from blog where deleted=0"+orderby);

            ResultSet res=prepare.executeQuery();
            System.out.println("Blogs");
            int id=1;
            while(res.next()){

                String title=res.getString(2);
                String content=res.getString(3);
                String date=res.getString(4);


                System.out.println("---------------------------------------------------------------------");
                System.out.println(id+++":"+title);
                System.out.println("Date : "+date);
                System.out.println(content);
                System.out.println();
                System.out.println("---------------------------------------------------------------------");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
