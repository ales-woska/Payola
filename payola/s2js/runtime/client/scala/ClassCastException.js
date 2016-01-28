s2js.runtime.client.core.get().classLoader.provide('scala.ClassCastException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Exception');
scala.ClassCastException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.Exception.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(scala.ClassCastException, scala.Exception);
scala.ClassCastException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.ClassCastException', [scala.Exception]);
