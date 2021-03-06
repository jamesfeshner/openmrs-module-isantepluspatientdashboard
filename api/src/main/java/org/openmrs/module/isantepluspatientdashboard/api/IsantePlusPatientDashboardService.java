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
package org.openmrs.module.isantepluspatientdashboard.api;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.isantepluspatientdashboard.ChartJSAgeAxis;
import org.openmrs.module.isantepluspatientdashboard.liquibase.InitialiseFormsHistory;
import org.openmrs.module.isantepluspatientdashboard.mapped.FormHistory;
import org.springframework.transaction.annotation.Transactional;

import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;

/**
 * This service exposes module's core functionality. It is a Spring managed bean
 * which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(IsantePlusPatientDashboardService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface IsantePlusPatientDashboardService extends OpenmrsService {

	Obs getLastViralLoadTestResultObsForPatient(Patient patient);

	JSONArray getPatientWeights(Patient patient);

	JSONArray getPatientHeights(Patient patient);

	Integer getPatientAgeInMonths(Patient patient);

	Integer getPatientAgeInDays(Patient patient);

	JSONArray getHeightsAtGivenPatientAges(Patient patient, ChartJSAgeAxis ageAxis);

	JSONArray getWeightsAtGivenPatientAges(Patient patient, ChartJSAgeAxis ageAxis);

	JSONArray getPatientHeadCircumferences(Patient patient);

	JSONArray getHeadCircumferenceAtGivenPatientAges(Patient patient, ChartJSAgeAxis ageAxis);

	/**
	 * Should only be run by {@link InitialiseFormsHistory}
	 * @throws SQLException 
	 * @throws DatabaseException 
	 */
	void runInitialHistoryCreatorAgainstDB(JdbcConnection connection) throws DatabaseException, SQLException;

	FormHistory getFormHistory(Integer formHistoryId);

	FormHistory getFormHistoryByUuid(String formHistoryUuid);

	void deleteFormHistory(FormHistory formHistory);

	List<FormHistory> getAllFormHistory();

	FormHistory saveFormHistory(FormHistory formHistory);

	boolean formHistoryExist(FormHistory formHistory, List<FormHistory> formHistoryLookup);

	List<FormHistory> getOnlyIsanteFormHistories();

	List<FormHistory> getOnlyIsanteFormHistories(Visit visit);

	List<FormHistory> getAllFormHistory(Visit visit);

	FormHistory createBasicFormHistoryObject(Encounter encounter, boolean contextUp);
}