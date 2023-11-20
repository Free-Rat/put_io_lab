public class NiceWordsPrinter {
    public static void main(String[] args) {
        String[] niceWords = {  //list bardzo ladnych slowek
            "Kind",
            "Generous",
            "Friendly",
            "Caring",
            "Lovely",
            "Positive",
            "Compassionate",
            "Polite",
            "Sweet",
            "Gentle"
        };

        System.out.println("Some Nice Words:");

        for (String word : niceWords) {
            System.out.println(word);
        }
    }
}
