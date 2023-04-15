package lib;

public class TaxFunction {

    private static final int MAX_MONTHS_WORKING = 12;
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
     * @param monthlySalary Gaji bulanan pegawai
     * @param otherMonthlyIncome Pemasukan bulanan lainnya
     * @param numberOfMonthsWorking Jumlah bulan bekerja dalam setahun
     * @param deductible Pemotongan pajak
     * @param isMarried Status perkawinan
     * @param numberOfChildren Jumlah anak yang dimiliki
     * @return Jumlah pajak yang harus dibayarkan
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int tax = 0;

        if (numberOfMonthsWorking > MAX_MONTHS_WORKING) {
            System.err.println("More than 12 month working per year");
        }

        numberOfChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorking;
        int taxDeductible = isMarried ? MARRIED_DEDUCTIBLE : SINGLE_DEDUCTIBLE;
        taxDeductible += numberOfChildren * CHILD_DEDUCTIBLE;

        tax = (int) Math.round(0.05 * (totalIncome - deductible - taxDeductible));

        if (tax < 0) {
            return 0;
        }

        return tax;
    }
}
