package com.api.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


import org.springframework.stereotype.Repository;

import com.api.util.FileOperations;

import org.slf4j.Logger;

@Repository
public class ReadExternlProperty {

	
	private static long LastModifiedTimeconfigPropFile = 0;

	private static File configPropertyFile = null;
	 
	public void readProperty(Logger log)
	{
		
		this.LoadFilePath(Config.EXTERNAL_FILEPATH,log);

		this.readExternalProperty(log);		

	}
	/*
	 * set external property file time
	 */	
	public void LoadFilePath(String propertyFilepath,Logger log) 
	{
		configPropertyFile = new File(propertyFilepath);
		LastModifiedTimeconfigPropFile = configPropertyFile.lastModified();
		
		System.out.println("Load time : "+LastModifiedTimeconfigPropFile);

	}
		
	
	/* check external property file  modified time */
	public synchronized void reloadPropertyIfChanged(Logger log)
	{

			ReadExternlProperty oRead = new ReadExternlProperty();
			
		
//			long currentLastModifiedTime = configPropertyFile.lastModified();
//		
//			
//			if (currentLastModifiedTime > LastModifiedTimeconfigPropFile && currentLastModifiedTime != 0) 
//			{
//				LastModifiedTimeconfigPropFile = currentLastModifiedTime;
//				oRead.readExternalProperty(log);				
//			}
			
			
			if(configPropertyFile == null && LastModifiedTimeconfigPropFile==0) {
				oRead.readExternalProperty(log);
				
			}
			else {									

				long currentLastModifiedTime = configPropertyFile.lastModified();
				
				if (currentLastModifiedTime > LastModifiedTimeconfigPropFile && currentLastModifiedTime != 0) 
				{
					LastModifiedTimeconfigPropFile = currentLastModifiedTime;
					oRead.readExternalProperty(log);
					
				}
				
			 }
			
			
	}
	

