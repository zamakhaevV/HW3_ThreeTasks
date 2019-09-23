import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class Main {
    public List<String> list1 = new ArrayList<>();
    public List<CheckedException> list2 = new ArrayList<>();

    public static void main(String[] args) {
        // First task
        String str1 = "Task one";
        String str2 = "";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("str.srl");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(str1);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("str.srl");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            str2 = (String) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean eq1 = str1.equals(str2);
        boolean eq2 = str1 == str2;
        System.out.println("--- First task ---");
        System.out.println(String.format("str1.equals(str2) = %s", eq1));
        System.out.println(String.format("str1 == str2 = %s", eq2));
        System.out.println();


        // Second task
        System.out.println("--- Second task ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input array length: ");
        int n = scanner.nextInt();
        int[] array;
        if (n > 50) {
            throw new UncheckedException("Array length too long");
        } else {
            array = new int[n];
            System.out.println(String.format("Create array with length = %d", array.length));
        }

        try {
            throw new CheckedException("Checked exception");
        } catch (CheckedException e) {
            System.out.println(e);
        }
        System.out.println();

        // Third task
        System.out.println("--- Third task ---");
        try {
            Field field = Main.class.getDeclaredField("list1");
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            System.out.println(clazz);

            field = Main.class.getDeclaredField("list2");
            parameterizedType = (ParameterizedType) field.getGenericType();
            clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            System.out.println(clazz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
