package library.tests.integration.entityDao;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import library.entities.Book;
import library.entities.Member;

import library.daos.BookHelper;
import library.daos.BookMapDAO;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.daos.IBookHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDAOIntegrationTests {

	IBookHelper _BookHelper;
	IBook _Book1;    
	IBook _Book2;
	
	BookMapDAO bookMapDao;
    private String name_ = "Hungry Man";
	private String title_ = "Hungery Catterpillar";
	private String callNumber_ = "1.23456";
	
    private String name2_ = "Bloated Woman";
	private String title2_ = "Bloated Catterpillar";
	private String callNumber2_ = "6.54321";
	
	private int id_ = 1;
	
	@Before
	public void setUp()
	{
		_BookHelper = new BookHelper();
		_Book1 = new Book(name_, title_, callNumber_, id_);
		bookMapDao = new BookMapDAO(_BookHelper);
	}
	
	//Making sure tests don't affect other tests
	@After
	public void nullSetUp()
	{
		_BookHelper = null;
		_Book1 = null;
		bookMapDao = null;
	}
	
	@Test
	public void testAddBook() {

		//execute
		IBook actual = bookMapDao.addBook(name_, title_, callNumber_);
		
		String expectedName = name_;
		String expectedTitle = title_;
		String expectedCallNo = callNumber_;
		
		assertEquals(expectedName, actual.getAuthor());
		assertEquals(expectedTitle, actual.getTitle());
		assertEquals(expectedCallNo, actual.getCallNumber());
	}

	@Test
	public void testListBooks() {

		//execute
		IBook book1 = bookMapDao.addBook(name_, title_, callNumber_);
		IBook book2 = bookMapDao.addBook(name2_, title2_, callNumber2_);

		List<IBook> listBooks = bookMapDao.listBooks();
		
		assertEquals(2, listBooks.size());
		assertTrue(listBooks.contains(book1));
		assertTrue(listBooks.contains(book2));
	}
}