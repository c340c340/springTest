
package com.kinsec;


public class PKISvrTcpJavaClt{
	
	static {
		System.loadLibrary("PKISvrTcpJavaClt");
	}

	private PKISvrTcpJavaClt(String svrURL){
		_strURL = svrURL;
		_strLastError = "";
		_nHandle = 0;
		_strLastSignCertByP1 = "";
		_strLastSignCertByVerifyP7 = "";
		_strLastSrcDataByVerifyP7 = "";
	}

	/**
	 * 获取最后一次调用的错误信息
	 * @return 错误信息
	 */
	public String KT_GetLastError(){
		return _strLastError;
	};
	
	/**
	 * 初始化库,支持多线程安全
	 * @return void
	 */
	public static native void InitilizeCrypto();
	public static void KT_Initilize()
	{
		InitilizeCrypto();
	}

	/**
	 * 多线程安全释放
	 * @return void
	 */
	public static native void FinalizeCrypto();
	public static void KT_Finalize()
	{
		FinalizeCrypto();
	}

	// 0 获取一个新的实例
	public static PKISvrTcpJavaClt KT_GetInstance(String serverURL){
		return new PKISvrTcpJavaClt(serverURL);
	}

	// 1 连接PKI服务器
	public native boolean ConnectPKISvr();
	public boolean KT_OpenPKISvr(){
		return ConnectPKISvr();
	}
	
	// 2 关闭PKI服务器
	public native void ClosePKISvr();
	public void KT_ClosePKISvr(){
		ClosePKISvr();
	}
	
	// 3 产生随机数
	public native String GenRandom( int nLen );
	public String KT_GenRandom( int nLen ){
		return GenRandom( nLen );
	}

	// 4 Raw签名(PKCS1格式)
	public native String P1_SignData( String strSrc );
	public String KT_P1SignData( String strSrc ){
		return P1_SignData( strSrc );
	}

	// 5 Raw验证(PKCS1格式)
	public native boolean P1_VerifySignData( String strSrc, String strB64Cert, String strB64Sign );
	public boolean KT_VerifyP1SignData( String strSrc, String strB64Cert, String strB64Sign ){
		return P1_VerifySignData( strSrc, strB64Cert, strB64Sign );
	}

	// 6 对文件Raw签名(Sha1摘要)
	public native String P1_SignFileBySha1( String strInFile );
	public String KT_P1SignFileBySha1( String strInFile ){
		return P1_SignFileBySha1( strInFile );
	}

	// 7 对文件Raw签名进行验证(签名使用Sha1摘要)
	public native boolean P1_VerifySignFileBySha1( String strInFile, String strB64Cert, String strB64SignValue );
	public boolean KT_P1VerifySignFileBySha1( String strInFile, String strB64Cert, String strB64SignValue ){
		return P1_VerifySignFileBySha1( strInFile, strB64Cert, strB64SignValue );
	}

	// 8 获取上一个Raw签名成功的签名公钥证书
	public String KT_GetLastP1SignCert(){
		return _strLastSignCertByP1;
	}

	// 9 Attach/dettach签名(PKCS7格式)
	public native String P7_SignData( String strSrc, int nAttachFlag );
	public String KT_P7SignData( String strSrc, int nAttachFlag ) {
		return P7_SignData( strSrc, nAttachFlag );
	}

	// 10 Attach/dettach验证(PKCS7格式)
	public native boolean P7_VerifySignData( String strB64SignValue, String strSrc );
	public boolean KT_P7VerifySignData( String strB64SignValue, String strSrc ){
		return P7_VerifySignData( strB64SignValue, strSrc );
	}

	// 11 获取上一个Attach/dettach验证成功时的签名公钥证书
	public String KT_GetLastP7VerifySignCert(){
		return _strLastSignCertByVerifyP7;
	}

	// 12获取上一个Attach/dettach验证成功时的源文数据
	public String KT_GetLastP7VerifySrcData(){
		return _strLastSrcDataByVerifyP7;
	}

	// 13 根据传入公钥证书判断证书USBKEY类型
	// 1代表CA新系统颁发的企业证书KEY -- 简称新企业证书KEY
    // 2代表CA新系统颁发的个人证书KEY
    // 3代表CA新系统颁发的编码证书KEY
    // 4代表CA旧系统颁发的企业证书KEY -- 简称旧企业证书KEY
    // 小于0 取错误信息LastErrorString
	public native int GetCertKeyType( String strB64Cert );
	public int KT_GetCertKeyType( String strB64Cert ){
		return GetCertKeyType( strB64Cert );
	}
	
