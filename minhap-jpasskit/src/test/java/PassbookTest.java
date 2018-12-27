

import java.awt.Color;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.brendamour.jpasskit.PKBarcode;
import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PKPass;
import de.brendamour.jpasskit.enums.PKBarcodeFormat;
import de.brendamour.jpasskit.passes.PKStoreCard;
import de.brendamour.jpasskit.signing.PKFileBasedSigningUtil;
import de.brendamour.jpasskit.signing.PKPassTemplateFolder;
import de.brendamour.jpasskit.signing.PKSigningInformation;
import de.brendamour.jpasskit.signing.PKSigningInformationUtil;

public class PassbookTest {
	
	private static final Logger LOGGER = Logger.getLogger(PassbookTest.class);
	

	public static void main(String[] args) {
	    try {
            PassbookTest.generate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	
	public static void generate() throws Exception{
        try{
            
            LOGGER.debug("[Passbook] - createPB - inicio");
            
            Date today = Calendar.getInstance().getTime();      
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            PKPass pass = new PKPass();
            
            List<PKBarcode> listaBarcodes = new ArrayList<PKBarcode>();
            PKBarcode barcode = new PKBarcode();
            PKStoreCard storeCard = new PKStoreCard();
            List<PKField> primaryFields = new ArrayList<PKField>();
    
            PKField nombre = new PKField();
            nombre.setKey( "nombre" );
            nombre.setLabel( "Nombre" );
            nombre.setValue( "Nombre: Francisco Javier Escobar Fernández" );
    
            primaryFields.add(nombre);
            
            PKField fecha = new PKField();
            fecha.setKey( "fecha" );
            fecha.setLabel( "Fecha" );
            fecha.setValue(df.format(today) );
            
            primaryFields.add(fecha);
            
            PKField doc = new PKField();
            doc.setKey( "doc" );
            doc.setLabel( "Enlace al documento" );
            doc.setValue( "QR" );
            
            primaryFields.add( doc );
    
            barcode.setFormat( PKBarcodeFormat.PKBarcodeFormatQR );
            barcode.setMessage( "https://des-sede-administracion.redsara.es/pagSedeFront/servicios/descargaCSV.htm?csvquery=FA5v6p6pRvz---2BJKj7CUhnu8nwPJEit7Clsi---2BbpgsMlqV4VakYkYUXmsqW5---2Bp0CFYtmJ---2Bar6R719OTviEU8tEXYA---3D---3D" );
            barcode.setMessageEncoding( Charset.forName( "utf-8" ) );
            
            listaBarcodes.add(barcode);
    
            storeCard.setPrimaryFields( primaryFields );
            storeCard.setHeaderFields(primaryFields);
            storeCard.setSecondaryFields(primaryFields);
            storeCard.setBackFields(primaryFields);
    
            pass.setLogoText( "Justificante de asuencia de antecedentes penales" );
            pass.setStoreCard( storeCard );
            pass.setBarcodes(listaBarcodes);
            
            pass.setDescription( "My PassBook" );
            pass.setBackgroundColorAsObject( Color.RED );
            pass.setForegroundColor( "rgb(255,255,255 )" );
            
            pass.setPassTypeIdentifier( "pass.com.kudit.generic" );
            
            pass.setSerialNumber( "000000007" );
            pass.setAuthenticationToken("vxwxd7J8AlNNFPS8k0a0FfUFtq0ewzFdc");
            pass.setTeamIdentifier( "myTeamId" );
            pass.setOrganizationName( "OrgName" );
        
    
            String template_path = "C:/tmp/";
            String keyStorePath = "C:/proyectos/MINHAP/SIM/SIM-156/PRE-Componente_SGPEIAE.p12";
            String keyStorePassword = "changeit";   
            String appleWWDRCA = "C:/proyectos/MINHAP/SIM/SIM-156/AppleWWDRCA.pem";
            String path_pkpass="C:/proyectos/MINHAP/SIM/SIM-156/";
        
            PKSigningInformation pkSigningInformation = new  PKSigningInformationUtil().loadSigningInformationFromPKCS12AndIntermediateCertificate(keyStorePath,  keyStorePassword, appleWWDRCA);
            PKPassTemplateFolder passTemplate = new PKPassTemplateFolder(template_path);
            PKFileBasedSigningUtil pkSigningUtil = new PKFileBasedSigningUtil(new ObjectMapper());
            byte[] signedAndZippedPkPassArchive = pkSigningUtil.createSignedAndZippedPkPassArchive(pass, passTemplate, pkSigningInformation);
            
             if (pass.isValid()) {
                    try {
                        //convert array of bytes into file
                        String pathFile = path_pkpass+"exampleBueno"+System.currentTimeMillis()+".pkpass";
                        FileOutputStream fileOuputStream =  new FileOutputStream(pathFile);
                        System.out.println(" ruta > " + pathFile);
                        fileOuputStream.write(signedAndZippedPkPassArchive);
                        fileOuputStream.close();
                           
                        System.out.println("Done");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                 
             } else {
                 System.out.println("the pass is NOT Valid man!!!");
             }
        }catch (Exception e){
            System.out.println("the pass is NOT Valid man!!!");
        }
    }
}
