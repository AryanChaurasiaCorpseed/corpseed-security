package com.corpseed.security.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The Class ResponseHendler.
 */
public class ResponseHandler {
	
	/**
	 * Generate response.
	 *
	 * @param status the status
	 * @param error the error
	 * @param message the message
	 * @param extendedMessage the extended message
	 * @param responseObj the response obj
	 * @return the map
	 */
	public static Map<String, Object> generateResponse(HttpStatus status, boolean error, String message, Object extendedMessage, Object responseObj) {
		Map<String, Object> map = new HashMap<>(6);
		try {
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", status);
			map.put("error", error);
			map.put("message", message);
			map.put("extendedMessage", extendedMessage);
			map.put("data",responseObj);
			return map;
		} catch (Exception e) {
			map.clear();
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("message", e.getMessage());
			return map;
		}
	}
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean isSuccess,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", status.value());
			map.put("isSuccess", isSuccess);
			map.put("message", message);
			map.put("data", responseObj);

			return new ResponseEntity<>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<>(map,status);
		}
	}
	
	/**
     * Use This Method When Need to Success Response with Status Code 200
     * @param  data 
     * @param  message
     * @return ResponseEntity
     */


	final static public ResponseEntity<Object> response(Object data,String message){
		Map<String,Object> map=new HashMap<>();
		map.put("isSuccess",true);
		map.put("data",data);
		map.put("status",HttpStatus.OK);
		map.put("message", message);
		map.put("timeStamp",System.currentTimeMillis());
		map.put("fieldError",null);
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	/**
	 * Use This Method when need to create error Response 
	 * @param message
	 * @param httpStatus
	 * @return ResponseEntity
	 */
	final static public ResponseEntity<Object> errorResponse(String message,HttpStatus httpStatus){
		Map<String,Object> map=new HashMap<>();
		map.put("isSuccess",false);
		map.put("data",null);
		map.put("status",httpStatus);
		map.put("message", message);
		map.put("timeStamp",System.currentTimeMillis());
		map.put("fieldError",null);
		return new ResponseEntity<>(map,httpStatus);
	} 
	
	/**
	 * Use This method when need to Genrating
	 * response relating to Spring's Form Validation
	 * Error
	 * @param message
	 * @param fieldError
	 * @return ResponseEntity
	 * @author dharmendra
	 */	
	final static public ResponseEntity<Object> fieldErrorResponse(String message,Object fieldError){
		Map<String,Object> map=new HashMap<>();
		map.put("isSuccess",false);
		map.put("data",null);
		map.put("status",HttpStatus.BAD_REQUEST);
		map.put("message", message);
		map.put("timeStamp",System.currentTimeMillis());
		map.put("fieldError",fieldError);
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	} 
	
	
	public static ResponseEntity<Object> errorResponse(HttpStatus status, boolean isSuccess,String message, Object responseObj) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", status.value());
			map.put("isSuccess", isSuccess);
			map.put("message", message);
			map.put("data", responseObj);

			return new ResponseEntity<>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<>(map,status);
		}
	}
	
	/*
	By :- Aryan Chaurasiya 
	for Exception
	*/
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean isSuccess,String message, Object responseObj,Exception ex) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(ex!=null) {
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", status.value());
			map.put("isSuccess", isSuccess);
			map.put("message", message);
			map.put("exceptionMessage", ex.getStackTrace()[0]);
			map.put("exceptionUrl", ex.toString());
			map.put("exception", ex);
			map.put("data", responseObj);
			}else {
				map.put("timeStamp", System.currentTimeMillis());
				map.put("status", status.value());
				map.put("isSuccess", isSuccess);
				map.put("message", message);
				map.put("exceptionMessage", null);
				map.put("exceptionUrl", null);
				map.put("exception", null);
				map.put("data", responseObj);
			}

			return new ResponseEntity<>(map,status);
		} catch (Exception e) {
			map.clear();
			map.put("timeStamp", System.currentTimeMillis());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess",false);
			map.put("message", e.getMessage());
			map.put("exceptionMessage", e.getStackTrace()[0]);
			map.put("exceptionUrl", e.toString());
			map.put("exception", e);
			map.put("data", null);
			return new ResponseEntity<>(map,status);
		}
	}
}