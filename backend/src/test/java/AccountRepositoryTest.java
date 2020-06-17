import com.alexxstepan.Application;
import com.alexxstepan.dao.repositories.AccountRepository;
import com.alexxstepan.entities.Account;
import org.apache.commons.collections4.IterableUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Application.class)
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository repository;

	@Before
	public void setUp() {
	}

	@After
	public void cleanUp() {
	}

	@Test
	public void test_insert_accounts() {
		repository.save(new Account(1L, "owner1", "USD", 0));
		repository.save(new Account(2L, "owner2", "EUR", 10));
		repository.save(new Account(3L, "owner3", "CAD", 20));
		repository.save(new Account(4L, "owner4", "USD", 30));

		Assert.assertEquals(4, IterableUtils.size(repository.findAll()));
	}
}
