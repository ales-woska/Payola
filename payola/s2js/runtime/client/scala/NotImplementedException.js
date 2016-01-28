s2js.runtime.client.core.get().classLoader.provide('scala.NotImplementedException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.RuntimeException');
scala.NotImplementedException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.RuntimeException.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(scala.NotImplementedException, scala.RuntimeException);
scala.NotImplementedException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.NotImplementedException', [scala.RuntimeException]);
