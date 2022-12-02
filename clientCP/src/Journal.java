public class Journal extends Publication{
    String number;

    public Journal(String ID, String title, String publisher, String genre, int year, int count, String number) {
        super(ID, title, publisher, genre, year, count);
        this.number = number;
    }
}
