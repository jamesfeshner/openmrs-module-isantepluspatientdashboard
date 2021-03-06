/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.isantepluspatientdashboard.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.isantepluspatientdashboard.api.db.IsantePlusPatientDashboardDAO;
import org.openmrs.module.isantepluspatientdashboard.mapped.FormHistory;

/**
 * It is a default implementation of {@link IsantePlusPatientDashboardDAO}.
 */
public class HibernateIsantePlusPatientDashboardDAO implements IsantePlusPatientDashboardDAO {
	protected final Log log = LogFactory.getLog(this.getClass());

	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public FormHistory getFormHistory(Integer formHistoryId) {
		return (FormHistory) getSessionFactory().getCurrentSession().get(FormHistory.class, formHistoryId);
	}

	@Override
	public FormHistory getFormHistoryByUuid(String formHistoryUuid) {
		return (FormHistory) getSessionFactory().getCurrentSession()
				.createQuery("from FormHistory fh where fh.uuid = :uuid").setString("uuid", formHistoryUuid)
				.uniqueResult();
	}

	@Override
	public void deleteFormHistory(FormHistory formHistory) {
		getSessionFactory().getCurrentSession().delete(formHistory);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FormHistory> getAllFormHistory() {
		return getSessionFactory().getCurrentSession().createCriteria(FormHistory.class).list();
	}

	@Override
	public FormHistory saveFormHistory(FormHistory formHistory) {
		sessionFactory.getCurrentSession().saveOrUpdate(formHistory);
		return formHistory;
	}
}