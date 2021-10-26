package rn.puzzle.structure.list.medium;

import org.junit.Test;
import rn.tool.Ass;
import rn.tool.FileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditorTest {
    public static void consoleRunner() {
        Scanner scan = new Scanner(System.in);
        int queriesCount = Integer.parseInt(scan.nextLine());
        String[] operations = new String[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            operations[i] = scan.nextLine();
        }
        scan.close();

        List<Character> output = processEditorOperations(operations);
        for (Character character : output) {
            System.out.println(character);
        }
    }

    public static void consoleMix() {
        Scanner scan = new Scanner(System.in);
        int queriesCount = Integer.parseInt(scan.nextLine());
        Stack<String> changeOperations = new Stack<>();
        StringBuilder builder = new StringBuilder();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < queriesCount; i++) {
            String operation = scan.nextLine();
            char type = operation.charAt(0);
            if (type == '1') {
                String newStr = operation.substring(2);
                builder.append(newStr);
                changeOperations.add("1 " + newStr.length());
            } else if (type == '2') {
                int count = Integer.parseInt(operation.substring(2));
                String delStr = builder.substring(builder.length() - count);
                builder.setLength(builder.length() - count);
                changeOperations.add("2 " + delStr);
            } else if (type == '3') {
                int index = Integer.parseInt(operation.substring(2));
                output.append(builder.charAt(index - 1)).append("\n");
            } else {
                String undo = changeOperations.pop();
                if (undo.charAt(0) == '1') {
                    int count = Integer.parseInt(undo.substring(2));
                    builder.setLength(builder.length() - count);
                } else {
                    String newStr = undo.substring(2);
                    builder.append(newStr);
                }
            }
        }
        System.out.println(output.toString());
    }

    static List<Character> processEditorOperations(String[] operations) {
        List<Character> output = new ArrayList<>();
        Stack<String> changeOperations = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (String operation : operations) {
            char type = operation.charAt(0);
            if (type == '1') {
                String newStr = operation.substring(2);
                builder.append(newStr);
                changeOperations.add("1 " + newStr.length());
            } else if (type == '2') {
                int count = Integer.parseInt(operation.substring(2));
                String delStr = builder.substring(builder.length() - count);
                builder.setLength(builder.length() - count);
                changeOperations.add("2 " + delStr);
            } else if (type == '3') {
                int index = Integer.parseInt(operation.substring(2));
                output.add(builder.charAt(index - 1));
            } else {
                String undo = changeOperations.pop();
                if (undo.charAt(0) == '1') {
                    int count = Integer.parseInt(undo.substring(2));
                    builder.setLength(builder.length() - count);
                } else {
                    String newStr = undo.substring(2);
                    builder.append(newStr);
                }
            }
        }

        return output;
    }

    @Test
    public void input7() {
        List<String> lines = FileHelper.INSTANCE.load("C:\\Users\\ronko\\Downloads\\input07.txt");
        lines.remove(0);
        String[] ops = new String[lines.size()];
        lines.toArray(ops);
        processEditorOperations(ops);
        processEditorOperations(ops);
        processEditorOperations(ops);
        List<Character> output = processEditorOperations(ops);
        Ass.assertSize(207488, output);
    }


    @Test
    public void test5() {
        String[] ops = new String[]{"1 abc","3 3","2 3","1 xy","3 2","4","4","3 1"};
        List<Character> output = processEditorOperations(ops);
        Ass.assertEquals('c', output.get(0));
        Ass.assertEquals('y', output.get(1));
        Ass.assertEquals('a', output.get(2));
    }

    @Test
    public void test4() {
        String[] ops = new String[]{"1 ab","2 1","1 c","2 1","1 d","2 1","1 e","3 2","4","4","3 2","4","4","3 2","4","4","3 2"};
        List<Character> output = processEditorOperations(ops);
        Ass.assertEquals('e', output.get(0));
        Ass.assertEquals('d', output.get(1));
        Ass.assertEquals('c', output.get(2));
        Ass.assertEquals('b', output.get(3));
    }

    @Test
    public void test3() {
        List<Character> output = processEditorOperations(new String[]{"1 abc", "2 2", "1 d", "3 2", "4", "4", "3 2"});
        Ass.assertEquals('d', output.get(0));
        Ass.assertEquals('b', output.get(1));
    }

    @Test
    public void test2() {
        List<Character> output = processEditorOperations(new String[]{"1 abc", "3 2", "2 2", "1 d", "3 2"});
        Ass.assertEquals('b', output.get(0));
        Ass.assertEquals('d', output.get(1));
    }

    @Test
    public void test1() {
        List<Character> output = processEditorOperations(new String[]{"1 abc", "3 2"});
        Ass.assertEquals('b', output.get(0));
    }
}
