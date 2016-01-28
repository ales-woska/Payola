s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.shared.rpc.RpcException');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Exception');
s2js.runtime.shared.rpc.RpcException = function(message, deepStackTrace, cause) {
var self = this;
if (typeof(message) === 'undefined') { message = ''; }
if (typeof(deepStackTrace) === 'undefined') { deepStackTrace = ''; }
if (typeof(cause) === 'undefined') { cause = null; }
self.message = message;
self.deepStackTrace = deepStackTrace;
self.cause = cause;
scala.Exception.apply(self, [message, cause]);
};
s2js.runtime.client.core.get().inherit(s2js.runtime.shared.rpc.RpcException, scala.Exception);
s2js.runtime.shared.rpc.RpcException.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.shared.rpc.RpcException', [scala.Exception]);
