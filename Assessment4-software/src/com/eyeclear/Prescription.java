package com.eyeclear;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Prescription {
        private int prescID;
        private String firstName;
        private String lastName;
        private String address;
        private float sphere;
        private float axis;
        private float cylinder;
        private Date examinationDate;
        private String optometrist;
        private String[] remarkTypes= {"Client","Optometrist"};
        private ArrayList <String> postRemarks= new ArrayList<>();
        private String remark; // Store the remark text inside the class
        private String category; // Store the category inside the class

        public boolean addPrescription()
        {
        	 // Condition 1: First Name and Last Name should be between 4 and 15 characters
            if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
                return false;
            }

            // Condition 2: Address should have a minimum of 20 characters
            if (address.length() < 20) {
                return false;
            }

            // Condition 3: Sphere should be between -20.00 and +20.00
            if (sphere < -20.00 || sphere > 20.00) {
                return false;
            }

            // Condition 3: Cylinder should be between -4.00 and +4.00
            if (cylinder < -4.00 || cylinder > 4.00) {
                return false;
            }

            // Condition 3: Axis should be between 0 and 180
            if (axis < 0 || axis > 180) {
                return false;
            }

            // Condition 4: Optometrist's name should be between 8 and 25 characters
            if (optometrist.length() < 8 || optometrist.length() > 25) {
                return false;
            }

            // Save the prescription to presc.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
                writer.write("Prescription ID: " + prescID + "\n");
                writer.write("First Name: " + firstName + "\n");
                writer.write("Last Name: " + lastName + "\n");
                writer.write("Address: " + address + "\n");
                writer.write("Sphere: " + sphere + "\n");
                writer.write("Axis: " + axis + "\n");
                writer.write("Cylinder: " + cylinder + "\n");
                writer.write("Examination Date: " + examinationDate + "\n");
                writer.write("Optometrist: " + optometrist + "\n");

            } catch (IOException e) {
                System.out.println("An error occurred while saving the prescription: " + e.getMessage());
                return false;
            }

        	return true;
        }
        
        public boolean addRemark()
        {
        	  // Condition 1: Remark should have between 6 and 20 words, and the first word should start with an uppercase letter
            String[] words = this.remark.split(" ");
            if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
                return false;
            }

            // Condition 2: The category must be either "Client" or "Optometrist"
            boolean validCategory = this.category.equals(remarkTypes[0]) || category.equals(remarkTypes[1]);
            if (!validCategory) {
                return false;
            }

            // Condition 3: A prescription can have only 2 remarks
            if (postRemarks.size() >= 2) {
                return false;  // Reject any further remarks if already 2 are added
            }

            // Add the remark to the list
            postRemarks.add(this.remark);

            // Save the remark to review.txt
           
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("review.txt", true))) {
                writer.write("Prescription ID: " + prescID + "\n");
                writer.write("Category: " + this.category + "\n");
                writer.write("Remark: " + this.remark + "\n");
 
            } catch (IOException e) {
                System.out.println("An error occurred while saving the remark: " + e.getMessage());
                return false;
            }
            


            return true;
        }

//        //set remark and catagory
//        public void setRemark(String remark, String category) {
//            this.remark = remark;
//            this.category = category;
//        }
        
        // Getter and Setter for prescID
        public int getPrescID() {
            return prescID;
        }

        public void setPrescID(int prescID) {
            this.prescID = prescID;
        }

        // Getter and Setter for firstName
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        // Getter and Setter for lastName
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        // Getter and Setter for address
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        // Getter and Setter for sphere
        public float getSphere() {
            return sphere;
        }

        public void setSphere(float sphere) {
            this.sphere = sphere;
        }

        // Getter and Setter for axis
        public float getAxis() {
            return axis;
        }

        public void setAxis(float axis) {
            this.axis = axis;
        }

        // Getter and Setter for cylinder
        public float getCylinder() {
            return cylinder;
        }

        public void setCylinder(float cylinder) {
            this.cylinder = cylinder;
        }

        // Getter and Setter for examinationDate
        public Date getExaminationDate() {
            return examinationDate;
        }

        public void setExaminationDate(Date examinationDate) {
            this.examinationDate = examinationDate;
        }

        // Getter and Setter for optometrist
        public String getOptometrist() {
            return optometrist;
        }

        public void setOptometrist(String optometrist) {
            this.optometrist = optometrist;
        }

        // Getter and Setter for remark
        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        // Getter and Setter for category
        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

}
