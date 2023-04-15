package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;

    private boolean isForeigner;
    private boolean isMale;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private Spouse spouse;
    private List<Child> children;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.isMale = isMale;

        children = new LinkedList<Child>();
    }

    public void setMonthlySalary(int grade) {
        if (grade == 1) {
            monthlySalary = 3000000;
        } else if (grade == 2) {
            monthlySalary = 5000000;
        } else if (grade == 3) {
            monthlySalary = 7000000;
        }

        if (isForeigner) {
            monthlySalary = (int) (monthlySalary * 1.5);
        }
    }

    public void setAnnualDeductible(int annualDeductible) {
        this.annualDeductible = annualDeductible;
    }

    public void setAdditionalIncome(int otherMonthlyIncome) {
        this.otherMonthlyIncome = otherMonthlyIncome;
    }

    public void setSpouse(Spouse spouse) {
        this.spouse = spouse;
    }

    public void addChild(Child child) {
        children.add(child);
    }

    public int getAnnualIncomeTax() {
        int monthsWorked = 12;

        if (dateJoined.getYear() == LocalDate.now().getYear()) {
            monthsWorked = LocalDate.now().getMonthValue() - dateJoined.getMonthValue();
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, spouse == null, children.size());
    }

    private class Spouse {
        private String name;
        private String idNumber;

        public Spouse(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }

        public String getName() {
            return name;
        }

        public String getIdNumber() {
            return idNumber;
        }
    }

    private class Child {
        private String name;
        private String idNumber;

        public Child(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }

        public String getName() {
            return name;
        }

        public String getIdNumber() {
            return idNumber;
        }
    }
}
