package library.daos;

import library.entities.Book;
import library.interfaces.entities.IBook;
import library.interfaces.daos.IBookHelper;


public class BookHelper implements IBookHelper {

	public IBook makeBook(String author, String title, String callNumber, int id) {
		
		//returns a new book with an id specified by id 
		Book book = new Book(author, title, callNumber, id);
		
		return book;		
	}

}
