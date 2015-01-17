package com.laima.maimm.global.util;

/**
 * 结果封装
 * 
 * @author jiayi.zhang
 * 
 * @param <T>
 */
public class ResultBean<T> {
	/* 返回主体 */
	private T result;
	/* 操作是否成功 */
	private String status;
	/* 错误描述 */
	private String description;
	/* 备注 */
	private String mark;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		switch (status) {
		case SUCCESS:
			this.status = "SUCCESS";
			break;
		case FAIL:
			this.status = "FAIL";
			break;
		case ERROR:
			this.status = "ERROR";
			break;
		default:
			this.status = "ERROR";
			break;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	

}
