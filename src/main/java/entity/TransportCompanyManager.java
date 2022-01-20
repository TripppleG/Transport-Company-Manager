package entity;

import javassist.bytecode.DuplicateMemberException;

import javax.persistence.Entity;
import java.util.TreeSet;


public class TransportCompanyManager {
    private TreeSet<TransportCompany> transportCompanies;

    void addCompany(TransportCompany transportCompany) {
        boolean result = transportCompanies.add(transportCompany);
        if (!result){
            throw new ArrayStoreException("The transport company already exist!");
        }
    }
}
