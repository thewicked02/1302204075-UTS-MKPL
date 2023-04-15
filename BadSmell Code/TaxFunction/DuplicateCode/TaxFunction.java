package lib;

public class TaxFunction {
    
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
    
    public static long calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, long deductible, boolean isMarried, int numberOfChildren) {
        
        long tax = 0;
        
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }
        
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }
        
        TaxThreshold taxThreshold = new TaxThreshold();
        long incomeNotTaxed = taxThreshold.getIncomeNotTaxed(isMarried, numberOfChildren);
        
        long taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible - incomeNotTaxed;
        tax = Math.round(0.05 * taxableIncome);
        
        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }
        
    }
    
}
