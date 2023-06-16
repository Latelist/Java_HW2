import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Task4 {
    Task4() throws IOException {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter expression: ");
        double a = in.nextDouble();
        System.out.println();
        String op = in.next();
        
        double b = in.nextDouble();
        in.close();

        String result = stringGenerator(a, b, op);
        File file = new File("logOfCalculator.txt");
        System.out.println(result);
        logger("logOfCalculator.txt", result);
    }

    public static double calculator(double a, String op, double b) {
        double res = 0;
        
        if (op.equals("+")) {
            res = a + b;
        } else if (op.equals("-")) {
            res = a - b;
        } else if (op.equals("*")) {
            res = a * b;
        } else if (op.equals("/")) {
            res = a / b;
        }
        return res;
    }

    public static String stringGenerator(double a, double b, String op) {
        String result = a + " " + op + " " + b + " = " + calculator(a, op, b);
        return result;
    }

    public static void logger(String filename, String message) throws IOException{
        FileWriter fw = new FileWriter(filename, true);
        String timestamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        String logmessage = timestamp + ":      " + message + "\n\n";
        fw.write(logmessage);
        fw.close();
    }
}
