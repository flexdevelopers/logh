/**
 * 
 * Copyright 2007-2009
 * 
 * Project website:
 * http://code.google.com/p/custom-context-menu/
 * 
 * --
 * RightClick for Flash Player. 
 * Version 0.6.3
 * 
 */

var RightClick = {
	/**
	 *  Constructor
	 */ 
	init: function () {
		if(window.location.hash) {
  			window.location = window.location.href.replace( /#.*/, "");
		}
		this.FlashObjectID = "loghApplication";
		this.Cache = this.FlashObjectID;
		var ieContent=document.getElementById('flashcontent');
		if(window.addEventListener){
			this.FlashContainerID = document.getElementById("flashcontent");
			 window.addEventListener("mousedown", this.onGeckoMouse(), true);
		} else {
			this.FlashContainerID = document.getElementById("iecontent");
			RightClick.FlashContainerID.onmouseup = function(){RightClick.FlashContainerID.releaseCapture(); }
			document.oncontextmenu = function(){ if(window.event.srcElement.id == RightClick.FlashObjectID) { return false; } else { RightClick.Cache = "nan"; }}
			RightClick.FlashContainerID.onmousedown = RightClick.onIEMouse;
		}
	},
	/**
	 *  Disable the Right-Click event trap  and continue showing flash player menu
	 */ 
	UnInit: function () { 
	    //alert('Un init is called' );			
		if(window.RemoveEventListener){
			alert('Un init is called for GECKO' );			
			window.addEventListener("mousedown", null, true);
			window.RemoveEventListener("mousedown",this.onGeckoMouse(),true);
			 //w//indow.releaseEvents("mousedown");
		} else {
			//alert('Un init is called for IE' );							
			RightClick.FlashContainerID.onmouseup = "" ;
			document.oncontextmenu = "";
			RightClick.FlashContainerID.onmousedown = "";
		}
	},

	/**
	 * GECKO / WEBKIT event overkill
	 * @param {Object} eventObject
	 */
	killEvents: function(eventObject) {
		if(eventObject) {
			if (eventObject.stopPropagation) eventObject.stopPropagation();
			if (eventObject.preventDefault) eventObject.preventDefault();
			if (eventObject.preventCapture) eventObject.preventCapture();
	   		if (eventObject.preventBubble) eventObject.preventBubble();
		}
	},
	/**
	 * GECKO / WEBKIT call right click
	 * @param {Object} ev
	 */
	onGeckoMouse: function(ev) {
	  	return function(ev) {
	    if (ev.button != 0) {
			RightClick.killEvents(ev);
			if(ev.target.id == RightClick.FlashObjectID && RightClick.Cache == RightClick.FlashObjectID) {
	    		RightClick.call();
			}
			RightClick.Cache = ev.target.id;
		}
	  }
	},
	/**
	 * IE call right click
	 * @param {Object} ev
	 */
	onIEMouse: function() {
	  	if (event.button > 1) {
			if(window.event.srcElement.id == RightClick.FlashObjectID && RightClick.Cache == RightClick.FlashObjectID) {
				RightClick.call(); 
			}
			RightClick.FlashContainerID.setCapture();
			if(window.event.srcElement.id)
			RightClick.Cache = window.event.srcElement.id;
		}
	},
	/**
	 * Main call to Flash External Interface
	 */
	call: function() {
		document.getElementById(this.FlashObjectID).rightClick();
	}
}