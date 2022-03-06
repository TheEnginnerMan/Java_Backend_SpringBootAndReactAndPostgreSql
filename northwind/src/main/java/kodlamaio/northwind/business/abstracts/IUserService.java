package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;

public interface IUserService {

	Result add(User user);

	DataResult<User> findByEmanil(String email);
}
