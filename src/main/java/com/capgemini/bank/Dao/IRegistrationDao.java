package com.capgemini.bank.Dao;

import com.capgemini.bank.bean.Registration;

public interface IRegistrationDao {
	public void login();
	public Integer register(Registration registration);
}
