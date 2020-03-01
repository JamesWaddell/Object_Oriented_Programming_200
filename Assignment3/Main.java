/**
 * Contains the main function for testing Item and derived classes.
 * DO NOT SUBMIT THIS FILE
 */
public class Main {
    /**
     * Initial number of items
     */
    private static final int NUM_ITEMS = 3;

    public static void main(String[] args) {
        BookShelf.printStudentInfo();

        Item items[] = new Item[]{
                new Book("S529.030MAR", "The adventures of Tom Sawyer", "Mark Twain", 500),
                new Journal("123.456SCI", "Scientific American", "XXI", 4),
                new DVD("235.707HAY", "Spirited Away", "Hayao Miyazaki"),
        };

        BookShelf bookShelf = new BookShelf();

        System.out.println("Test 01: empty bookshelf capacity should be 0. Actual capacity: " + bookShelf.getCapacity());

        for (int i=0; i<NUM_ITEMS; i++) {
            Item item = items[i];
            bookShelf.pushBack(item);
        }

        System.out.println("Test 02: After inserting 3 books, bookshelf capacity should be 4. Actual capacity: " + bookShelf.getCapacity());
        System.out.println("Test 03: print out: ");
        System.out.println(bookShelf);

        Book duplicatedBook = new Book("S529.030MAR", "The adventures of Tom Sawyer second edition", "Mark Twain", 500);
        Book newBook = new Book("S759.010BEN", "Bullies", "Ben Shapiro", 336);

        try {
            bookShelf.insert(duplicatedBook, 1);
            System.out.println("Test 04: Inserting a duplicate book...but no exception thrown");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Test 04: Inserting a duplicate book...get exception: " + e.getMessage());
        }

        try {
            bookShelf.insert(newBook, -1);
            System.out.println("Test 05: Inserting a book at invalid position...but no exception thrown");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Test 05: Inserting a book at invalid position...get exception: " + e.getMessage());
        }

        bookShelf.insert(newBook, 1);
        System.out.println( "Test 06: After inserting 4 books, bookshelf capacity should be 4. Actual capacity: " + bookShelf.getCapacity());
        System.out.println( "Test 07: After inserting 4 books, bookshelf size should be 4. Actual size: " + bookShelf.getSize());

        System.out.println("Test 08: print out: ");
        System.out.println(bookShelf);

        Item retrieved = bookShelf.get(2);
        System.out.println("Test 09: Retrieve item at index 2, call number should be 123.456SCI, actual: " + retrieved.getCallNumber());

        retrieved = bookShelf.find("123.456ABC");
        System.out.println("Test 10: Retrieve non-existent item 123.456ABC, should return null. Actual: " + retrieved);

        Item removed = bookShelf.remove(0);
        System.out.println("Test 11: Remove item at index 0. Call number should be S529.030MAR. Actual: " + removed.getCallNumber());
        System.out.println("Test 12: print out: ");
        System.out.println(bookShelf);
    }
}
