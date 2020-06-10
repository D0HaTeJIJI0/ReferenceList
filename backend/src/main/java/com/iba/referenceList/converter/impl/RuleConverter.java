package com.iba.referenceList.converter.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.domain.Rule;
import com.iba.referenceList.dto.RuleDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "RuleConverter")
public class RuleConverter implements BaseConverter<Rule, RuleDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Rule toEntity(RuleDto ruleDto) {
        return ruleDto == null ? null : modelMapper.map(ruleDto, Rule.class);
    }

    @Override
    public List<Rule> toEntity(List<RuleDto> ruleDtos) {
        return ruleDtos == null ? null : ruleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RuleDto toDto(Rule entity) {
        return entity == null ? null : modelMapper.map(entity, RuleDto.class);
    }

    @Override
    public List<RuleDto> toDto(List<Rule> categories) {
        return categories == null ? null : categories.stream()
                .map(rule -> toDto(rule))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Rule.class, RuleDto.class)
                .addMappings(m -> m.skip(RuleDto::setReferenceNoteIds)).setPostConverter(toDtoPostConverter());
        modelMapper.createTypeMap(RuleDto.class, Rule.class)
                .addMappings(m -> m.skip(Rule::setReferenceNotes)).setPostConverter(toEntityPostConverter());
    }

    private Converter<RuleDto, Rule> toEntityPostConverter() {
        return context -> {
            RuleDto source = context.getSource();
            Rule destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };

    }

    private Converter<Rule, RuleDto> toDtoPostConverter() {
        return context -> {
            Rule source = context.getSource();
            RuleDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Rule source, RuleDto destination) {
        List<ReferenceNote> referenceNotes = source.getReferenceNotes();

        destination.setReferenceNoteIds(referenceNotes == null ? null : referenceNotes.stream()
                .map(referenceNote -> referenceNote.getId())
                .collect(Collectors.toList()));
    }

    private void mapSpecificFields(RuleDto source, Rule destination) {
        List<Long> referenceNoteIds = source.getReferenceNoteIds();

        destination.setReferenceNotes(referenceNoteIds == null ? null : referenceNoteIds.stream()
                .map(ReferenceNote::new)
                .collect(Collectors.toList()));

    }
}
