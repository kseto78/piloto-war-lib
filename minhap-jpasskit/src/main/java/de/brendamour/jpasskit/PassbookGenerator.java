package de.brendamour.jpasskit;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.brendamour.jpasskit.enums.PKBarcodeFormat;
import de.brendamour.jpasskit.passes.PKEventTicket;
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil;
import de.brendamour.jpasskit.signing.PKPassTemplateFolder;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningInformationUtil;

public class PassbookGenerator {

    private static final Logger LOGGER = Logger.getLogger(PassbookGenerator.class);

    public static String generate(List<PKField> primaryFields, List<PKField> secondaryFields, List<PKField> auxiliaryFields,List<PKField> headerFields,
            List<PKField> backFields, String url, String logoText, String description, String backgroundColor, String foregroundColor, String labelColor,
            String passTypeIdentifier, String serialNumber, String autheticationToken, String teamIdentifier, String organizationName,
            String template_path, String appleWWDRCA, String keyStorePath, String keyStorePassword, String path_pkpass) throws Exception {

        LOGGER.debug("[Passbook] - createPB - inicio");
        
        String pathFile = null;

        PKPass pass = new PKPass();
//        pass.setFormatVersion(1);
//        pass.setVoided(true);

        List<PKBarcode> listaBarcodes = new ArrayList<PKBarcode>();
        PKBarcode barcode = new PKBarcode();
        
        barcode.setFormat(PKBarcodeFormat.PKBarcodeFormatQR);
        barcode.setMessage(url);
        barcode.setMessageEncoding(Charset.forName("utf-8"));
      
        listaBarcodes.add(barcode);
        pass.setBarcodes(listaBarcodes);
        
//        PKStoreCard storeCard = new PKStoreCard();
//        
//        if (!primaryFields.isEmpty()) {
//            storeCard.setPrimaryFields(primaryFields);
//        }
//        if (!headerFields.isEmpty()) {
//            storeCard.setHeaderFields(headerFields);
//        }
//        if (!secondaryFields.isEmpty()) {
//            storeCard.setSecondaryFields(secondaryFields);
//        }
//        if (!backFields.isEmpty()) {
//            storeCard.setBackFields(backFields);
//        }
//
//        pass.setStoreCard(storeCard);
        
        PKEventTicket eventTicket = new PKEventTicket();

        if (!primaryFields.isEmpty()) {
            eventTicket.setPrimaryFields(primaryFields);
        }
        if (!headerFields.isEmpty()) {
            eventTicket.setHeaderFields(headerFields);
        }
        if (!secondaryFields.isEmpty()) {
            eventTicket.setSecondaryFields(secondaryFields);
        }
        if (!auxiliaryFields.isEmpty()) {
            eventTicket.setAuxiliaryFields(auxiliaryFields);
        }
        if (!backFields.isEmpty()) {
            eventTicket.setBackFields(backFields);
        }

        pass.setEventTicket(eventTicket);

        if (null != logoText) {
            pass.setLogoText(logoText);
        }
        
        if (null != description) {
            pass.setDescription(description);
        }

        if (null != backgroundColor) {
            pass.setBackgroundColor(backgroundColor);
        }

        if (null != foregroundColor) {
            pass.setForegroundColor(foregroundColor);
        }

        if (null != passTypeIdentifier) {
            pass.setPassTypeIdentifier(passTypeIdentifier);
        }
        
        if (null != labelColor) {
            pass.setLabelColor(labelColor);
        }

        if (null != serialNumber) {
            pass.setSerialNumber(serialNumber);
        }
        if (null != autheticationToken) {
            pass.setAuthenticationToken(autheticationToken);
        }

        if (null != teamIdentifier) {
            pass.setTeamIdentifier(teamIdentifier);
        }

        if (null != organizationName) {
            pass.setOrganizationName(organizationName);
        }

        PKSigningInformation pkSigningInformation = new PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(keyStorePath, keyStorePassword, appleWWDRCA);
        PKPassTemplateFolder passTemplate = new PKPassTemplateFolder(template_path);
        PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil(new ObjectMapper());
        byte[] signedAndZippedPkPassArchive = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);

        if (pass.isValid()) {
            
            // convert array of bytes into file
            pathFile = path_pkpass + "passBook_" + serialNumber + ".pkpass";
            FileOutputStream fileOuputStream = new FileOutputStream(pathFile);
            fileOuputStream.write(signedAndZippedPkPassArchive);
            fileOuputStream.close();
            return pathFile;
            
        } else {
            LOGGER.error("PassbookGenerator - El Passbook generado no es valido");
        }
        return pathFile;
    }
}
