package com.common.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 校验工具类
 *
 * @author wdmcygah
 */
public class ValidationUtils {

  private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  public static <T> ValidationResult validateEntity(T obj) {
    ValidationResult result = new ValidationResult();
    Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
    if (ValidateUtils.isCollectionEmpty(set)) {
      result.setHasErrors(true);
      Map<String, String> errorMsg = new HashMap(16);
      for (ConstraintViolation<T> cv : set) {
        errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
      }
      result.setErrorMsg(errorMsg);
    }
    return result;
  }

  public static <T> ValidationResult validateProperty(T obj, String propertyName) {
    ValidationResult result = new ValidationResult();
    Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
    if (ValidateUtils.isCollectionEmpty(set)) {
      result.setHasErrors(true);
      Map<String, String> errorMsg = new HashMap(16);
      for (ConstraintViolation<T> cv : set) {
        errorMsg.put(propertyName, cv.getMessage());
      }
      result.setErrorMsg(errorMsg);
    }
    return result;
  }
}
