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
 * <p>Java class for GetBillingAgreementCustomerDetailsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetBillingAgreementCustomerDetailsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ebay:apis:eBLBaseComponents}GetBillingAgreementCustomerDetailsResponseDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetBillingAgreementCustomerDetailsResponseType", namespace = "urn:ebay:api:PayPalAPI", propOrder = {
    "getBillingAgreementCustomerDetailsResponseDetails"
})
public class GetBillingAgreementCustomerDetailsResponseType
    extends AbstractResponseType
{

    @XmlElement(name = "GetBillingAgreementCustomerDetailsResponseDetails", namespace = "urn:ebay:apis:eBLBaseComponents", required = true)
    protected GetBillingAgreementCustomerDetailsResponseDetailsType getBillingAgreementCustomerDetailsResponseDetails;

    /**
     * Gets the value of the getBillingAgreementCustomerDetailsResponseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GetBillingAgreementCustomerDetailsResponseDetailsType }
     *     
     */
    public GetBillingAgreementCustomerDetailsResponseDetailsType getGetBillingAgreementCustomerDetailsResponseDetails() {
        return getBillingAgreementCustomerDetailsResponseDetails;
    }

    /**
     * Sets the value of the getBillingAgreementCustomerDetailsResponseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetBillingAgreementCustomerDetailsResponseDetailsType }
     *     
     */
    public void setGetBillingAgreementCustomerDetailsResponseDetails(GetBillingAgreementCustomerDetailsResponseDetailsType value) {
        this.getBillingAgreementCustomerDetailsResponseDetails = value;
    }

}
