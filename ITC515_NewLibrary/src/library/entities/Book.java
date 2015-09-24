package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

	// Creating variables
	private String author_;
	private String title_;
	private String callNumber_;
	private int id_;
	
	private ILoan loan_;
	private EBookState state_;
	
	
	// Constructor
	// Will throw exception if arguments are not correct, as required
	public Book(String author, String title, String callNumber, int bookID) {
		if ( (sane(author, title, callNumber, bookID)) == false) {
			throw new IllegalArgumentException("Invalid arguments for constructor");
		}
		this.author_ = author;
		this.title_ = title;
		this.callNumber_ = callNumber;
		this.id_ = bookID;
		this.state_ = EBookState.AVAILABLE;
		this.loan_ = null;
	}
	
	//Decides if arguments are invalid for constructor
	private boolean sane(String author, String title, String callNumber, int bookID){
		boolean isSane = false;
		
		if (!(author.isEmpty()) && !(title.isEmpty()) && !(callNumber.isEmpty()) && bookID > 0 
				&& author != null && title != null && callNumber != null){
			isSane = true;
		}
		
	return isSane;
	}
	
	// associates loan with the book
	// throws runtime exception if the book is !available as required
	public void borrow(ILoan loan) {
		if (loan == null) {
			throw new RuntimeException("Loan cannot be null");
		}
		
		if ((state_ == EBookState.AVAILABLE) == false) {
			throw new RuntimeException("State must be available");
		}
		
		this.loan_ = loan;
		
		state_ = EBookState.ON_LOAN;

	}

	// returns the loan associated with the book
	// returns null if book is not current ON_LOAN
	public ILoan getLoan() {
		return loan_;
	}
	
	//removes the loan the book is associated with
	// if damaged, state is DAMAGED
	// else, state is AVAILABLE
	// throws runtime exception is book is not ON_LOAN
	public void returnBook(boolean damaged) {
		if ((state_ == EBookState.ON_LOAN) == false) {
			throw new RuntimeException("Book must be ON_LOAN in order to be returned");
		}
		
		loan_ = null;
		if (damaged) {
			state_ = EBookState.DAMAGED;
		}
		
		else {
			state_ = EBookState.AVAILABLE;
		}
	}
	
	//sets the state to LOST
	//RuntimeException if: the book is not currently ON_LOAN 
	public void lose() {
		if ((state_ == EBookState.ON_LOAN) == false) {
			throw new RuntimeException("Book must be ON_LOAN in order to be lost");
		}
		
		state_ = EBookState.LOST;
	}
	
	//sets the BookState to AVAILABLE
	//throws RuntimeException if: the book is not currently DAMAGED 
	public void repair() {
		if ((state_ == EBookState.DAMAGED) == false) {
			throw new RuntimeException("Book must be DAMAGED in order to be repaired");
		}
		
		state_ = EBookState.AVAILABLE;
	}

	//sets the BookState to DISPOSED
	//throws RuntimeException if: the book is not currently AVAILABLE, DAMAGED, or LOST 
	public void dispose() {
		
		if (((state_ == EBookState.AVAILABLE) == false || (state_ == EBookState.DAMAGED) == false || (state_ == EBookState.LOST) == false)) {
			throw new RuntimeException("Book must be AVAILABLE, DAMAGED or LOST to dispose");
		}
		
		state_ = EBookState.DISPOSED;
	}
	
	//returns the book’s current BookState
	public EBookState getState() {
		return state_;
	}

	// returns the book’s author 
	public String getAuthor() {
		return author_;
	}

	//returns the book’s title 
	public String getTitle() {
		return title_;
	}

	//returns the book’s call number 
	public String getCallNumber() {
		return callNumber_;
	}

	//returns the book’s unique id 
	public int getID() {
		return id_;
	}
	
	//Returns a string output of the book
	public String toString() {
		
		String stringOutput = (id_ + "\n" + author_ + "\n" + title_ + "\n" + callNumber_);
		return stringOutput;
	}
}
