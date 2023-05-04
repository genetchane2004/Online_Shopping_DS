/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Conor
 */
@Entity
@Table(name = "PURCHASES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchases.findAll", query = "SELECT p FROM Purchases p")
    , @NamedQuery(name = "Purchases.findByPurchaseId", query = "SELECT p FROM Purchases p WHERE p.purchaseId = :purchaseId")
    , @NamedQuery(name = "Purchases.findByTime", query = "SELECT p FROM Purchases p WHERE p.time = :time")
    , @NamedQuery(name = "Purchases.findByCustomerId", query = "SELECT p FROM Purchases p WHERE p.customerId = :customerId")
    , @NamedQuery(name = "Purchases.findByCustomerName", query = "SELECT p FROM Purchases p WHERE p.customerName = :customerName")
    , @NamedQuery(name = "Purchases.findByPurchaseAmount", query = "SELECT p FROM Purchases p WHERE p.purchaseAmount = :purchaseAmount")})
public class Purchases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PURCHASE_ID")
    private Integer purchaseId;
    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private int customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PURCHASE_AMOUNT")
    private int purchaseAmount;

    public Purchases() {
    }

    public Purchases(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Purchases(Integer purchaseId, int customerId, String customerName, int purchaseAmount) {
        this.purchaseId = purchaseId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseId != null ? purchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchases)) {
            return false;
        }
        Purchases other = (Purchases) object;
        if ((this.purchaseId == null && other.purchaseId != null) || (this.purchaseId != null && !this.purchaseId.equals(other.purchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DB_Entities.Purchases[ purchaseId=" + purchaseId + " ]";
    }
    
}
