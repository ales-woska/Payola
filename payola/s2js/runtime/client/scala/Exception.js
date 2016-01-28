s2js.runtime.client.core.get().classLoader.provide('scala.Exception');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Throwable');
scala.Exception = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.Throwable.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Exception, scala.Throwable);
scala.Exception.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Exception', [scala.Throwable]);
