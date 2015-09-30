package library.tests.daos;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.*;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;
import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.entities.Book;

import java.util.HashMap;
import java.util.Map;



public class TestBookMapDAO {

	IBookHelper _helper;
	IBookDAO _dao;
	
	private IBook book_;
	private String author_, title_, callNo_;
	private int id_;
	
	@Before
	public void setUp() throws Exception {
		_helper = mock(IBookHelper.class);
		_dao = new BookMapDAO(_helper);
		
		author_ = "John Smith";
		title_ = "Generic Name";
		callNo_ = "SMI 1.2345";
		id_ = 1;
		book_ = new Book(author_, title_, callNo_, id_);
	}
	
	@After
	public void tearDown() throws Exception {
		_helper = null;
		_dao = null;
	}
	
	@Test
	public void testContructor() {
		BookMapDAO dao = new BookMapDAO(_helper);
		assertTrue(dao instanceof IBookDAO);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorHelperNull() {
		BookMapDAO dao = new BookMapDAO(null);
	}

	@Test
	public void testCreationOfBook() {
		
		//setup
		IBook expectedBook = mock(IBook.class);
		
		when(_helper.makeBook(eq(author_), eq(title_), eq(callNo_), eq(id_))).thenReturn(expectedBook);
		
		//execute
		IBook actualBook = _dao.addBook(author_, title_, callNo_);
		
		//verifies and asserts
		verify(_helper).makeBook(eq(author_), eq(title_), eq(callNo_), eq(id_));
		assertEquals(expectedBook, actualBook);		
	}
}
