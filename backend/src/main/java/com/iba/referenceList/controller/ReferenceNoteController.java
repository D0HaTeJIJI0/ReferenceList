package com.iba.referenceList.controller;

import com.iba.referenceList.dto.EmployeeDto;
import com.iba.referenceList.dto.ReferenceNoteDto;
import com.iba.referenceList.exception.CustomServiceException;
import com.iba.referenceList.service.ReferenceNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.iba.referenceList.route.WebRouteConstants.*;

@RestController
@RequestMapping(API)
public class ReferenceNoteController {

    @Autowired
    private ReferenceNoteService referenceNoteService;

    @DeleteMapping(DELETE_REFERENCE_NOTES)
    public ResponseEntity<?> deleteReferenceNotes(
            @RequestBody List<ReferenceNoteDto> referenceNoteDtos) {

        try {
            referenceNoteService.deleteReferenceNote(referenceNoteDtos);
        } catch (CustomServiceException e) {
            return new ResponseEntity(e.getErrorResponse().getResponseStatus());
        }

        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping(POST_REFERENCE_NOTE)
    public ResponseEntity<?> createReferenceNote(
            @Validated(ReferenceNoteDto.Add.class) @RequestBody ReferenceNoteDto referenceNoteDto) {

        try {
            referenceNoteService.addReferenceNote(referenceNoteDto);
        } catch (CustomServiceException e) {
            return new ResponseEntity(e.getErrorResponse().getResponseStatus());
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(GET_ALL_REFERENCES)
    public ResponseEntity<?> getReferenceNoteEmployeeNotAcquainted() {

        List<ReferenceNoteDto> allReferenceNote = referenceNoteService.getAllReferenceNote();

        return ResponseEntity.ok()
                .body(allReferenceNote);
    }

    @GetMapping(GET_REFERENCE_NOTE_EMPLOYEE_NOT_ACQUAINTED)
    public ResponseEntity<?> getReferenceNoteEmployeeNotAcquainted(
            @PathVariable("employeeId") UUID employeeId) {

        List<ReferenceNoteDto> referenceNotesWhereEmployeeIsNotAcquintanted = referenceNoteService.getReferenceNotesWhereEmployeeIsNotAcquintanted(new EmployeeDto(employeeId));

        return ResponseEntity.ok()
                .body(referenceNotesWhereEmployeeIsNotAcquintanted);
    }

}
