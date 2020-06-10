package com.iba.referenceList.validator.impl;

import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.domain.Rule;
import com.iba.referenceList.dto.ReferenceNoteDto;
import com.iba.referenceList.exception.CustomServiceException;
import com.iba.referenceList.exception.DataDeleteException;
import com.iba.referenceList.exception.DataDuplicationException;
import com.iba.referenceList.repository.ReferenceNoteRepository;
import com.iba.referenceList.validator.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.iba.referenceList.constant.ErrorMessageConstants.DELETE_REFERENCE_NOTE_ACQUAINTED_EXCEPTION;
import static com.iba.referenceList.constant.ErrorMessageConstants.DUPLICATE_REFERENCE_NOTE_NOT_ACQUAINTED_EXCEPTION;

@Component
public class ReferenceNoteDtoValidator implements BaseValidator<ReferenceNoteDto> {

    @Autowired
    private ReferenceNoteRepository referenceNoteRepository;

    @Override
    public void validateAdd(ReferenceNoteDto referenceNoteDto) throws CustomServiceException {
        Optional<List<ReferenceNote>> referenceNoteByEmployeeAndRule = referenceNoteRepository.findByEmployeeAndRule(
                new Employee(referenceNoteDto.getEmployeeId()),
                new Rule(referenceNoteDto.getRuleId()));
        if (referenceNoteByEmployeeAndRule.isPresent()
                && referenceNoteByEmployeeAndRule.get()
                    .stream()
                    .anyMatch(note -> note.getDate() == null)) {

            throw new DataDuplicationException(DUPLICATE_REFERENCE_NOTE_NOT_ACQUAINTED_EXCEPTION);
        }
    }

    @Override
    public void validateDelete(ReferenceNoteDto referenceNoteDto) throws CustomServiceException {
        Optional<ReferenceNote> referenceNoteById = referenceNoteRepository.findById(referenceNoteDto.getId());
        if (referenceNoteById.isPresent()
                && referenceNoteById.get().getDate() != null) {

            throw new DataDeleteException(DELETE_REFERENCE_NOTE_ACQUAINTED_EXCEPTION);
        }
    }

}
