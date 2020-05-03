package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.PartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartMapper {

    @Autowired
    private DocumentMapper documentMapper;

    public Part mapToPart(PartDto partDto) {
        Part partToBeReturned = new Part.PartBuilder()
                .model(partDto.getModel())
                .manufacturer(partDto.getManufacturer())
                .type(partDto.getType())
                .build();

        for (DocumentDto d : partDto.getDocs()) {
            partToBeReturned.addDoc(documentMapper.mapToDocument(d));
        }

        partToBeReturned.setId(partDto.getId());

        return partToBeReturned;
    }

    public PartDto mapToPartDto(Part part) {

        PartDto partDtoToBeReturned = new PartDto(part.getModel(),part.getManufacturer(),part.getType());

        List<DocumentDto> documentDtoList = new ArrayList<>();

        for (Document d : part.getDocs()) {
            documentDtoList.add(documentMapper.mapToDocumentDto(d));
        }

        partDtoToBeReturned.setDocs(documentDtoList);

        partDtoToBeReturned.setId(part.getId());

        return partDtoToBeReturned;
    }

    public List<PartDto> mapToPartDtoList(List<Part> partList) {
        return partList.stream()
                .map(part -> mapToPartDto(part))
                .collect(Collectors.toList());
    }

    public List<Part> mapToPartList(List<PartDto> partDtoList) {
        return partDtoList.stream()
                .map(partDto -> mapToPart(partDto))
                .collect(Collectors.toList());
    }



}