	// 14 获取传入证书的CN
	public native String GetCertCN( String strB64Cert );
	public String KT_GetCertCN( String strB64Cert ){
		return GetCertCN( strB64Cert );
	}

	// 15 获取传入证书的SN
	public native String GetCertSN( String strB64Cert );
	public String KT_GetCertSN( String strB64Cert ){
		return GetCertSN( strB64Cert );
	}

	// 16 获取传入证书的DN
	public native String GetCertDN( String strB64Cert );
	public String KT_GetCertDN( String strB64Cert ){
		return GetCertDN( strB64Cert );
	}

	// 17 获取传入证书的OU
	public native String GetCertOU( String strB64Cert );
	public String KT_GetCertOU( String strB64Cert ){
		return GetCertOU( strB64Cert );
	}

	// 18 获取传入证书的唯一标识码
	public native String GetCertUniqueID( String strB64Cert );
	public String KT_GetCertUniqueID( String strB64Cert ){
		return GetCertUniqueID( strB64Cert );
	}

	// 19 获取传入证书的注册号(企业证书)
	public native String GetUserRegisterNumber( String strB64Cert );
	public String KT_GetUserRegisterNumber( String strB64Cert ){
		return GetUserRegisterNumber( strB64Cert );
	}

	// 20 获取传入证书的组织结构代码(企业证书)
	public native String GetUserOrganizationNumber( String strB64Cert );
	public String KT_GetUserOrganizationNumber( String strB64Cert ){
		return GetUserOrganizationNumber( strB64Cert );
	}
	
	// 21 获取传入证书的身份证号(个人证书)
	public native String GetUserIdentificationNumber( String strB64Cert );
	public String KT_GetUserIdentificationNumber( String strB64Cert ){
		return GetUserIdentificationNumber( strB64Cert );
	}

	// 22 PKCS7方式对文件签名
	public native String P7_SignFile( String strPathFile, int nAttachFlag );
	public String KT_P7SignFile( String strPathFile, int nAttachFlag ) {
		return P7_SignFile( strPathFile, nAttachFlag );
	}

	// 23 PKCS7方式对文件验证
	public native boolean P7_VerifySignFile( String strSignPathFile, String strSrcPathFile );
	public boolean KT_P7VerifySignFile( String strSignPathFile, String strSrcPathFile ) {
		return P7_VerifySignFile( strSignPathFile, strSrcPathFile );
	}

	// 24 自定义格式对文件进行PKCS7数字签名
	public native boolean P7_SignKtfFile( String strInPathFile, String strOutPathFile );
	public boolean KT_P7SignKtfFile( String strInPathFile, String strOutPathFile ) {
		return P7_SignKtfFile( strInPathFile, strOutPathFile );
	}

	// 25 对自定义格式的PKCS7数字签名文件进行验证
	public native boolean P7_VerifyKtfFile( String strInPathFile, String strOutPathFile );
	public boolean KT_P7VerifyKtfFile( String strInPathFile, String strOutPathFile ) {
		return P7_VerifyKtfFile( strInPathFile, strOutPathFile );
	}

	// 26 对数据自定义格式进行数字信封可签名(nCipher值为1时只作信封;值为2时信封并签名)
	public native byte[] P7_CipherKinsecData( byte[] indata, String strDecipherCert, int nCipher );
	public byte[] KT_P7CipherKinsecData( byte[] indata, String strDecipherCert, int nCipher )
	{
		return P7_CipherKinsecData( indata, strDecipherCert, nCipher );
	}

	// 27 对自定义格式的数据进行数字信封解密并验证
	public native byte[] P7_DecipherKinsecData( byte[] indata );
	public byte[] KT_P7DecipherKinsecData( byte[] indata )
	{
		return P7_DecipherKinsecData( indata );
	}

	// 28 对文件自定义格式进行数字信封并签名(nCipher值为1时只作信封;值为2时信封并签名)
	public native boolean P7_CipherKinsecFile( String strInPathFile, String strDeCipherCert, int nCipher, String strOutPathFile );
	public boolean KT_P7CipherKinsecFile( String strInPathFile, String strDeCipherCert, int nCipher, String strOutPathFile )
	{
		return P7_CipherKinsecFile( strInPathFile, strDeCipherCert, nCipher, strOutPathFile );
	}

	// 29 对自定义格式的数字信封文件进行解密并验证
	public native boolean P7_DecipherKinsecFile( String strInPathFile, String strOutPathFile );
	public boolean KT_P7DecipherKinsecFile( String strInPathFile, String strOutPathFile )
	{
		return P7_DecipherKinsecFile( strInPathFile, strOutPathFile );
	}

