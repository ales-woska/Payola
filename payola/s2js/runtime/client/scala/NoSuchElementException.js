s2js.runtime.client.core.get().classLoader.provide('scala.NoSuchElementException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.RuntimeException');
scala.NoSuchElementException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.RuntimeException.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(scala.NoSuchElementException, scala.RuntimeException);
scala.NoSuchElementException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.NoSuchElementException', [scala.RuntimeException]);
