s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.ReferenceToResolve');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.shared.rpc.RpcException');
s2js.runtime.client.rpc.ReferenceToResolve = function(reference, sourceObject, propertyName) {
var self = this;
self.reference = reference;
self.sourceObject = sourceObject;
self.propertyName = propertyName;
};
s2js.runtime.client.rpc.ReferenceToResolve.prototype.resolve = function(context) {
var self = this;
var targetObjectID = self.reference.targetObjectID;
var targetObject = context.objectRegistry.get(targetObjectID);
if (targetObject.isEmpty()) {
(function() {
throw new s2js.runtime.shared.rpc.RpcException((('The deserialized object graph contains an invalid reference \'' + targetObjectID) + '\'.'), undefined, undefined);
})();
}

eval((('self.sourceObject.' + self.propertyName) + ' = targetObject.get()'));



};
s2js.runtime.client.rpc.ReferenceToResolve.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.ReferenceToResolve', []);
