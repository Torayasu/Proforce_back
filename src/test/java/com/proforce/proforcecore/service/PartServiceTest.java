package com.proforce.proforcecore.service;

import com.proforce.proforcecore.domain.Document;
import com.proforce.proforcecore.domain.Part;
import com.proforce.proforcecore.domain.Pdf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartServiceTest {

    @Autowired
    private PartService partService;

    @Test
    public void testCheckIfPartExists() {

        Part testPart = new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build();

        testPart = partService.createPartFromObject(testPart);

        assertTrue(partService.checkIfPartExists(testPart.getId()));

    }

    @Test
    public void testPartCreationMethods() {

        Part testPart = new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build();
        Part createdPart = partService.createPartFromObject(testPart);
        Part emptyCreatedPart = partService.createEmptyPart();

        assertEquals("SV06", createdPart.getModel());
        assertEquals("PARKER", createdPart.getManufacturer());
        assertEquals(Part.TYPE_PROCESS, createdPart.getType());;
        assertEquals(0, createdPart.getDocs().size());

        assertEquals("", emptyCreatedPart.getModel());
        assertEquals("", emptyCreatedPart.getManufacturer());
        assertEquals("", emptyCreatedPart.getType());
        assertEquals(0, emptyCreatedPart.getDocs().size());

    }

    @Test
    public void testUpdateAndDelete() {


        Part testPart = new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build();
        Part createdPart = partService.createPartFromObject(testPart);

        Part partToBeUpdated = new Part.PartBuilder()
                .model("MHAC")
                .manufacturer("FRIGOTERMICA")
                .type(Part.TYPE_COMPLEX)
                .doc(new Document("Atex", "P+F", Document.TYPE_COC, LocalDate.now(), new Pdf()))
                .build();
        partToBeUpdated.setId(createdPart.getId());
        partToBeUpdated.addDoc(new Document("CuTR012", "Cortem", Document.TYPE_COC_RUS, LocalDate.now(), new Pdf()));
        createdPart = partService.updatePart(partToBeUpdated);

        assertEquals("MHAC", createdPart.getModel());
        assertEquals("FRIGOTERMICA", createdPart.getManufacturer());
        assertEquals(Part.TYPE_COMPLEX, createdPart.getType());
        assertEquals(2,createdPart.getDocs().size() );
        assertEquals("Atex",createdPart.getDocs().get(0).getName());
        assertEquals(Document.TYPE_COC_RUS,createdPart.getDocs().get(1).getType());



        partService.deletePart(createdPart.getId());

        assertFalse(partService.checkIfPartExists(createdPart.getId()));

    }

}