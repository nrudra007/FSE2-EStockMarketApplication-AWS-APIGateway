package com.cts.fsebkend.apigatewayserver.models;

public class Company {

	private String id;
	private String companyCode;
	private String companyName;
	private String companyCeoName;
	private long companyTurnover;
	private String companyWebsite;
	private String companyStockExchange;
	private double latestStockPrice;
	
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
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the companyCeoName
	 */
	public String getCompanyCeoName() {
		return companyCeoName;
	}
	/**
	 * @param companyCeoName the companyCeoName to set
	 */
	public void setCompanyCeoName(String companyCeoName) {
		this.companyCeoName = companyCeoName;
	}
	/**
	 * @return the companyTurnover
	 */
	public long getCompanyTurnover() {
		return companyTurnover;
	}
	/**
	 * @param companyTurnover the companyTurnover to set
	 */
	public void setCompanyTurnover(long companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	/**
	 * @return the companyWebsite
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	/**
	 * @param companyWebsite the companyWebsite to set
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	/**
	 * @return the companyStockExchange
	 */
	public String getCompanyStockExchange() {
		return companyStockExchange;
	}
	/**
	 * @param companyStockExchange the companyStockExchange to set
	 */
	public void setCompanyStockExchange(String companyStockExchange) {
		this.companyStockExchange = companyStockExchange;
	}
	/**
	 * @return the latestStockPrice
	 */
	public double getLatestStockPrice() {
		return latestStockPrice;
	}

	/**
	 * @param latestStockPrice the latestStockPrice to set
	 */
	public void setLatestStockPrice(double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (companyCode != null) {
			builder.append("companyCode=");
			builder.append(companyCode);
			builder.append(", ");
		}
		if (companyName != null) {
			builder.append("companyName=");
			builder.append(companyName);
			builder.append(", ");
		}
		if (companyCeoName != null) {
			builder.append("companyCeoName=");
			builder.append(companyCeoName);
			builder.append(", ");
		}
		builder.append("companyTurnover=");
		builder.append(companyTurnover);
		builder.append(", ");
		if (companyWebsite != null) {
			builder.append("companyWebsite=");
			builder.append(companyWebsite);
			builder.append(", ");
		}
		if (companyStockExchange != null) {
			builder.append("companyStockExchange=");
			builder.append(companyStockExchange);
			builder.append(", ");
		}
		builder.append("latestStockPrice=");
		builder.append(latestStockPrice);
		builder.append("]");
		return builder.toString();
	}
}