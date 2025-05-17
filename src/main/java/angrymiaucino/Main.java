package angrymiaucino;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> listOfInd = new MyArrayList<>();

        for (int i = 0; i < 10; i++) {
            listOfInd.add(i);
        }

        System.out.println(listOfInd);
        System.out.println(listOfInd.get(0));
        try {
            Integer nr = listOfInd.get(-1);
            System.out.println(nr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}