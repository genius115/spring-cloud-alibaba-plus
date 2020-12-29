
package com.sap.document.sap.soap.functions.mc_style;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INPUT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZFNAME" type="{urn:sap-com:document:sap:rfc:functions}char12" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "zfname"
})
@XmlRootElement(name = "ZFII00_JGJS_WS")
public class ZFII00JGJSWS_Type {

    @XmlElement(name = "INPUT")
    protected String input;
    @XmlElement(name = "ZFNAME")
    protected String zfname;

    /**
     * 获取input属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINPUT() {
        return input;
    }

    /**
     * 设置input属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINPUT(String value) {
        this.input = value;
    }

    /**
     * 获取zfname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZFNAME() {
        return zfname;
    }

    /**
     * 设置zfname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZFNAME(String value) {
        this.zfname = value;
    }

}
