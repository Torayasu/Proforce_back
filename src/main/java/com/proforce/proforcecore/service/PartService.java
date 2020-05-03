package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.Pdf;
import com.proforce.proforcecore.exception.PartNotFound;
import com.proforce.proforcecore.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;


    public Part createEmptyPart() {
        Part newPart = new Part.PartBuilder()
                .model("")
                .manufacturer("")
                .type("")
                .build();

        partRepository.save(newPart);

        return newPart;
    }

    public Part createPartFromObject(Part part) {

        Part newPart = new Part.PartBuilder()
                .model(part.getModel())
                .manufacturer(part.getManufacturer())
                .type(part.getType())
                .build();

        for (Document d : part.getDocs()) {
            newPart.addDoc(d);
        }

        partRepository.save(newPart);

        return newPart;
    }


    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public List<Part> getAllPartsByType(String type) {
        return partRepository.findAllByType(type);
    }

    public Part getPart(Long id) {

        Optional<Part> retrievedPart = partRepository.findById(id);

        if (retrievedPart.isPresent()) {
            return retrievedPart.get();
        } else {
            throw new PartNotFound();
        }

    }

    public void deletePart(Long id) {
        Optional<Part> partToBeDeleted = partRepository.findById(id);

        if (partToBeDeleted.isPresent()) {
            partRepository.deleteById(id);
        } else {
            throw new PartNotFound();
        }
    }

    public Part updatePart(Part part) {

        Optional<Part> partToBeUpdated = partRepository.findById(part.getId());

        if (partToBeUpdated.isPresent()) {
            partRepository.save(part);
            return part;
        } else {
            throw new PartNotFound();
        }

    }

}
