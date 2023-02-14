package kr.edu.mit;

import java.util.Date;

public class SalesVO {
	private String fruit_name;
	private int fruit_code;
	private Date sales_date;
	private int sales_quantity;//Integer-객체로 선언 (널값이 들어갈 수 있다. ) , int는 기본값이 0이기 때문에 널값이 매핑 안된다. 널인지 0인지 구분못함
	@Override
	public String toString() {
		return "과일: " + fruit_name + ", fruit_code=" + fruit_code + ", sales_date=" + sales_date
				+ ", sales_quantity=" + sales_quantity + ", total=" + total + ", getFruit_name()=" + getFruit_name()
				+ ", getFruit_code()=" + getFruit_code() + ", getSales_date()=" + getSales_date()
				+ ", getSales_quantity()=" + getSales_quantity() + ", getTotal()=" + getTotal() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	private int total;

	public String getFruit_name() {
		return fruit_name;
	}

	public void setFruit_name(String fruit_name) {
		this.fruit_name = fruit_name;
	}

	public int getFruit_code() {
		return fruit_code;
	}

	public void setFruit_code(int fruit_code) {
		this.fruit_code = fruit_code;
	}

	public Date getSales_date() {
		return sales_date;
	}

	public void setSales_date(Date sales_date) {
		this.sales_date = sales_date;
	}

	public int getSales_quantity() {
		return sales_quantity;
	}


	public void setSales_quantity(int sales_quantity) {
		this.sales_quantity = sales_quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
