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
 * <p>Java class for DoUATPExpressCheckoutPaymentResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoUATPExpressCheckoutPaymentResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:api:PayPalAPI}DoExpressCheckoutPaymentResponseType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ebay:apis:eBLBaseComponents}UATPDetails"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoUATPExpressCheckoutPaymentResponseType", namespace = "urn:ebay:api:PayPalAPI", propOrder = {
    "uatpDetails"
})
public class DoUATPExpressCheckoutPaymentResponseType
    extends DoExpressCheckoutPaymentResponseType
{

    @XmlElement(name = "UATPDetails", namespace = "urn:ebay:apis:eBLBaseComponents", required = true)
    protected UATPDetailsType uatpDetails;

    /**
     * Gets the value of the uatpDetails property.
     * 
     * @return
     *     possible object is
     *     {@link UATPDetailsType }
     *     
     */
    public UATPDetailsType getUATPDetails() {
        return uatpDetails;
    }

    /**
     * Sets the value of the uatpDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link UATPDetailsType }
     *     
     */
    public void setUATPDetails(UATPDetailsType value) {
        this.uatpDetails = value;
    }

}
