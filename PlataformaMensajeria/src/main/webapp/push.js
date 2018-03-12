window.addEventListener('load', registerServiceWorker, false);
'use strict';
const pushButton = document.querySelector('.js-push-btn');
// const publicKey =
// base64UrlToUint8Array('BG04vVnnE31ukif3ag30-mMwyR7I0FFoOKxmCKFUCTaVpVCyFf54PnNHvrWV9xgFtH7wSvjg1vlS-zOFRtQw3J4=');
const publicKey = base64UrlToUint8Array('BNV1IoMKoNFKwxfhJojULPYtYhDAZpJCaguaLcKQnfEfOhNrRhH458bJwACKTMVIVCFbfN4SZ0jZtz5LMocx8GQ=');
const servicio = 2502; // ////////////Se indica el servicio/////////////////
const idUsuario = 999999999; // ////////////Se indica usuario logueado en la
							// aplicacion como ella lo identifique (dni, telf,
							// email,...)/////////////////
const user = 'pruebasSIMdes';
const pass = 'pruebasSIMdes';


let isSubscribed = true;
let swRegistration = null;

function registerServiceWorker() {
    if ('serviceWorker' in navigator ) {
    	initialiseState();
    	
    } else {
        console.log('Service workers are not supported in this browser.');
    }
}

function initialiseState() {
	if (!('showNotification' in ServiceWorkerRegistration.prototype)) {
        console.warn('Notifications aren\'t supported.');
        return;
    }

    if (Notification.permission === 'denied') {
        console.warn('The user has blocked notifications.');
        return;
    }

    if (!('PushManager' in window)) {
        console.warn('Push messaging isn\'t supported.');
        return;
    }
    
    // anadimos el evento al boton para registrar o desregistrar
    pushButton.addEventListener('click', function() {
        pushButton.disabled = true;
        if (isSubscribed) {
        	unsubscribe();
        } else {
        	subscribe();
        }
      });
      if (null == swRegistration) {
       		isSubscribed = false;
      }
        	updateBtn();
            // Keep your server in sync with the latest subscriptionId
            // sendSubscriptionToServer(subscription);
        
    
}

function unsubscribe(){
	navigator.serviceWorker.ready.then(function(reg) {
		  reg.pushManager.getSubscription().then(function(subscription) {
		    subscription.unsubscribe().then(function(successful) {
		    	isSubscribed = false;
		    	updateBtn();
		    	document.getElementById('subscription').value = "";
		    	sendSubscriptionToServer(subscription, 'B');
		    	console.log('Service worker desregistrado correctamente');
		    	document.getElementById('subscription').value = 'Service worker desregistrado correctamente';
		    }).catch(function(e) {
		    	console.warn('Error desregistrando Service worker ', e);
		    })
		  })        
		});
}

function subscribe() {
	console.log('Service Worker and Push is supported');
	navigator.serviceWorker.register('sw.js')
	  .then(function(swReg) {
	    console.log('Service Worker is registered', swReg);

	    swRegistration = swReg;
	    initialiseState();
	  })
	  .catch(function(error) {
	    console.error('Service Worker Error', error);
	  });

    navigator.serviceWorker.ready.then(function (serviceWorkerRegistration) {
        serviceWorkerRegistration.pushManager.subscribe({
            userVisibleOnly: true,
            applicationServerKey: publicKey
        })
        .then(function (subscription) {
            return sendSubscriptionToServer(subscription, 'A');
        })
        .catch(function (e) {
            if (Notification.permission === 'denied') {
                console.warn('Permission for Notifications was denied');
                updateBtn();
            } else {
                console.error('Unable to subscribe to push.', e);
                updateBtn();
            }
        });
    });
    isSubscribed = true;
    updateBtn();
}

function sendSubscriptionToServer(subscription, action) {
    var key = subscription.getKey ? subscription.getKey('p256dh') : '';
    var auth = subscription.getKey ? subscription.getKey('auth') : '';
    var acc = action;
    
    if (acc == 'A'){
    	document.getElementById('subscription').value = JSON.stringify(subscription);
    }

    console.log({
        endpoint: subscription.endpoint,
        key: key ? btoa(String.fromCharCode.apply(null, new Uint8Array(key))) : '',
        auth: auth ? btoa(String.fromCharCode.apply(null, new Uint8Array(auth))) : ''
    });

// return Promise.resolve();
              
    return fetch('https://des-misim.redsara.es/misim-bus-webapp/rest/gestionServiciosPush/registroUsuarioWebPush', {
        credentials: 'include',
        headers: {
        	 'Accept': 'application/json',
        	 'Content-Type': 'application/x-www-form-urlencoded'
        	 
        },
         mode: 'no-cors',
        method: 'POST',
        body: JSON.stringify({
            Endpoint: subscription.endpoint,
            Key: key ? btoa(String.fromCharCode.apply(null, new Uint8Array(key))) : '',
            Auth: auth ? btoa(String.fromCharCode.apply(null, new Uint8Array(auth))) : '',
            IdServicio: servicio,
            IdUsuario: idUsuario,
            Accion: acc,
            Usuario: user,
            Password: pass
        })
    }).then(function(response) { 
    	// Convert to JSON
    	return response.json();
    }).then(function(j) {
    	// Yay, `j` is a JavaScript object
    	console.log(j); 
    });
       
}

function base64UrlToUint8Array(base64UrlData) {
    const padding = '='.repeat((4 - base64UrlData.length % 4) % 4);
    const base64 = (base64UrlData + padding)
        .replace(/\-/g, '+')
        .replace(/_/g, '/');

    const rawData = atob(base64);
    const buffer = new Uint8Array(rawData.length);
    
    for (let i = 0; i < rawData.length; ++i) {
        buffer[i] = rawData.charCodeAt(i);
    }
    
    return buffer;
}

function updateBtn() {
	  if (Notification.permission === 'denied') {
	    pushButton.textContent = 'Push Messaging Blocked.';
	    pushButton.disabled = true;
	    updateSubscriptionOnServer(null);
	    return;
	  }

	  if (isSubscribed) {
	    pushButton.textContent = 'Desactivar Mensajeria Web Push';
	  } else {
	    pushButton.textContent = 'Activar Mensajeria Web Push';
	  }

	  pushButton.disabled = false;
	}
