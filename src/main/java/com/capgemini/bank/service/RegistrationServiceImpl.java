/**
 * 
 */
package com.capgemini.bank.service;

import com.capgemini.bank.Dao.IRegistrationDao;
import com.capgemini.bank.Dao.RegistrationDaoImpl;
import com.capgemini.bank.bean.Registration;

/**
 * @author VJIT
 *
 */
public class RegistrationServiceImpl implements IRegistrationService {

	IRegistrationDao RegistrationDao= new RegistrationDaoImpl();
	public void login() {
		RegistrationDao.login();
		
		
	}

	public Integer register(Registration registration) {
		
		
		return RegistrationDao.register(registration);
	}


}
