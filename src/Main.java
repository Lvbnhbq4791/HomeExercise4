import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] operations = {"Добавить", "Показать", "Удалить", "Найти"};
        while (true) {
            System.out.println("---------------------------------------------------");
            System.out.println("Выберете номер операции или наберите end для выхода :");
            int o = 0;
            for (String operation : operations) {
                System.out.print((o + 1) + "-" + operation + "; ");
                o++;
            }
            System.out.println("");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                show();
                break;
            }
            int numberOperation;
            try {
                numberOperation = Integer.parseInt(input.trim());
            } catch (NumberFormatException c) {
                System.out.println("Не коректный номер операции" +
                        "\n" + "введите только номер");
                continue;
            }
            if (numberOperation < 1 || numberOperation > operations.length) {
                System.out.println("Нет такой операции введите правильный номер");
                continue;
            }
            switch (numberOperation) {
                case 1 -> additive();
                case 2 -> show();
                case 3 -> delete();
                case 4 -> search();
            }
        }
    }

    public static void additive() {
        System.out.println("Какую покупку хотите добавить?");
        String inputProduct = scanner.nextLine();
        try {
            int line = Integer.parseInt(inputProduct);
            System.out.println("Продукт не может быть числом " + line);
        } catch (NumberFormatException e) {
            list.add(inputProduct);
            System.out.println("Итого в списке покупок: " + list.size());
        }
    }

    public static void show() {
        if (list.isEmpty()) {
            System.out.println("Список покупок пуст");
            return;
        }
        System.out.println("Список покупок:");
        int n = 1;
        for (String purchase : list) {
            System.out.println(n + ". " + purchase);
            n++;
        }
    }

    public static void delete() {
        show();
        if (list.isEmpty()) {
            return;
        }
        System.out.println("Какую покупку хотите удалить? Введите номер или название");
        String deleteProduct = scanner.nextLine();
        String nameProduct;
        try {
            int line = Integer.parseInt(deleteProduct);
            if (line < list.size() || line > list.size()) {
                System.out.println("Нет покупки с таким номером");
                return;
            }
            nameProduct = list.get(line - 1);
            list.remove(line - 1);
        } catch (NumberFormatException e) {
            if (!list.contains(deleteProduct)) {
                System.out.println("Нет покупки с таким названием");
                return;
            }
            nameProduct = deleteProduct;
            list.remove(deleteProduct);
        }
        System.out.println("Покупка " + nameProduct + " удалена");
        show();
    }

    public static void search() {
        System.out.println("Введите текст для поиска:");
        String queryLower = scanner.nextLine();
        queryLower = queryLower.toLowerCase();
        int k = 1;
        for (int s = 0; s < list.size(); s++) {
            String itemLower = list.get(s);
            itemLower = itemLower.toLowerCase();
            if (itemLower.contains(queryLower)) {
                if (k == 1) {
                    System.out.println("Найдено:");
                    k++;
                }
                System.out.println((s + 1) + ". " + list.get(s));
            }
        }
        if (k == 1) {
            System.out.println(queryLower + " Не найдено");
        }
    }
}

