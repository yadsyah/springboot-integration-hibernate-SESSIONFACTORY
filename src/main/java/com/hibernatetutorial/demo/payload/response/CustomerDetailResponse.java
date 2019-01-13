package com.hibernatetutorial.demo.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * CustomerDetailResponse
 */
public class CustomerDetailResponse {
    @JsonIgnore
    private long customerDetailId;
    private String noKTP;
    private String jenisKelamin;
    private boolean isAdult;
    private String tglLahir;

    public CustomerDetailResponse() {
    }
	/**
	 * @return the customerDetailId
	 */
	public long getCustomerDetailId() {
		return customerDetailId;
	}
	/**
	 * @param customerDetailId the customerDetailId to set
	 */
	public void setCustomerDetailId(long customerDetailId) {
		this.customerDetailId = customerDetailId;
	}
	/**
	 * @return the noKTP
	 */
	public String getNoKTP() {
		return noKTP;
	}
	/**
	 * @param noKTP the noKTP to set
	 */
	public void setNoKTP(String noKTP) {
		this.noKTP = noKTP;
	}
	/**
	 * @return the jenisKelamin
	 */
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	/**
	 * @param jenisKelamin the jenisKelamin to set
	 */
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	/**
	 * @return the isAdult
	 */
	public boolean isAdult() {
		return isAdult;
	}
	/**
	 * @param isAdult the isAdult to set
	 */
	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}
	/**
	 * @return the tglLahir
	 */
	public String getTglLahir() {
		return tglLahir;
	}
	/**
	 * @param tglLahir the tglLahir to set
	 */
	public void setTglLahir(String tglLahir) {
		this.tglLahir = tglLahir;
	}



}