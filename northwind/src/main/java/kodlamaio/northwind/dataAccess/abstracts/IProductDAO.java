package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;

//JPARepository<T,D> verilen veri tipi için yani Entity anotationu ile süslenmiş nesne için yani 
//burdaki Product için,primary key alanınıda vererek sorguları ona göre hazırlar <T=Entity,D=primarkey tipi
//şu anda ürünle ile ilgili olarak crud operasyonları hazır durumdadır.
//interface,interface i exten edebilir

public interface IProductDAO extends JpaRepository<Product, Integer> {
	Product getByProductName(String productName);

	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);// categorinin categori Idsi

	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);

	List<Product> getByCategoryIn(List<Integer> categories);

	List<Product> getByProductNameContains(String productName);

	List<Product> getByProductNameStartsWith(String productName);

	// Product ismi table karşılık değil entitye karşılık geliyot.Productın
	// categorisinin categori idsi ise category.categoryid diye çağrılır.
	@Query("From Product where productName = :productName and category.categoryId =:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
}
