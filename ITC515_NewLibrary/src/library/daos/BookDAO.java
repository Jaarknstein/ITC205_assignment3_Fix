package library.daos;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookDAO implements IBookDAO {
	
	// declaring variables
	private IBookHelper helper;
	private int id;
	
	//uses helper to create a new Book with a unique book ID
	//adds the new book to a collection of Books
	//returns the new book 
	public IBook addBook(String author, String title, String callNo) {
		IBook book = helper.makeBook(author, title, callNo, id);
		//add to list
		return book;
	}

	//returns the book identified by the id from the Book collection
	//returns null if book not found 
	public IBook getBookByID(int id) {
		if (/* list contains ID */){
			//return book
		}
		return null;
	}

	//returns a list of all books in the Book collection 
	public List<IBook> listBooks() {
		
		return ;
	}

	//returns a list of all books in the collection written by author
	//returns empty list if no books found 
	public List<IBook> findBooksByAuthor(String author) {

		return ;
	}

	//returns a list of all books in the collection whose title is title
	//returns empty list if no books found 
	public List<IBook> findBooksByTitle(String title) {

		return ;
	}

	//returns a list of all books in the collection whose title is title and whose author is author
	//returns empty list if no books found 
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		
		return ;
	}
	
}
