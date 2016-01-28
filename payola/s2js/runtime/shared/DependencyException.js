s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.shared.DependencyException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Exception');
s2js.runtime.shared.DependencyException = function(message, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.cause = cause;
scala.Exception.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(s2js.runtime.shared.DependencyException, scala.Exception);
s2js.runtime.shared.DependencyException.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.shared.DependencyException', [scala.Exception]);
