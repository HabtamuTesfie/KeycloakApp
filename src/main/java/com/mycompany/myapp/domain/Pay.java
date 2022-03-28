package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Pay.
 */
@Entity
@Table(name = "pay")
public class Pay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "cik")
    private String cik;

    @Column(name = "ccc")
    private String ccc;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "approval")
    private String approval;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Pay id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCik() {
        return this.cik;
    }

    public Pay cik(String cik) {
        this.setCik(cik);
        return this;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public String getCcc() {
        return this.ccc;
    }

    public Pay ccc(String ccc) {
        this.setCcc(ccc);
        return this;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public Double getPaymentAmount() {
        return this.paymentAmount;
    }

    public Pay paymentAmount(Double paymentAmount) {
        this.setPaymentAmount(paymentAmount);
        return this;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getName() {
        return this.name;
    }

    public Pay name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public Pay email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Pay phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApproval() {
        return this.approval;
    }

    public Pay approval(String approval) {
        this.setApproval(approval);
        return this;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pay)) {
            return false;
        }
        return id != null && id.equals(((Pay) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pay{" +
            "id=" + getId() +
            ", cik='" + getCik() + "'" +
            ", ccc='" + getCcc() + "'" +
            ", paymentAmount=" + getPaymentAmount() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", approval='" + getApproval() + "'" +
            "}";
    }
}
