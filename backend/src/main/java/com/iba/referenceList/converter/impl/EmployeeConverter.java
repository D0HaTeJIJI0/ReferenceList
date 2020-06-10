package com.iba.referenceList.converter.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.dto.EmployeeDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component(value = "EmployeeConverter")
public class EmployeeConverter implements BaseConverter<Employee, EmployeeDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        return employeeDto == null ? null : modelMapper.map(employeeDto, Employee.class);
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDto> employeeDtos) {
        return employeeDtos == null ? null : employeeDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        return entity == null ? null : modelMapper.map(entity, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> toDto(List<Employee> categories) {
        return categories == null ? null : categories.stream()
                .map(employee -> toDto(employee))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Employee.class, EmployeeDto.class)
                .addMappings(m -> m.skip(EmployeeDto::setBossId)).setPostConverter(toDtoPostConverter())
                .addMappings(m -> m.skip(EmployeeDto::setSubordinateIds)).setPostConverter(toDtoPostConverter())
                .addMappings(m -> m.skip(EmployeeDto::setReferenceNoteIds)).setPostConverter(toDtoPostConverter());
        modelMapper.createTypeMap(EmployeeDto.class, Employee.class)
                .addMappings(m -> m.skip(Employee::setBoss)).setPostConverter(toEntityPostConverter())
                .addMappings(m -> m.skip(Employee::setSubordinates)).setPostConverter(toEntityPostConverter())
                .addMappings(m -> m.skip(Employee::setReferenceNotes)).setPostConverter(toEntityPostConverter());
    }

    private Converter<EmployeeDto, Employee> toEntityPostConverter() {
        return context -> {
            EmployeeDto source = context.getSource();
            Employee destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };

    }

    private Converter<Employee, EmployeeDto> toDtoPostConverter() {
        return context -> {
            Employee source = context.getSource();
            EmployeeDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Employee source, EmployeeDto destination) {
        Employee boss = source.getBoss();
        List<Employee> subordinates = source.getSubordinates();
        List<ReferenceNote> referenceNotes = source.getReferenceNotes();

        destination.setBossId(boss == null ? null : boss.getId());

        destination.setSubordinateIds(subordinates == null ? null : subordinates.stream()
                .map(subordinate -> subordinate.getId())
                .collect(Collectors.toList()));

        destination.setReferenceNoteIds(referenceNotes == null ? null : referenceNotes.stream()
                .map(referenceNote -> referenceNote.getId())
                .collect(Collectors.toList()));
    }

    private void mapSpecificFields(EmployeeDto source, Employee destination) {
        UUID bossId = source.getBossId();
        List<UUID> subordinateIds = source.getSubordinateIds();
        List<Long> referenceNoteIds = source.getReferenceNoteIds();

        destination.setBoss(bossId == null ? null : new Employee(bossId));

        destination.setSubordinates(subordinateIds == null ? null : subordinateIds.stream()
                .map(Employee::new)
                .collect(Collectors.toList()));

        destination.setReferenceNotes(referenceNoteIds == null ? null : referenceNoteIds.stream()
                .map(ReferenceNote::new)
                .collect(Collectors.toList()));

    }
}
