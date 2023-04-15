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

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(EmployeeData employeeData) {
		this.employeeId = employeeData.getEmployeeId();
		this.firstName = employeeData.getFirstName();
		this.lastName = employeeData.getLastName();
		this.idNumber = employeeData.getIdNumber();
		this.address = employeeData.getAddress();
		this.yearJoined = employeeData.getYearJoined();
		this.monthJoined = employeeData.getMonthJoined();
		this.dayJoined = employeeData.getDayJoined();
		this.isForeigner = employeeData.isForeigner();
		this.gender = employeeData.isMale();

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade
	 * 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan) Jika pegawai adalah warga negara asing gaji bulanan
	 * diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		} else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya
		// maka otomatis dianggap 12 bulan.
		LocalDate currentDate = LocalDate.now();
		int monthsWorkedInYear;

		if (currentDate.getYear() == yearJoined) {
			monthsWorkedInYear = currentDate.getMonthValue() - monthJoined;
		} else {
			monthsWorkedInYear = 12;
		}

		int numberOfChildren = childIdNumbers.size();
		boolean hasSpouse = !spouseIdNumber.isEmpty();

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorkedInYear, annualDeductible, hasSpouse, numberOfChildren);
	}
