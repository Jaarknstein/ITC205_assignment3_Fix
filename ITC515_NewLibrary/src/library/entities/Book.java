package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

	private String author;
	private String title;
	private String callNumber;
	private int id;
	
	private ILoan loan;
	private EBookState state;
	
	private boolean sane(String author, String title, String callNumber, int bookID){
		boolean isSane = false;
		
		if (!(author.isEmpty()) && !(title.isEmpty()) && !(callNumber.isEmpty()) && bookID > 0 
				&& author != null && title != null && callNumber != null){
			isSane = true;
		}
		
	return isSane;
	}
	
	public Book(String author, String title, String callNumber, int bookID) {
		if ( (sane(author, title, callNumber, bookID)) == false) {
			//throw error;
		}
		this.author = author;
		this.title = title;
		this.callNumber = callNumber;
		this.id = bookID;
		this.state = EBookState.AVAILABLE;
		this.loan = null;
	}
	
	public void borrow(ILoan loan) {
		if (loan == null) {
			//throw error
		}
		
		if ((state == EBookState.AVAILABLE) == false) {
			//throw error
		}
		
		this.loan = loan;
		
		state = EBookState.ON_LOAN;

	}

	public ILoan getLoan() {
		return loan;
	}
	
	public void returnBook(boolean damaged) {
		loan = null;
		if (damaged) {
			state = EBookState.DAMAGED;
		}
		
		else {
			state = EBookState.AVAILABLE;
		}
	}
	
	public void lose() {
		state = EBookState.LOST;
	}
	
	public void repair() {
		state = EBookState.AVAILABLE;
	}

	public void dispose() {
		state = EBookState.DISPOSED;
	}
	
	public EBookState getState() {
		return state;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public int getID() {
		return id;
	}

	public String toString() {
		
		String stringOutput = (id + "\n" + author + "\n" + title + "\n" + callNumber);
		return stringOutput;
	}
}
