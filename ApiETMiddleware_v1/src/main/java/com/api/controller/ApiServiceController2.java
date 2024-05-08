package com.api.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.configuration.ReadExternlProperty;
import com.api.servicecall.PurchasedSupplementaryOffering;
import com.api.servicecall.UnsubscribedRecurringPackage;
import com.api.servicecall.VoucherCardRecharge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ApiServiceController2 {

	@Autowired
	ReadExternlProperty oProFile;

	
	private static final Logger log = LoggerFactory.getLogger("ApiServiceController");
	
	
	//complete@
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/VoucherCardRecharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> voucherCardRecharge(@RequestBody String obj) {

		HashMap<String, Object> respJson = new HashMap<String, Object>();

		try {

			log.info("\n******** VoucherCardRecharge ***********");

			log.info("Request input :" + obj.toString());

			oProFile.reloadPropertyIfChanged(log);

			LinkedHashMap<String, Object> request = null;

			request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

			VoucherCardRecharge oVoucherCardRecharge = new VoucherCardRecharge();

			log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

			respJson = oVoucherCardRecharge.voucherCardRechargeImp(request, log);

			log.info("Final outputData : " + respJson);

			return respJson;
		} catch (Exception e) {
			log.error("Exception VoucherCardRecharge API : " + e);
			respJson.put("StatusCode", "000");
			respJson.put("StatusDescription", "Failure");
	
			return respJson;
		}
	}
	
	
		//complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/PurchasedSupplementaryOffering", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object> purchasedSupplementaryOffering(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				log.info("\n******** PurchasedSupplementaryOffering ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				PurchasedSupplementaryOffering oPurchasedSupplementaryOffering = new PurchasedSupplementaryOffering();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = oPurchasedSupplementaryOffering.purchasedSupplementaryOfferingImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception PurchasedSupplementaryOffering API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
		
		
		//complete@
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/UnsubscribedRecurringPackage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public HashMap<String, Object>  unsubscribedRecurringPackage(@RequestBody String obj) {

			HashMap<String, Object> respJson = new HashMap<String, Object>();

			try {

				log.info("\n******** UnsubscribedRecurringPackage ***********");

				log.info("Request input :" + obj.toString());

				oProFile.reloadPropertyIfChanged(log);

				LinkedHashMap<String, Object> request = null;

				request = new ObjectMapper().readValue(obj, LinkedHashMap.class);

				UnsubscribedRecurringPackage oUnsubscribedRecurringPackage = new UnsubscribedRecurringPackage();

				log.info("Request validate ucid_id :" + request.get("ucid_id").toString());

				respJson = oUnsubscribedRecurringPackage.unsubscribedRecurringPackageImp(request, log);

				log.info("Final outputData : " + respJson);

				return respJson;
			} catch (Exception e) {
				log.error("Exception UnsubscribedRecurringPackage API : " + e);
				respJson.put("StatusCode", "000");
				respJson.put("StatusDescription", "Failure");
		
				return respJson;
			}
		}
}
