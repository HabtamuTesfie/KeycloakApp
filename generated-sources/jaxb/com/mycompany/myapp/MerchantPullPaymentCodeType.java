//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.24 at 06:26:31 PM EAT 
//


package com.mycompany.myapp;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MerchantPullPaymentCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MerchantPullPaymentCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Any"/>
 *     &lt;enumeration value="InstantOnly"/>
 *     &lt;enumeration value="EcheckOnly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MerchantPullPaymentCodeType")
@XmlEnum
public enum MerchantPullPaymentCodeType {

    @XmlEnumValue("Any")
    ANY("Any"),
    @XmlEnumValue("InstantOnly")
    INSTANT_ONLY("InstantOnly"),
    @XmlEnumValue("EcheckOnly")
    ECHECK_ONLY("EcheckOnly");
    private final String value;

    MerchantPullPaymentCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MerchantPullPaymentCodeType fromValue(String v) {
        for (MerchantPullPaymentCodeType c: MerchantPullPaymentCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
