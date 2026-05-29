package org.example.lab6_resuelto.beans;

public class Employee {

    // ── Atributos (según columnas de la tabla employees) ──
    private int employeeId;
    // CORRECCIÓN: Se eliminó 'fullNameEmployee' y se reemplazó por 'firstName' y 'lastName'
    // por separado. Esto se hizo porque en los nombres compuestos (ej. "Juan Carlos"),
    // la lógica antigua que los separaba por espacios rompía el formulario.
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String hireDate;
    private String jobId;
    private double salary;
    private double commissionPct;
    private int managerId;
    private int departmentId;
    private int enabled;

    // ── Constructor vacío (obligatorio en Beans) ──
    public Employee() {
    }

    // contructores con parametros
    public Employee(int employeeId, String firstName, String lastName, String email,
                    String password, String phoneNumber, String hireDate,
                    String jobId, double salary, double commissionPct,
                    int managerId, int departmentId, int enabled) {
        this.employeeId      = employeeId;
        this.firstName       = firstName;
        this.lastName        = lastName;
        this.email           = email;
        this.password        = password;
        this.phoneNumber     = phoneNumber;
        this.hireDate        = hireDate;
        this.jobId           = jobId;
        this.salary          = salary;
        this.commissionPct   = commissionPct;
        this.managerId       = managerId;
        this.departmentId    = departmentId;
        this.enabled         = enabled;
    }

    // ── Getters ──
    public int getEmployeeId()          { return employeeId; }
    public String getFirstName()        { return firstName; }
    public String getLastName()         { return lastName; }
    public String getEmail()            { return email; }
    public String getPassword()         { return password; }
    public String getPhoneNumber()      { return phoneNumber; }
    public String getHireDate()         { return hireDate; }
    public String getJobId()            { return jobId; }
    public double getSalary()           { return salary; }
    public double getCommissionPct()    { return commissionPct; }
    public int getManagerId()           { return managerId; }
    public int getDepartmentId()        { return departmentId; }
    public int getEnabled()             { return enabled; }

    // ── Setters ──
    public void setEmployeeId(int employeeId)             { this.employeeId = employeeId; }
    public void setFirstName(String firstName)            { this.firstName = firstName; }
    public void setLastName(String lastName)              { this.lastName = lastName; }
    public void setEmail(String email)                    { this.email = email; }
    public void setPassword(String password)              { this.password = password; }
    public void setPhoneNumber(String phoneNumber)        { this.phoneNumber = phoneNumber; }
    public void setHireDate(String hireDate)              { this.hireDate = hireDate; }
    public void setJobId(String jobId)                    { this.jobId = jobId; }
    public void setSalary(double salary)                  { this.salary = salary; }
    public void setCommissionPct(double commissionPct)    { this.commissionPct = commissionPct; }
    public void setManagerId(int managerId)               { this.managerId = managerId; }
    public void setDepartmentId(int departmentId)         { this.departmentId = departmentId; }
    public void setEnabled(int enabled)                   { this.enabled = enabled; }










}

