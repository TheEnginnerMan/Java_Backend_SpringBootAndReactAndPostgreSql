package kodlamaio.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;

//bir interface classı başka bir interface classı extend ederek özelliklerini miras alabilir ama
//bir interface başka bir interfaci implemente edemez.
public interface UserDao extends JpaRepository<User, Integer> {
	User findByEmail(String email);

}
