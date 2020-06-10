package com.iba.referenceList.converter.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.domain.Rule;
import com.iba.referenceList.dto.ReferenceNoteDto;
import com.iba.referenceList.dto.ReferenceNoteDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component(value = "ReferenceNoteConverter")
public class ReferenceNoteConverter implements BaseConverter<ReferenceNote, ReferenceNoteDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReferenceNote toEntity(ReferenceNoteDto referenceNoteDto) {
        return referenceNoteDto == null ? null : modelMapper.map(referenceNoteDto, ReferenceNote.class);
    }

    @Override
    public List<ReferenceNote> toEntity(List<ReferenceNoteDto> referenceNoteDtos) {
        return referenceNoteDtos == null ? null : referenceNoteDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReferenceNoteDto toDto(ReferenceNote entity) {
        return entity == null ? null : modelMapper.map(entity, ReferenceNoteDto.class);
    }

    @Override
    public List<ReferenceNoteDto> toDto(List<ReferenceNote> categories) {
        return categories == null ? null : categories.stream()
                .map(referenceNote -> toDto(referenceNote))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(ReferenceNote.class, ReferenceNoteDto.class)
                .addMappings(m -> m.skip(ReferenceNoteDto::setRuleId)).setPostConverter(toDtoPostConverter())
                .addMappings(m -> m.skip(ReferenceNoteDto::setEmployeeId)).setPostConverter(toDtoPostConverter());
        modelMapper.createTypeMap(ReferenceNoteDto.class, ReferenceNote.class)
                .addMappings(m -> m.skip(ReferenceNote::setEmployee)).setPostConverter(toEntityPostConverter())
                .addMappings(m -> m.skip(ReferenceNote::setRule)).setPostConverter(toEntityPostConverter());
    }

    private Converter<ReferenceNoteDto, ReferenceNote> toEntityPostConverter() {
        return context -> {
            ReferenceNoteDto source = context.getSource();
            ReferenceNote destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };

    }

    private Converter<ReferenceNote, ReferenceNoteDto> toDtoPostConverter() {
        return context -> {
            ReferenceNote source = context.getSource();
            ReferenceNoteDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(ReferenceNote source, ReferenceNoteDto destination) {
        Employee employee = source.getEmployee();
        Rule rule = source.getRule();

        destination.setEmployeeId(employee == null ? null : employee.getId());
        destination.setRuleId(rule == null ? null : rule.getId());
    }

    private void mapSpecificFields(ReferenceNoteDto source, ReferenceNote destination) {
        UUID employeeId = source.getEmployeeId();
        Long ruleId = source.getRuleId();

        destination.setEmployee(employeeId == null ? null : new Employee(employeeId));
        destination.setRule(ruleId == null ? null : new Rule(ruleId));
    }
}
