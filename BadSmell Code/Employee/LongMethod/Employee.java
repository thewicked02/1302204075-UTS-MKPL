public class Employee {
    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthWorkingInYear;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private String spouseName;
    private String spouseIdNumber;
    private List<String> childNames;
    private List<String> childIdNumbers;
    
    // Constructor dan method set lainnya
    
    private int calculateWorkingMonth() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }
        return monthWorkingInYear;
    }
    
    private int calculateTaxableIncome() {
        int taxableIncome = monthlySalary + otherMonthlyIncome - annualDeductible;
        return taxableIncome;
    }
    
    public int getAnnualIncomeTax() {
        int workingMonth = calculateWorkingMonth();
        int taxableIncome = calculateTaxableIncome();
        boolean spouseHasIdNumber = !spouseIdNumber.equals("");
        int numOfChildren = childIdNumbers.size();
        int annualIncomeTax = TaxFunction.calculateTax(taxableIncome, workingMonth, spouseHasIdNumber, numOfChildren);
        return annualIncomeTax;
    }
}
