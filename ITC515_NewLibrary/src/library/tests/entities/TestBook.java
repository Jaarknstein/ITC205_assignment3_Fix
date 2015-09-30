package library.tests.entities;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;

import library.entities.Book;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.EBookState;

public class TestBook {

	private IBook book_;
	private String author_, title_, callNo_;
	private int id_;
	
	// added to reduce the amount of code, despite being sometimes redundant.
	@Before
	public void setUp() throws Exception {
		author_ = "John Smith";
		title_ = "Generic Name";
		callNo_ = "SMI 1.2345";
		id_ = 1;
		book_ = new Book(author_, title_, callNo_, id_);
				
	}
	
	@After
	public void tearDown() throws Exception {
		book_ = null;
	}
	
	//tests if book instance is an instance of IBook
	@Test
	public void testBook(){
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertNotNull(actual);
		assertTrue(actual instanceof IBook);
	}
	
	//tests if exception is thrown due to illegal argument
	@Test(expected=IllegalArgumentException.class)
	public void testBookAuthorNull() {
		book_ = new Book(null, title_, callNo_, id_);
	}
	
	//tests if book's loan variable is the same to the actual loan
	@Test
	public void testBorrowAndGetLoan() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book_.borrow(mockLoan);
		ILoan actual = book_.getLoan();
		assertNotNull(actual);
		assertEquals(mockLoan, actual);
		
	}
	
	//Tests to see if returnbook() will change state from ON_LOAN to AVAILABLE
	@Test
	public void testReturnBookUndamaged() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book_.borrow(mockLoan);
		book_.returnBook(false);
		//loan should now be NULL
		ILoan actualLoan = book_.getLoan();
		assertNull(actualLoan);
		//state should be available
		EBookState expectedState = EBookState.AVAILABLE;
		EBookState actualState = book_.getState();
		assertEquals(expectedState, actualState);
		
	}
	
	//does it return state?
	@Test
	public void testGetState() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertTrue(actual.getState() == EBookState.AVAILABLE);
	}
	
	//does it return title?
	@Test
	public void testGetTitle() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertTrue(actual.getTitle() == "Generic Name");
	}
	
	//does it return callnumber?
	@Test
	public void testGetCallNumber() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertTrue(actual.getCallNumber() == "SMI 1.2345");
	}
	
	//does it return id?
	@Test
	public void testGetID() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertTrue(actual.getID() == 1);
	}
	
	//does it return author?
	@Test
	public void testGetAuthor() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		assertTrue(actual.getAuthor() == "John Smith");
	}
	
	//Tests to see if lose() will change state from ON_LOAN to LOST
	@Test
	public void testLose() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
				
		//execute
		book_.borrow(mockLoan);
		book_.lose();
		assertTrue(book_.getState() == EBookState.LOST);
	}
	
	//Tests to see if dispose() will change state from available to disposed
	@Test
	public void testDispose() {
		IBook actual = new Book(author_, title_, callNo_, id_);
		actual.dispose();
		assertTrue(actual.getState() == EBookState.DISPOSED);
	}
	
	//Tests to see if returned damaged book will change state from ON_LOAN to DAMAGED
	@Test
	public void testReturnBookDamaged() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book_.borrow(mockLoan);
		book_.returnBook(true);
		//loan should now be NULL
		ILoan actualLoan = book_.getLoan();
		assertNull(actualLoan);
		
		//state should be damaged
		EBookState expectedState = EBookState.DAMAGED;
		EBookState actualState = book_.getState();
		assertEquals(expectedState, actualState);
	}
	
	//Tests to see if repair() will change state from damaged to available
	@Test
	public void testGetRepair() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book_.borrow(mockLoan);
		book_.returnBook(true);
		//loan should now be NULL
		ILoan actualLoan = book_.getLoan();
		assertNull(actualLoan);
		
		//state should be damaged
		book_.repair();
		//state should be available
		EBookState expectedState = EBookState.AVAILABLE;
		EBookState actualState = book_.getState();
		assertEquals(expectedState, actualState);
	}
	
}
