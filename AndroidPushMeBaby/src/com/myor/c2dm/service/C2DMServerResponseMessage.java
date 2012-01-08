package com.myor.c2dm.service;

public class C2DMServerResponseMessage {
	private String statusLine;
	private String content;

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "server response from c2dm cloud server [statusLine : {"
				+ statusLine + "} , content : {" + content + "}";
	}

}
