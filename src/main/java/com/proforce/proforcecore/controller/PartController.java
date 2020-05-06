package com.proforce.proforcecore.controller;


import com.proforce.proforcecore.domain.PartDto;
import com.proforce.proforcecore.exception.PartNotFound;
import com.proforce.proforcecore.mapper.PartMapper;
import com.proforce.proforcecore.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PartController {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    @Autowired
    private PartService partService;

    @Autowired
    private PartMapper partMapper;

    @RequestMapping(method = RequestMethod.POST, value="/part")
    private PartDto createEmptyPart() {
        return partMapper.mapToPartDto(partService.createEmptyPart());
    }

    @RequestMapping(method = RequestMethod.POST, value="/part", consumes = APPLICATION_JSON_VALUE)
    private PartDto createPart(@RequestBody PartDto partDto) {
        return partMapper.mapToPartDto(partService.createPartFromObject(partMapper.mapToPart(partDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/part")
    private List<PartDto> getAllParts() {
        return partMapper.mapToPartDtoList(partService.getAllParts());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/part/{partId}")
    private void deletePart(@PathVariable Long partId) throws PartNotFound {
        partService.deletePart(partId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/part")
    private PartDto updatePart(@RequestBody PartDto partDto) throws PartNotFound {
        return partMapper.mapToPartDto(partService.updatePart(partMapper.mapToPart(partDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/part/{type}")
    private List<PartDto> getPartsByType(@PathVariable String type) {
        return partMapper.mapToPartDtoList(partService.getAllPartsByType(type));
    }

}
