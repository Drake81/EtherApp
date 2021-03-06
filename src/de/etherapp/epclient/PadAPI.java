package de.etherapp.epclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.etherpad_lite_client.EPLiteClient;
import org.etherpad_lite_client.EPLiteException;

import android.accounts.NetworkErrorException;

public class PadAPI {
	private String APINAME = null;
	private String APIKEY = null;
	private String PADURL = null;
	private String APIID = null;
	private int PORT = 9001;
	private EPLiteClient client = null;
	private boolean ready = false;
	private boolean error = false;
	
	private HashMap<String, Pad> padhash = null;
	private ArrayList<String> grouplist = new ArrayList<String>();
	
	//constructor with new (random) API id
	public PadAPI(String apiname, String padurl, int port, String apikey){
		this.APINAME = apiname;
		this.APIKEY  = apikey;
		this.PADURL  = padurl;
		this.PORT    = port;
		
		//generate new random ID
		this.APIID = UUID.randomUUID().toString();
		
		client = new EPLiteClient(PADURL + ":" + PORT, APIKEY);
	}
	
	//constructor with given API id (for API update)
	public PadAPI(String apiname, String padurl, int port, String apikey, String apiid){
		this.APINAME = apiname;
		this.APIKEY  = apikey;
		this.PADURL  = padurl;
		this.PORT    = port;
		this.APIID   = apiid;
		
		client = new EPLiteClient(PADURL + ":" + PORT, APIKEY);
	}
	

	public void init() throws NetworkErrorException{
		new PadThread(this);
		while(!error && !ready){}
		if(error){
			throw new NetworkErrorException("Connection Error");
		}
	}
	
	//sets a padlist to work with
	public void setLists(HashMap<String,Pad> pads, ArrayList<String> groups){
		if(pads != null && groups != null){
			ready = true;
			padhash = pads;
			grouplist = groups;
		}
		else{
			error = true;
		}
	}
	
	public boolean checkApi(){
		// try to create and delete a pad to the reachability of the EtherPad
		try{
			this.getClient().createPad(this.getAPIID());
			this.getClient().deletePad(this.getAPIID());
		}
		catch(EPLiteException e){
			return false;
		}
		
		return true;
	}
	
	//getters & setters
	public HashMap<String, Pad> getPadList() {
		return padhash;
	}
	
	public ArrayList<String> getGroupList() {
		return grouplist;
	}
	
	public EPLiteClient getClient() {
		return client;
	}

	public boolean isReady(){
		return ready;
	}

	public String getAPIKEY() {
		return APIKEY;
	}

	public String getAPIURL() {
		return PADURL;
	}
	
	public int getPORT() {
		return PORT;
	}

	public String getAPINAME() {
		return APINAME;
	}
	
	public String getAPIID() {
		return APIID;
	}
}
