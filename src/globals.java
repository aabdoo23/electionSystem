import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class globals {
    public static boolean[] candidateIDs = new boolean[200];
    public static boolean[] votersIDs = new boolean[200];
    public static boolean[] voteIDs = new boolean[200];
    public static boolean[] electionIDs = new boolean[200];
    public static LinkedList<Election> electionsLinkedList = new LinkedList<>();


    public static int createNewID(boolean[] v) {
        Random random = new Random();
        int x = random.nextInt(199);
        while (v[x]) {
            x = random.nextInt(199);
        }
        v[x] = true;
        return x;
    }

    public static void customMakeList(LinkedList<Election> linkedList, JList<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Election ob : linkedList) {
            strings[i] = ob.toStringed();
            i++;
        }
        list.setListData(strings);
    }

    public static void customMakeList(LinkedList<Election> linkedList, JComboBox<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Election ob : linkedList) {
            strings[i] = ob.toStringed();
            i++;
        }
        list.setModel(new DefaultComboBoxModel<>(strings));
    }

    public static void makeList(LinkedList linkedList, JList<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        list.setListData(strings);
    }

    public static void makeList(String[] strings, JList<String> list) {
        list.setListData(strings);
    }

    public static void makeList(LinkedList linkedList, JComboBox<String> list) {

        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        list.setModel(new DefaultComboBoxModel<>(strings));
    }
}
