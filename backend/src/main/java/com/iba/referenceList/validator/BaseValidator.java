package com.iba.referenceList.validator;

import com.iba.referenceList.exception.CustomServiceException;

public interface BaseValidator<Dto> {

    void validateAdd(Dto dto) throws CustomServiceException;

    void validateDelete(Dto dto) throws CustomServiceException;

}
