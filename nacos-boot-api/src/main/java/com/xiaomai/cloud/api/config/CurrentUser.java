package com.xiaomai.cloud.api.config;

public class CurrentUser {
	private Long userId;
    private String name;
    private String username;
    private Long accountId;
    private String accountitemcode;
    private String accountuserid;
    private String accountusername;

    public Long getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }  

    public String getAccountitemcode() {
		return accountitemcode;
	}

	public void setAccountitemcode(String accountitemcode) {
		this.accountitemcode = accountitemcode;
	}

	public String getAccountuserid() {
		return accountuserid;
	}

	public void setAccountuserid(String accountuserid) {
		this.accountuserid = accountuserid;
	}

	public String getAccountusername() {
		return accountusername;
	}

	public void setAccountusername(String accountusername) {
		this.accountusername = accountusername;
	}

	public CurrentUser(Long userId, String name, String username, Long accountId) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.accountId = accountId;
    }

    public CurrentUser() {
    }
}
