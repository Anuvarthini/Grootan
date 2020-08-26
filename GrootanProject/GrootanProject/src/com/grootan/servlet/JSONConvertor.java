package com.grootan.servlet;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsonschema2pojo.DefaultGenerationConfig;  
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;  
import org.jsonschema2pojo.SchemaMapper;  
import org.jsonschema2pojo.SchemaStore;  
import org.jsonschema2pojo.SourceType;  
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

import java.net.URL;
 

public class JSONConvertor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String jsonString = request.getParameter("jsonString");
	        String packageName="com.grootan";  
	          File inputJson= new File("."+File.separator+"input.json");  
	          File outputPojoDirectory=new File("."+File.separator+"convertedPojo");  
	          outputPojoDirectory.mkdirs();  
	          try {  
	               new JSONConvertor().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));  
	          } catch (IOException e) {  
	               // TODO Auto-generated catch block  
	               System.out.println("Encountered issue while converting to pojo: "+e.getMessage());  
	               e.printStackTrace();  
	          }    
	}
	
	 public void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{  
         JCodeModel codeModel = new JCodeModel();  
         URL source = inputJson;  
         GenerationConfig config = new DefaultGenerationConfig() {  
         @Override  
         public boolean isGenerateBuilders() { // set config option by overriding method  
             return true;  
         }  
         public SourceType getSourceType(){  
     return SourceType.JSON;  
   }  
         };  
         SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(), new SchemaStore()), new SchemaGenerator());  
         mapper.generate(codeModel, className, packageName, source);  
         codeModel.build(outputPojoDirectory);  
    }  
	 
	 public static void download(String filePath,String fileName,HttpServletResponse response,String fileFormat) throws Exception 
	  {
		     InputStream in = null;
			 ServletOutputStream out = null;			
			 try 
			 {			
					in = new FileInputStream(filePath);
		               if( in != null ) 
		               {
		            	    out= response.getOutputStream();
		            	    response.setContentType("application/"+fileFormat);
		            	    response.setHeader("Content-Disposition","attachment; fileName=\""+fileName+"\"");
		                   int c; while( ( c=in.read() ) != -1 ) out.write( c ); 
		            }
		     } 
			 catch (IOException e) 
			 {
				throw new Exception(e);
			 }
			 catch (Exception e) 
			 {
				throw new Exception(e);
			}
			finally 
			{
		        if( in != null ) 
		        try 
		        { 
		        	in.close(); 
		        } 
		        catch( Exception e ) 
		        {}
		        if( out != null ) 
		        try 
		        {
		        	out.flush(); 
		        	out.close(); 
		        }
		        catch( Exception e ) 
		        {
		        	throw new Exception(e);
		        }
		    }
	  }
	

	
}