	// 30 获取上一个解密数字信封并验证通过的签名公钥证书
	public String KT_GetLastP7DecipherSignCert(){
		return _strLastSignCertByDecipherP7;
	}

	private String _strURL;
	private String _strLastSignCertByP1;
	private String _strLastSignCertByVerifyP7;
	private String _strLastSrcDataByVerifyP7;
	private String _strLastSignCertByDecipherP7;
	private String _strLastError;
	private long _nHandle;
	
	/**
	 * 
	 * @Description: 凯特CA验签
	 * @date: 2017年12月25日
	 * @author :lushaorong
	 * @param src	原串
	 * @param singStr	签名串
	 * @param key	证书
	 * @param orgName	机构名称
	 * @return     
	 * @throws
	 */
//	public static String CheckCASign(String src,String singStr,String key,String orgName){
//		String reMess = "";
////		String strSvrUrl = MsgService.getMsg("ktca.url");
//		String strSvrUrl =  "222.76.48.93:101";
//		
//		PKISvrTcpJavaClt clt = KT_GetInstance( strSvrUrl );
//		if( null == clt ){
//			//整个工程只释放一次	
//			KT_Finalize();
//			reMess = "验证签名失败：初始化失败!";
//			return reMess;
//		}
//	
//		//连接服务器
//		boolean bRes = clt.KT_OpenPKISvr();
//		if( !bRes ){
//			
//			//整个工程只释放一次	
//			KT_Finalize();
//			reMess = "连接服务器失败!"+clt.KT_GetLastError();
//			return reMess;
//		}
//		
//		//ca查询CN名称为空
//		String CNName =  BCConvert.qj2bj(clt.KT_GetCertCN(key));		
//		if (!BsStringUtil.isEmpty(CNName))
//		{
//			reMess = "CA证书错误!"+clt.KT_GetLastError();
//	 		return reMess;
//		}	
//		
//		//传入的机构名称为空
//		orgName = BCConvert.qj2bj(orgName);
//		if(!BsStringUtil.isEmpty(orgName))
//		{
//			reMess ="传入机构名称为空";
//			return reMess;
//		}
//		//传入的机构名称与CA签名中的不符
//		if (orgName.length() > CNName.length())
//		{
//			reMess ="CA证书与代理机构名称不符";
//			return reMess;
//		}
//		if (!orgName.equals(CNName.substring(0, orgName.length())))
//		{
//			reMess ="CA证书与代理机构不符";
//			return reMess;
//		}
//		
//		//验签		
//		bRes = clt.KT_VerifyP1SignData(src, key,singStr);		
//		if( !bRes ){
//			//整个工程只释放一次	
//			KT_Finalize();
//			reMess = "验证签名失败："+clt.KT_GetLastError();
//			return reMess;
//		}	
//		
//		//整个工程只释放一次	
//		KT_Finalize();
//		clt.KT_ClosePKISvr();
//				
//		return reMess;
//	}
	
	
	

