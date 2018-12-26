'use strict';
const pushButton = document.querySelector('.js-push-btn');
const publicKey = base64UrlToUint8Array(publicKeyConstant);


let isSubscribed = false;
let swRegistration = null;

// anadimos el evento al boton para suscribir o des-suscribir
pushButton.addEventListener('click', function() {
    if (isSubscribed) {
    	unsubscribe();
    } else {
    	subscribe();
    }
});

function getSubscription() {
	return navigator.serviceWorker.ready
	.then(function(registration) {
	      return registration.pushManager.getSubscription();
	});
}


if ('serviceWorker' in navigator) {
	navigator.serviceWorker.register('sw.js')
		.then(function() {
			console.log('service worker registered');
		});
	
		getSubscription()
		.then(function(subscription) {
			// check if already subscribed
			// get push manager subscription
			if (subscription) {
				// already subscribed
				isSubscribed=true;
				console.log('Already subscribed', subscription.endpoint);
			    pushButton.textContent = 'Desactivar Mensajeria Web Push';
			    pushButton.disabled = false;
			} else {
				// user needs to subscribe
				isSubscribed=false;
			    pushButton.textContent = 'Activar Mensajeria Web Push';
			    pushButton.disabled = false;
			}
		});
}

function subscribe() {
	  navigator.serviceWorker.ready.then(function(registration) {
		
	    // subscribe to push service
	    return registration.pushManager.subscribe({ userVisibleOnly: true, applicationServerKey: publicKey })
	      .then(function(newSubscription) {
	    	 
	        return newSubscription;
	      });
	  }).then(function(subscription) {
		pushButton.disabled = true;  
		var key = subscription.getKey ? subscription.getKey('p256dh') : '';
		var auth = subscription.getKey ? subscription.getKey('auth') : '';
	    document.getElementById('endpoint').value = subscription.endpoint;
	    document.getElementById('accion').value = 'A';
	    document.getElementById('pdh').value = key ? btoa(String.fromCharCode.apply(null, new Uint8Array(key))) : '';
	    document.getElementById('auth').value = auth ? btoa(String.fromCharCode.apply(null, new Uint8Array(auth))) : '';
		  
	    console.log('Subscribed', subscription.endpoint);
	    // register subscription in the application server
	    enviarRegistro();
	  });
}

function unsubscribe() {
	  getSubscription().then(function(subscription) {

	    return subscription.unsubscribe()
	      .then(function() {
	    	pushButton.disabled = true;  
	        // unregister subscription in the application server
	        // otherwise server will try to continue sending the push messages
	    	var key = subscription.getKey ? subscription.getKey('p256dh') : '';  
	    	var auth = subscription.getKey ? subscription.getKey('auth') : '';
	    	document.getElementById('endpoint').value = subscription.endpoint;
	    	document.getElementById('accion').value = 'B';
	    	document.getElementById('pdh').value = key ? btoa(String.fromCharCode.apply(null, new Uint8Array(key))) : '';
	    	document.getElementById('auth').value = auth ? btoa(String.fromCharCode.apply(null, new Uint8Array(auth))) : '';
	    	  
	        console.log('Unsubscribed', subscription.endpoint);
	        enviarRegistro();
	      });
	  });
}

function base64UrlToUint8Array(base64UrlData) {
    const padding = '='.repeat((4 - base64UrlData.length % 4) % 4);
    const base64 = (base64UrlData + padding).replace(/\-/g, '+').replace(/_/g, '/');
    const rawData = atob(base64);
    const buffer = new Uint8Array(rawData.length);
    
    for (var i = 0; i < rawData.length; ++i) {
        buffer[i] = rawData.charCodeAt(i);
    }
    
    return buffer;
}

function updateBtn() {
	  if (Notification.permission === 'denied') {
	    pushButton.textContent = 'Push Messaging Blocked.';
	    pushButton.disabled = true;
	    return;
	  }

	  if (isSubscribed) {
	    pushButton.textContent = 'Desactivar Mensajeria Web Push';
	    document.getElementById('accion').value = 'A';
	  } else {
	    pushButton.textContent = 'Activar Mensajeria Web Push';
	    document.getElementById('accion').value = 'B';
	  }

	  pushButton.disabled = false;
}


function enviarRegistro(){
	var params = {};
	var url = encodeURIComponent(document.getElementById('endpoint').value);
	params["endpoint"] = btoa(url.substring(0,url.length/2));
	params["endpoint2"] = btoa(url.substring(url.length/2,url.length));
	params["accion"] = document.getElementById('accion').value;
	params["pdh"] = document.getElementById('pdh').value;
	params["auth"] = document.getElementById('auth').value; 
	params["idUsuario"] = document.getElementById('idUsuario').value; 
	var retorno = false;
	jQuery.ajax({
		type: "POST",
		url: "insertarUsuarioWebPush.action",
		data: params,
		async : false,
		dataType: "html",
		success: function() 
		{
			if (document.getElementById('accion').value == 'A') {
				isSubscribed = true;
			} else {
				isSubscribed = false;
			}
		    setTimeout(function() {
		    	 updateBtn();
		    	}, 2000);
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert("Se ha producido un error en el servidor");
		}
	});
	
	
	return retorno;
}

