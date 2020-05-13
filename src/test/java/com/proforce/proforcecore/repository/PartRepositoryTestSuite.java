package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Part;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartRepositoryTestSuite {

    @Autowired
    private PartRepository partRepository;

    @Test
    public void testSavingAndDeletingParts() {

        List<Part> partList = new ArrayList<>();
        partList.add(new Part.PartBuilder()
                .model("SV06")
                .manufacturer("PARKER")
                .type(Part.TYPE_PROCESS)
                .build());
        partList.add(new Part.PartBuilder()
                .model("MHAC")
                .manufacturer("FRIGOTERMICA")
                .type(Part.TYPE_COMPLEX)
                .build());
        partList.add(new Part.PartBuilder()
                .model("GHG411")
                .manufacturer("EATON")
                .type(Part.TYPE_ELECTRICAL)
                .build());

        partRepository.saveAll(partList);

        assertEquals("SV06", partList.get(0).getModel());
        assertEquals("PARKER", partList.get(0).getManufacturer());
        assertEquals(Part.TYPE_PROCESS, partList.get(0).getType());
        assertEquals("MHAC", partList.get(1).getModel());
        assertEquals("FRIGOTERMICA", partList.get(1).getManufacturer());
        assertEquals(Part.TYPE_COMPLEX, partList.get(1).getType());
        assertEquals("GHG411", partList.get(2).getModel());
        assertEquals("EATON", partList.get(2).getManufacturer());
        assertEquals(Part.TYPE_ELECTRICAL, partList.get(2).getType());

        partRepository.deleteAll(partList);

        assertFalse(partRepository.existsById(partList.get(0).getId()));
        assertFalse(partRepository.existsById(partList.get(1).getId()));
        assertFalse(partRepository.existsById(partList.get(2).getId()));

    }
}

/*


*/
