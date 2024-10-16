package com.eyeclear.tests;

import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.eyeclear.Prescription;
import java.util.Date;

public class PrescriptionTest {

//    private Prescription prescription;

    @Test
    public void testValidPrescription() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(1.00f);
        prescription.setCylinder(-1.00f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertTrue(prescription.addPrescription());
    }

    @Test
    public void testFirstNameTooShort() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("pp"); // Too short
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(1.00f);
        prescription.setCylinder(-1.00f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testLastNameTooLong() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("DiddyDiddyDiddyDiddyDiddyDiddy"); // Too long
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(1.00f);
        prescription.setCylinder(-1.00f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testAddressTooShort() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("Short addy"); // Too short
        prescription.setSphere(1.00f);
        prescription.setCylinder(-1.00f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testSphereTooLow() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(-30.00f); // Out of range
        prescription.setCylinder(-1.00f);
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertFalse(prescription.addPrescription());
    }

    @Test
    public void testCylinderTooHigh() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(1.00f);
        prescription.setCylinder(10.00f); // Out of valid range (too high)
        prescription.setAxis(90);
        prescription.setOptometrist("Dr. Opto");
        prescription.setExaminationDate(new Date());

        assertFalse(prescription.addPrescription());
    }
    
    @Test
    public void testValidExtremeValues() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setSphere(20.00f); // Maximum valid value
        prescription.setCylinder(4.00f); // Maximum valid value
        prescription.setAxis(180); // Maximum valid value
        prescription.setOptometrist("OptometristsRock");
        prescription.setExaminationDate(new Date());

        assertTrue(prescription.addPrescription());
    }

    @Test
    public void testValidRemark() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setRemark("This is a valid remark for sure this time.");
        prescription.setCategory("Client"); // Valid category

        assertTrue(prescription.addRemark());
    }

    @Test
    public void testRemarkTooShort() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setRemark("Short"); // Too short
        prescription.setCategory("Client");

        assertFalse(prescription.addRemark());
    }

    @Test
    public void testInvalidRemarkCategory() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setRemark("This is a valid remark.");
        prescription.setCategory("Doctor"); // Invalid category

        assertFalse(prescription.addRemark());
    }

    @Test
    public void testRemarkExceedsWordLimit() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("William");
        prescription.setLastName("Diddy");
        prescription.setAddress("123 Example Street, Melbourne, VIC, 3000");
        prescription.setRemark("This remark is far too long because it contains more than twenty words. This sentence should cause the test to fail.");
        prescription.setCategory("Client");

        assertFalse(prescription.addRemark());
    }

    @Test
    public void testExceedRemarkLimit() {
        Prescription prescription1 = new Prescription();
        prescription1.setFirstName("William");
        prescription1.setLastName("Diddy");
        prescription1.setAddress("123 Example Street, Melbourne, VIC, 3000");

        // Add two valid remarks first
        prescription1.setRemark("First valid remark that actually works.");
        prescription1.setCategory("Client");
        assertTrue(prescription1.addRemark());

        prescription1.setRemark("Second valid remark that actually works.");
        prescription1.setCategory("Optometrist");
        assertTrue(prescription1.addRemark());

        // Now, adding a third remark should fail
        prescription1.setRemark("Third remark should fail hopefully it did not before.");
        prescription1.setCategory("Client");
        assertFalse(prescription1.addRemark());
    }
}