	public void readExternalProperty(Logger log)
	{
		
		try 
		{
			log.info("Reading configuration from External property file !!");
			Properties oProp = new Properties();
	    	FileInputStream file;

	    	//AesAlgorithom oAes = new AesAlgorithom();
	    	
	    	file = new FileInputStream(Config.EXTERNAL_FILEPATH);
			oProp.load(file);
		    file.close();		    

		    log.debug("TEST DATA : " + (String) oProp.getProperty("Test_data"));
		    
		    //Encryprion key
		    Config.ENCRYPTION_KEY										=	(String) oProp.getProperty("encryptionKey");
		   
		    Config.APILOGINUSERNAME										=	(String) oProp.getProperty("apiLoginUsername");
		    Config.APILOGINPASSWORD										=	(String) oProp.getProperty("apiLoginPassword");
		    Config.APIOPERATORID										=	(String) oProp.getProperty("operatorId");
		    Config.APICHANNELID											=	(String) oProp.getProperty("channelId");
		    Config.API_CONNECTION_TIMEOUT								=	(String) oProp.getProperty("apiConnectionTimeOut");
		    Config.API_READ_TIMEOUT										=	(String) oProp.getProperty("apiReadTimeOut");

			
		    //Request URL       
		    Config.GET_SUBSCRIBER_URL 									=   (String) oProp.getProperty("getSubscriberUrl");		
		    Config.CHANGE_SUBINFO_URL                                   =   (String) oProp.getProperty("changeSubInfoUrl");
		    Config.GET_CUSTOMER_URL                                     =   (String) oProp.getProperty("getCustomerUrl");
		    Config.CHANGE_SUPPLEMENTARY_OFFERING_URL                    =   (String) oProp.getProperty("changeSupplementaryOfferingUrl");
		    Config.SUB_ACTIVATION_URL                   				=   (String) oProp.getProperty("subActivationUrl");
		    Config.CHECK_PUK_URL                   						=   (String) oProp.getProperty("checkPUKUrl");
		    Config.REPORT_LOST_URL                   					=   (String) oProp.getProperty("reportLostUrl");
		    Config.CANCEL_LOST_URL                   					=   (String) oProp.getProperty("cancelLostUrl");
		    Config.QUERY_UNBILLED_AMOUNT_URL                   			=   (String) oProp.getProperty("queryUnbilledAmountUrl");
		    Config.LOYALTY_SERVICE_REGISTERATION_URL                   	=   (String) oProp.getProperty("loyaltyServiceRegistrationUrl");
		    Config.QUERY_POINT_BALANCE_URL                   			=   (String) oProp.getProperty("queryPointBalanceUrl");
		    Config.TRANSFER_POINT_URL                   				=   (String) oProp.getProperty("transferPointUrl");
		    Config.QUERY_VOUCHER_URL                   					=   (String) oProp.getProperty("queryVoucherUrl");
		    Config.QUERY_RECHARGE_LOG_URL                   			=   (String) oProp.getProperty("queryRechargeLogUrl");
		    Config.QUERY_INVOICE_URL                   					=   (String) oProp.getProperty("queryInvoiceUrl");
		    Config.CHECK_PASSWORD_URL                   				=   (String) oProp.getProperty("checkPasswordUrl");
		    Config.CHECK_SUB_PASSWORD_URL                   			=   (String) oProp.getProperty("checkSubPasswordUrl");
		    Config.REEDEM_COMMODITY_URL                   				=   (String) oProp.getProperty("redeemCommodityUrl");
		    Config.QUERY_BALANCE_URL                   					=   (String) oProp.getProperty("queryBalanceUrl");
		    Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_URL               =   (String) oProp.getProperty("getCustomerDataViaAccountCodeUrl");
		    Config.CHANGE_SUB_STATUS_URL               					=   (String) oProp.getProperty("changeSubStatusUrl");
		    Config.PRESENT_SERVICE_GIFT_URL               				=   (String) oProp.getProperty("presentServiceGiftUrl");
		    Config.QUERY_FREE_UNIT_URL               					=   (String) oProp.getProperty("queryFreeUnitUrl");
		    Config.GET_GROUP_MEMBER_DATA_URL               			    =   (String) oProp.getProperty("getGroupMemberDataUrl");
		    Config.QUERY_MEMBER_URL               						=   (String) oProp.getProperty("queryMemberUrl");
		    Config.CREATE_FAMILY_GROUP_URL               				=   (String) oProp.getProperty("createFamilyGroupUrl");
		    Config.ADD_FAMILY_GROUP_MEMBER_URL               			=   (String) oProp.getProperty("addFamilyGroupMemberUrl");
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_URL               	=   (String) oProp.getProperty("changeFamilyFreeResourceShareUrl");
		    Config.DELETE_FAMILY_GROUP_MEMBER_URL               		=   (String) oProp.getProperty("deleteFamilyGroupMemberUrl");
		    Config.DEACTIVATE_SUBSCRIBTION_URL               			=   (String) oProp.getProperty("deactivateSubscribtionUrl");
		    Config.SUB_FREE_RSC_RELA_INFO_URL               			=   (String) oProp.getProperty("GetSubFreeRscRelaInfoUrl");
		    Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_URL               	=   (String) oProp.getProperty("ChangeFamilyGroupSuppOfferUrl");
		    Config.PURCHASED_SUP_OFFERING_URL			               	=   (String) oProp.getProperty("PurchasedSupOfferingUrl");
		    Config.UNSUBSCRIBED_RECURRING_PACKAGE_URL			       	=   (String) oProp.getProperty("UnsubscribedRecurringPackageUrl");
		    
		    Config.VOUCHER_CARD_RECHARGE_URL					       	=   (String) oProp.getProperty("VoucherCardRechargeUrl");

		    
		    
		    //XML path
		    Config.GET_SUBSCRIBER_RQU_XML_PATH 							=   (String) oProp.getProperty("subscriberXmlPath");
		    Config.CHANGE_SUBINFO_RQU_XML_PATH 							=   (String) oProp.getProperty("changeSubInfoXmlPath");
		    Config.GET_CUSTOMER_XML_PATH 								=   (String) oProp.getProperty("getCustomerXmlPath");
		    Config.CHANGE_SUPPLEMENTARY_OFFERING_XML_PATH 				=   (String) oProp.getProperty("changeSupplementaryOfferingXmlPath");
		    Config.SUB_ACTIVATION_XML_PATH 								=   (String) oProp.getProperty("subActivationXmlPath");
		    Config.CHECK_PUK_XML_PATH 									=   (String) oProp.getProperty("checkPUKXmlPath");
		    Config.REPORT_LOST_XML_PATH 								=   (String) oProp.getProperty("reportLostXmlPath");
		    Config.CANCEL_LOST_XML_PATH 								=   (String) oProp.getProperty("cancelLostXmlPath");
		    Config.QUERY_UNBILLED_AMOUNT_XML_PATH 						=   (String) oProp.getProperty("queryUnbilledAmountXmlPath");
		    Config.LOYALTY_SERVICE_REGISTERATION_XML_PATH 				=   (String) oProp.getProperty("loyaltyServiceRegistrationXmlPath");
		    Config.QUERY_POINT_BALANCE_XML_PATH 						=   (String) oProp.getProperty("queryPointBalanceXmlPath");
		    Config.TRANSFER_POINT_XML_PATH 								=   (String) oProp.getProperty("transferPointXmlPath");
		    Config.QUERY_VOUCHER_XML_PATH 								=   (String) oProp.getProperty("queryVoucherXmlPath");
		    Config.QUERY_RECHARGE_LOG_XML_PATH 							=   (String) oProp.getProperty("queryRechargeLogXmlPath");
		    Config.QUERY_INVOICE_SERVICE_XML_PATH 						=   (String) oProp.getProperty("queryInvoiceServiceXmlPath");
		    Config.QUERY_INVOICE_ACCOUNT_XML_PATH 						=   (String) oProp.getProperty("queryInvoiceAccountXmlPath");
		    Config.CHECK_PASSWORD_XML_PATH 								=   (String) oProp.getProperty("checkPasswordXmlPath");
		    Config.CHECK_SUB_PASSWORD_XML_PATH 							=   (String) oProp.getProperty("checkSubPasswordXmlPath");
		    Config.REEDEM_COMMODITY_XML_PATH 							=   (String) oProp.getProperty("redeemCommodityXmlPath");
		    Config.QUERY_BALANCE_XML_PATH 								=   (String) oProp.getProperty("queryBalanceXmlPath");
		    Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_XML_PATH 			=   (String) oProp.getProperty("getCustomerDataViaAccountCodeXmlPath");
		    Config.CHANGE_SUB_STATUS_XML_PATH 							=   (String) oProp.getProperty("changeSubStatusXmlPath");
		    Config.PRESENT_SERVICE_GIFT_XML_PATH 						=   (String) oProp.getProperty("presentServiceGiftXmlPath");
		    Config.QUERY_FREE_UNIT_XML_PATH 							=   (String) oProp.getProperty("queryFreeUnitXmlPath");
		    Config.GET_GROUP_MEMBER_GROUP_ID_XML_PATH 					=   (String) oProp.getProperty("getGroupMemberGroupIdXmlPath");
		    Config.GET_GROUP_MEMBER_DATA_XML_PATH 						=   (String) oProp.getProperty("getGroupMemberDataXmlPath");
		    Config.QUERY_MEMBER_XML_PATH 								=   (String) oProp.getProperty("queryMemberXmlPath");
		    Config.CREATE_FAMILY_GROUP_XML_PATH 						=   (String) oProp.getProperty("createFamilyGroupXmlPath");
		    Config.ADD_FAMILY_GROUP_MEMBER_XML_PATH 					=   (String) oProp.getProperty("addFamilyGroupMemberXmlPath");
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_ADD_XML_PATH 		=   (String) oProp.getProperty("changeFamilyFreeResourceShareXmlAddPath");
		    Config.DELETE_FAMILY_GROUP_MEMBER_XML_PATH 					=   (String) oProp.getProperty("deleteFamilyGroupMemberXmlPath");
		    Config.DEACTIVATE_SUBSCRIBTION_XML_PATH 					=   (String) oProp.getProperty("deactivateSubscribtionXmlPath");
		    Config.SUB_FREE_RSC_RELA_INFO_XML_PATH 					    =   (String) oProp.getProperty("GetSubFreeRscRelaInfoUrlXmlPath");
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_MODIFY_XML_PATH 	=   (String) oProp.getProperty("changeFamilyFreeResourceShareModifyXmlPath");
		    Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_XML_PATH			 	=   (String) oProp.getProperty("ChangeFamilyGroupSuppOfferXmlPath");

		    Config.PURCHASED_SUP_OFFERING_XML_PATH			 			=   (String) oProp.getProperty("PurchasedSupOfferingXmlPath");
		    Config.UNSUBSCRIBED_RECURRING_PACKAGE_XML_PATH	 			=   (String) oProp.getProperty("UnsubscribedRecurringPackageXmlPath");
		    Config.VOUCHER_CARD_RECHARGE_XML_PATH			 			=   (String) oProp.getProperty("VoucherCardRechargeXmlPath");

		    
		    
		    //Soap Action Name
		    Config.GETSUBSCRIBER_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("getSubscriberSoapActionName");
		    Config.CHANGE_SUBINFO_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("changeSubInfoSoapActionName");
		    Config.GET_CUSTOMER_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("getCustomerSoapActionName");
		    Config.CHANGE_SUPPLEMENTARY_OFFERING_SOAP_ACTION_NAME 		=   (String) oProp.getProperty("changeSupplementaryOfferingSoapActionName");
		    Config.SUB_ACTIVATION_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("subActivationSoapActionName");
		    Config.CHECK_PUK_SOAP_ACTION_NAME 							=   (String) oProp.getProperty("checkPUKSoapActionName");
		    Config.REPORT_LOST_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("reportLostSoapActionName");
		    Config.CANCEL_LOST_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("cancelLostSoapActionName");
		    Config.QUERY_UNBILLED_AMOUNT_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("queryUnbilledAmountSoapActionName");
		    Config.LOYALTY_SERVICE_REGISTERATION_SOAP_ACTION_NAME 		=   (String) oProp.getProperty("loyaltyServiceRegistrationSoapActionName");
		    Config.QUERY_POINT_BALANCE_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("queryPointBalanceSoapActionName");
		    Config.TRANSFER_POINT_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("transferPointSoapActionName");
		    Config.QUERY_VOUCHER_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("queryVoucherSoapActionName");
		    Config.QUERY_RECHARGE_LOG_SOAP_ACTION_NAME 					=   (String) oProp.getProperty("queryRechargeLogSoapActionName");
		    Config.QUERY_INVOICE_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("queryInvoiceSoapActionName");
		    Config.CHECK_PASSWORD_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("checkPasswordSoapActionName");
		    Config.CHECK_SUB_PASSWORD_SOAP_ACTION_NAME 					=   (String) oProp.getProperty("checkSubPasswordSoapActionName");
		    Config.REEDEM_COMMODITY_SOAP_ACTION_NAME 					=   (String) oProp.getProperty("redeemCommoditySoapActionName");
		    Config.QUERY_BALANCE_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("queryBalanceSoapActionName");
		    Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_SOAP_ACTION_NAME 	=   (String) oProp.getProperty("getCustomerDataViaAccountCodeSoapActionName");
		    Config.CHANGE_SUB_STATUS_SOAP_ACTION_NAME 					=   (String) oProp.getProperty("changeSubStatusSoapActionName");
		    Config.PRESENT_SERVICE_GIFT_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("presentServiceGiftSoapActionName");
		    Config.QUERY_FREE_UNIT_SOAP_ACTION_NAME 					=   (String) oProp.getProperty("queryFreeUnitSoapActionName");
		    Config.GET_GROUP_MEMBER_DATA_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("getGroupMemberDataSoapActionName");
		    Config.QUERY_MEMBER_SOAP_ACTION_NAME 						=   (String) oProp.getProperty("queryMemberSoapActionName");
		    Config.CREATE_FAMILY_GROUP_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("createFamilyGroupSoapActionName");
		    Config.ADD_FAMILY_GROUP_MEMBER_SOAP_ACTION_NAME 			=   (String) oProp.getProperty("addFamilyGroupMemberSoapActionName");
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_SOAP_ACTION_NAME 	=   (String) oProp.getProperty("changeFamilyFreeResourceShareSoapActionName");
		    Config.DELETE_FAMILY_GROUP_MEMBER_SOAP_ACTION_NAME 			=   (String) oProp.getProperty("deleteFamilyGroupMemberSoapActionName");
		    Config.DEACTIVATE_SUBSCRIBTION_SOAP_ACTION_NAME 			=   (String) oProp.getProperty("deactivateSubscribtionSoapActionName");
		    Config.SUB_FREE_RSC_RELA_INFO_SOAP_ACTION_NAME 				=   (String) oProp.getProperty("GetSubFreeRscRelaInfoSoapActionName");
		    Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_SOAP_ACTION_NAME		=   (String) oProp.getProperty("ChangeFamilyGroupSuppOfferSoapActionName");

		    Config.PURCHASED_SUP_OFFERING_SOAP_ACTION_NAME				=   (String) oProp.getProperty("PurchasedSupOfferingSoapActionName");
		    Config.UNSUBSCRIBED_RECURRING_PACKAGE_SOAP_ACTION_NAME		=   (String) oProp.getProperty("UnsubscribedRecurringPackageSoapActionName");
		    Config.VOUCHER_CARD_RECHARGE_SOAP_ACTION_NAME				=   (String) oProp.getProperty("VoucherCardRechargeSoapActionName");

		    

		    //For certificate
		    Config.CERTIFICATE_PATH										=   (String) oProp.getProperty("certificatePath");		
		    Config.CERTIFICATE_KEY										=   (String) oProp.getProperty("certificateKey");		
		    Config.API_HOSTNAME											=   (String) oProp.getProperty("apiHostName");		

		    
		    log.debug("GET_SUBSCRIBER_RQU_XML_PATH : " 	+ Config.GET_SUBSCRIBER_RQU_XML_PATH );
		    
		    Config.GET_SUBSCRIBER_RQU_XML_DATA 							=	this.readXmlData(log, Config.GET_SUBSCRIBER_RQU_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_SUBINFO_RQU_XML_DATA 							=	this.readXmlData(log, Config.CHANGE_SUBINFO_RQU_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.GET_CUSTOMER_XML_DATA 								=	this.readXmlData(log, Config.GET_CUSTOMER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_SUPPLEMENTARY_OFFERING_XML_DATA 				=	this.readXmlData(log, Config.CHANGE_SUPPLEMENTARY_OFFERING_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.SUB_ACTIVATION_XML_DATA 								=	this.readXmlData(log, Config.SUB_ACTIVATION_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHECK_PUK_XML_DATA 									=	this.readXmlData(log, Config.CHECK_PUK_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.REPORT_LOST_XML_DATA 								=	this.readXmlData(log, Config.REPORT_LOST_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CANCEL_LOST_XML_DATA 								=	this.readXmlData(log, Config.CANCEL_LOST_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_UNBILLED_AMOUNT_XML_DATA 						=	this.readXmlData(log, Config.QUERY_UNBILLED_AMOUNT_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_OPERATOR_ID, Config.APIOPERATORID);
		    Config.LOYALTY_SERVICE_REGISTERATION_XML_DATA 				=	this.readXmlData(log, Config.LOYALTY_SERVICE_REGISTERATION_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_POINT_BALANCE_XML_DATA 						=	this.readXmlData(log, Config.QUERY_POINT_BALANCE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		   
		    
  
		    
		    Config.TRANSFER_POINT_XML_DATA 								=	this.readXmlData(log, Config.TRANSFER_POINT_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_VOUCHER_XML_DATA 								=	this.readXmlData(log, Config.QUERY_VOUCHER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_OPERATOR_ID, Config.APIOPERATORID).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_RECHARGE_LOG_XML_DATA 							=	this.readXmlData(log, Config.QUERY_RECHARGE_LOG_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_OPERATOR_ID, Config.APIOPERATORID).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_INVOICE_SERVICE_XML_DATA 						=	this.readXmlData(log, Config.QUERY_INVOICE_SERVICE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD);
		    Config.QUERY_INVOICE_ACCOUNT_XML_DATA 						=	this.readXmlData(log, Config.QUERY_INVOICE_ACCOUNT_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD);
		    Config.CHECK_PASSWORD_XML_DATA 								=	this.readXmlData(log, Config.CHECK_PASSWORD_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_OPERATOR_ID, Config.APIOPERATORID).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHECK_SUB_PASSWORD_XML_DATA 							=	this.readXmlData(log, Config.CHECK_SUB_PASSWORD_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.REEDEM_COMMODITY_XML_DATA 							=	this.readXmlData(log, Config.REEDEM_COMMODITY_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_BALANCE_XML_DATA 								=	this.readXmlData(log, Config.QUERY_BALANCE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_OPERATOR_ID, Config.APIOPERATORID);
		    Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_XML_DATA 			=	this.readXmlData(log, Config.GET_CUSTOMER_DATA_VIA_ACCOUNT_CODE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_SUB_STATUS_XML_DATA 							=	this.readXmlData(log, Config.CHANGE_SUB_STATUS_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.PRESENT_SERVICE_GIFT_XML_DATA 						=	this.readXmlData(log, Config.PRESENT_SERVICE_GIFT_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_FREE_UNIT_XML_DATA 							=	this.readXmlData(log, Config.QUERY_FREE_UNIT_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.GET_GROUP_MEMBER_GROUP_ID_XML_DATA 					=	this.readXmlData(log, Config.GET_GROUP_MEMBER_GROUP_ID_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.GET_GROUP_MEMBER_DATA_XML_DATA 						=	this.readXmlData(log, Config.GET_GROUP_MEMBER_DATA_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.QUERY_MEMBER_XML_DATA 								=	this.readXmlData(log, Config.QUERY_MEMBER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CREATE_FAMILY_GROUP_XML_DATA 						=	this.readXmlData(log, Config.CREATE_FAMILY_GROUP_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.ADD_FAMILY_GROUP_MEMBER_XML_DATA 					=	this.readXmlData(log, Config.ADD_FAMILY_GROUP_MEMBER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_ADD_XML_DATA 		=	this.readXmlData(log, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_ADD_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.DELETE_FAMILY_GROUP_MEMBER_XML_DATA 					=	this.readXmlData(log, Config.DELETE_FAMILY_GROUP_MEMBER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.DEACTIVATE_SUBSCRIBTION_XML_DATA 					=	this.readXmlData(log, Config.DEACTIVATE_SUBSCRIBTION_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_MODIFY_XML_DATA 	=	this.readXmlData(log, Config.CHANGE_FAMILY_FREE_RESOURCE_SHARE_MODIFY_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.SUB_FREE_RSC_RELA_INFO_XML_DATA 						=   this.readXmlData(log, Config.SUB_FREE_RSC_RELA_INFO_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_XML_DATA 				=   this.readXmlData(log, Config.CHANGE_FAMILY_GROUP_SUPP_OFFER_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);

		    Config.PURCHASED_SUP_OFFERING_XML_DATA 						=   this.readXmlData(log, Config.PURCHASED_SUP_OFFERING_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.UNSUBSCRIBED_RECURRING_PACKAGE_XML_DATA				=   this.readXmlData(log, Config.UNSUBSCRIBED_RECURRING_PACKAGE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);
		    Config.VOUCHER_CARD_RECHARGE_XML_DATA						=   this.readXmlData(log, Config.VOUCHER_CARD_RECHARGE_XML_PATH).replace(Config.API_USERNAME, Config.APILOGINUSERNAME).replace(Config.API_PASSWORD, Config.APILOGINPASSWORD).replace(Config.API_CHANNELID, Config.APICHANNELID);


		} catch (Exception e) {
			log.error("Exception to read the external property file : "+ e);
		}

	    
	}
	
	public String readXmlData(Logger log,String path)
	{
		try 
		{
			//log.debug("INFO readXmlData !!");
			FileOperations oFileOperations = new FileOperations();
			return oFileOperations.parseXmlData(path,log);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception to read xml : "+e);
			return null;
		}
	}
	
	
}
