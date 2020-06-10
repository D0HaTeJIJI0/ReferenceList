package com.iba.referenceList.service;

import com.iba.referenceList.dto.EmployeeDto;
import com.iba.referenceList.dto.ReferenceNoteDto;
import com.iba.referenceList.exception.CustomServiceException;

import java.util.List;

public interface ReferenceNoteService {

    void deleteReferenceNote(List<ReferenceNoteDto> referenceNoteDtos) throws CustomServiceException;

    void addReferenceNote(ReferenceNoteDto referenceNoteDto) throws CustomServiceException;

    List<ReferenceNoteDto> getReferenceNotesWhereEmployeeIsNotAcquintanted(EmployeeDto employeeDto);

    List<ReferenceNoteDto> getAllReferenceNote();

}
