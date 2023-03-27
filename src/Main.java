import javax.naming.directory.SearchControls;
import java.nio.ByteOrder;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    String gender;
    int age;
    String classroom;
    double score;
    public Student(){}

    public Student(int id, String name, String gender, int age, String classroom, double score) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.classroom = classroom;
        this.score = score;
    }
    void outputInfo(){
        System.out.println("Student ID = "+this.id+ ", Name = "+this.name+", Gender = "+this.gender+", Age = "+this.age+", ClassName = "+this.classroom+", Score = "+this.score);
    }
    void inputInfo(Scanner input){
        boolean isIDvalid = false;
        do{
            System.out.print("Enter ID Student :");
            try{
                id = input.nextInt();
                isIDvalid = true;
            }catch( Exception ex){
                input.nextLine();
                System.out.println("ERROR!! ID can only be Integer..!");
            }
        }while (!isIDvalid);
        System.out.print("Enter Student Name :");
        input.nextLine();
        name = input.next();
        System.out.print("Enter Student Gender :");
        input.nextLine();
        gender= input.next();
        boolean isAgevalid = false;
        do {
            System.out.print("Enter Student Age : ");
            try{
                age = input.nextInt();
                isAgevalid = true;
            }catch (Exception ex){
                input.nextLine();
                System.out.println("ERROR!! Age can only be Integer..!");
            }
        }while (!isAgevalid);
        System.out.print("Enter Studnet ClassName :");
        classroom = input.next();

        boolean isScore = false;
        do {
            System.out.print("Enter Student scorce : ");
            try{
                score = input.nextInt();
                isScore = true;
            }catch (Exception ex ){
                input.nextLine();
                System.out.println("ERROR!! Score can not be String ...!");
            }
        }while (!isScore);
    }
}
public class Main {
    static void pressEnterKey(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(" =================== Press Enter to continue ===================");
        scanner.nextLine();
    }
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student(1,"Dett","Male",22,"JAVA",100));
        students.add(new Student(2,"Nano","Male",21,"JAVA",90));
        students.add(new Student(3,"Koki","Male",20,"JAVA",80));
        Scanner input = new Scanner(System.in);
        int option = 0 ;
        do{
            System.out.println("================= Student Management System =================");
            System.out.println("1. Insert Student to System");
            System.out.println("2. Edit Student Information");
            System.out.println("3. Search Student Information");
            System.out.println("4. Delete Student Information");
            System.out.println("5. Show Student Information");
            System.out.println("6. Exit");
            System.out.print("Choose your option from (1 - 6) : ");
            try{
                option = input.nextInt();
                if(option > 6){
                    System.out.println("Worng Option ! Choose again from 1 - 6 !! ");
                }

            }catch (Exception ex){
                input.nextLine();
                option = 0;
                System.out.println("ERROR!! INVALID INPUT...! TRY AGAIN");
                pressEnterKey();
            }

            switch (option){
                case 1 :
                    System.out.println("================= Insert Student =================");
                    Student newStudent = new Student();
                    newStudent.inputInfo(input);
                    students.add(newStudent);
                    System.out.println("Successfully Add Information Student ");
                    break;
                case 2 :
                    System.out.println("=================  Edit Student Information =================");
                    Student editStudent  = new Student();
                    boolean isEditID = false;
                    int getID =0;
                    if(students.size()>0){
                        do {
                            System.out.print("Enter Student ID to Edit : ");
                            try{
                                getID = input.nextInt();
                                isEditID = true;


                            }catch (Exception ex){
                                input.nextLine();
                                System.out.println("ERROR!! ID can only be Integer..!");
                            }

                        }while (!isEditID);
                        boolean isStudent = false;
                        for(int i = 0 ; i<students.size() ; i++) {
                            if (students.get(i).id == getID) {
                                isStudent = true;
                                editStudent = students.get(i);
                                editStudent.inputInfo(input);
                                students.set(i, editStudent);
                                System.out.println("Successfully for edit Student");
                            }else {
                                isStudent = false;
                            }


                        }
                            if(!isStudent){
                                System.out.println("Student with ID = "+getID+ "doesn't exist");
                            }
                    }else{
                        System.out.println("!!! Student not found for Edit ....");
                    }
                    break;
                case 3:
                    System.out.println("================= Search Student Information =================");

                        int searchOption = 0;
                        boolean isSearchOption = false;
                        if(students.size()>0){
                            do{
                                System.out.println("1. Search By ID ");
                                System.out.println("2. Search By Name ");
                                System.out.println("3. Search By Gender");
                                System.out.println("4. Search By ClassName ");
                                System.out.println("5. Exit");
                                System.out.print("Choose your option from (1 to 5) : ");
                                try{
                                    searchOption = input.nextInt();
                                    if(searchOption>5){
                                    System.out.println("Worng Option ! Choose again from 1 - 5 !!");
                                    }
                                    isSearchOption = true;
                                }catch (Exception ex ){
                                input.nextLine();
                                System.out.println("ERROR !! OPTION CAN ONLY THE INTEGER ....!" );
                                }
                                switch (searchOption){
                                    case 1 :
                                        int searchID = 0 ;
                                        boolean isStudentExit = false;
                                    System.out.println("========== Search By ID ==========");
                                        System.out.print(" Enter ID for Search : ");
                                        searchID = input.nextInt();
                                        for(int i = 0 ; i< students.size();i++){
                                            if(students.get(i).id == searchID){
                                                isStudentExit = true;
                                                students.get(i).outputInfo();
                                            }
                                        }
                                        if(!isStudentExit){
                                            System.out.println("Student ID "+searchID+"not exit try different  again !");
                                        }

                                    break;
                                    case 2 :
                                    System.out.println("========== Search By Name ==========");
                                        String searchName ;
                                        System.out.print(" Enter Name for Search : ");
                                        input.nextLine();
                                        searchName = input.nextLine();
                                        for(int i = 0 ; i< students.size();i++){
                                            if(searchName.equalsIgnoreCase(students.get(i).name)){
                                                System.out.println(students.get(i));
                                            }
                                        }
                                        input.nextLine();
                                    break;
                                    case 3 :
                                    System.out.println("========== Search By Gender ==========");
                                        String searchGender ;
                                        System.out.print(" Enter Gender for Search : ");
                                        input.nextLine();
                                        searchGender = input.nextLine();
                                        for(int i = 0 ; i< students.size();i++){
                                            if(searchGender.equalsIgnoreCase(students.get(i).gender)){
                                                System.out.println(students.get(i));
                                            }
                                        }
                                        input.nextLine();
                                    break;
                                    case 4 :
                                    System.out.println("========== Search By Classname ==========");
                                        String searchClassName ;
                                        System.out.print(" Enter Gender for Search : ");
                                        input.nextLine();
                                        searchClassName = input.nextLine();
                                        for(int i = 0 ; i< students.size();i++){
                                            if(searchClassName.equalsIgnoreCase(students.get(i).classroom)){
                                                System.out.println(students.get(i));
                                            }
                                        }
                                        input.nextLine();
                                    break;
                                }
                            }while (searchOption!=5);
                        }else {
                            System.out.println("Student not found for search.....!!!!");
                    }
                    break;
                case 4:
                    System.out.println("================= Delete Student Information =================");
                    boolean idDelete = false;
                    int getId = 0;
                    if(students.size()>0){
                        do {
                            System.out.print("Enter Student ID to Remove : ");
                            try{
                                getId = input.nextInt();
                                idDelete = true;
                            }catch (Exception ex){
                                input.nextLine();
                                getId = 0;
                                System.out.println("ERROR!! ID can only be Integer..");
                                pressEnterKey();
                            }
                        }while (!idDelete);
                        boolean isDelete = false;
                            for(int i = 0 ; i<students.size(); i++){
                                if(students.get(i).id == getId){
                                    isDelete=true;
                                    students.remove(i);
                                    System.out.println("Delete Successfully");
                                }
                            }
                            if(!isDelete){
                                System.out.println("Student with  ID"+getId+" doesn't exit");
                            }

                    }else {
                        System.out.println("!!! Student not found for Delete ....");
                    }
                    break;
                case 5:
                    System.out.println("================= Show Student Infromation =================");
                    for (int i = 0 ;i <students.size(); i++){
                        students.get(i).outputInfo();
                    }
                    break;
                case 6:
                    System.out.println("Exit the program....");
                    break;
//                default:
//                    System.out.println("Worng Option ! Choose again from 1 - 6 !! ");
//                    pressEnterKey();
//                    break;
            }

        }while(option != 6);

    }
}