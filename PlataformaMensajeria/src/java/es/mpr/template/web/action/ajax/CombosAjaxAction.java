package es.mpr.template.web.action.ajax;

import java.util.ArrayList;
import java.util.List;

import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionSupport;

public class CombosAjaxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<KeyValueObject> languageObjList;
	private List<KeyValueObject> frameworkList;
	private List<String> versionList;
	private String framework; 
	private String language;
		
	public String execute()
	{
		return INPUT;
	}
	
	public String getJSON(){
				
		languageObjList = new ArrayList<KeyValueObject>();
		frameworkList = new ArrayList<KeyValueObject>();
		versionList = new ArrayList<String>();
		    
	    languageObjList.add(new KeyValueObject("J", "Java"));
	    languageObjList.add(new KeyValueObject("P", "PHP"));
	    languageObjList.add(new KeyValueObject("C", "C#"));
	    
	   
	    if (language != null && language.equalsIgnoreCase("J"))
	    {
	      frameworkList.add(new KeyValueObject("S","Struts2"));
	      frameworkList.add(new KeyValueObject("M","MyFaces"));
	      frameworkList.add(new KeyValueObject("T","Tapestry"));
	      
	      if (framework!=null && framework.equals("S"))
	      {
	    	  versionList.add("2.0.0");
	    	  versionList.add("2.0.1");
	    	  versionList.add("2.0.2");
	      }
	      
	      if (framework!=null && framework.equals("M"))
	      {
	    	  versionList.add("1.0.0");
	    	  versionList.add("1.0.1");
	    	  versionList.add("1.0.2");
	      }
	      
	      if (framework!=null && framework.equals("T"))
	      {
	    	  versionList.add("3.0");
	    	  versionList.add("3.1");
	    	  versionList.add("2.0.2");
	      }
	    }
	    else if (language != null && language.equalsIgnoreCase("P"))
	    {
	      frameworkList.add(new KeyValueObject("S","Symfony"));
	      frameworkList.add(new KeyValueObject("Z","Zend"));
	      
	      if (framework!=null && framework.equals("S"))
	      {
	    	  versionList.add("2.0");
	    	  versionList.add("1.4");
	    	  versionList.add("1.3");
	      }
	      
	      if (framework!=null && framework.equals("Z"))
	      {
	    	  versionList.add("5.1.0");
	      }
	      
	    }
	    else if (language != null && language.equalsIgnoreCase("C"))
	    {
	      frameworkList.add(new KeyValueObject("N","NStruts"));
	      frameworkList.add(new KeyValueObject("P","ProMesh.NET"));
	      frameworkList.add(new KeyValueObject("W","Websharp"));
	      
	      if (framework!=null && framework.equals("N"))
	      {
	    	  versionList.add("1.1 Alpha");
	      }
	      
	      if (framework!=null && framework.equals("P"))
	      {
	    	  versionList.add("2.0.1");
	    	  versionList.add("2.0.0");
	      }
	      
	      if (framework!=null && framework.equals("W"))
	      {
	    	  versionList.add("1.0");
	      }
	    }
	    
		return SUCCESS;
	}
		

	public void setLanguage(String language){
	    this.language = language;
	  }

	public List<KeyValueObject> getLanguageObjList() {
		return languageObjList;
	}

	public void setLanguageObjList(List<KeyValueObject> languageObjList) {
		this.languageObjList = languageObjList;
	}

	public List<KeyValueObject> getFrameworkList() {
		return frameworkList;
	}

	public void setFrameworkList(List<KeyValueObject> frameworkList) {
		this.frameworkList = frameworkList;
	}

	public List<String> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<String> versionList) {
		this.versionList = versionList;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}
	
}
