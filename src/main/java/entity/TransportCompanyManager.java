package entity;

import dao.TransportCompanyDAO;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "transport_company_list")
public class TransportCompanyManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToMany
    @Column(name = "transport_companies", nullable = false)
    private Set<TransportCompany> transportCompanies;

    public Long getId() {
        return id;
    }

    public TransportCompanyManager(Set<TransportCompany> transportCompanies) {
        this.transportCompanies = transportCompanies;
    }

    public TransportCompanyManager() {
        this.transportCompanies = new TreeSet<>();
    }

    public Set<TransportCompany> getTransportCompanies() {
        return transportCompanies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportCompanyManager that)) return false;
        return transportCompanies.equals(that.transportCompanies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportCompanies);
    }

    public void addTransportCompany(TransportCompany transportCompany) {
        TransportCompanyDAO.saveTransportCompany(transportCompany);
    }
}