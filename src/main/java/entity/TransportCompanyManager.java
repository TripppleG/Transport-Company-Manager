package entity;

import java.util.Set;

public class TransportCompanyManager {
    private Set<TransportCompany> transportCompanies;

    void addCompany(TransportCompany transportCompany) {
        boolean result = transportCompanies.add(transportCompany);
        if (!result){
            throw new ArrayStoreException("The transport company already exist!");
        }
    }
}
