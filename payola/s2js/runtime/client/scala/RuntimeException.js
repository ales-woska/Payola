s2js.runtime.client.core.get().classLoader.provide('scala.RuntimeException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Exception');
scala.RuntimeException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.Exception.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(scala.RuntimeException, scala.Exception);
scala.RuntimeException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.RuntimeException', [scala.Exception]);
