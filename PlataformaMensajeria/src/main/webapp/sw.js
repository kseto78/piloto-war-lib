/**
 * Service worker file.
 *
 * NOTE: This file MUST be located in the root.
 */
'use strict';

console.log('Started', self);

self.addEventListener('install', function (event) {
    self.skipWaiting();
    console.log('Installed', event);
});

self.addEventListener('activate', function (event) {
    console.log('Activated', event);
});

self.addEventListener('notificationclick', function (event) {  
	  // Do something with the event  
	  event.notification.close();  
	});

	self.addEventListener('notificationclose', function (event) {  
	  // Do something with the event  
	});

self.addEventListener('push', function (event) {
    console.log('Push message', event);

    console.log('[Service Worker] Push Received.');
    var data = event.data.text();
	var title;
	var message;
	var json;
    
	if (isJson(data)) {
		json = JSON.parse(data);
		title = json.title;
        	message = json.message;

	}else{
		title = "Hello";
        	message = "Mensaje por defecto";
	}
  
  const options = {
    body: message,
    icon: 'images/icon.png',
    badge: 'images/badge.png'
  };

  event.waitUntil(self.registration.showNotification(title, options));});

var isJson = function (str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}