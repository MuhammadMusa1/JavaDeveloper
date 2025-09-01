package com.example.iso20022;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PmtInf")
public class PaymentInfo {
    @XmlElement(name = "PmtId")
    private String paymentId;

    @XmlElement(name = "Amt")
    private double amount;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentInfo{paymentId='" + paymentId + "', amount=" + amount + "}";
    }
}