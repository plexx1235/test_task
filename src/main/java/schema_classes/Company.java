package schema_classes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "company",
        "company_size",
        "customer"
})
public class Company {

    @JsonProperty("company")
    private String company;
    @JsonProperty("company_size")
    private long companySize;
    @JsonProperty("customer")
    private Customer customer;

    /**
     * No args constructor for use in serialization
     *
     */
    public Company() {
    }

    /**
     *
     * @param company
     * @param companySize
     * @param customer
     */
    public Company(String company, long companySize, Customer customer) {
        this.company = company;
        this.companySize = companySize;
        this.customer = customer;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("company_size")
    public long getCompanySize() {
        return companySize;
    }

    @JsonProperty("company_size")
    public void setCompanySize(long companySize) {
        this.companySize = companySize;
    }

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("company", company)
                .append("companySize", companySize)
                .append("customer", customer).toString();
    }
}