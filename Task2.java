import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

// 2) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

public class Task2 {
    Task2() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Entern array length: ");
        int l = in.nextInt();
        in.close();
        File file = new File("logOfSorting.txt");
        int[] numbers = generator(l);
        printer(numbers);
        printer(sorter(numbers, "logOfSorting.txt"));
    }


    public static int[] generator(int n) {
        int[] numbers = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; ++i) {
            numbers[i] = rnd.nextInt(100);
        }
        return numbers;
    }

    public static void printer(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static String stringGenerator(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i + " ");
        }
        return sb.toString();
    }

    public static void logger(String filename, String message) throws IOException{
        FileWriter fw = new FileWriter(filename, true);
        String timestamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        String logmessage = timestamp + ": " + message + "\n";
        fw.write(logmessage);
        fw.close();
    }

    public static int[] sorter(int[] numbers, String filename) throws IOException {
        for (int i = 0; i < numbers.length-2; ++i) {
            for (int j = 0; j < numbers.length-1; ++j) {
                if (numbers[j] > numbers[j+1]) {
                    int temporary = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temporary;
                }
            }
            String toLog = stringGenerator(numbers);
            logger(filename, toLog);
        }
        logger(filename, " ");
        return numbers;
    }
}
