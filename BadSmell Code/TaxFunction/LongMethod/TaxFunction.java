package lib;

public class TaxFunction {

    private static final int MAX_CHILDREN = 3;
    private static final int SINGLE_DEDUCTIBLE = 54000000;
    private static final int MARRIED_DEDUCTIBLE = 58500000;
    private static final int CHILD_DEDUCTIBLE = 4500000;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     * 
     */

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
            return 0;
        }

        int totalIncome = calculateTotalIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int totalDeductible = calculateTotalDeductible(deductible, isMarried, numberOfChildren);
        int taxableIncome = calculateTaxableIncome(totalIncome, totalDeductible);

        return calculateIncomeTax(taxableIncome);
    }

    private static int calculateTotalIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }

    private static int calculateTotalDeductible(int deductible, boolean isMarried, int numberOfChildren) {
        int totalDeductible = deductible;

        if (isMarried) {
            totalDeductible += MARRIED_DEDUCTIBLE;
        } else {
            totalDeductible += SINGLE_DEDUCTIBLE;
        }

        totalDeductible += numberOfChildren * CHILD_DEDUCTIBLE;
        totalDeductible = Math.min(totalDeductible, MARRIED_DEDUCTIBLE + MAX_CHILDREN * CHILD_DEDUCTIBLE);

        return totalDeductible;
    }

    private static int calculateTaxableIncome(int totalIncome, int totalDeductible) {
        int taxableIncome = totalIncome - totalDeductible;
        return Math.max(taxableIncome, 0);
    }

    private static int calculateIncomeTax(int taxableIncome) {
        return (int) Math.round(0.05 * taxableIncome);
    }
}
