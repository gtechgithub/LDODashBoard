package com.common;

public class CommonConstants {
	
	// URL page names
	public static String loginPage="welcome";
	public static String L1DashboardPage="showMessage";
	public static String LogoutPage = "logoutPage";
	public static String L1SrcLinkPage = "L1SrcLinkPage";
	public static String L2SrcLinkPage = "L2SrcLinkPage";

	public static String L2LandingPage = "L2LandingPage";
	public static String L1OutputDisplayPage = "L1OutputDisplayPage";
	public static String L2OutputDisplayPage = "L2OutputDisplayPage";
	public static String OutputDisplayPage = "OutputDisplayPage";
	public static String UnixProcessDisplayPage = "UnixProcessDisplayPage";
	public static String AppViewDisplayPage = "AppviewDisplayPage";
	public static String AuditDisplayPage = "AuditDisplayPage";
	

	public static String Fail2LoginPage = "fail2LoginPage";
	public static String LdmUnprocessedLink = "ldmUnprocessedLink";
	public static String GcmmLink2 = "gcmmLink2";
	public static String DatabaseCheckout = "dbCheckout";
	public static String DatabaseCheckoutEMEA  = "dbCheckoutEMEA";
	public static String DatabaseCheckoutDisplaPage = "databaseCheckout";
	public static String IndDatabaseCheckoutDisplaPage = "IndividualDatabaseCheckout";
	public static String DatabaseCheckoutLogsDisplaPage = "databaseCheckoutLogsPage";
	public static String HostCheckouttDisplayPage = "ServersCheckout";
	public static String UrlConnectivityDisplayPage ="UrlConnectivityVerify"; 
			
	//Global cash man pages
	public static String GlobalCashmanClusterinfoDisplayPage ="GlobalCashmanClusterInfoDisplay";
	public static String GlobalCashmanProgsDisplayPage ="GlobalCashmanProgsDisplay";
	public static String GlobalCashmanSysControlDisplayPage ="GlobalCashmanSysControlDisplay";
	
	// DBNames in synomnyms
	public static String LDMDbName = "LDMDB";
	public static String GCMMDbName = "GCMMDB";
	public static String CVCheckoutDbName = "CVDBCHECKOUT";
	public static String CVCheckoutEmeaDbName = "CVEMEADBCHECKOUT";
	public static String CVCheckoutAmericaDbName = "CVAMERICADBCHECKOUT";
	public static String OPUSdbName = "OPUSDB";
	public static String CDRdbName = "CDRDB";
	public static String TLMdbName = "TLMDB";
	public static String GlobalCashTLMdbName = "GCMTLMDB";
	public static String MatchDerivativeDBCheckout = "MDDBCHECKOUT";
	
	public static String MatchWebDerivativedbName = "MDWDBCHECKOUT";
	public static String MatchImatchDerivativedbName = "MDIDBCHECKOUT";
	public static String MatchProtDerivativedbName = "MDPDBCHECKOUT";
	
	
	//DB instance name
	public static String cvTSEInstance = "PSG10000_CVISION_APPL.WORLD";
	public static String cvHKFEInstance = "PHK02400_CVISION_APPL.WORLD";
	public static String cvSGXInstance = "PSG09900.WORLD";
	public static String cvTOCOMInstance = "PSG09800.WORLD";
	public static String cvLN1Instance = "PLN20100.WORLD";
	public static String cvLN3Instance = "PLN20300.WORLD";
	public static String LDMDBInstance = "PSG08400_LDM_APPL.WORLD";
	public static String OPUSDBInstance =  "PNYODS01";
	public static String CDRDBInstance =  "PNY32800";
	public static String TLMDBInstance =  "PLN24400";
	public static String TLMETLDBInstance =  "PLN24600";
	public static String GCMTLMLDBInstance =  "PLN24500_CASHMAN_APP";
	public static String MDWebPRODDBInstance =  "PINDX00400";
	public static String MDWebDRDBInstance =  "PHKDX01300";
	public static String MDImatchPRODDBInstance =  "PINDX00400";
	public static String MDImatchDRDBInstance =  "PHKDX01300";
	public static String MDProtPRODDBInstance =  "PINDX00500";
	public static String MDProtDRDBInstance =  "PHKDX01400";
	
