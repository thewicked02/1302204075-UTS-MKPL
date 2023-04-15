public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {

    int tax = 0;

    if (numberOfMonthsWorked > 12) {
        System.err.println("Number of months worked cannot exceed 12");
        return tax;
    }

    int taxExemptIncome = 54000000;

    if (isMarried) {
        taxExemptIncome += 4500000;
    }

    int maxChildren = 3;
    int childTaxExemptIncome = 4500000;
    int totalChildTaxExemptIncome = Math.min(numberOfChildren, maxChildren) * childTaxExemptIncome;
    taxExemptIncome += totalChildTaxExemptIncome;

    int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked - deductible - taxExemptIncome;

    if (taxableIncome > 0) {
        tax = (int) Math.round(0.05 * taxableIncome);
    }

    return tax;
}
