package library.tests.integration.entity;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import library.entities.Book;
import library.entities.Loan;
import library.entities.Member;

import library.interfaces.entities.EBookState;

import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;
import library.interfaces.entities.ILoan;

import java.util.Calendar;
import java.util.Date;

public class BookEntityIntegrationTests {

	private IBook mockBook_;
    private ILoan mockLoan_;
    private IMember mockMember_;
	
    Date borrowDate_, dueDate_;
    Calendar calendar_;
    
    private String author_ = "Hungry Man";
	private String title_ = "Hungery Catterpillar";
	private String callNumber_ = "1.23456";
	private int id_ = 1;
    
	@Before
	public void setUp() throws Exception {
		mockBook_ = new Book(author_, title_, callNumber_, id_);
		mockMember_ = new Member("John", "Smith", "12345678", "email@email.com", 1);
		
	    calendar_ = Calendar.getInstance();
	    borrowDate_ = new Date();
	    calendar_.setTime(borrowDate_);
	    calendar_.add(Calendar.DATE, ILoan.LOAN_PERIOD);
	    dueDate_ = calendar_.getTime();
		
		mockLoan_ = new Loan(mockBook_, mockMember_, borrowDate_, dueDate_);

	}
	
    @After
    public void tearDown() throws Exception {  
    	
    }
    
    @Test
    public void testBorrowAvailable() {
        //execute
        mockBook_.borrow(mockLoan_);  
        EBookState expected = EBookState.ON_LOAN;
        assertEquals(expected, mockBook_.getState());
    }
    
    @Test(expected=RuntimeException.class)
    public void testBorrowNotAvailable() {

        //first loan to make book unavailable
        mockBook_.borrow(mockLoan_); 
        
        //execute
        mockBook_.borrow(mockLoan_);        
        
        fail("Should have thrown an exception");

    }
    
    @Test
    public void testGetLoanAvailable() {
        ILoan actual = mockBook_.getLoan();
        
        assertNull(actual);
    }	
}