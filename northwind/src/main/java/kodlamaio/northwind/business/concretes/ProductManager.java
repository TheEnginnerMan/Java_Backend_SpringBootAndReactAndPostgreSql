package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.IProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.IProductDAO;
import kodlamaio.northwind.entities.concretes.Product;

@Service
public class ProductManager implements IProductService {

	private IProductDAO productDAO;

	@Autowired
	public ProductManager(IProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDAO.findAll(), "Data Listelendi  ");
	}

	@Override
	public Result add(Product product) {
		this.productDAO.save(product);
		return new SuccessResult("Ürün Eklendi");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>(this.productDAO.getByProductName(productName), "Data Listelendi  ");
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<Product>(
				this.productDAO.getByProductNameAndCategory_CategoryId(productName, categoryId), "Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(
				this.productDAO.getByProductNameOrCategory_CategoryId(productName, categoryId), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(this.productDAO.getByCategoryIn(categories), "Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDAO.getByProductNameContains(productName),
				"Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDAO.getByProductNameStartsWith(productName),
				"Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDAO.getByNameAndCategory(productName, categoryId),
				"Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pagaPageable = PageRequest.of(pageNo - 1, pageSize);

		return new SuccessDataResult<List<Product>>(this.productDAO.findAll(pagaPageable).getContent(),
				"Data Listelendi  ");
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort = Sort.by(Sort.Direction.DESC, "productName");
		return new SuccessDataResult<List<Product>>(this.productDAO.findAll(sort), "Data Listelendi  ");
	}

}
