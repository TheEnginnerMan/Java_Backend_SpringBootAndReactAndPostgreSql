package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.IProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

	private IProductService productService;

	@Autowired
	public ProductsController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Product>> getAll() {
		return this.productService.getAll();
	}

	// post işlemi ile parametre alınırsa ,yani react tarafından json objesi ile
	// gönderilirse json içerisindeki alanlar
	// request alanlar olup reques body olarak kabul edilir.bu şekilde post
	// işleminde parametre alınırsa gelen requestin bir body si olur
	// tüm bilgilerin bir pakete konulması ve gönderilmesi requestbody parametresi
	// ile olmaktadır.RequestBody deki data aslında producttır.
	// Requesbody ile gelen paket içerisindeki alanlar entity içerisindeki productın
	// alanlarını kontrol edip mapler
	@PostMapping("/add")
	public Result add(@RequestBody Product product) {

		return this.productService.add(product);
	}

	// ProductName yapılan istekte parametre olarak gönderileceği için @RequestParam
	// erişim belirteci kullanılmalı,yani yapılan isteğin parametrelerine bakılıp
	// productName gibi bir propert olacak ve ataması yapılacak sonra servis
	// tarafında işlemlerini gerçekleştirecek
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName) {
		return this.productService.getByProductName(productName);
	}

	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName,
			@RequestParam("categoryId") int categoryId) {
		return this.productService.getByProductNameAndCategoryId(productName, categoryId);
	}

	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam("productName") String productName) {
		return this.productService.getByProductNameContains(productName);
	}

	@GetMapping("/getAllByPage")
	public DataResult<List<Product>> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
		return this.productService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted() {
		return this.productService.getAllSorted();
	}

	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return this.productService.getProductWithCategoryDetails();
	}
}
