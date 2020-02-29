package schema_classes;

public class Company {
    private String company;
    private int company_size;
    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCompany_size() {
        return company_size;
    }

    public void setCompany_size(int company_size) {
        this.company_size = company_size;
    }

    public Company() {

    }
}
