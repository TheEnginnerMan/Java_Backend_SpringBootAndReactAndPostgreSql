package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.IUserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
	private IUserService userService;

	@Autowired
	public UsersController(IUserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));

	}// ResponseEntity ile durum mesajı gönderilir 200,300,400,500 gibi httprequest
		// parametresine göre

	// validation kuralları olabilir,sistemle ilgili hata olabilir onun için herşeyi
	// kapsayan object tipi kullanılır.AOP mimari kullılacak.
	// Bu sistemde şu türde hata olursa bu metod devreye girecek
	@ExceptionHandler(MethodArgumentNotValidException.class) // bütün validation hatalarını not null,email gibi alanları
	@ResponseStatus(HttpStatus.BAD_REQUEST) // argumentnotvalidexception olarak geçiyor .tipi vermek
	// için typeof( yerine .class kullanılıyor)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) { // validation
																											// hatalarını
																											// test eder
																											// ve bütüm
																											// metodlar
		Map<String, String> validationErrors = new HashMap<String, String>(); // burdan geçecek

		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
		return errors;
	}
}
