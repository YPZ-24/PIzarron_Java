package mx.com.ipn.upiicsa.poo.pizarron.pr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.HibernateValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import mx.com.ipn.upiicsa.poo.pizarron.bs.UserBs;
import mx.com.ipn.upiicsa.poo.pizarron.dto.UserDto;
import mx.com.ipn.upiicsa.poo.pizarron.entity.User;
import mx.com.ipn.upiicsa.poo.pizarron.exceptions.DuplicatedLoginException;
import mx.com.ipn.upiicsa.poo.pizarron.util.Error;
import mx.com.ipn.upiicsa.poo.pizarron.util.Result;
import mx.com.ipn.upiicsa.poo.pizarron.util.StatusCodes;
import mx.com.ipn.upiicsa.poo.pizarron.util.Error.TypeErrorEnum;

public class RegisterPr {
	
	public static Result<UserDto> register(UserDto userDto) {
		Result<UserDto> userResult = new Result<UserDto>();
			HibernateValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
					.messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()
					.unwrap(HibernateValidatorFactory.class);
			Validator validator = validatorFactory.getValidator();
			Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
			if(constraintViolations.isEmpty()) {
				List<Error> errors = new ArrayList<Error>();
					if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
						User newUser = null;
						try {
							userResult.setStatus(StatusCodes.SUCCESS);
							newUser = UserBs.save(userDto.toEntity());
							userResult.setResult(UserDto.fromEntity(newUser));
						} catch (DuplicatedLoginException e) {
							userResult.setStatus(StatusCodes.ERROR_DUPLICATED_LOGIN);
							errors.add(new Error(TypeErrorEnum.FIELD,  "login", "Ya existe un usuario con el mismo correo registrado"));
						}
					}else {
						userResult.setStatus(StatusCodes.ERROR_PASSWORD_CONFIRM);
						errors.add(new Error(TypeErrorEnum.FIELD,  "password", "La confirmacion de contraseña no existe"));
					}
				userResult.setErrors(errors);
			}else {
				userResult.setStatus(StatusCodes.ERROR_FORM);
				List<Error> errors = new ArrayList<Error>();
				for(ConstraintViolation<UserDto> constraint: constraintViolations) {
					Error error = new Error(TypeErrorEnum.FIELD, constraint.getPropertyPath().toString(), constraint.getMessage());
					errors.add(error);
				}
				userResult.setErrors(errors);
			}
			
		
		
		return userResult;
	}
	
}
