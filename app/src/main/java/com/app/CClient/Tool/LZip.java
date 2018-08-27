package com.app.CClient.Tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class LZip {
       static public byte[] compress(byte[] lpBuffer){
    	  
    	  
    	   try {
    		   ByteArrayOutputStream out =  new ByteArrayOutputStream();
    		   GZIPOutputStream gzipout = new GZIPOutputStream(out);
    		   gzipout.write(lpBuffer);
    		   gzipout.finish(); 
    		   return out.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   return null;
       }
       
       static public byte[] uncompress(byte[] lpZipBuffer){
     	  
     	  
    	   try {
    		   ByteArrayInputStream in =  new ByteArrayInputStream(lpZipBuffer);
    		   ByteArrayOutputStream out =  new ByteArrayOutputStream();
    		   
    		   GZIPInputStream gzipis = new GZIPInputStream(in);
    		   int nCount = -1;
    		   do{
    			   byte[] lpBuffer = new byte[1024];
        		   nCount = gzipis.read(lpBuffer);
        		   if(nCount > 0)
        		   {
        			   out.write(lpBuffer,0,nCount);
        			  
        		   }
    		   } while(nCount != -1);
    			  return out.toByteArray();
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   return null;
       }
       
       
}















