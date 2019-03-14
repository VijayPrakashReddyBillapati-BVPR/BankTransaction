package com.capgemini.bank.service;

import com.capgemini.bank.bean.Registration;

public interface IRegistrationService {
	public Integer register(Registration registration);
	public void login();

}
