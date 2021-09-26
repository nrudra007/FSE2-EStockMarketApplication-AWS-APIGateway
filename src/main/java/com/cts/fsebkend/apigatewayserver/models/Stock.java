package com.cts.fsebkend.apigatewayserver.models;

public class Stock {
	
    private String id;
    private String date;
    private String time;
    private Double price;
    private String companyCode;
    
	/**
	 * No arg constructor
	 */
	public Stock() {
		super();
	}
	
	/**
	 * @param id
	 * @param date
	 * @param time
	 * @param price
	 * @param companyCode
	 */
	public Stock(String id, String date, String time, Double price, String companyCode) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.price = price;
		this.companyCode = companyCode;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (date != null) {
			builder.append("date=");
			builder.append(date);
			builder.append(", ");
		}
		if (time != null) {
			builder.append("time=");
			builder.append(time);
			builder.append(", ");
		}
		if (price != null) {
			builder.append("price=");
			builder.append(price);
			builder.append(", ");
		}
		if (companyCode != null) {
			builder.append("companyCode=");
			builder.append(companyCode);
		}
		builder.append("]");
		return builder.toString();
	}
}