	public static void main(String[] args) 
	{	

		String strSvrUrl = "222.76.48.93:101";
		String strSrc = "123456";   //原文
		
		PKISvrTcpJavaClt clt = KT_GetInstance( strSvrUrl );
		if( null == clt ){
			//整个工程只释放一次	
			KT_Finalize();
			return;
		}
		StringBuffer sb = new StringBuffer();
		

//        String strB64Cert = "MIIFRzCCBC+gAwIBAgIQA/8AAAAAsnu0jUUxSWifjTANBgkqhkiG9w0BAQUFADBcMQswCQYDVQQGEwJDTjEPMA0GA1UECAwGZnVqaWFuMS0wKwYDVQQKDCRGdWppYW4gRGlnaXRhbCBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxDTALBgNVBAMMBEZKQ0EwHhcNMTMxMTE3MTYwMDAwWhcNMTcxMjAxMTU1OTU5WjB5MQswCQYDVQQGEwJDTjESMBAGA1UECAwJ56aP5bu655yBMRIwEAYDVQQHDAnnpo/lt57luIIxQjBABgNVBAMMOeemj+W7uuWNmuaAnei9r+S7tuiCoeS7veaciemZkOWFrOWPuO+8iOS4muWKoeS4k+eUqOS6lO+8iTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA4c1fG5p+godi+bOrfQqoBXoeR+weUzCVWs7UhXNS5kZrR2TBPgPYBH7nETRG9pHVe9ZKCSq51GwM28+A7ctnNmXoYKie6u7WTRkjzfjTBEP5OP85eFwOsooFvZkVoVgtfJ1CZnj2XGLoEF0bXAR+0jLIdMjL4P0n4KuT/pqYY3sCAwEAAaOCAmowggJmMAwGA1UdEwQFMAMBAQAwEwYDVR0lBAwwCgYIKwYBBQUHAwIwCwYDVR0PBAQDAgDoMBYGCCqBHNAUBAEEBAo3MzE4NDQyMC03MCIGByqBHIbvQgEEFzYwMTZAMTA1OUAwMDAwMDA1MTU1MjUyMBsGCCqBHNAUBAEDBA8zNTAxMDAxMDAwOTEwNTUwHwYDVR0jBBgwFoAU+zBVFXwtgR+F1qdDcLDTGLR0qKowgckGA1UdHwSBwTCBvjCBu6CBuKCBtYaBiGxkYXA6Ly8yMDIuMTA5LjE5NC4yMjk6Mzg5L0NOPUZKQ0EsQ049RkpDQSwgT1U9Q1JMRGlzdHJpYnV0ZVBvaW50cywgYz1jbj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnSGKGh0dHA6Ly8yMDIuMTA5LjE5NC4yMjkvZG93bmxvYWQvRkpDQS5jcmwwgc4GCCsGAQUFBwEBBIHBMIG+MIGFBggrBgEFBQcwAoZ5bGRhcDovLzIwMi4xMDkuMTk0LjIyOTozODkvQ049RkpDQSxDTj1GSkNBLCBPVT1jQUNlcnRpZmljYXRlcywgYz1jbj9jQUNlcnRpZmljYXRlP2Jhc2U/b2JqZWN0Q2xhc3M9Y2VydGlmaWNhdGlvbkF1dGhvcml0eTA0BggrBgEFBQcwAoYoaHR0cDovLzIwMi4xMDkuMTk0LjIyOS9kb3dubG9hZC9GSkNBLmNlcjAdBgNVHQ4EFgQUct+93hTF/cSgUDDZDs7cSYgJ0HIwDQYJKoZIhvcNAQEFBQADggEBABa/vzAFm6Nize5vSllctAxQ5IVDsNSNRbIR35k+vRpnLx9jmSTNxpB9SGxjipqdCvkK+F9x1s2DDbYm9zTZD1vE9klGkU3+EzqSRfpyYrBH1AQu8reTDM77dLegIkPj+VhvuqiNVBUlLRQTZLHA+0IaJXyWx296IRROAAm5/P0KxXu3SLhCApdj3mb9gNI0zyZj8SSuyjEWuvxoFTWjz6jeODvFcoYRf57Y31smmJxDCWikp5lF6Zlhdc5OzBkR9L7Wv6+3XOVoPmw7GLHqCGMPlDZVAt50rGICO9KHiRt1KfRculLS8OECgRzSaRJ0nuAugRq0toe5QlXsZZuAU7Y=";
//		String strB64Sign ="mG5qLBtXN4sHjj3xiv5S/movNryKXEhbdCAi9cg5Bqr0bBNNekij/H+IRcIbXcclIV7VAnaVi//Z60Tz49gemWPNII07wqBJjG83yDZsdJHCjNr7Yi8KJnqfnoRyyEJe+s+U3n543147kh2fOS80SpqDl8tbsx9z3u0a+qLMjkw=";
		
		
		String strB64Cert = "MIIFXDCCBESgAwIBAgIQA/8AAAAdyOXqhmQVvPJV1zANBgkqhkiG9w0BAQUFADBcMQswCQYDVQQGEwJDTjEPMA0GA1UECAwGZnVqaWFuMS0wKwYDVQQKDCRGdWppYW4gRGlnaXRhbCBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxDTALBgNVBAMMBEZKQ0EwHhcNMTcxMTAxMTYwMDAwWhcNMTgwNTAyMTU1OTU5WjCBwjELMAkGA1UEBhMCQ04xEjAQBgNVBAgMCeemj+W7uuecgTESMBAGA1UEBwwJ56aP5bee5biCMRswGQYDVQQLDBI5MTM1MDEwMDczMTg0NDIwN1kxJDAiBgNVBAsMG+atpOivgeS5puS7heS9v+eUqOS6jua1i+ivlTFIMEYGA1UEAww/56aP5bu65Y2a5oCd6L2v5Lu26IKh5Lu95pyJ6ZmQ5YWs5Y+477yI5rWL6K+V5LiT55So5LiJ5Y2B5LiD77yJMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjC9GxjuIrZF2QSy/K/HcdsGvOTRF8sQd5eofLHz7gFQ4CWOmvE4YFbRiuCiWzfEr/4QJW8AI9Y9NgoGPKyaZpcorLB/vT20jlyVycRbDn45L9Z/daS5fPkMHkkmR03TwgsPaXMQBb5KeFuchpxQ6u3BebUWxelgGVmGUUe6IyhQIDAQABo4ICNTCCAjEwDAYDVR0TBAUwAwEBADATBgNVHSUEDDAKBggrBgEFBQcDAjALBgNVHQ8EBAMCAOgwIgYHKoEchu9CAQQXNjAxNkAxMDU5QDAwMDAwMDk5OTI2NDEwHwYDVR0jBBgwFoAU+zBVFXwtgR+F1qdDcLDTGLR0qKowgckGA1UdHwSBwTCBvjCBu6CBuKCBtYaBiGxkYXA6Ly8yMDIuMTA5LjE5NC4yMjk6Mzg5L0NOPUZKQ0EsQ049RkpDQSwgT1U9Q1JMRGlzdHJpYnV0ZVBvaW50cywgYz1jbj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnSGKGh0dHA6Ly8yMDIuMTA5LjE5NC4yMjkvZG93bmxvYWQvRkpDQS5jcmwwgc4GCCsGAQUFBwEBBIHBMIG+MIGFBggrBgEFBQcwAoZ5bGRhcDovLzIwMi4xMDkuMTk0LjIyOTozODkvQ049RkpDQSxDTj1GSkNBLCBPVT1jQUNlcnRpZmljYXRlcywgYz1jbj9jQUNlcnRpZmljYXRlP2Jhc2U/b2JqZWN0Q2xhc3M9Y2VydGlmaWNhdGlvbkF1dGhvcml0eTA0BggrBgEFBQcwAoYoaHR0cDovLzIwMi4xMDkuMTk0LjIyOS9kb3dubG9hZC9GSkNBLmNlcjAdBgNVHQ4EFgQUPJlX7UEt8ZDOOLQI9LaO+XGP7hswDQYJKoZIhvcNAQEFBQADggEBAIGKWEx6jVux4nXh1nzvPwMKn82FM6fzucnF/LdY5jBgOWWo4qxZj9DiGLIYCCXe6G+NTTRhXASS1hUmnQ/o7wKxgyIE1vjJ6OhXSbgUqy4wZn2wV1XFsSNmJB5FLs74oIYyxGf6yqMXKo15ROGLSQV+TFAWUJqbJU6OPmfxEzJF5/cGtwXPBaDFEn2lsPaFDG5VyaU+NK8PZKE5GXizMuCkPczodVlfWA/IbL7U7wbBk5G+ZaeZZeKFRKaX2BVpZ1fnOF+e3z+tiUeukLrRq/ARc7uIWkxr0jy+kVc59wIZYzbZBkQrnpHORwb6SxtbJlV0wEqEejSR/7TNBzZri+Y=";
		String strB64Sign = "jwLGyADyC4tYhttIMzWxD4aLU3vtsOdSHjNHDrqz28BEC3qvxeQidHriGPZurW5LdyTnDQaMvhacP7J31yLhtet4O7R1dNUSYw2DN4RpjElrRxF+JaIZOBpFp9tN5MJjZwZa51BmmuBOo2Et+VZCHwSDPVsN6bCq7Horj5agWj0=";
		
		//验证
		boolean bRes = clt.KT_OpenPKISvr();
		if( !bRes ){
			System.out.println( "验证签名失败："+clt.KT_GetLastError() );
			//整个工程只释放一次	
			KT_Finalize();
			return;
		}
		bRes = clt.KT_VerifyP1SignData(strSrc, strB64Cert, strB64Sign);
		if( !bRes ){
			System.out.println( "验证签名失败："+clt.KT_GetLastError() );
			//整个工程只释放一次	
			KT_Finalize();
			return;
		}else{
		   System.out.println("验证签名成功：证书通用名为:"+clt.KT_GetCertCN(strB64Cert));
		   System.out.println("              证书序列号："+clt.GetCertSN(strB64Cert));
		   System.out.println("              企业组织机构代码为"+clt.KT_GetUserOrganizationNumber(strB64Cert));
           System.out.println("              个人身份证号码为"+clt.KT_GetUserIdentificationNumber(strB64Cert));

		}

		//整个工程只释放一次	
		KT_Finalize();
		clt.KT_ClosePKISvr();
	}
	
	
}
