package org.example.lab6_resuelto.beans;

public class Job {

    // ── Atributos (según columnas de la tabla jobs) ──
    private String jobId;
    private String jobTitle;
    private int minSalary;
    private int maxSalary;

    // ── Constructor vacío (obligatorio en Beans) ──
    public Job() {
    }

    // ── Constructor con parámetros ──
    public Job(String jobId, String jobTitle, int minSalary, int maxSalary) {
        this.jobId     = jobId;
        this.jobTitle  = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    // ── Getters ──
    public String getJobId()    { return jobId; }
    public String getJobTitle() { return jobTitle; }
    public int getMinSalary()   { return minSalary; }
    public int getMaxSalary()   { return maxSalary; }

    // ── Setters ──
    public void setJobId(String jobId)       { this.jobId = jobId; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setMinSalary(int minSalary)  { this.minSalary = minSalary; }
    public void setMaxSalary(int maxSalary)  { this.maxSalary = maxSalary; }
}