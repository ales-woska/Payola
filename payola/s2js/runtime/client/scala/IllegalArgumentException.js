s2js.runtime.client.core.get().classLoader.provide('scala.IllegalArgumentException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.RuntimeException');
scala.IllegalArgumentException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.RuntimeException.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(scala.IllegalArgumentException, scala.RuntimeException);
scala.IllegalArgumentException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.IllegalArgumentException', [scala.RuntimeException]);
