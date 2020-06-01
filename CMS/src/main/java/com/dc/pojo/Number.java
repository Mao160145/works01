package com.dc.pojo;

import javafx.scene.chart.PieChart;

import java.util.Date;

public class Number {
   private int countNumber;
   private Date dateTime;

    public int getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(int countNumber) {
        this.countNumber = countNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Number{");
        sb.append("countNumber=").append(countNumber);
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }
}
