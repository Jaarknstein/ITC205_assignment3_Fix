package library.daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookMapDAO implements IBookDAO {
	
	// declaring variables
	private IBookHelper helper;
	private int nextId;
	
	// bookMap is a collection
	private Map<Integer, IBook> bookMap;
	
	//constructor
	public BookMapDAO(IBookHelper helper) {
		if (helper == null ) {
			throw new IllegalArgumentException("Helper cannot be NULL");
		}
		nextId = 1;
		this.helper = helper; 
		bookMap = new HashMap<Integer, IBook>();
	}
	
	// constructor, if receiving a collection
	public BookMapDAO(IBookHelper helper, Map<Integer, IBook> bookMap) {
		this.helper = helper;
		// test new helper
		if (helper == null ) {
			throw new IllegalArgumentException("bookMap cannot be NULL");
		}
		this.bookMap = bookMap;
	}
	
	//uses helper to create a new Book with a unique book ID
	//adds the new book to a collection of Books
	//returns the new book 
	public IBook addBook(String author, String title, String callNo) {
		int id = getNextId();
		IBook book = helper.makeBook(author, title, callNo, id);
		bookMap.put(Integer.valueOf(id), book);
		return book;
	}

	//returns the book identified by the id from the Book collection
	//returns null if book not found 
	public IBook getBookByID(int id) {
		if (bookMap.containsKey(Integer.valueOf(id))) {
			IBook getBook = bookMap.get(Integer.valueOf(id));
			return getBook;
		}
		return null;
	}

	//returns a list of all books in the Book collection 
	public List<IBook> listBooks() {
		List<IBook> list = new ArrayList<IBook>(bookMap.values());
		List<IBook> cList = Collections.unmodifiableList(list);
		return cList;
	}

	//returns a list of all books in the collection written by author
	//returns empty list if no books found 
	
	//needed help from website for this one
	public List<IBook> findBooksByAuthor(String author) {
		List<IBook> list = new ArrayList<IBook>();
		for (IBook book : bookMap.values()) {
			if (author.equals(book.getAuthor())) {
				list.add(book);
			}
		}
		List<IBook> cList = Collections.unmodifiableList(list);
		return cList;
	}

	//returns a list of all books in the collection whose title is title
	//returns empty list if no books found 
	public List<IBook> findBooksByTitle(String title) {
		List<IBook> list = new ArrayList<IBook>();
		for (IBook book : bookMap.values()) {
			if (title.equals(book.getTitle())) {
				list.add(book);
			}
		}
		List<IBook> cList = Collections.unmodifiableList(list);
		return cList;
	}

	//returns a list of all books in the collection whose title is title and whose author is author
	//returns empty list if no books found 
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		List<IBook> list = new ArrayList<IBook>();
		for (IBook book : bookMap.values()) {
			if (author.equals(book.getAuthor()) && title.equals(book.getTitle())) {
				list.add(book);
			}
		}
		List<IBook> cList = Collections.unmodifiableList(list);
		return cList;
	}
	
	// A separate method for changing the Id, instead of being in the addbook method
	private int getNextId() {
		return nextId++;
	}
	
}
