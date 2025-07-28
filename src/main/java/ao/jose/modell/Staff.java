package ao.jose.modell;

public class Staff {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private BigDecimal salary;

    public staff(String name, String email, String phoneNumber, BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
}
