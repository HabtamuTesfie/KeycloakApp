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
 * <p>Java class for BillOutstandingAmountResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillOutstandingAmountResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ebay:apis:eBLBaseComponents}BillOutstandingAmountResponseDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillOutstandingAmountResponseType", namespace = "urn:ebay:api:PayPalAPI", propOrder = {
    "billOutstandingAmountResponseDetails"
})
public class BillOutstandingAmountResponseType
    extends AbstractResponseType
{

    @XmlElement(name = "BillOutstandingAmountResponseDetails", namespace = "urn:ebay:apis:eBLBaseComponents", required = true)
    protected BillOutstandingAmountResponseDetailsType billOutstandingAmountResponseDetails;

    /**
     * Gets the value of the billOutstandingAmountResponseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BillOutstandingAmountResponseDetailsType }
     *     
     */
    public BillOutstandingAmountResponseDetailsType getBillOutstandingAmountResponseDetails() {
        return billOutstandingAmountResponseDetails;
    }

    /**
     * Sets the value of the billOutstandingAmountResponseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillOutstandingAmountResponseDetailsType }
     *     
     */
    public void setBillOutstandingAmountResponseDetails(BillOutstandingAmountResponseDetailsType value) {
        this.billOutstandingAmountResponseDetails = value;
    }

}