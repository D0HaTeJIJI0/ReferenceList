package com.iba.referenceList.service.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.dto.EmployeeDto;
import com.iba.referenceList.dto.ReferenceNoteDto;
import com.iba.referenceList.exception.CustomServiceException;
import com.iba.referenceList.repository.ReferenceNoteRepository;
import com.iba.referenceList.service.ReferenceNoteService;
import com.iba.referenceList.validator.impl.ReferenceNoteDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceNoteServiceImpl implements ReferenceNoteService {

    @Autowired
    private ReferenceNoteRepository referenceNoteRepository;

    @Autowired
    private ReferenceNoteDtoValidator referenceNoteDtoValidator;

    @Autowired
    @Qualifier(value = "ReferenceNoteConverter")
    private BaseConverter<ReferenceNote, ReferenceNoteDto> referenceNoteConverter;

    @Autowired
    @Qualifier(value = "EmployeeConverter")
    private BaseConverter<Employee, EmployeeDto> employeeConverter;

    @Override
    public void deleteReferenceNote(List<ReferenceNoteDto> referenceNoteDtos) throws CustomServiceException {
        for (ReferenceNoteDto referenceNoteDto: referenceNoteDtos) {
            referenceNoteDtoValidator.validateDelete(referenceNoteDto);
        }
        List<ReferenceNote> referenceNotes = referenceNoteConverter.toEntity(referenceNoteDtos);
        referenceNoteRepository.deleteAll(referenceNotes);
    }

    @Override
    public void addReferenceNote(ReferenceNoteDto referenceNoteDto) throws CustomServiceException {
        referenceNoteDtoValidator.validateAdd(referenceNoteDto);
        ReferenceNote referenceNote = referenceNoteConverter.toEntity(referenceNoteDto);
        referenceNoteRepository.save(referenceNote);
    }

    @Override
    public List<ReferenceNoteDto> getReferenceNotesWhereEmployeeIsNotAcquintanted(EmployeeDto employeeDto) {
        Employee employee = employeeConverter.toEntity(employeeDto);
        List<ReferenceNote> allByEmployeeAndDate = referenceNoteRepository.findAllByEmployeeAndDate(employee, null);
        return referenceNoteConverter.toDto(allByEmployeeAndDate);
    }

    @Override
    public List<ReferenceNoteDto> getAllReferenceNote() {
        List<ReferenceNote> all = referenceNoteRepository.findAll();
        return referenceNoteConverter.toDto(all);
    }
}
