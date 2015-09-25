package library.tests.entities;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;

import library.entities.Book;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.EBookState;

public class TestBook {

	private IBook book;
	private String author, title, callNo;
	private int id;
	
	// added to reduce the amount of code, despite being sometimes redundant.
	@Before
	public void setUp() throws Exception {
		author = "John Smith";
		title = "Generic Name";
		callNo = "SMI 1.2345";
		id = 1;
		book = new Book(author, title, callNo, id);
				
	}
	
	@After
	public void tearDown() throws Exception {
		book = null;
	}
	
	@Test
	public void testBook(){
		IBook actual = new Book(author, title, callNo, id);
		assertNotNull(actual);
		assertTrue(actual instanceof IBook);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBookAuthorNull() {
		book = new Book(null, title, callNo, id);
	}
	
	@Test
	public void testBorrowAndGetLoan() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book.borrow(mockLoan);
		ILoan actual = book.getLoan();
		assertNotNull(actual);
		assertEquals(mockLoan, actual);
		
	}
	
	@Test
	public void testReturnBookUndamaged() {
		//setup
		ILoan mockLoan = mock(ILoan.class);
		
		//execute
		book.borrow(mockLoan);
		book.returnBook(false);
		//loan should now be NULL
		ILoan actualLoan = book.getLoan();
		assertNull(actualLoan);
		//state should be available
		EBookState expectedState = EBookState.AVAILABLE;
		EBookState actualState = book.getState();
		assertEquals(expectedState, actualState);
		
		
		
	}
	
}
