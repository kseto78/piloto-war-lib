package es.mpr.plataformamensajeria.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

//import es.mpr.plataformamensajeria.beans.DecodeBean;
//import es.mpr.plataformamensajeria.servicios.ifaces.CifradoService;


public class Utiles {
	private static final Logger LOGGER = Logger.getLogger(Utiles.class);
	
	private static final String ORDEN_LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Codificacion Base64
	 * @param param
	 * @return
	 */
	public static String encode(byte[] param){
		byte[] encoded = Base64.encodeBase64(param);
		return new String(encoded);
	}
	
	/**
	 * Decodificacion Base64
	 * @param param
	 * @return
	 */
	public static byte[] decode(String param){
		byte[] decoded = Base64.decodeBase64(param.getBytes());
		return decoded;
	}
	
//	public static DecodeBean desencripta(HttpServletRequest request, DecodeBean decodeBean, CifradoService cifradoService){
//
////		Properties ps = new Properties (request);
//		
//		PlataformaMensajeriaProperties props = new PlataformaMensajeriaProperties();
//		
//		if (decodeBean.getXmlCifrado()!=null && !("").equals(decodeBean.getXmlCifrado())){
//		
//			try {
//				
//				String certificado ="";
//				if(decodeBean.getCertificado()!=null && !"".equals(decodeBean.getCertificado())){
//					certificado = decodeBean.getCertificado();
//				}else{
//					certificado =  props.getProperty("decode.keystore.alias.defecto", null);;
//				}
//				
//				String xmlCifrado = decodeBean.getXmlCifrado();
//			
//				final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//				dbf.setNamespaceAware(true);
//				
//				final Document docOriginal = XMLUtils.xml2doc(xmlCifrado, Charset.forName("UTF-8"));
//
//				String keystoreType = props.getProperty("decode.keystore.type", null);
//				String keystore = props.getProperty("decode.keystore.path", null);
//				String keystorePassword = props.getProperty("decode.keystore.password", null);
//				String alias = props.getProperty("decode.keystore.alias." + certificado, null);
//				String aliasPassword = props.getProperty("decode.keystore.alias.password." + certificado, null);
//				
//				// Desciframos el documento cifrado
//				Document docDescifrado = cifradoService.descifrarKey(
//						docOriginal,
//						keystoreType,
//						keystorePassword,
//						alias,
//						aliasPassword,
//						keystore);
//			
//				docDescifrado = cifradoService.descifrar(
//						docDescifrado,
//						keystoreType,
//						keystorePassword,
//						alias,
//						aliasPassword,
//						keystore);
//				
//				NodeList node = docDescifrado.getElementsByTagNameNS("http://schemas.xmlsoap.org/soap/envelope/", "Header");
//				
//				if(node.getLength()>0){
//					for (int i=0;i< node.getLength();i++){
//						
//						NodeList node2 = node.item(i).getChildNodes();
//						if(node2.getLength()>0){
//							for (int j=0;j< node2.getLength();j++){
//								if(node2.item(j)!=null && node2.item(j).getNodeName().contains("Security")){
//									node.item(i).removeChild(node2.item(j));
//								}
//							}
//						}
//					}
//				}
//				
////				SOAPMessage soapMessage = XMLUtils.dom2soap(docDescifrado);
////				soapMessage.getSOAPHeader().removeContents();
////				
////				String xmlDescifrado = XMLUtils.dom2xml(XMLUtils.soap2dom(soapMessage));
////				decodeBean.setXmlDescifrado(xmlDescifrado);
//				
//				String xmlDescifrado = XMLUtils.dom2xml(docDescifrado);
//				decodeBean.setXmlDescifrado(xmlDescifrado);
//				
//				
//			}catch (Exception e){
//			}
//			
//		}
//		return decodeBean;
//	}
	
	public static String FiletoBase64String (File originalFile){
        String iconoBase64 = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
            byte[] bytes = new byte[(int)originalFile.length()];
            fileInputStreamReader.read(bytes);
            iconoBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return iconoBase64;
		
	}
}