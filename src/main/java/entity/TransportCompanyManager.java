package entity;

import dao.TransportCompanyDAO;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TransportCompanyManager {
    private Set<TransportCompany> transportCompanies;

    public TransportCompanyManager(Set<TransportCompany> transportCompanies) {
        this.transportCompanies = transportCompanies;
    }

    public TransportCompanyManager() {
        this.transportCompanies = new TreeSet<>();
    }

    public void addTransportCompany(TransportCompany transportCompany){
        transportCompanies.add(transportCompany);
        TransportCompanyDAO.saveOrUpdateTransportCompanies(transportCompanies);
    }

    public void removeTransportCompany(String bulstat){
        for (TransportCompany transportCompany : transportCompanies) {
            if (transportCompany.getBulstat().equals(bulstat)) {
                transportCompanies.remove(transportCompany);
                TransportCompanyDAO.saveOrUpdateTransportCompanies(transportCompanies);
                return;
            }
        }
        throw new NoSuchElementException("No company with bulstat " + bulstat + "exists!");
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
}