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
 * <p>Java class for UpdateRecurringPaymentsProfileResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateRecurringPaymentsProfileResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ebay:apis:eBLBaseComponents}UpdateRecurringPaymentsProfileResponseDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateRecurringPaymentsProfileResponseType", namespace = "urn:ebay:api:PayPalAPI", propOrder = {
    "updateRecurringPaymentsProfileResponseDetails"
})
public class UpdateRecurringPaymentsProfileResponseType
    extends AbstractResponseType
{

    @XmlElement(name = "UpdateRecurringPaymentsProfileResponseDetails", namespace = "urn:ebay:apis:eBLBaseComponents", required = true)
    protected UpdateRecurringPaymentsProfileResponseDetailsType updateRecurringPaymentsProfileResponseDetails;

    /**
     * Gets the value of the updateRecurringPaymentsProfileResponseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateRecurringPaymentsProfileResponseDetailsType }
     *     
     */
    public UpdateRecurringPaymentsProfileResponseDetailsType getUpdateRecurringPaymentsProfileResponseDetails() {
        return updateRecurringPaymentsProfileResponseDetails;
    }

    /**
     * Sets the value of the updateRecurringPaymentsProfileResponseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateRecurringPaymentsProfileResponseDetailsType }
     *     
     */
    public void setUpdateRecurringPaymentsProfileResponseDetails(UpdateRecurringPaymentsProfileResponseDetailsType value) {
        this.updateRecurringPaymentsProfileResponseDetails = value;
    }

}
