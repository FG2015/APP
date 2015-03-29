package fabertelecom.fabergroup.Models;

/**
 * Created by Aitor on 26/03/2015.
 */

public class Task {
    String name;
    String rma;
    Long start_date;
    Long end_date;
    String problem;
    String solution;
    String client_name;
    String client_phone;

    public Float getClient_latitude() {
        return client_latitude;
    }

    public void setClient_latitude(Float client_latitude) {
        this.client_latitude = client_latitude;
    }

    public Float getClient_longitude() {
        return client_longitude;
    }

    public void setClient_longitude(Float client_longitude) {
        this.client_longitude = client_longitude;
    }

    Float client_latitude;
    Float client_longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRma() {
        return rma;
    }

    public void setRma(String rma) {
        this.rma = rma;
    }

    public Long getStart_date() {
        return start_date;
    }

    public void setStart_date(Long start_date) {
        this.start_date = start_date;
    }

    public Long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Long end_date) {
        this.end_date = end_date;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

}
