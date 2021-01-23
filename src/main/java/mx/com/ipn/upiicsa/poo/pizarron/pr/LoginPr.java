package mx.com.ipn.upiicsa.poo.pizarron.pr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.HibernateValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import mx.com.ipn.upiicsa.poo.pizarron.bs.LoginBs;
import mx.com.ipn.upiicsa.poo.pizarron.dto.LoginDto;
import mx.com.ipn.upiicsa.poo.pizarron.dto.UserDto;
import mx.com.ipn.upiicsa.poo.pizarron.entity.User;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.UserNotFoundException;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.WrongLoginException;
import mx.com.ipn.upiicsa.poo.pizarron.util.Result;
import mx.com.ipn.upiicsa.poo.pizarron.util.StatusCodes;
import mx.com.ipn.upiicsa.poo.pizarron.util.Error.TypeErrorEnum;
import mx.com.ipn.upiicsa.poo.pizarron.util.Error;

public class LoginPr {
	public static Result<UserDto> login(LoginDto loginDto) {
		Result<UserDto> usuarioResult = new Result<UserDto>();
			HibernateValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
					.messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()
					.unwrap(HibernateValidatorFactory.class);
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<LoginDto>> constraintViolations = validator.validate(loginDto);
			if(constraintViolations.isEmpty()) {
				try {
					User usuario = LoginBs.login(loginDto.getLogin(), loginDto.getPassword());
					UserDto usuarioDto = UserDto.fromEntity(usuario);
					usuarioResult.setResult(usuarioDto);
				} catch (WrongLoginException | UserNotFoundException e) {
					usuarioResult.setStatus(StatusCodes.ERROR_LOGIN);
					List<Error> errors = new ArrayList<Error>();
					errors.add(new Error(TypeErrorEnum.OPERATION,  "Usuario y/o contraseña incorrectos"));
					usuarioResult.setErrors(errors);
				}
			}else {
				usuarioResult.setStatus(StatusCodes.ERROR_FORM);
				List<Error> errors = new ArrayList<Error>();
				for(ConstraintViolation<LoginDto> constraint: constraintViolations) {
					Error error = new Error(TypeErrorEnum.FIELD, constraint.getPropertyPath().toString(), constraint.getMessage());
					errors.add(error);
					System.out.println(constraint.getPropertyPath()+" "+constraint.getMessage());
				}
				usuarioResult.setErrors(errors);
			}
			
		
		
		return usuarioResult;
	}
}