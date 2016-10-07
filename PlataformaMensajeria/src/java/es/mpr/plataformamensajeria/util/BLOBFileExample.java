package es.mpr.plataformamensajeria.util;

//-----------------------------------------------------------------------------
//BLOBFileExample.java
//-----------------------------------------------------------------------------

/*
* =============================================================================
* Copyright (c) 1998-2011 Jeffrey M. Hunter. All rights reserved.
* 
* All source code and material located at the Internet address of
* http://www.idevelopment.info is the copyright of Jeffrey M. Hunter and
* is protected under copyright laws of the United States. This source code may
* not be hosted on any other site without my express, prior, written
* permission. Application to host any of the material elsewhere can be made by
* contacting me at jhunter@idevelopment.info.
*
* I have made every effort and taken great care in making sure that the source
* code and other content included on my web site is technically accurate, but I
* disclaim any and all responsibility for any loss, damage or destruction of
* data or any other property which may arise from relying on it. I will in no
* case be liable for any monetary damages arising from such loss, damage or
* destruction.
* 
* As with any code, ensure to test this code in a development environment 
* before attempting to run it in production.
* =============================================================================
*/

import java.io.File;
import java.sql.Connection;


/**
* -----------------------------------------------------------------------------
* Used to test the functionality of how to load and unload binary data from an
* Oracle BLOB.
* 
* This example uses an Oracle table with the following definition:
* 
*      CREATE TABLE test_blob (
*            id               NUMBER(15)
*          , image_name       VARCHAR2(1000)
*          , image            BLOB
*          , timestamp        DATE
*      );
* -----------------------------------------------------------------------------
* @version 1.0
* @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
* @author  http://www.idevelopment.info
* -----------------------------------------------------------------------------
*/

public class BLOBFileExample  {

 private String          inputBinaryFileName     = null;
 private File            inputBinaryFile         = null;

 private String          outputBinaryFileName1   = null;
 private File            outputBinaryFile1       = null;

 private String          outputBinaryFileName2   = null;
 private File            outputBinaryFile2       = null;
 
 private String          dbUser                  = "SCOTT";
 private String          dbPassword              = "TIGER";
 private Connection      conn                    = null;
 

 /**
  * Default constructor used to create this object. Responsible for setting
  * this object's creation date, as well as incrementing the number instances
  * of this object.
  * @param args Array of string arguments passed in from the command-line.
  * @throws java.io.IOException
  */
 
     
	  public static void main(String[] args) throws Exception {
		  

		    } 
	  
}

