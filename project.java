import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class project {
    static void mainmenu() {
        System.out.println("Press 1 to Book Room.");
        System.out.println("Press 2 to Cancel Booking.");
        System.out.println("Press 3 to Show Reservations.");
        System.out.println("Press 4 to Exit Application.");
    }

    public static void main(String[] args) throws IOException {
        String roomno[] = new String[100];
        String roomtype[] = new String[100];
        double[] roomprice = new double[100];
        String[] hasbalcony = new String[100];
        String[] haslounge = new String[100];
        String[] email = new String[100];
        File file = new File("rooms.txt");// file reading
        Scanner input = new Scanner(file);
        int count = 1;
        int cn = 0;
        try {

            // make a connection to the file
            Path f = Paths.get("rooms.txt");

            // read all lines of the file
            long c = Files.lines(f).count();
            int cnc = (int) c;
            cn = cnc;

        } catch (Exception e) {
            e.getStackTrace();
        }
        int i = 0, j = 0, k = 0, l = 0, m = 0, n = 0;
        while (input.hasNext()) {
            String word = input.next();
            if (count % 6 == 1) {
                roomno[i] = word;
                i++;
            }
            if (count % 6 == 2) {
                roomtype[j] = word;
                j++;
            }
            if (count % 6 == 3) {
                roomprice[k] = Double.parseDouble(word);
                k++;
            }
            if (count % 6 == 4) {
                hasbalcony[l] = word;
                l++;
            }
            if (count % 6 == 5) {
                haslounge[m] = word;
                m++;
            }
            if (count % 6 == 0) {
                email[n] = word;
                n++;
            }
            count = count + 1;
        }
        int choice;
        Scanner scan = new Scanner(System.in);
        while (true) {
            mainmenu();
            System.out.print("Enter Your Choice - ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Room Type(Single/Double/Suite) - ");
                    String roomtypeuser = scan.next();
                    System.out.print("Balcony Needed ?(true/false) - ");
                    String balcon = scan.next();
                    System.out.print("Lounge Needed ?(true/false) - ");
                    String lounge = scan.next();
                    int booked = 0;
                    String roomnobooked = "0";
                    for (int a = 0; a < cn; a++) {
                        Boolean var1 = roomtype[a].equals(roomtypeuser);
                        Boolean var2 = hasbalcony[a].equals(balcon);
                        Boolean var3 = haslounge[a].equals(lounge);
                        Boolean var4 = email[a].equals("free");
                        if (var1 && var2 && var3 && var4 && booked == 0) {
                            booked = 1;
                            email[a] = "occupied";
                            roomnobooked = roomno[a];
                        }
                    }
                    if (booked == 0) {
                        System.out.println("No Room available as per your preference");
                    } else {
                        System.out.println("Room No." + roomnobooked + " is booked");
                        try {
                            FileWriter myWriter = new FileWriter("rooms.txt",false);
                            myWriter.write(roomno[0]+" "+roomtype[0]+" "+roomprice[0]+" "+hasbalcony[0]+" "+haslounge[0]+" "+email[0]+"\n");
                            myWriter.close();
                            for (int a=1;a<cn;a++){
                                myWriter = new FileWriter("rooms.txt",true);
                                myWriter.write(roomno[a]+" "+roomtype[a]+" "+roomprice[a]+" "+hasbalcony[a]+" "+haslounge[a]+" "+email[a]+"\n");
                                myWriter.close();
                            }
                            System.out.println("Successfully updated the file.");
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Room No -");
                    String roomcancelno;
                    roomcancelno = scan.next();
                    int index = 0;
                    for (int a = 0; a < cn; a++) {
                        if (roomcancelno.equals(roomno[a])) {
                            index = a;
                            break;
                        }
                    }
                    if (email[index].equals("occupied")) {
                        email[index] = "free";
                        System.out.println("Reservation for roomno " + roomno[index] + " stands cancelled");
                    } else {
                        System.out.println("There is no Reservation on roomno");
                    }
                    try {
                        FileWriter myWriter = new FileWriter("rooms.txt",false);
                        myWriter.write(roomno[0]+" "+roomtype[0]+" "+roomprice[0]+" "+hasbalcony[0]+" "+haslounge[0]+" "+email[0]+"\n");
                        myWriter.close();
                        for (int a=1;a<cn;a++){
                            myWriter = new FileWriter("rooms.txt",true);
                            myWriter.write(roomno[a]+" "+roomtype[a]+" "+roomprice[a]+" "+hasbalcony[a]+" "+haslounge[a]+" "+email[a]+"\n");
                            myWriter.close();
                        }
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    for (int a = 0; a < cn; a++) {
                        System.out.println(roomno[a] + " is " + email[a]);
                    }
                    break;
                case 4:
                    System.out.println("Exit");
                    System.exit(0);
                default:
                    System.out.println("Enter The Correct Input");
                    break;
            }
        }
    }
}