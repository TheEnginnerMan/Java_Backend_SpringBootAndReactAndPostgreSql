package kodlamaio.northwind.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
//core katmanı sadece northwind için değil ilerisde başka projeler içinde kullanılacak katmandır.
//Ortak olan kurumsal projelerde ortak olan kodları core katmanına taşınır.Entities ve dataAccess için örneğin bir JPA repository kullanılmasaydı
//repository i kendimiz oluştururduk ve onuda data access içerisine atardık.Çünkü repository pattern i her projede kullanırız.
public class User { // bütün projede kullanılacak bir yapıdır.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	@Email // anotationu gelmezse projede spring validatinınbu
	@NotBlank // boş geçilmesi istenmiyorsa "" şeklinde kullanım
	@NotNull // hiç bir şey gelmezse
	private String email;

	@Column(name = "password")
	@NotBlank
	@NotNull
	private String password;

}
