import java.io.*;
import java.net.PasswordAuthentication;
import java.text.BreakIterator;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class semesterProject {

    public static void main(String[] args) {
        ArrayList<String> returnstaffrec = new ArrayList<>();
        ArrayList<String> returnmemberdata=new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int admin;
        int adminselect;
        int adminselectstaffoption;
        int selctmember;

        // Admin login
        do {
            System.out.println("1-Administtation\n2-Society member\n3-Staff\n4-Exit");
            admin = input.nextInt();
            if (admin == 1) {
                adminlogin(admin, input);

                // housing, rent house , staff manage,
                do {

                System.out.println("select the following option\n1-member record\n2-Staff record\n3-client house booking detail\n4-view member complaint\n5-Exit");
                adminselect = input.nextInt();
                if (adminselect == 1) {
                    do {
                        System.out.println(
                                "Select option\n1-ADD member\n2-update member\n3-delete member\n4-View member\n Enter 0 for exit");
                        adminselectstaffoption = input.nextInt();
                        String adminselectoptioninstr = Integer.toString(adminselectstaffoption);

                        if (adminselectoptioninstr.equals("1")) {
                            returnmemberdata = memberrecord(adminselect);
                            System.out.println(returnmemberdata);

                        } else if (adminselectoptioninstr.equals("2")) {
                            updatemem(returnmemberdata);

                        } else if (adminselectoptioninstr.equals("3")) {
                            deletememberrecord(returnmemberdata);
                        } else if (adminselectoptioninstr.equals("4")) {
                            viewmemrec(returnmemberdata);
                        }
                    } while (adminselectstaffoption != 0);

                } else if (adminselect == 2) {
                    do {
                        System.out.println(
                                "Select option\n1-ADD staff\n2-update staff\n3-delete staff\n4-View Staff\n Enter 0 for exit");
                        adminselectstaffoption = input.nextInt();
                        String adminselectoptioninstr = Integer.toString(adminselectstaffoption);

                        if (adminselectoptioninstr.equals("1")) {
                            returnstaffrec = staffrecord(adminselect, input);
                            System.out.println(returnstaffrec);



                        } else if (adminselectoptioninstr.equals("2")) {
                            update(returnstaffrec);

                        } else if (adminselectoptioninstr.equals("3")) {
                            deletestaffrecord(returnstaffrec);
                        } else if (adminselectoptioninstr.equals("4")) {
                            viewstaffrecord(returnstaffrec);
                        }
                    } while (adminselectstaffoption != 0);
                } else if (adminselect == 3) {
                    clientbokhouse();

                } else if (adminselect == 4) {
                    viewcomplaintmem();
                }else{
                    System.out.println("please enter correct option");
                }
            }while (adminselect!=5);
            } else if (admin == 2) {
                int choice;

                do {
                    System.out.println("Select option\n1-sign up\n2-login\3-Exit");
                    choice = input.nextInt();
                    // int toint= Integer.parseInt(choice);
                    if (choice == 1) {
                        signup(input);

                    } else if (choice == 2) {
                        loginin(input);

                    }
                } while (choice != 2);
                do {
                    System.out.println("select the option\n1-Plots detail and booking\n2-Houses detail and booking\n3-Registered complain\n4-Exit");
                    selctmember = input.nextInt();
                    if (selctmember == 1) {
                        plotbook();

                    } else if (selctmember == 2) {
                        housebook();

                    } else if (selctmember == 3) {
                        registercom();


                    }
                }while (selctmember!=4);



            }else{
                System.out.println("Please enter correct option");
            }
        } while (admin != 3);

    }

    // method for admin login
    public static void adminlogin(int admin, Scanner input) {
        while (true) {
            System.out.println("Enter username");
            String username = input.next().toLowerCase();
            System.out.println("Enter password");
            String password = input.next();
            if (password.equals("1234") && username.equals("admin")) {
                System.out.println("Welcome to the admin panel");
                break;

            } else {
                System.out.println("Invalid password please enter again");
                continue;
            }
        }

    }

    // for sign up the
    // signup
    public static void signup(Scanner input) {
        System.out.println("Welcome to the sign up panel");
        System.out.print("Enter your First Name: ");
        String Fname = input.next().toLowerCase();
        System.out.print("Enter Last name: ");
        String Lname = input.next().toLowerCase();
        System.out.print("Enter password: ");
        String pasword = input.next();
        System.out.println("                     Your username is:" + Fname.concat(Lname));
        System.out.println("                   Your password is:***********");
        try {
            FileWriter F = new FileWriter("username.txt",true);

            BufferedWriter username = new BufferedWriter(F);

            String signupid = Fname.concat(Lname);
            username.write(signupid);
            username.write("\n");
            username.write(pasword);
            username.write("\n");

            username.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    // login the member

    public static void loginin(Scanner input) {
        ArrayList<String> infostat = new ArrayList<>();

        // buffered reader
        try {
            FileReader loginfo = new FileReader(
                    "username.txt");
            BufferedReader bfinfo = new BufferedReader(loginfo);

            String Line;
            while ((Line = bfinfo.readLine()) != null) {
                // String[] arr = Line.split(",");
                infostat.add(Line);

            }
            bfinfo.close();
            System.out.println("welcome to the login page");
            System.out.print("Enter your username");
            String user = input.next();
            System.out.print("Enter password");
            String pasinfo = input.next();
            // System.out.println(infostat);
            boolean usernamefound = false;
            boolean passwordfound = false;

            for (int i = 0; i < infostat.size(); i++) {
                if (infostat.get(i).equals(user) && infostat.get(i + 1).equals(pasinfo)) {
                    usernamefound = true;
                    passwordfound = true;
                    break;

                }
            }
            if (usernamefound && passwordfound) {
                System.out.println("Succesfully login");
            } else if (!usernamefound) {
                System.out.println("Invalid username");
            } else if (!passwordfound) {
                System.out.println("Invalid password");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    //member record
    public static ArrayList<String> memberrecord(int admin) {
        Scanner input = new Scanner(System.in);
        // declare array list
        ArrayList<String> listofmember = new ArrayList<>();

        System.out.println("Enter no of member you want to add");
        int noofmember = input.nextInt();
        // delare array
        // Array delare for Staff reg No
        String[] memberRegno = new String[noofmember];
        // Array decalre for member name;
        String[] membername = new String[noofmember];
        // Array delare for staff duty
        String[] memberphoneno = new String[noofmember];
        // array for stadff slary
        String[] memberHouseNo = new String[noofmember];

        // input
        for (int i = 0; i < noofmember; i++) {

            System.out.println("Enter staff member RegNo");
            memberRegno[i] = input.next();
            System.out.println("Enter staff member Name");
            membername[i] = input.next();
            System.out.println("Enter staff member phone no");
            memberphoneno[i] = input.next();
            System.out.println("Enter Staff member house");
            memberHouseNo[i] = input.next();

        }

        // output
        for (int i = 0; i < memberRegno.length; i++) {
            // System.out.println memberRegno[i] + " " + membername[i] + " " + memberphoneno[i] +
            // " " + memberHouseNo[i]);
            listofmember.add( memberRegno[i]);
            listofmember.add(membername[i]);
            listofmember.add(memberphoneno[i]);
            listofmember.add(memberHouseNo[i]);
            Addstaffmemnertofile(listofmember);

        }


        // return listofmember;

        return listofmember;

    }


    public static void Addstaffmemnertofile(ArrayList<String> listofmember) {
        // staff array list is return
        // using buffered writer to write thw data in file
        // change the arraylist into string

        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("memeberdata.txt", true));

            for (int i = 0; i < listofmember.size(); i++) {
                bw.write(listofmember.get(i) + ",");
            }

            bw.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void deletememberrecord(ArrayList<String> returnmemberdata) {
        System.out.println("Enter your red no");
        Scanner in = new Scanner(System.in);
        String removeword = in.nextLine();
        for (int i = 0; i < returnmemberdata.size(); i++) {

            if (returnmemberdata.get(i).contains(removeword)) {
                List<String> sublist = returnmemberdata.subList(i, i + 4);
                returnmemberdata.removeAll(sublist);

            }

        }
        System.out.println(returnmemberdata);

    }

    public static void updatemem(ArrayList<String> returnmemberdata) {
        Scanner in = new Scanner(System.in);
        String newphoneno;
        String newhouseno;
        System.out.println("Enter your registration number");
        String regno = in.nextLine();

        // updatemem the array information in array list

        for (int i = 0; i < returnmemberdata.size(); i++) {
            if (returnmemberdata.get(i).contains(regno)) {
                // List<String> sublist = returnmemberdata.subList(i, i + 4);
                System.out.println("select option for updradation\n 1-updatemem phoneno\n 2-updatemem houseNo ");
                String input = in.next();
                if (input.equals("1")) {
                    System.out.println("enter new phone NO");
                    newphoneno = in.next();
                    returnmemberdata.set(i + 2, newphoneno);

                } else if (input.equals("2")) {
                    System.out.println("Enter new houseNo");
                    newhouseno = in.next();
                    returnmemberdata.set(i + 3, newhouseno);
                }
                // System.out.println(sublist);
                System.out.println(returnmemberdata);

            }

        }
    }

    public static void viewmemrec(ArrayList<String> returnmemberdata) {
        System.out.println("Enter your red no");
        Scanner in = new Scanner(System.in);
        String viewrcord = in.nextLine();
        for (int i = 0; i < returnmemberdata.size(); i++) {

            if (returnmemberdata.get(i).contains(viewrcord)) {
                List<String> sublist = returnmemberdata.subList(i, i + 4);
                System.out.println(sublist);

            }

        }

    }

    public static void getmemdatafromfile() {
        // declare arraylist
        ArrayList<String> datastaff = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("memberdata.txt"));

            String Line;
            while ((Line = br.readLine()) != null) {
                String[] arr = Line.split(",");
                Line = String.join(",", arr);
                datastaff.add(Line);
            }
            System.out.println(datastaff);
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    // member add the the recoed
    public static ArrayList<String> staffrecord(int adminselect, Scanner input) {
        // declare array list
        ArrayList<String> listofstaff = new ArrayList<>();
        if (adminselect == 2) {

            System.out.println("Enter no of staff");
            int noofstaff = input.nextInt();
            // delare array
            // Array delare for Staff reg No
            String[] StaffRedNo = new String[noofstaff];
            // Array decalre for staff staff name;
            String[] staffname = new String[noofstaff];
            // Array delare for staff duty
            String[] staffsuty = new String[noofstaff];
            // array for stadff slary
            String[] staffsalary = new String[noofstaff];

            // input
            for (int i = 0; i < noofstaff; i++) {

                System.out.println("Enter staff member RegNo");
                StaffRedNo[i] = input.next();
                System.out.println("Enter staff member Name");
                staffname[i] = input.next();
                System.out.println("Enter staff member duty");
                staffsuty[i] = input.next();
                System.out.println("Enter Staff member salary");
                staffsalary[i] = input.next();

            }

            // output
            for (int i = 0; i < StaffRedNo.length; i++) {
                // System.out.println(StaffRedNo[i] + " " + staffname[i] + " " + staffsuty[i] +
                // " " + staffsalary[i]);
                listofstaff.add(StaffRedNo[i]);
                listofstaff.add(staffname[i]);
                listofstaff.add(staffsuty[i]);
                listofstaff.add(staffsalary[i]);

            }
            // return listofstaff;
        }
        return listofstaff;
    }

    public static void deletestaffrecord(ArrayList<String> returnstaffrec) {
        System.out.println("Enter your red no");
        Scanner in = new Scanner(System.in);
        String removeword = in.nextLine();
        for (int i = 0; i < returnstaffrec.size(); i++) {

            if (returnstaffrec.get(i).contains(removeword)) {
                List<String> sublist = returnstaffrec.subList(i, i + 4);
                returnstaffrec.removeAll(sublist);

            }

        }
        System.out.println(returnstaffrec);

    }

    public static void update(ArrayList<String> returnstaffrec) {
        Scanner in = new Scanner(System.in);
        String newduty;
        String newsalary;
        System.out.println("Enter your registration number");
        String regno = in.nextLine();

        // update the array information in array list

        for (int i = 0; i < returnstaffrec.size(); i++) {
            if (returnstaffrec.get(i).contains(regno)) {
                // List<String> sublist = returnstaffrec.subList(i, i + 4);
                System.out.println("select option for updradation\n 1-update duty\n 2-update salary package ");
                String input = in.next();
                if (input.equals("1")) {
                    System.out.println("enter new duty assign to staff");
                    newduty = in.next();
                    returnstaffrec.set(i + 2, newduty);

                } else if (input.equals("2")) {
                    System.out.println("Enter new salary pakage");
                    newsalary = in.next();
                    returnstaffrec.set(i + 3, newsalary);
                }
                // System.out.println(sublist);
                System.out.println(returnstaffrec);

            }

        }
    }

    public static void viewstaffrecord(ArrayList<String> returnstaffrec) {
        System.out.println("Enter your red no");
        Scanner in = new Scanner(System.in);
        String viewrcord = in.nextLine();
        for (int i = 0; i < returnstaffrec.size(); i++) {

            if (returnstaffrec.get(i).contains(viewrcord)) {
                List<String> sublist = returnstaffrec.subList(i, i + 4);
                System.out.println(sublist);

            }

        }

    }

    // method for plot details
    public static void plotforsale() {
        Scanner input=new Scanner(System.in);

        System.out.println("Welcome to our society ");
        System.out.println("select the option \n1-To see the plot detail and price \n2- booking of plot");
        int memeber = input.nextInt();
        if (memeber == 1) {

            // read the file
            try {
                BufferedReader plotdata = new BufferedReader(new FileReader(
                        "addplots.txt"));
                // String data= plotdata.readLine();
                String data = " ";
                while (plotdata.readLine() != null) {
                    data = plotdata.readLine();
                    System.out.println(data);

                }

            } catch (Exception e) {
                // TODO: handle exception
            }

        } else if (memeber == 2) {// bookin the plot
            System.out.println("Enter search word");
            String word = input.nextLine();
            try {
                BufferedReader plotdata = new BufferedReader(new FileReader(
                        "addplots.txt"));
                // String data= plotdata.readLine();

                String str = " ";
                String data = " ";
                while ((str = plotdata.readLine()) != null) {
                    if (str.contains(word)) {
                        System.out.println(str + "\n");
                    }

                }
                plotdata.close();

            } catch (Exception e) {
                System.out.println("Exception handled");

            }

        }

    }
    public static void view(){

        //Arraylist
        ArrayList<String>list= new ArrayList<>();
        Scanner input= new Scanner(System.in);
        BufferedReader bw= null;
        try {
            bw = new BufferedReader(new FileReader(
                    "E:\\Java vscode\\java project file\\addhouses.txt"));

            String str=" ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }
            while ((str=bw.readLine())!=null){
                System.out.println(str);

            }
            System.out.println("_______________________________________________");
            bw.close();


        }catch (Exception e){
            System.out.println("Exception handle");
        }




    }
    public static void viewhouses(){

        //Arraylist
        ArrayList<String>list= new ArrayList<>();
        Scanner input= new Scanner(System.in);
        BufferedReader bw= null;
        try {
            bw = new BufferedReader(new FileReader("E:\\Java vscode\\java project file\\addhouses.txt"));

            String str=" ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }
            System.out.println("Enter house size for booking (5 for marla 10 for 10marla 19 for 19marla");
            String userbokhouse = input.nextLine();
            String word= userbokhouse.concat("MARLA");
            while ((str=bw.readLine())!=null){
                if(str.contains(word)){
                    System.out.println(str);
                }
            }
            bw.close();


        }catch (Exception e){
            System.out.println("Exception handle");
        }




    }
    public static void housebook(){
        view();
        viewhouses();

        Scanner input= new Scanner(System.in);
        BufferedReader bw= null;
        try {


            bw = new BufferedReader(new FileReader(
                    "addhouses.txt"));

            String str=" ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }

            System.out.println("Enter house no you want to book");
            String houseno = input.nextLine();
            System.out.println("Enter your name");
            String nameofclient= input.nextLine();
            String housenotostr= houseno.concat("HOUSENO");
            while ((str=bw.readLine())!=null){
                if(str.contains(housenotostr)){
                    System.out.println(str);
                }

            }
            System.out.println("You are successfully booked");
            bw.close();


            // ArrayList
            ArrayList<String> list = new ArrayList<>();

            // BufferedReader
            File F = new File("E:\\Java vscode\\java project file\\addhouses.txt");
            try (BufferedReader bf = new BufferedReader(new FileReader(F))) {
                String line;

                while ((line = bf.readLine()) != null) {
                    String[] values = line.split(",");
                    line=String.join(",",values);
                    list.add(line);



                }
                //first create the file and append the house no that user buy in a another file
                FileWriter us=new FileWriter("E:\\Java vscode\\java project file\\houseclient.txt",true);
                BufferedWriter newus=new BufferedWriter(us);
                newus.write(nameofclient+":");
                for(int i=0; i<list.size(); i++){
                    if(list.get(i).contains(housenotostr)){
                        newus.write("\n");
                        newus.write(list.get(i));

                    }
                }
                newus.close();

                for (int i=0; i<list.size(); i++) {
                    if (list.get(i).contains(housenotostr)) {
                        list.remove(i);
                    }


                }
                System.out.println(list);
                bf.close();
//            F.delete();

                //make new file
                BufferedWriter newbf=new BufferedWriter(new FileWriter("E:\\Java vscode\\java project file\\addhouses.txt"));

                for(int i=0; i<list.size(); i++){
                    newbf.write(list.get(i));
                    newbf.write("\n");
                }
                newbf.close();




            } catch (Exception e) {
                e.printStackTrace();
            }


        }catch (Exception e){
            System.out.println("Exception handle");
        }



    }

    //method for client book
    public static void clientbokhouse(){
        //client housing file deatail
        try{
            BufferedReader clientpurchasehousedetail= new BufferedReader(new FileReader("E:\\Java vscode\\java project file\\houseclient.txt"));

            String str=" ";
            System.out.println("client data who purchase your data");
            while((str=clientpurchasehousedetail.readLine())!=null){
                System.out.println(str);

            }
            clientpurchasehousedetail.close();

        }catch (Exception e){
            System.out.println("Exception handle");

        }


    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Plots booking>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void plotview(){

        //Arraylist
        ArrayList<String>list= new ArrayList<>();
        Scanner input= new Scanner(System.in);
        BufferedReader bw= null;
        try {
            bw = new BufferedReader(new FileReader("E:\\Java vscode\\java project file\\addplots.txt"));

            String str=" ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }
            System.out.println("plotNO   :StreetNo  :PlotSize  :PlotPrice ");
            while ((str=bw.readLine())!=null){
                System.out.println(str);

            }
            System.out.println("_______________________________________________");
            bw.close();


        }catch (Exception e){
            System.out.println("Exception handle");
        }




    }
    public static void viewplot(){


        //Arraylist
        ArrayList<String>list= new ArrayList<>();
        Scanner input= new Scanner(System.in);
        BufferedReader bw= null;
        try {
            bw = new BufferedReader(new FileReader(
                    "E:\\Java vscode\\java project file\\addplots.txt"));

            String str=" ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }
            System.out.println("Enter house size for booking (5 for marla 10 for 10marla 19 for 19marla");
            String userbokhouse = input.nextLine();
            String word= userbokhouse.concat("MARLA");
            while ((str=bw.readLine())!=null){
                if(str.contains(word)){
                    System.out.println(str);
                }
            }
            bw.close();


        }catch (Exception e){
            System.out.println("Exception handle");
        }




    }
    public static void plotbook(){

        plotview();
        viewplot();

        Scanner input= new Scanner(System.in);
        String choicselect;
        System.out.println("You want to book your plot (YES or NO)");
        choicselect = input.nextLine().toUpperCase();

        if (choicselect.equals("YES")) {
            viewplot();
            BufferedReader bw = null;
            try {


                bw = new BufferedReader(new FileReader(
                        "E:\\Java vscode\\java project file\\addplots.txt"));

                String str = " ";
//            while ((str= bw.readLine())!=null){
//                System.out.println(str);
//            }

                System.out.println("Enter Plot no you want to book");
                String plotno = input.nextLine();
                System.out.println("Enter your name");
                String nameofclient = input.nextLine();
                System.out.println("Your mobile number");
                String phonenoofclient = input.nextLine();
                String housenotostr = plotno.concat("PLOTNO");
                while ((str = bw.readLine()) != null) {
                    if (str.contains(housenotostr)) {
                        System.out.println(str);
                    }

                }
                System.out.println("You are successfully booked Your plot");
                bw.close();


                // ArrayList
                ArrayList<String> list = new ArrayList<>();

                // BufferedReader
                File F = new File("E:\\Java vscode\\java project file\\addplots.txt");
                try (BufferedReader bf = new BufferedReader(new FileReader(F))) {
                    String line;

                    while ((line = bf.readLine()) != null) {
                        String[] values = line.split(",");
                        line = String.join(",", values);
                        list.add(line);


                    }
                    //first create the file and append the house no that user buy in a another file
                    FileWriter us = new FileWriter("E:\\Java vscode\\java project file\\plotclient.txt", true);
                    BufferedWriter newus = new BufferedWriter(us);
                    newus.write(nameofclient + ":");
                    newus.write(phonenoofclient + ":");
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).contains(housenotostr)) {
                            newus.write("\n");
                            newus.write(list.get(i));

                        }
                    }
                    newus.close();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).contains(housenotostr)) {
                            list.remove(i);
                        }


                    }
                    System.out.println(list);
                    bf.close();
//            F.delete();

                    //make new file
                    BufferedWriter newbf = new BufferedWriter(new FileWriter("E:\\Java vscode\\java project file\\addplots.txt"));

                    for (int i = 0; i < list.size(); i++) {
                        newbf.write(list.get(i));
                        newbf.write("\n");
                    }
                    newbf.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


            } catch (Exception e) {
                System.out.println("Exception handle");
            }



        }


    }
    public static void registercom(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter house number");
        String houseno=sc.nextLine();
        System.out.println("Enter you compalint");
        String enter=sc.nextLine();
        try{
            BufferedWriter br= new BufferedWriter(new FileWriter("comp.txt"));
            br.write("\n");
            br.write(houseno+":");
            br.write(enter);

        }catch (Exception e){
            System.out.println("exception handle");
        }

    }
    public static void viewcomplaintmem(){
        try{
            BufferedReader bf= new BufferedReader(new FileReader("comp.txt"));

            String Line;

            while((Line=bf.readLine())!=null){
                System.out.println(Line);

            }

        }catch (Exception e){
            System.out.println("Exception handle");
        }

    }


}