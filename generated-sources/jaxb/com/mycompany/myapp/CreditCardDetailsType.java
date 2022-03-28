//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.24 at 06:26:31 PM EAT 
//


package com.mycompany.myapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 CreditCardDetailsType
 *                 Information about a Credit Card.
 *             
 * 
 * <p>Java class for CreditCardDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCardDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreditCardType" type="{urn:ebay:apis:eBLBaseComponents}CreditCardTypeType" minOccurs="0"/>
 *         &lt;element name="CreditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ExpYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CardOwner" type="{urn:ebay:apis:eBLBaseComponents}PayerInfoType" minOccurs="0"/>
 *         &lt;element name="CVV2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="StartYear" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IssueNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ThreeDSecureRequest" type="{urn:ebay:apis:eBLBaseComponents}ThreeDSecureRequestType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardDetailsType", propOrder = {
    "creditCardType",
    "creditCardNumber",
    "expMonth",
    "expYear",
    "cardOwner",
    "cvv2",
    "startMonth",
    "startYear",
    "issueNumber",
    "threeDSecureRequest"
})
public class CreditCardDetailsType {

    @XmlElement(name = "CreditCardType")
    protected CreditCardTypeType creditCardType;
    @XmlElement(name = "CreditCardNumber")
    protected String creditCardNumber;
    @XmlElement(name = "ExpMonth")
    protected Integer expMonth;
    @XmlElement(name = "ExpYear")
    protected Integer expYear;
    @XmlElement(name = "CardOwner")
    protected PayerInfoType cardOwner;
    @XmlElement(name = "CVV2")
    protected String cvv2;
    @XmlElement(name = "StartMonth")
    protected Integer startMonth;
    @XmlElement(name = "StartYear")
    protected Integer startYear;
    @XmlElement(name = "IssueNumber")
    protected String issueNumber;
    @XmlElement(name = "ThreeDSecureRequest")
    protected ThreeDSecureRequestType threeDSecureRequest;

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardTypeType }
     *     
     */
    public CreditCardTypeType getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardTypeType }
     *     
     */
    public void setCreditCardType(CreditCardTypeType value) {
        this.creditCardType = value;
    }

    /**
     * Gets the value of the creditCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the value of the creditCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
    }

    /**
     * Gets the value of the expMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpMonth() {
        return expMonth;
    }

    /**
     * Sets the value of the expMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpMonth(Integer value) {
        this.expMonth = value;
    }

    /**
     * Gets the value of the expYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpYear() {
        return expYear;
    }

    /**
     * Sets the value of the expYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpYear(Integer value) {
        this.expYear = value;
    }

    /**
     * Gets the value of the cardOwner property.
     * 
     * @return
     *     possible object is
     *     {@link PayerInfoType }
     *     
     */
    public PayerInfoType getCardOwner() {
        return cardOwner;
    }

    /**
     * Sets the value of the cardOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayerInfoType }
     *     
     */
    public void setCardOwner(PayerInfoType value) {
        this.cardOwner = value;
    }

    /**
     * Gets the value of the cvv2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVV2() {
        return cvv2;
    }

    /**
     * Sets the value of the cvv2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVV2(String value) {
        this.cvv2 = value;
    }

    /**
     * Gets the value of the startMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartMonth() {
        return startMonth;
    }

    /**
     * Sets the value of the startMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartMonth(Integer value) {
        this.startMonth = value;
    }

    /**
     * Gets the value of the startYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * Sets the value of the startYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartYear(Integer value) {
        this.startYear = value;
    }

    /**
     * Gets the value of the issueNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets the value of the issueNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueNumber(String value) {
        this.issueNumber = value;
    }

    /**
     * Gets the value of the threeDSecureRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ThreeDSecureRequestType }
     *     
     */
    public ThreeDSecureRequestType getThreeDSecureRequest() {
        return threeDSecureRequest;
    }

    /**
     * Sets the value of the threeDSecureRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeDSecureRequestType }
     *     
     */
    public void setThreeDSecureRequest(ThreeDSecureRequestType value) {
        this.threeDSecureRequest = value;
    }

}
