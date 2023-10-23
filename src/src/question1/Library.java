package question1;

//Najla Alsafadi
//c20312866


import java.util.ArrayList;

public class Library {
	
	public Book Book1;
	private ArrayList<Book> bookList = new ArrayList<Book>();// instance variable stores book objects

	public Library(Book Book1) {// adds book1 into array list
		bookList.add(Book1);
	}

	public int findBookIndex(String isbn) {//method searches through Book collection for book1, isbn as parameter
		int index = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getIsbn().equals(isbn)) {
				return bookList.indexOf(Book1);// returns index of book if found
				
			} else {
			return -1; //returns -1 if found
			}
		}
return index;
		

	}

	public Book loanBook(int index) {//loan book by removing from collection
		Book Book1 = null;

		for (int i = 0; i < bookList.size(); i++) {
			if (i == index) {
				Book1 = bookList.get(i);
				bookList.remove(i);
			} else {
				Book1 = null;//return null if not found
			}
		}

		return Book1;

	}

	public void returnBook(Book Book1) {//methods that adds book back into collection
		bookList.add(Book1);

	}

	public static void main(String[] args) {//main method 
		Book Book1 = new Book("Mary", "Poppins", "Ab11228", 2001);// creates book with ISBN value
		
		Library library = new Library(Book1);//creates library object
       
       //2 member objects
		Member member1 = new Member(library);
		Member member2 = new Member(library);
		
		//starts member object threads
		member1.start();
		member2.start();
		 System.out.print(library.findBookIndex("Ab11228"));

	}
	
}

class Member extends Thread {//member class extends thread class
	private Library library;
	private String isbn = "Ab11228";
	private int index;
	private Book Book1;

public Member(Library library) {
		this.library = library;
	}

	public void run() {
		 library.findBookIndex(isbn);//calls findBookIndex using ISBN
		 
		library.loanBook(index); // calls loanBook using Index
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		library.returnBook(Book1); // sleeps for 2000 ms and returns book
		

	}

}//END
