package com.proforce.proforcecore.mapper;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.DocumentDto;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.PartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartMapper {

    public Part mapToPart(PartDto partDto) {
        Part partToBeReturned = new Part.PartBuilder()
                .model(partDto.getModel())
                .manufacturer(partDto.getManufacturer())
                .type(partDto.getType())
                .build();

        return partToBeReturned;
    }

    public PartDto mapToPartDto(Part part) {

        PartDto partDtoToBeReturned = new PartDto(part.getModel(),part.getManufacturer(),part.getType());


        return partDtoToBeReturned;
    }

}