	//mapping of the jsp DB description
	public static String cvTSEDBDesc =  "TSE/SYDNEY/4M";
	public static String cvHKFEDBDesc =  "HKFE";
	public static String cvSGXDBDesc =  "SGX";
	public static String cvTOCOMDBDesc =  "TOCOM";
	public static String cvLN1DBDesc =  "LONDON_PLN20100";
	public static String cvLN3DBDesc =  "LONDON_PLN20300";
	public static String LDMDBDesc =  "LDM DB";
	public static String OPUSBDesc =  "OPUS DB";
	public static String CDRDBDesc =  "CDR DB";
	public static String TLMDBDesc =  "TLM DB";
	public static String TLMETLDBDesc =  "TLM ETL DB";
	public static String GCMTLMETLDBDesc =  "Global CashMan DB";
	public static String MDWebProdDBDesc =  "MatchDerivate Web PROD DB";
	public static String MDWebDRDBDesc =  "MatchDerivate Web DR DB";
	public static String MDImatchProdDBDesc =  "MatchDerivate Imatch PROD DB";
	public static String MDImatchDRDBDesc =  "MatchDerivate Imatch DR DB";
	public static String MDProtProdDBDesc =  "MatchDerivate Protector PROD DB";
	public static String MDProtDRDBDesc =  "MatchDerivate Protector DR DB";

	//mapping of the link connection group
	public static String CDRLinkGroup =  "cdrlink";
	public static String TLMLinkGroup =  "tlmlink";
	public static String GCMMLinkGroup =  "gcmmlink";
	public static String MATCHLinkGroup =  "matchlink";
	

	//mapping of the link connection id
	public static String CDRLinkIdGat =  "cdrgat";
	public static String CDRLinkIdcommMgr =  "cdrcommgr";
	public static String CDRRPTLinkIdcommMgr =  "cdrrptmgr";
	public static String TLMwebConnectLink =  "TLMWebConnect";
	public static String GCMMwebConnectLink =  "GCMMWebConnect";
	public static String MATCHDerivMDSwebConnectLink =  "matchDerivMDSConnect";
	public static String MATCHDerivImatchwebConnectLink =  "matchDeriviMatchConnect";
	public static String MATCHDerivMDSwebDRConnectLink =  "matchDerivMDSDRConnect";
	public static String MATCHDerivImatchwebDRConnectLink =  "matchDeriviMatchDRConnect";	
	
	//query id names
	public static String GCMTLMClusterInfo =  "ClusterInfo";
	public static String GCMTLMMactchProgs =  "MatchProgs";
	public static String GCMTLMSysControl =  "SysControl";
	public static String CVAuditStaticUser = "CVAuditStaticUser";
	public static String CVModif = "CVModif";
	
	//query id description
	public static String GCMTLMClusterDesc =  "Results from TLMBUS.CLUSTER_INFO";
	public static String GCMTLMProgsDesc =  "Results from TLMBUS.PROGS";
	public static String GCMTLMSysCtrlDesc =  "Results from TLMBUS.CMS_REF_SYSCONTROL";
	public static String CVAuditStaticUserDesc =  "Results from CVISION.CVAuditStaticUser";
	public static String CVModifDesc =  "Results from CVISION.CVModif";
	
	//list of the app view id
	public static String AppViewLDMRec1 = "LDM-RECS1";
	public static String AppViewCDR = "CDR-COMMFEE";
	public static String AppViewCVAPACCHECKOUT = "CV-APAC-CHECKOUT";
}
